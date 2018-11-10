package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.game.model.GridPiece;
import com.oops.wallsandwarriors.game.view.BackgroundView;
import com.oops.wallsandwarriors.game.view.BoundedViewObject;
import com.oops.wallsandwarriors.game.view.FpsDisplayView;
import com.oops.wallsandwarriors.game.view.GridPieceView;
import com.oops.wallsandwarriors.game.view.GridView;
import com.oops.wallsandwarriors.game.view.HighTowerView;
import com.oops.wallsandwarriors.game.view.KnightView;
import com.oops.wallsandwarriors.game.view.WallView;
import com.oops.wallsandwarriors.util.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public abstract class BaseGameScreen extends GeneralScreen {
    
    private double lastRenderTime;
    private final AnimationTimer stepTimer;
    
    protected GraphicsContext graphics;
    protected Coordinate hoveredBlock;
    protected GridPiece selectedPiece;
    protected boolean placementIsSuitable;
    protected double lastMouseX;
    protected double lastMouseY;
    
    protected BackgroundView backgroundView;
    protected GridView gridView;
    protected FpsDisplayView fpsDisplayView;
    protected List<BoundedViewObject> clickables;
    protected GridPieceView previewView;
    
    protected List<WallView> wallViews;
    protected List<KnightView> knightViews;
    protected List<HighTowerView> highTowerViews;
    
    public BaseGameScreen() {
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
        addComponents(root);
        initViewObjects();
        restartTimer();
        return scene;
    }
    
    protected void initViewObjects() {
        fpsDisplayView = new FpsDisplayView();
        wallViews = new ArrayList<WallView>();
        knightViews = new ArrayList<KnightView>();
        highTowerViews = new ArrayList<HighTowerView>();
        clickables = new ArrayList<BoundedViewObject>();
    }
    
    private void initHoverController(Scene scene) {
        scene.setOnMouseMoved(
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    lastMouseX = e.getX();
                    lastMouseY = e.getY();
                    calculateHoveredBlock(e.getX(), e.getY());
                    checkPlacement();
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
                        attemptPlacement();
                    }
                    else if (e.getButton() == MouseButton.SECONDARY && selectedPiece != null) {
                        selectedPiece.rotate();
                        checkPlacement();
                        return;
                    }
                    checkObjectClick(e.getX(), e.getY(), e.getButton());
                }
            }
        );
    }
    
    private void restartTimer() {
        stepTimer.stop();
        lastRenderTime = System.nanoTime();
        stepTimer.start();
    }
    
    private void calculateHoveredBlock(double mouseX, double mouseY) {
        List<Rectangle> blockBounds = gridView.getBlockBounds();
        hoveredBlock = null;
        if (selectedPiece != null) {
            for (int i = 0; i < blockBounds.size(); i++) {
                Rectangle rectangle = blockBounds.get(i);
                if (rectangle.contains(mouseX, mouseY)) {
                    hoveredBlock = new Coordinate(
                            gridView.translateToGridX(rectangle.x),
                            gridView.translateToGridY(rectangle.y));
                    break;
                }
            }
        }
    }
    
    private boolean checkObjectClick(double mouseX, double mouseY, MouseButton button) {
        for (BoundedViewObject clickableObjects : clickables) {
            if (clickableObjects.getBounds().contains(mouseX, mouseY)) {
                if (handleViewClick(clickableObjects, button)) {
                    return true;
                }
            }
        }
        selectedPiece = null;
        previewView = null;
        return false;
    }
    
    protected void checkPlacement() {
        if (hoveredBlock != null) {
            placementIsSuitable = Game.getInstance().gridManager
                    .isPiecePlacable(hoveredBlock, selectedPiece);
        }
    }
    
    protected void drawKnights(double deltaTime) {
        for (KnightView knightView : knightViews) {
            knightView.draw(graphics, deltaTime);
        }
    }

    protected void drawHighTowers(double deltaTime) {
        for (HighTowerView highTowerView : highTowerViews) {
            highTowerView.draw(graphics, deltaTime);
        }
    }
    
    protected abstract void addComponents(Group root);
    
    protected abstract boolean attemptPlacement();
    
    protected abstract boolean handleViewClick(BoundedViewObject clickedView, MouseButton button);
    
    protected abstract void resetState();
    
    protected abstract void step(double deltaTime);
    
    protected abstract void drawWalls(double deltaTime);
    
}
