/**
 * A class to implement the distinguishable features for Campaign Challenges Screen.
 * Extending the BaseChallengesScreen , it implements an additional method for
 * marking locked challenges.
 * Extends BaseChallengesScreen
 * @author OOPs
 * @version 21.12.19
 */
package com.oops.wallsandwarriors.screens.challenges;

import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.model.HighTowerData;
import com.oops.wallsandwarriors.model.KnightData;
import com.oops.wallsandwarriors.util.FileUtils;
import com.oops.wallsandwarriors.view.GridView;
import com.oops.wallsandwarriors.view.HighTowerView;
import com.oops.wallsandwarriors.view.KnightView;
import com.oops.wallsandwarriors.util.DebugUtils;
import com.oops.wallsandwarriors.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;

public class CampaignChallengesScreen extends BaseChallengesScreen {

    public List<ChallengeData> campaignChallenges;

    CampaignChallengesData campaignChallengesData;

    GridPane grid = super.getGrid();

    /**
     * An overriden getScene method to return the current Screen.
     * @return the current screen as a Screen object.
     */
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);

        campaignChallengesData = new CampaignChallengesData();
        campaignChallenges = campaignChallengesData.getCampaignChallenges();

        DebugUtils.initClickDebugger(scene);
        addBackgroundCanvas(root, "/com/oops/wallsandwarriors/resources/images/background2.png",
                "Campaign Challenges");
        super.renderButtons(root);

        Text title = new Text(GameConstants.CAMP_SCR_TEXT_X,
                GameConstants.CAMP_SCR_TEXT_Y, "Choose a challenge.");
        Font theFont = Font.font("Arial", FontWeight.BOLD, GameConstants.CAMP_SCR_TEXT_SIZE);
        title.setFont(theFont);
        root.getChildren().add(title);

        showChallenges(root);

        return scene;
    }

    /**
     * A method to display CampaignChallenges on the Screen..
     * @param root root as a Group object.
     */
    private void showChallenges(Group root)
    {
        ObservableList<HBox> hBoxes = FXCollections.observableArrayList ();

        HBox hBox = new HBox();
        int hBoxCount = 0;
        
        javafx.scene.image.Image lock = new javafx.scene.image.Image(FileUtils.getInputStream(
                    "/com/oops/wallsandwarriors/resources/images/lock.png"));

        for (int i = 0; i < campaignChallenges.size(); i++) {
            final int index = i;

            ImageView imageView = new ImageView();
            imageView.setFitHeight(GameConstants.CAMP_SCR_CH_HEIGHT);
            imageView.setFitWidth(GameConstants.CAMP_SCR_CH_WIDTH);
            imageView.setImage(lock);

            ChallengeData challengeData = campaignChallenges.get(i);

            Canvas canvas = drawAndGetCanvas(challengeData);
            Text challengeNameText = new Text(challengeData.getName());
            
            BorderPane border = new BorderPane();
            
            BorderPane.setAlignment(challengeNameText, Pos.CENTER);
            BorderPane.setMargin(challengeNameText, new Insets(GameConstants.CAMP_SCR_TEXT_MARG_T,
                    GameConstants.CAMP_SCR_TEXT_MARG_R, GameConstants.CAMP_SCR_TEXT_MARG_B,
                    GameConstants.CAMP_SCR_TEXT_MARG_L));
            border.setBottom(challengeNameText);

            if (CampaignChallengesData.campaignChallengesProgress.get(i).equals("0") && i != 0) {
                BorderPane.setMargin(imageView, new Insets(GameConstants.CAMP_SCR_BORDER_MARG_T,
                        GameConstants.CAMP_SCR_BORDER_MARG_R,
                        GameConstants.CAMP_SCR_BORDER_MARG_B, GameConstants.CAMP_SCR_BORDER_MARG_L)
                );
                border.setCenter(imageView);
            }
            else {
                BorderPane.setMargin(canvas, new Insets(GameConstants.CAMP_SCR_BORDER_MARG_T,
                        GameConstants.CAMP_SCR_BORDER_MARG_R, GameConstants.CAMP_SCR_BORDER_MARG_B,
                        GameConstants.CAMP_SCR_BORDER_MARG_L));
                border.setCenter(canvas);
                border.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Game.getInstance().soundManager.playClick();
                        CampaignChallengesScreen.super.startChallenge(
                                challengeData.createCopy(true), challengeData.createCopy(false));
                    }
                });
            }

            if (index / GameConstants.CAMP_SCR_NO_OF_CHALS  <= hBoxCount) {
                hBox.getChildren().add(border);
            }
            else {
                hBoxes.add(hBox);
                hBox = new HBox();
                hBox.getChildren().add(border);
                hBoxCount++;
            }

            if (index / GameConstants.CAMP_SCR_NO_OF_CHALS == hBoxCount ) {
                if (index == campaignChallenges.size() - 1) {
                    hBoxes.add(hBox);
                }
            }
        }

        ListView<HBox> list = new ListView<>();
        list.setLayoutX(GameConstants.CAMP_SCR_LAY_X);
        list.setLayoutY(GameConstants.CAMP_SCR_LAY_Y);
        list.setOrientation(Orientation.VERTICAL);
        list.setPrefHeight(GameConstants.CAMP_SCR_PREF_HEIGHT);
        list.setPrefWidth(GameConstants.CAMP_SCR_PREF_WIDTH);
        list.setItems(hBoxes);
        
        list.setStyle("-fx-control-inner-background: beige;");
        list.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });
        root.getChildren().add(list);
    }

    /**
     * A method to draw the given ChallengeData on  the Canvas and get it.
     * @param challenge ChallengeData to draw on the canvas.
     * @return drawn Canvas.
     */
    private Canvas drawAndGetCanvas(ChallengeData challenge)
    {
        Game.getInstance().challengeManager.setChallengeData(challenge);

        Canvas previewCanvas = new Canvas();
        previewCanvas.setHeight(GameConstants.CAMP_SCR_HEIGHT);
        previewCanvas.setWidth(GameConstants.CAMP_SCR_WIDTH);
        GraphicsContext graphics = previewCanvas.getGraphicsContext2D();

        GridView gridView = new GridView(GameConstants.CAMP_SCR_GRID_X_VAL,
                GameConstants.CAMP_SCR_GRID_Y_VAL, GameConstants.CAMP_SCR_MARGIN,
                GameConstants.CAMP_SCR_BLOCK_LENGTH);
        gridView.draw(graphics, 1);
        for (KnightData knight : challenge.knights) {
            new KnightView(knight, GameConstants.CAMP_SCR_GRID_X_VAL,
                    GameConstants.CAMP_SCR_GRID_Y_VAL, GameConstants.CAMP_SCR_GRID_B).
                    draw(graphics, 1);
        }
        for (HighTowerData highTower : challenge.highTowers) {
            new HighTowerView(highTower, GameConstants.CAMP_SCR_GRID_X_VAL,
                    GameConstants.CAMP_SCR_GRID_Y_VAL, GameConstants.CAMP_SCR_GRID_B).
                    draw(graphics, 1);
        }
        return previewCanvas;
    }
}
