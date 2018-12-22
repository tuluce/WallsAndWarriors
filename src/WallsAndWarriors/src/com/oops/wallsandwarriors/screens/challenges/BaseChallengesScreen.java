/**
 * An abstract class to define a general screen structure for Challenges Screens. This
 * screen will provide a generic definition of standard methods for viewing the
 * challenges, but the distinguishable methods will be implemented separately in the
 * sub-classes.
 * Extends GeneralScreen
 * @author OOPs
 * @version 21.12.19
 */
package com.oops.wallsandwarriors.screens.challenges;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.model.HighTowerData;
import com.oops.wallsandwarriors.model.KnightData;
import com.oops.wallsandwarriors.screens.GeneralScreen;
import com.oops.wallsandwarriors.screens.Screen;
import com.oops.wallsandwarriors.screens.game.GameScreen;
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

    private static final int HEIGHT = 150;
    private static final int WIDTH = 200;
    private static final int GRID_X_VAL = 5;
    private static final int GRID_Y_VAL = 5;
    private static final int MARGIN = 5;
    private static final int BLOCK_LENGTH = 30;
    private static final int DELTA_T = 1;
    private static final int COL_IN = 0;
    private static final int ROW_IN = 0;
    private static final int GRID_B = 30;
    private static final int BUTTON_X = 700;
    private static final int BUTTON_Y = 50;

    List<ChallengeData> challenges;
    GridPane grid = new GridPane();

    /**
     * A default constructor that initializes a BaseChallengesScreen with no given parameters
     * with an empty ArrayList to hold ChallengeData .
     */
    public BaseChallengesScreen() {
        challenges = new ArrayList<>();
    }

    /**
     * A get method to return the grid of the BaseChallengesScreen.
     * @return grid of the BaseChallengesScreen.
     */
    public GridPane getGrid()
    {
        return grid;
    }

    /**
     * A get method to return the challenges of the BaseChallengesScreen.
     * @return challenges of the BaseChallengesScreen as a List of ChallengeData objects.
     */
    public List<ChallengeData> getChallenges()
    {
        return challenges;
    }

    /**
     * A get method to display the challenge preview of a given Challenge
     * in the BaseChallengesScreen.
     * @param challenge a challenge to display the preview of in the BaseChallengesScreen.
     */
    public void displayChallengePreview(ChallengeData challenge) {
        Canvas previewCanvas = new Canvas();
        previewCanvas.setHeight(HEIGHT);
        previewCanvas.setWidth(WIDTH);
        GraphicsContext graphics = previewCanvas.getGraphicsContext2D();

        GridView gridView = new GridView(GRID_X_VAL, GRID_Y_VAL, MARGIN, BLOCK_LENGTH);
        gridView.draw(graphics, DELTA_T);
        grid.add(previewCanvas, COL_IN, ROW_IN);

        for (KnightData knight : challenge.knights) {
            new KnightView(knight, GRID_X_VAL, GRID_Y_VAL, GRID_B).draw(graphics, DELTA_T);
        }
        for (HighTowerData highTower : challenge.highTowers) {
            new HighTowerView(highTower, GRID_X_VAL, GRID_Y_VAL, GRID_B).draw(graphics, DELTA_T);
        }
    }

    /**
     * A method to render Back Button to provide screen transition to
     * MainMenuScreen on the BaseChallengesScreen.
     * @param root a Group object to indicate the root of the screen.
     */
    public void renderButtons(Group root) {
        addTransitionScreen(root, "Back", BUTTON_X, BUTTON_Y, Game.getInstance().screenManager.mainMenu);
    }

    /**
     * A method to start a clicked challenge and to provide screen transition to
     * GameScreen on the BaseChallengesScreen.
     * @param challengeData a ChallengeData object of the challenge to start.
     * @param solutionData a ChallengeData object to store the solution of the challenge to start.
     */
    public void startChallenge(ChallengeData challengeData, ChallengeData solutionData)
    {
        Screen gameScreen = Game.getInstance().screenManager.gameScreen;
        ((GameScreen) gameScreen).setPreviousScreen(this);
        Game.getInstance().challengeManager.setChallengeData(challengeData);
        Game.getInstance().hintManager.setChallengeData(solutionData);
        Game.getInstance().setScreen(gameScreen);
    }

}
