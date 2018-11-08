package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.BoundsManager;
import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.game.model.ChallengeData;
import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.game.model.WallData;
import com.oops.wallsandwarriors.game.view.BackgroundView;
import com.oops.wallsandwarriors.game.view.FpsDisplayView;
import com.oops.wallsandwarriors.game.view.GridView;
import com.oops.wallsandwarriors.game.view.WallPaletteView;
import com.oops.wallsandwarriors.game.view.WallView;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
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
    private WallData selectedWall;
    private boolean placementIsSuitable;
    private double lastMouseX;
    private double lastMouseY;
    
    private final AnimationTimer stepTimer;
    
    private BackgroundView backgroundView;
    private GridView gridView;
    private FpsDisplayView fpsDisplayView;
    private WallPaletteView wallPaletteView;
    private List<WallView> wallViews;
    
    public GameScreen() {
        stepTimer = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                double deltaTime = (currentTime - lastRenderTime) / 1000000000.0;
                lastRenderTime = currentTime;
                step(deltaTime);
            }
        };
    }
    
    @Override
    public Scene getScene() {
        Group root = new Group();
        Scene scene = new Scene(root);
        graphics = addBackgroundCanvas(root);
        initClickController(scene);
        initHoverController(scene);
        addButtons(root);
        initViewObjects();
        restartTimer();
        return scene;
    }
    
    private void initViewObjects() {
        backgroundView = new BackgroundView();
        gridView = new GridView(GameConstants.GRID_X, GameConstants.GRID_Y,
                GameConstants.GRID_MARGIN, GameConstants.GRID_B);
        fpsDisplayView = new FpsDisplayView();
        wallPaletteView = new WallPaletteView();
        
        ChallengeData challenge = Game.getInstance().getChallengeManager().getChallengeData();
        wallViews = new ArrayList<WallView>();
        for (WallData wall : challenge.walls) {
            wallViews.add(new WallView(wall));
        }
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
                    boolean rightClickConsumed = false;
                    if (e.getButton() == MouseButton.PRIMARY) {
                        checkGridClick(e.getX(), e.getY());
                    }
                    if (e.getButton() == MouseButton.SECONDARY && selectedWall != null) {
                        selectedWall.rotate();
                        rightClickConsumed = true;
                    }
                    if (!rightClickConsumed) {
                        checkWallClick(e.getX(), e.getY(), e.getButton());   
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
        if (selectedWall != null) {
            for (int i = 0; i < blockBounds.size(); i++) {
                Rectangle2D.Double rectangle = blockBounds.get(i);
                if (rectangle.contains(mouseX, mouseY)) {
                    attemptPlacement(new Coordinate(
                            BoundsManager.translateToGridX(rectangle.x),
                            BoundsManager.translateToGridY(rectangle.y)));
                    selectedWall = null;
                    return;
                }
            }
        }
    }
    
    private void attemptPlacement(Coordinate selectedBlock) {
        if (Game.getInstance().getGameManager()
                .attemptWallPlacement(selectedBlock, selectedWall)) {
            System.out.println("Checking for solved...");
        }
    }
    
    private void checkPlacement(double mouseX, double mouseY) {
        List<Rectangle2D.Double> blockBounds = Game.getInstance()
                .getBoundsManager().getBlockBounds();
        hoveredBlock = null;
        if (selectedWall != null) {
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
                    .isWallPlacable(hoveredBlock, selectedWall);
        }
    }
    
    private void checkWallClick(double mouseX, double mouseY, MouseButton button) {
        for (WallView wallView : wallViews) {
            if (wallView.getBounds().contains(mouseX, mouseY)) {
                WallData clickedWall = wallView.getModel();
                if (button == MouseButton.PRIMARY) {
                    clickedWall.setPosition(null);
                    selectedWall = clickedWall;
                    return;
                } else if (button == MouseButton.SECONDARY) {
                    clickedWall.setPosition(null);
                    return;
                }
            }
        }
    }
    
    private void resetState() {
        selectedWall = null;
        Game.getInstance().getChallengeManager().getChallengeData().reset();
    }
    
    private void step(double deltaTime) {
        Game.getInstance().getBoundsManager().calculateBounds();
        
        backgroundView.draw(graphics, deltaTime);
        fpsDisplayView.draw(graphics, deltaTime);
        wallPaletteView.draw(graphics, deltaTime);
        gridView.draw(graphics, deltaTime);
        
        for (WallView wallView : wallViews) {
            double dragX;
            double dragY;
            boolean previewSuitable;
            if (hoveredBlock == null) {
                dragX = lastMouseX;
                dragY = lastMouseY;
                previewSuitable = false;
            } else {
                dragX = BoundsManager.translateToScreenX(hoveredBlock.x + 0.5);
                dragY = BoundsManager.translateToScreenY(hoveredBlock.y + 0.5);
                previewSuitable = placementIsSuitable;
            }
            wallView.update(selectedWall == wallView.getModel(), previewSuitable, dragX, dragY);
            wallView.draw(graphics, deltaTime);
        }
    }
        
}
