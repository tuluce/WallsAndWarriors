package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.definitions.GridDefinitions;
import com.oops.wallsandwarriors.definitions.WallDefinitions;
import com.oops.wallsandwarriors.game.data.ChallengeData;
import com.oops.wallsandwarriors.game.data.Coordinate;
import com.oops.wallsandwarriors.game.data.HighTowerData;
import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.util.FileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomChallengesScreen extends ParentScreen {

    ObservableList<String> challengeNames = FXCollections.observableArrayList ();
    List<ChallengeData> challenges = new ArrayList<>();

    public static final ChallengeData CHALLENGE_45;
    public static final ChallengeData CHALLENGE_51;

    GridPane grid = new GridPane();

    static {

        List<Coordinate> castleKnights45 = new ArrayList<Coordinate>();
        castleKnights45.add(new Coordinate(1, 0));
        castleKnights45.add(new Coordinate(3, 1));
        castleKnights45.add(new Coordinate(0, 2));
        List<HighTowerData> highTowers45 = new ArrayList<HighTowerData>();
        highTowers45.add(new HighTowerData(new Coordinate(1, 2), new Coordinate(2, 2)));
        List<Coordinate> enemyKnights45 = new ArrayList<Coordinate>();
        enemyKnights45.add(new Coordinate(2, 0));
        enemyKnights45.add(new Coordinate(0, 1));
        enemyKnights45.add(new Coordinate(3, 2));

        Image image45 = new Image(FileUtils.getInputStream("resources/images/ch45.jpg"));
        CHALLENGE_45 = new ChallengeData(
                "Challenge 45", "OOPs",true, image45,
                GridDefinitions.SMALL,
                castleKnights45, highTowers45, enemyKnights45,
                WallDefinitions.STANDARD);

        List<Coordinate> castleKnights51 = new ArrayList<Coordinate>();
        castleKnights51.add(new Coordinate(1, 3));
        castleKnights51.add(new Coordinate(3, 3));
        List<HighTowerData> highTowers51 = new ArrayList<HighTowerData>();
        highTowers51.add(new HighTowerData(new Coordinate(1, 1), new Coordinate(2, 1)));
        List<Coordinate> enemyKnights51 = new ArrayList<Coordinate>();
        enemyKnights51.add(new Coordinate(1, 2));
        Image image51 = new Image(FileUtils.getInputStream("resources/images/ch51.jpg"));
        CHALLENGE_51 = new ChallengeData(
                "Challenge 51", "OOPs",false, image51,
                GridDefinitions.SMALL,
                castleKnights51, highTowers51, enemyKnights51,
                WallDefinitions.STANDARD);

    }


    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);

        DebugUtils.initClickDebugger(scene);
        renderBackgroundCanvas(root, "resources/images/background2.png", "Custom Challenges");
        renderButtons(root);

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

                TextInputDialog textInputDialog = new TextInputDialog(null);
                textInputDialog.setTitle("Add Challenge");
                textInputDialog.setHeaderText("Enter the code of the challenge");
                textInputDialog.setContentText("Code: ");
                textInputDialog.showAndWait();


                String code = textInputDialog.getEditor().getText();
                System.out.println(code);
            }
        });

        root.getChildren().add(importButton);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setLayoutX(450);
        grid.setLayoutY(150);
        root.getChildren().add(grid);

        return scene;
    }


    private void renderButtons(Group root)
    {
        addTransactionButton(root, "Back", 700, 500, Game.MAIN_MENU);
    }

    private void showChallenges(Group root)
    {
        challengeNames.clear();

        challengeNames.add(CHALLENGE_45.name);
        challengeNames.add(CHALLENGE_51.name);
        challengeNames.add(CHALLENGE_51.name);
        challengeNames.add(CHALLENGE_51.name);
        challengeNames.add(CHALLENGE_51.name);
        challengeNames.add(CHALLENGE_51.name);
        challengeNames.add(CHALLENGE_51.name);
        challengeNames.add(CHALLENGE_51.name);
        challengeNames.add(CHALLENGE_51.name);
        challengeNames.add(CHALLENGE_51.name);
        challengeNames.add(CHALLENGE_51.name);



        challenges.clear();

        challenges.add(CHALLENGE_45);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);


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
                showChallengeInfo(challenges.get(challengeIndex), root);
            }
        });
    }


    public void showChallengeInfo(ChallengeData challenge, Group root)
    {
        //Image of the challenge
        ImageView imageview=new ImageView(challenge.image);
        imageview.setFitHeight(150);
        imageview.setFitWidth(200);

        Button btn = new Button("",imageview);
        grid.add(btn,0,0);


        //created by info
        Label creatorLabel = new Label("Creator:  " + challenge.creator);

        //warriors info
        Label warriorLabel = new Label("Info:  " + challenge.enemyKnights.size() + " Red Knights, " + challenge.castleKnights.size() + " Blue Knights.");

        //type info
        String solved;
        if(challenge.getSolved())
        {
            solved = " Yes";
        }
        else
        {
            solved = " No";
        }

        Label isSolved = new Label("Solved: " + solved);

        Button playButton = new Button("Play");
        playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });


        Button shareButton = new Button("Share");
        shareButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Share Challenge");
                alert.setHeaderText(null);
                alert.setContentText("Code for sharing this challenge: " + "\n");

                alert.showAndWait();
            }
        });


        grid.add(creatorLabel,0,1);
        grid.add(warriorLabel,0,2);
        grid.add(isSolved,0,3);
        grid.add(shareButton, 0, 4);
        grid.add(playButton, 1,4);

    }


}
