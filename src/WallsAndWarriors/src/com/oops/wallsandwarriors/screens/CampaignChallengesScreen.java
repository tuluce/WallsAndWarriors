package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.game.model.ChallengeData;
import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.util.TestUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CampaignChallengesScreen extends GeneralScreen {

    public ArrayList<ChallengeData> challenges = new ArrayList<>();

    ObservableList<Button> buttons;


    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);

        DebugUtils.initClickDebugger(scene);
        addBackgroundCanvas(root, "resources/images/background2.png", "Campaign Challenges");
        renderButtons(root);

        buttons = FXCollections.observableArrayList ();
        Text title = new Text(200, 150, "Campaign Mode - Choose a Challenge");
        Font theFont = Font.font("Arial", FontWeight.BOLD, 20);
        title.setFont(theFont);
        root.getChildren().add(title);


        showChallenges(root);

        return scene;
    }

    private void renderButtons(Group root) {
        addTransactionButton(root, "Back", 700, 500, Game.getInstance().screenManager.mainMenu);
    }

    private void showChallenges(Group root)
    {
        challenges.clear();
        buttons.clear();

        challenges.add(TestUtils.CHALLENGE_45);
        challenges.add(TestUtils.CHALLENGE_51);
        challenges.add(TestUtils.CHALLENGE_51);
        challenges.add(TestUtils.CHALLENGE_51);
        challenges.add(TestUtils.CHALLENGE_51);
        challenges.add(TestUtils.CHALLENGE_51);



        for(int i = 0; i < challenges.size(); i++)
        {
//            ChallengeData challengeData = challenges.get(i);
//            Image image = challengeData.image;
//
//            ImageView imageview=new ImageView(image);
//            imageview.setFitHeight(120);
//            imageview.setFitWidth(120);
//
//            Button btn = new Button("",imageview);
//
//            if(!challengeData.getSolved())
//            {
//                btn.setDisable(true);
//            }
            final int index = i;
            Button btn = new Button(challenges.get(i).getName());
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        Screen gameScreen = Game.getInstance().screenManager.gameScreen;
                        ChallengeData challenge = 
                                (index == 45) ? TestUtils.CHALLENGE_45.createCopy() 
                                              : TestUtils.CHALLENGE_51.createCopy() ;
                        Game.getInstance().challengeManager.setChallengeData(challenge);
                        Game.getInstance().setScreen(gameScreen);
                    }
                }
            );
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
