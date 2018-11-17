package com.oops.wallsandwarriors.screens.challenges;

import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.model.HighTowerData;
import com.oops.wallsandwarriors.model.KnightData;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.List;

import javafx.event.EventHandler;

public class CampaignChallengesScreen extends BaseChallengesScreen {

    public List<ChallengeData> campaignChallenges;

    CampaignChallengesData campaignChallengesData;

    GridPane grid = super.getGrid();

    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);

        campaignChallengesData = new CampaignChallengesData();
        campaignChallenges = campaignChallengesData.getCampaignChallenges();

        DebugUtils.initClickDebugger(scene);
        addBackgroundCanvas(root, "/com/oops/wallsandwarriors/resources/images/background2.png", "Campaign Challenges");
        super.renderButtons(root);

        Text title = new Text(30, 100, "Choose a challenge.");
        Font theFont = Font.font("Arial", FontWeight.BOLD, 20);
        title.setFont(theFont);
        root.getChildren().add(title);

        showChallenges(root);

        return scene;
    }



    private void showChallenges(Group root)
    {
        ObservableList<HBox> hBoxes = FXCollections.observableArrayList ();;

        HBox hBox = new HBox();
        int hBoxCount = 0;

        for(int i = 0; i < campaignChallenges.size(); i++)
        {
            final int index = i;

            ChallengeData challengeData = campaignChallenges.get(i);

            BorderPane border = new BorderPane();
            Canvas canvas = drawAndGetCanvas(challengeData);
            border.setCenter(canvas);
            border.setBottom(new Text("     " + challengeData.getName()));

            border.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CampaignChallengesScreen.super.startChallenge(challengeData.createCopy());
                }
            });

            if(index / 6  <= hBoxCount)
            {
                hBox.getChildren().add(border);
            }
            else
            {
                hBoxes.add(hBox);
                hBox = new HBox();
                hBox.getChildren().add(border);
                hBoxCount++;
            }

            if(index / 6 == hBoxCount )
            {
                if (index == campaignChallenges.size() - 1)
                {
                    hBoxes.add(hBox);
                }
            }

        }
        ListView<HBox> list = new ListView<>();
        list.setLayoutX(30);
        list.setLayoutY(130);
        list.setOrientation(Orientation.VERTICAL);
        list.setPrefHeight(440);
        list.setPrefWidth(800 - 60);
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


    private Canvas drawAndGetCanvas(ChallengeData challenge)
    {
        Game.getInstance().challengeManager.setChallengeData(challenge);

        Canvas previewCanvas = new Canvas();
        previewCanvas.setHeight(90);
        previewCanvas.setWidth(120);
        GraphicsContext graphics = previewCanvas.getGraphicsContext2D();

        GridView gridView = new GridView(3,3, 3, 18);
        gridView.draw(graphics, 1);
        for (KnightData knight : challenge.knights) {
            new KnightView(knight, 3, 3, 18).draw(graphics, 1);
        }
        for (HighTowerData highTower : challenge.highTowers) {
            new HighTowerView(highTower, 3, 3, 18).draw(graphics, 1);
        }

        return previewCanvas;
    }
}
