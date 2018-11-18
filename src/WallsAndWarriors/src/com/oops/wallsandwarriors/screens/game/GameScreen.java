package com.oops.wallsandwarriors.screens.game;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.SolutionManager;
import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.model.HighTowerData;
import com.oops.wallsandwarriors.model.KnightData;
import com.oops.wallsandwarriors.model.WallData;
import com.oops.wallsandwarriors.view.BackgroundView;
import com.oops.wallsandwarriors.view.BoundedViewObject;
import com.oops.wallsandwarriors.view.GridView;
import com.oops.wallsandwarriors.view.HighTowerView;
import com.oops.wallsandwarriors.view.KnightView;
import com.oops.wallsandwarriors.view.GamePaletteView;
import com.oops.wallsandwarriors.view.WallView;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;


public class GameScreen extends BaseGameScreen {

    private GamePaletteView wallPaletteView;

    @Override
    protected void initViewObjects() {
        super.initViewObjects();
        gridView = new GridView(GameConstants.GRID_X, GameConstants.GRID_Y,
                GameConstants.GRID_MARGIN, GameConstants.GRID_B);
        ChallengeData challenge = Game.getInstance().challengeManager.getChallengeData();
        for (WallData wall : challenge.walls) {
            wallViews.add(new WallView(wall));
        }
        for (KnightData knight : challenge.knights) {
            knightViews.add(new KnightView(knight));
        }
        for (HighTowerData highTower : challenge.highTowers) {
            highTowerViews.add(new HighTowerView(highTower));
        }
        backgroundView = new BackgroundView(false);
        wallPaletteView = new GamePaletteView();
        clickables.addAll(wallViews);
    }

    @Override
    protected void addComponents(Group root) {
        addTransactionButton(root, "Back", 700, 50, Game.getInstance().screenManager.mainMenu);
        addButton(root, "Check", 700, 550, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                checkSolution(true);
            }
        });
        addButton(root, "Reset", 650, 550, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    resetState();
            }
        });
    }

    @Override
    protected boolean attemptPlacement() {
        if (hoveredBlock != null && selectedPiece != null &&
            Game.getInstance().gridManager.attemptPlacement(hoveredBlock, selectedPiece)) {
            checkSolution(false);
            return true;
        }
        return false;
    }

    @Override
    protected boolean handleViewClick(BoundedViewObject clickedView, MouseButton button) {
        if (clickedView instanceof WallView) {
            WallView wallView = (WallView) clickedView;
            WallData clickedWall = wallView.getModel();
            if (button == MouseButton.PRIMARY) {
                if (selectedPiece == clickedWall) {
                    selectedPiece = null;
                } else {
                    clickedWall.setPosition(null);
                    selectedPiece = clickedWall;
                }
                return true;
            } else if (button == MouseButton.SECONDARY) {
                clickedWall.setPosition(null);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void resetState() {
        selectedPiece = null;
        Game.getInstance().challengeManager.getChallengeData().resetWalls();
    }

    @Override
    protected void step(double deltaTime) {
        backgroundView.draw(graphics, deltaTime);
        fpsDisplayView.draw(graphics, deltaTime);
        wallPaletteView.draw(graphics, deltaTime);
        gridView.draw(graphics, deltaTime);

        drawKnights(deltaTime);
        drawHighTowers(deltaTime);
        drawWalls(deltaTime);
    }

    @Override
    protected void drawWalls(double deltaTime) {
        WallView selectedWallView = null;
        for (WallView wallView : wallViews) {
            double dragX;
            double dragY;
            boolean previewSuitable;
            if (hoveredBlock == null) {
                dragX = lastMouseX;
                dragY = lastMouseY;
                previewSuitable = false;
            } else {
                dragX = gridView.translateToScreenX(hoveredBlock.x + 0.5);
                dragY = gridView.translateToScreenY(hoveredBlock.y + 0.5);
                previewSuitable = placementIsSuitable;
            }
            wallView.update(selectedPiece == wallView.getModel(), previewSuitable, dragX, dragY);
            if (selectedPiece == wallView.getModel()) {
                selectedWallView = wallView;
            } else {
                wallView.draw(graphics, deltaTime);
            }
        }
        if (selectedWallView != null) {
            selectedWallView.draw(graphics, deltaTime);
        }
    }

    private void checkSolution(boolean showMistake) {
        ChallengeData challenge = Game.getInstance().challengeManager.getChallengeData();
        SolutionManager solutionManager = Game.getInstance().solutionManager;
        ArrayList<KnightData> incorrectRedKnights = solutionManager.checkSolution(challenge);
        if (incorrectRedKnights == null) {
            handleAlert("Mistake", "Walls are not closed.", showMistake);
        } else if (!incorrectRedKnights.isEmpty()) {
            handleAlert("Mistake", "Problem with red Knights: " + incorrectRedKnights.size(), showMistake);
        } else {
            handleAlert("WIN!", "Congratulations!!! You solved the challenge", true);
        }
    }
    
    private void handleAlert(String title, String content, boolean show) {
        if (show) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();
        }
    }
    
}
