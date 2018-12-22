package com.oops.wallsandwarriors.screens.game;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.model.Coordinate;
import com.oops.wallsandwarriors.model.GridPiece;
import com.oops.wallsandwarriors.screens.GeneralScreen;
import com.oops.wallsandwarriors.view.BackgroundView;
import com.oops.wallsandwarriors.view.BoundedViewObject;
import com.oops.wallsandwarriors.view.FpsDisplayView;
import com.oops.wallsandwarriors.view.GridPieceView;
import com.oops.wallsandwarriors.view.GridView;
import com.oops.wallsandwarriors.view.HighTowerView;
import com.oops.wallsandwarriors.view.KnightView;
import com.oops.wallsandwarriors.view.WallView;
import com.oops.wallsandwarriors.util.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * This abstract class defines a general screen structure for the Game Screens. This
 * screen will provide a generic definition of standard structure for playing and creating
 * challenges using the mutual components. But the distinguishing methods will be
 * implemented separately in the sub-classes.
 * Extends GeneralScreen
 * @author Emin Bahadir Tuluce
 */
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

    /**
     * A default constructor that initializes a BaseGAmeScreen with
     * no given parameters to provide animations
     */
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

    /**
     * An overriden get method to return current Scene.
     * @return the current Scene.
     */
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

    /**
     * A method to initialize objects in the current view.
     */
    protected void initViewObjects() {
        fpsDisplayView = new FpsDisplayView();
        wallViews = new ArrayList<WallView>();
        knightViews = new ArrayList<KnightView>();
        highTowerViews = new ArrayList<HighTowerView>();
        clickables = new ArrayList<BoundedViewObject>();
    }

    /**
     * A method to initialize
     * @param scene
     */
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
                        Game.getInstance().soundManager.playRotate();
                        selectedPiece.rotate();
                        checkPlacement();
                        return;
                    }
                    checkObjectClick(e.getX(), e.getY(), e.getButton());
                }
            }
        );
    }

    /**
     * A method to restart the timer for the game
     */
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

    /**
     * A method to check whether object is clicked on with a mouse button
     * @param mouseX X coordinate of the screen where mouse button is clicked
     * @param mouseY Y coordinate of the screen where mouse button is clicked
     * @param button mouse button
     * @return boolean value indicating whether the object is chosen with mouse button click
     */
    private boolean checkObjectClick(double mouseX, double mouseY, MouseButton button) {
        Iterator<BoundedViewObject> iterator = clickables.iterator();
        while (iterator.hasNext()) {
            BoundedViewObject clickableObjects = iterator.next();
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

    /**
     * A method to draw all knights on the Screen
     * @param deltaTime the time difference until last render
     */
    protected void drawKnights(double deltaTime) {
        for (KnightView knightView : knightViews) {
            knightView.draw(graphics, deltaTime);
        }
    }

    /**
     * A method to draw all high towers on the screen
     * @param deltaTime the time difference until last render
     */
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

    /**
     * An abstract method  to draw all wals on the screen in their correct places
     * @param deltaTime the time difference until last render
     */
    protected abstract void drawWalls(double deltaTime);
    
}
