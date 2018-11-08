package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.definitions.GridDefinitions;
import com.oops.wallsandwarriors.definitions.WallDefinitions;
import com.oops.wallsandwarriors.game.model.ChallengeData;
import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.game.model.HighTowerData;
import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.util.FileUtils;
import com.oops.wallsandwarriors.util.TestUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CampaignChallengesScreen extends ParentScreen {

    public ArrayList<ChallengeData> challenges = new ArrayList<>();
    public static final ChallengeData CHALLENGE_45;
    public static final ChallengeData CHALLENGE_51;


    ObservableList<Button> buttons = FXCollections.observableArrayList ();


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
                "Challenge 45", "OOPs",
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
                "Challenge 51", "OOPs",
                GridDefinitions.SMALL,
                castleKnights51, highTowers51, enemyKnights51,
                WallDefinitions.STANDARD);

    }

    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);

        DebugUtils.initClickDebugger(scene);
        addBackgroundCanvas(root, "resources/images/background2.png", "Campaign Challenges");
        renderButtons(root);

        Text title = new Text(200, 150, "Campaign Mode - Choose a Challenge");
        Font theFont = Font.font("Arial", FontWeight.BOLD, 20);
        title.setFont(theFont);
        root.getChildren().add(title);


        showChallenges(root);

        return scene;
    }

    private void renderButtons(Group root) {
        addTransactionButton(root, "Back", 700, 500, Game.MAIN_MENU);
    }

    private void showChallenges(Group root)
    {
        challenges.clear();
        buttons.clear();

        challenges.add(CHALLENGE_45);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);
        challenges.add(CHALLENGE_51);



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
            Button btn = new Button(challenges.get(i).name);
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        GameScreen gameScreen = Game.GAME_SCREEN;
                        ChallengeData challenge = 
                                (index == 45) ? TestUtils.CHALLENGE_45.createCopy() 
                                              : TestUtils.CHALLENGE_51.createCopy() ;
                        Game.getInstance().getChallengeManager().setChallengeData(challenge);
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
