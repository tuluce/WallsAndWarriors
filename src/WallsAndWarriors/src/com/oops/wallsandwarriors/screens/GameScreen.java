package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.BoundsManager;
import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.game.model.WallData;
import com.oops.wallsandwarriors.game.view.BackgroundView;
import com.oops.wallsandwarriors.game.view.FpsDisplayView;
import com.oops.wallsandwarriors.game.view.GridView;
import com.oops.wallsandwarriors.game.view.WallPaletteView;
import com.oops.wallsandwarriors.game.view.WallPreviewView;
import java.awt.geom.Rectangle2D;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class GameScreen extends ParentScreen {
    
    private double lastRenderTime;
    private GraphicsContext graphics;
    private Coordinate hoveredBlock;
    private int selectedWallIndex = -1;
    private boolean placementIsSuitable;
    private double lastMouseX;
    private double lastMouseY;
    
    private final AnimationTimer stepTimer;
    private final BackgroundView backgroundView;
    private final GridView gridView;
    private final FpsDisplayView fpsDisplayView;
    private final WallPaletteView wallPaletteView;
    private final WallPreviewView wallPreviewView;
    
    public GameScreen() {
        stepTimer = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                double deltaTime = (currentTime - lastRenderTime) / 1000000000.0;
                lastRenderTime = currentTime;
                step(deltaTime);
            }
        };
        backgroundView = new BackgroundView();
        gridView = new GridView(GameConstants.GRID_X, GameConstants.GRID_Y,
                GameConstants.GRID_MARGIN, GameConstants.GRID_B);
        fpsDisplayView = new FpsDisplayView();
        wallPaletteView = new WallPaletteView();
        wallPreviewView = new WallPreviewView();
    }
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        graphics = addBackgroundCanvas(root);
        initClickController(scene);
        initHoverController(scene);
        addButtons(root);
        restartTimer();
        return scene;
    }
    
    private void addButtons(Group root) {
        addTransactionButton(root, "Back", 700, 50, Game.MAIN_MENU);
        addButton(root, "Check", 700, 550, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    System.out.println("Checking...");
                }
            }
        );
        addButton(root, "Reset", 650, 550, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    resetState();
                }
            }
        );
    }
    
    private void initHoverController(Scene scene) {
        scene.setOnMouseMoved(
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    lastMouseX = e.getX();
                    lastMouseY = e.getY();
                    checkPlacement(e.getX(), e.getY());
                }
            }
        );
    }
    
    private void initClickController(Scene scene) {
        scene.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        checkGridClick(e.getX(), e.getY());
                        checkPaletteClick(e.getX(), e.getY());
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        if (selectedWallIndex >= 0) {
                            rotateSelection();
                            checkPlacement(e.getX(), e.getY());
                        }
                    }
                }
            }
        );
    }
    
    private void restartTimer() {
        stepTimer.stop();
        lastRenderTime = System.nanoTime();
        stepTimer.start();
    }
    
    private void checkGridClick(double mouseX, double mouseY) {
        List<Rectangle2D.Double> blockBounds = Game.getInstance()
                .getBoundsManager().getBlockBounds();
        if (selectedWallIndex >= 0) {
            for (int i = 0; i < blockBounds.size(); i++) {
                Rectangle2D.Double rectangle = blockBounds.get(i);
                if (rectangle.contains(mouseX, mouseY)) {
                    attemptPlacement(new Coordinate(
                            BoundsManager.translateToGridX(rectangle.x),
                            BoundsManager.translateToGridY(rectangle.y)));
                    selectedWallIndex = -1;
                    return;
                }
            }
        }
    }
    
    private void attemptPlacement(Coordinate selectedBlock) {
        if (Game.getInstance().getGameManager()
                .attemptWallPlacement(selectedBlock, selectedWallIndex)) {
            System.out.println("Checking for solved...");
        }
    }
    
    private void checkPlacement(double mouseX, double mouseY) {
        List<Rectangle2D.Double> blockBounds = Game.getInstance()
                .getBoundsManager().getBlockBounds();
        hoveredBlock = null;
        if (selectedWallIndex >= 0) {
            for (int i = 0; i < blockBounds.size(); i++) {
                Rectangle2D.Double rectangle = blockBounds.get(i);
                if (rectangle.contains(mouseX, mouseY)) {
                    hoveredBlock = new Coordinate(
                            BoundsManager.translateToGridX(rectangle.x),
                            BoundsManager.translateToGridY(rectangle.y));
                    break;
                }
            }
        }
        if (hoveredBlock != null) {
            placementIsSuitable = Game.getInstance().getGameManager()
                    .isWallPlacable(hoveredBlock, selectedWallIndex);
        }
    }
    
    private void rotateSelection() {
        WallData selection = Game.getInstance().getChallengeManager()
                .getChallengeData().walls.get(selectedWallIndex);
        selection.rotate();
    }
    
    private void checkPaletteClick(double mouseX, double mouseY) {
        List<Rectangle2D.Double> paletteBounds = Game.getInstance()
                .getBoundsManager().getPaletteBounds();
        for (int i = 0; i < paletteBounds.size(); i++) {
            Rectangle2D.Double rectangle = paletteBounds.get(i);
            if (rectangle.contains(mouseX, mouseY)) {
                if (selectedWallIndex == i) {
                    selectedWallIndex = -1;
                } else {
                    selectedWallIndex = i;
                }
                return;
            }
        }
        selectedWallIndex = -1;
    }
    
    private void resetState() {
        selectedWallIndex = -1;
        Game.getInstance().getChallengeManager().getChallengeData().reset();
    }
    
    private void step(double deltaTime) {
        Game.getInstance().getBoundsManager().calculateBounds();
        
        wallPaletteView.update(selectedWallIndex);
        wallPreviewView.update(selectedWallIndex, hoveredBlock,
                placementIsSuitable, lastMouseX, lastMouseY);
        
        backgroundView.draw(graphics, deltaTime);
        fpsDisplayView.draw(graphics, deltaTime);
        wallPaletteView.draw(graphics, deltaTime);
        gridView.draw(graphics, deltaTime);
        wallPreviewView.draw(graphics, deltaTime);
    }
        
}
