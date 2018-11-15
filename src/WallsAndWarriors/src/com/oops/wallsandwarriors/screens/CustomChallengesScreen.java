package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.data.CustomChallengesData;
import com.oops.wallsandwarriors.util.CopyUtils;
import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.game.model.ChallengeData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.oops.wallsandwarriors.util.EncodeUtils;

public class CustomChallengesScreen extends BaseChallengesScreen {

    CustomChallengesData customChallengesData;

    ObservableList<String> challengeNames;
    List<ChallengeData> customChallenges;

    GridPane grid = super.getGrid();

    @Override
    public Scene getScene(){
        Group root = new Group();
        Scene scene = new Scene(root);

        challengeNames = FXCollections.observableArrayList ();
        customChallengesData = new CustomChallengesData();
        customChallenges = customChallengesData.getCustomChallenges();

        DebugUtils.initClickDebugger(scene);
        addBackgroundCanvas(root, "resources/images/background2.png", "Custom Challenges");
        super.renderButtons(root);

        Text title = new Text(50, 100, "Custom Mode - Choose a Challenge");
        Font theFont = Font.font("Arial", FontWeight.BOLD, 20);
        title.setFont(theFont);
        root.getChildren().add(title);

        showChallenges(root);


        Button importButton = new Button("Import");
        importButton.setLayoutX(50);
        importButton.setLayoutY(500);
        importButton.setPrefSize(100,50);

        importButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                importChallenge();
            }
        });
        root.getChildren().add(importButton);


        constructGrid(root,grid);

        return scene;
    }



    private void showChallenges(Group root)
    {
        for (int i = 0; i < customChallenges.size(); i++)
        {
            challengeNames.add(customChallenges.get(i).getName());
        }

        ListView<String> list = new ListView<>();
        list.setLayoutX(50);
        list.setLayoutY(150);
        list.setOrientation(Orientation.VERTICAL);
        list.setPrefWidth(350);
        list.setPrefHeight(300);
        list.setItems(challengeNames);
        root.getChildren().add(list);

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                grid.getChildren().clear();
                int challengeIndex = list.getSelectionModel().getSelectedIndex();
                try {
                    if (challengeIndex >= 0) {
                        showChallengeInfo(customChallenges.get(challengeIndex), root);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void showChallengeInfo(ChallengeData challenge, Group root) throws FileNotFoundException
    {
        Game.getInstance().challengeManager.setChallengeData(challenge);
        
        super.displayChallengePreview(challenge);
        Label creatorLabel = new Label("Creator:  " + challenge.getCreator());
        Label warriorLabel = new Label("Info:  " + challenge.knights.size() + " Knights.");

        Button playButton = new Button("Play");
        playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Screen gameScreen = Game.getInstance().screenManager.gameScreen;
                Game.getInstance().challengeManager.setChallengeData(challenge.createCopy());
                Game.getInstance().setScreen(gameScreen);
            }
        });


        Button shareButton = new Button("Share");
        shareButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){

                try {
                    shareChallenge(challenge);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        grid.add(creatorLabel,0,1);
        grid.add(warriorLabel,0,2);
        grid.add(shareButton, 0, 4);
        grid.add(playButton, 1,4);

    }

    private void shareChallenge(ChallengeData challenge ) throws FileNotFoundException,IOException{

        TextArea textArea = new TextArea(EncodeUtils.encode(challenge));
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.add(textArea, 0, 0);
        ButtonType clipboard = new ButtonType("Copy To Clipboard!");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Copy the exported challenge code.");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(gridPane);
        alert.getButtonTypes().add(clipboard);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == clipboard )
        {
            CopyUtils.copyToClipboard(textArea.getText());
        }
        
    }


    public void importChallenge()
    {
        TextInputDialog textInputDialog = new TextInputDialog(null);
        textInputDialog.setTitle("Add Challenge");
        textInputDialog.setHeaderText("Enter the code of the challenge");
        textInputDialog.setContentText("Code: ");
        textInputDialog.showAndWait();


        String code = textInputDialog.getEditor().getText();
        try {
            if(code != null)
            {
                ChallengeData toImp = EncodeUtils.decode(code);
                customChallenges.add(toImp);
                customChallengesData.update(toImp);
            }

        }catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
        }

        Screen refresh = Game.getInstance().screenManager.customChallenges;
        Game.getInstance().setScreen(refresh);
    }


    public void constructGrid(Group root, GridPane grid)
    {
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setLayoutX(450);
        grid.setLayoutY(150);
        root.getChildren().add(grid);
    }

}
