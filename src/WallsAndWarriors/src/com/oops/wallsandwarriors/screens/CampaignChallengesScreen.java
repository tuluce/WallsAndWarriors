package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.data.CampaignChallengesData;
import com.oops.wallsandwarriors.game.model.ChallengeData;
import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.util.EncodeUtils;
import com.oops.wallsandwarriors.util.TestUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CampaignChallengesScreen extends BaseChallengesScreen {

    public List<ChallengeData> challenges = super.getChallenges();

    ObservableList<Button> buttons;
    String code = "";


    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);

        DebugUtils.initClickDebugger(scene);
        addBackgroundCanvas(root, "resources/images/background2.png", "Campaign Challenges");
        super.renderButtons(root);

        buttons = FXCollections.observableArrayList ();
        Text title = new Text(200, 150, "Campaign Mode - Choose a Challenge");
        Font theFont = Font.font("Arial", FontWeight.BOLD, 20);
        title.setFont(theFont);
        root.getChildren().add(title);


        showChallenges(root);

        return scene;
    }



    private void showChallenges(Group root)
    {
        buttons.clear();

        challenges = CampaignChallengesData.originalChallenges;

        List<File> files = CampaignChallengesData.files;

        for (int i = 0; i < GameConstants.CAMPAIGN_CHALLENGES_COUNT; i++)
        {
            File challengeFile = files.get(i);

            try {
                code = new String(Files.readAllBytes(Paths.get(challengeFile.getAbsolutePath())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        for(int i = 0; i < challenges.size(); i++)
        {
            Button btn = new Button(challenges.get(i).getName());
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        ChallengeData challenge = EncodeUtils.decode(code).createCopy();
                        CampaignChallengesScreen.super.startChallenge(challenge);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            });
            buttons.add(btn);
        }


        ListView<Button> list = new ListView<>();
        list.setLayoutX(100);
        list.setLayoutY(200);
        list.setOrientation(Orientation.HORIZONTAL);
        list.setMaxHeight(150);
        list.setPrefWidth(600);
        list.setItems(buttons);
        root.getChildren().add(list);

    }

}
