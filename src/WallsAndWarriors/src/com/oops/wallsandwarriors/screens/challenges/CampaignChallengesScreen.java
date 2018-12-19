package com.oops.wallsandwarriors.screens.challenges;

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
import javafx.scene.text.TextAlignment;

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
        
        javafx.scene.image.Image lock = new javafx.scene.image.Image(FileUtils.getInputStream(
                    "/com/oops/wallsandwarriors/resources/images/lock.png"));

        for(int i = 0; i < campaignChallenges.size(); i++)
        {
            final int index = i;

            ImageView imageView = new ImageView();
            imageView.setFitHeight(80);
            imageView.setFitWidth(95);
            imageView.setImage(lock);

            ChallengeData challengeData = campaignChallenges.get(i);


            
            Canvas canvas = drawAndGetCanvas(challengeData);
            Text challengeNameText = new Text(challengeData.getName());
            
            BorderPane border = new BorderPane();
            
            BorderPane.setAlignment(challengeNameText, Pos.CENTER);
            BorderPane.setMargin(challengeNameText, new Insets(0, 0, 20, 0));
            border.setBottom(challengeNameText);

            
            if(CampaignChallengesData.campaignChallengesProgress.get(i).equals("0") && i != 0)
            {
                BorderPane.setMargin(imageView, new Insets(5, 10, 5, 10));
                border.setCenter(imageView);
            }
            else
            {
                BorderPane.setMargin(canvas, new Insets(5, 10, 5, 10));
                border.setCenter(canvas);
                border.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        CampaignChallengesScreen.super.startChallenge(challengeData.createCopy(true));
                    }
                });
            }

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
        list.setLayoutX(20);
        list.setLayoutY(130);
        list.setOrientation(Orientation.VERTICAL);
        list.setPrefHeight(460);
        list.setPrefWidth(800 - 40);
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
        previewCanvas.setHeight(80);
        previewCanvas.setWidth(95);
        GraphicsContext graphics = previewCanvas.getGraphicsContext2D();

        GridView gridView = new GridView(3, 3, 3, 18);
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
