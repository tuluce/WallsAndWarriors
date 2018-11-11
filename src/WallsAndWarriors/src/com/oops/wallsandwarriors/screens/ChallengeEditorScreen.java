package com.oops.wallsandwarriors.screens;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.definitions.WallDefinitions;
import com.oops.wallsandwarriors.game.model.ChallengeData;
import com.oops.wallsandwarriors.game.model.GridPiece;
import com.oops.wallsandwarriors.game.model.HighTowerData;
import com.oops.wallsandwarriors.game.model.KnightData;
import com.oops.wallsandwarriors.game.model.WallData;
import com.oops.wallsandwarriors.game.view.BackgroundView;
import com.oops.wallsandwarriors.game.view.BoundedViewObject;
import com.oops.wallsandwarriors.game.view.EditorPaletteElementView;
import com.oops.wallsandwarriors.game.view.EditorPaletteView;
import com.oops.wallsandwarriors.game.view.GridView;
import com.oops.wallsandwarriors.game.view.HighTowerView;
import com.oops.wallsandwarriors.game.view.KnightView;
import com.oops.wallsandwarriors.game.view.WallView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.oops.wallsandwarriors.util.CopyUtils;
import com.oops.wallsandwarriors.util.EncodeUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ChallengeEditorScreen extends BaseGameScreen {
    
    private EditorPaletteView paletteView;
    private List<EditorPaletteElementView> paletteElementViews;
    private TextField nameField;
    private TextField creatorField;
    private TextField descriptionField;
    
    @Override
    protected void initViewObjects() {
        super.initViewObjects();
        gridView = new GridView(GameConstants.EDITOR_GRID_X, GameConstants.EDITOR_GRID_Y,
                GameConstants.EDITOR_GRID_MARGIN, GameConstants.EDITOR_GRID_B);
        Game.getInstance().challengeManager.initChallengeData();
        backgroundView = new BackgroundView(true);
        paletteView = new EditorPaletteView();
        initPaletteElements();
    }
    
    private void initPaletteElements() {
        paletteElementViews = new ArrayList<EditorPaletteElementView>();
        int index;
        for (index = 0; index < WallDefinitions.STANDARD.size(); index++) {
            WallData wall = WallDefinitions.STANDARD.get(index);
            paletteElementViews.add(new EditorPaletteElementView(index, new WallView(wall)));
        }
        paletteElementViews.add(index + 0, new EditorPaletteElementView(index + 0,
                new KnightView(new KnightData(null, true))));
        paletteElementViews.add(index + 1, new EditorPaletteElementView(index + 1,
                new KnightView(new KnightData(null, false))));
        paletteElementViews.add(index + 2, new EditorPaletteElementView(index + 2,
                new HighTowerView(new HighTowerData(null, null))));
        clickables.addAll(paletteElementViews);
    }
    
    @Override
    protected void addComponents(Group root) {
        addTransactionButton(root, "Back", 700, 50, Game.getInstance().screenManager.mainMenu);
        addButton(root, "Export", 700, 550, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    exportChallenge();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        addTextComponents(root);
    }
    
    private void addTextComponents(Group root) {
        Font labelFont = new Font("Arial", 20);
        Label nameLabel = new Label("Challenge Name:");
        Label descriptionLabel = new Label("Description:");
        Label creatorLabel = new Label("Creator:");
        nameLabel.setFont(labelFont);
        descriptionLabel.setFont(labelFont);
        creatorLabel.setFont(labelFont);
        nameLabel.setTextFill(Color.WHITE);
        descriptionLabel.setTextFill(Color.WHITE);
        creatorLabel.setTextFill(Color.WHITE);
        setLayoutPos(nameLabel, 350, 450);
        setLayoutPos(descriptionLabel, 350, 480);
        setLayoutPos(creatorLabel, 350, 510);
        
        nameField = new TextField();
        creatorField = new TextField();
        descriptionField = new TextField();
        nameField.setPrefWidth(250);
        creatorField.setPrefWidth(250);
        descriptionField.setPrefWidth(250);
        setLayoutPos(nameField, 520, 450);
        setLayoutPos(creatorField, 520, 480);
        setLayoutPos(descriptionField, 520, 510);
        
        root.getChildren().add(nameLabel);
        root.getChildren().add(descriptionLabel);
        root.getChildren().add(creatorLabel);
        root.getChildren().add(nameField);
        root.getChildren().add(creatorField);
        root.getChildren().add(descriptionField);
    }
    
    @Override
    protected boolean attemptPlacement() {
        if (hoveredBlock != null && selectedPiece != null) {
            boolean placed = Game.getInstance().gridManager.attemptPlacement(hoveredBlock, selectedPiece);
            if (placed) {
                Game.getInstance().challengeManager.getChallengeData().addPiece(selectedPiece);
                updateViewList();
                return true;
            }
        }
        return false;
    }
    
    @Override
    protected boolean handleViewClick(BoundedViewObject clickedView, MouseButton button) {
        if (clickedView instanceof EditorPaletteElementView) {
            EditorPaletteElementView paletteElement = (EditorPaletteElementView) clickedView;
            GridPiece clickedPiece = paletteElement.generateModel();
            if (button == MouseButton.PRIMARY) {
                if (selectedPiece == null) {
                    selectedPiece = clickedPiece;
                    if (selectedPiece instanceof KnightData) {
                        previewView = new KnightView((KnightData) selectedPiece, true);
                    } else if (selectedPiece instanceof HighTowerData) {
                        previewView = new HighTowerView((HighTowerData) selectedPiece, true);
                    } else if (selectedPiece instanceof WallData) {
                        previewView = new WallView((WallData) selectedPiece, true);
                    }
                    previewView.setIndex(clickables.indexOf(clickedView));
                } else {
                    selectedPiece = null;
                    previewView = null;
                }
                return true;
            }
        }
        return false;
    }
    
    @Override
    protected void resetState() {
        selectedPiece = null;
        previewView = null;
        Game.getInstance().challengeManager.getChallengeData().resetAll();
        updateViewList();
    }
    
    @Override
    protected void step(double deltaTime) {
        backgroundView.draw(graphics, deltaTime);
        fpsDisplayView.draw(graphics, deltaTime);
        paletteView.draw(graphics, deltaTime);
        gridView.draw(graphics, deltaTime);
        
        drawPaletteElements(deltaTime);
        drawKnights(deltaTime);
        drawHighTowers(deltaTime);
        drawWalls(deltaTime);
        drawPreview(deltaTime);
    }
    
    private void drawPaletteElements(double deltaTime) {
        for (EditorPaletteElementView paletteElement : paletteElementViews) {
            paletteElement.draw(graphics, deltaTime);
        }
    }
    
    @Override
    protected void drawWalls(double deltaTime) {
        for (WallView wallView : wallViews) {
            wallView.update(false, false, -1, -1);
            wallView.draw(graphics, deltaTime);
        }
    }
    
    private void drawPreview(double deltaTime) {
        if (previewView != null) {
            double dragX;
            double dragY;
            boolean previewSuitable;
            if (hoveredBlock == null) {
                dragX = lastMouseX;
                dragY = lastMouseY;
                previewSuitable = true;
            } else {
                dragX = gridView.translateToScreenX(hoveredBlock.x + 0.5);
                dragY = gridView.translateToScreenY(hoveredBlock.y + 0.5);
                previewSuitable = placementIsSuitable;
            }
            previewView.update(true, previewSuitable, dragX, dragY);
            previewView.draw(graphics, deltaTime);
        }
    }
    
    private void updateViewList() {
        ChallengeData challenge = Game.getInstance().challengeManager.getChallengeData();
        wallViews.clear();
        knightViews.clear();
        highTowerViews.clear();
        for (WallData wall : challenge.walls) {
            wallViews.add(new WallView(wall, true));
        }
        for (KnightData knight : challenge.knights) {
            knightViews.add(new KnightView(knight, true));
        }
        for (HighTowerData highTower : challenge.highTowers) {
            highTowerViews.add(new HighTowerView(highTower, true));
        }
    }
    
    private void exportChallenge() throws FileNotFoundException,IOException{
        ChallengeData exportedChallenge = Game.getInstance().challengeManager.getChallengeData().createCopy();
        exportedChallenge.resetWalls();
        exportedChallenge.setName(nameField.getText());
        exportedChallenge.setDescription(nameField.getText());
        exportedChallenge.setCreator(creatorField.getText());
        
        TextArea textArea = new TextArea(EncodeUtils.encode(exportedChallenge));
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.add(textArea, 0, 0);
        ButtonType clipboard = new ButtonType("Copy To Clipboard!");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Copy the exported challenge code.");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(gridPane);
        alert.getButtonTypes().add(clipboard);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == clipboard )
        {
                CopyUtils.copyToClipboard(textArea.getText());
        }
        alert.showAndWait();
    }
    
}
