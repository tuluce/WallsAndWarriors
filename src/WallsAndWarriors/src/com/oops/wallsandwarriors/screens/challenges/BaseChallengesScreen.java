package com.oops.wallsandwarriors.screens.challenges;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.model.HighTowerData;
import com.oops.wallsandwarriors.model.KnightData;
import com.oops.wallsandwarriors.screens.GeneralScreen;
import com.oops.wallsandwarriors.screens.Screen;
import com.oops.wallsandwarriors.view.GridView;
import com.oops.wallsandwarriors.view.HighTowerView;
import com.oops.wallsandwarriors.view.KnightView;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseChallengesScreen extends GeneralScreen {

    List<ChallengeData> challenges;
    GridPane grid = new GridPane();

    public BaseChallengesScreen() {
        challenges = new ArrayList<>();
    }

    public GridPane getGrid()
    {
        return grid;
    }

    public List<ChallengeData> getChallenges()
    {
        return challenges;
    }


    public void displayChallengePreview(ChallengeData challenge) {
        Canvas previewCanvas = new Canvas();
        previewCanvas.setHeight(150);
        previewCanvas.setWidth(200);
        GraphicsContext graphics = previewCanvas.getGraphicsContext2D();

        GridView gridView = new GridView(5, 5, 5, 30);
        gridView.draw(graphics, 1);
        grid.add(previewCanvas, 0, 0);

        for (KnightData knight : challenge.knights) {
            new KnightView(knight, 5, 5, 30).draw(graphics, 1);
        }
        for (HighTowerData highTower : challenge.highTowers) {
            new HighTowerView(highTower, 5, 5, 30).draw(graphics, 1);
        }
    }


    public void renderButtons(Group root) {
        addTransactionButton(root, "Back", 700, 50, Game.getInstance().screenManager.mainMenu);
    }

    public void startChallenge(ChallengeData challengeData)
    {
        Screen gameScreen = Game.getInstance().screenManager.gameScreen;
        Game.getInstance().challengeManager.setChallengeData(challengeData);
        Game.getInstance().setScreen(gameScreen);
    }

}
