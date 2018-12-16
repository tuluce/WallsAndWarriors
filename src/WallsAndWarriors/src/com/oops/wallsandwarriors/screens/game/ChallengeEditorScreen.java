package com.oops.wallsandwarriors.screens.game;

import com.oops.wallsandwarriors.ChallengeManager;
import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.GameConstants;
import com.oops.wallsandwarriors.SolutionManager;
import com.oops.wallsandwarriors.definitions.WallDefinitions;
import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.model.GridPiece;
import com.oops.wallsandwarriors.model.HighTowerData;
import com.oops.wallsandwarriors.model.KnightData;
import com.oops.wallsandwarriors.model.WallData;
import com.oops.wallsandwarriors.screens.challenges.CustomChallengesData;
import com.oops.wallsandwarriors.util.CopyUtils;
import com.oops.wallsandwarriors.util.EncodeUtils;
import com.oops.wallsandwarriors.view.BackgroundView;
import com.oops.wallsandwarriors.view.BoundedViewObject;
import com.oops.wallsandwarriors.view.EditorPaletteElementView;
import com.oops.wallsandwarriors.view.EditorPaletteView;
import com.oops.wallsandwarriors.view.GridPieceView;
import com.oops.wallsandwarriors.view.GridView;
import com.oops.wallsandwarriors.view.HighTowerView;
import com.oops.wallsandwarriors.view.KnightView;
import com.oops.wallsandwarriors.view.WallView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
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

    List<ChallengeData> customChallenges;
    
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
        });
        addButton(root, "Reset", 650, 550, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resetState();
            }
        });
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
        setLayoutPos(descriptionField, 520, 480);
        setLayoutPos(creatorField, 520, 510);
        
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
                    boolean canPick = true;
                    if (selectedPiece instanceof WallData) {
                        canPick = checkWallCount();
                    }
                    if (canPick) {
                        refreshPreview();
                        previewView.setIndex(clickables.indexOf(clickedView));
                    } else {
                        selectedPiece = null;
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Wall Count");
                        alert.setContentText("There can be at most 4 walls in a challenge.");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                } else {
                    selectedPiece = null;
                    previewView = null;
                }
                return true;
            }
        } else if (clickedView instanceof GridPieceView) {
            GridPieceView clickedGridPieceView = (GridPieceView) clickedView;
            GridPiece clickedGridPiece = clickedGridPieceView.getModel();
            if (button == MouseButton.PRIMARY || button == MouseButton.SECONDARY) {
                ChallengeManager challengeManager = Game.getInstance().challengeManager;
                challengeManager.getChallengeData().removePiece(clickedGridPiece);
                selectedPiece = null;
                previewView = null;
            }
            if (button == MouseButton.PRIMARY) {
                selectedPiece = clickedGridPiece.createCopy();
                refreshPreview();
                previewView.getModel().setPosition(null);
                previewView.setIndex(-1);
            }
            if (button == MouseButton.PRIMARY || button == MouseButton.SECONDARY) {
                updateViewList();
                return true;
            }
        }
        return false;
    }
    
    private void refreshPreview() {
        if (selectedPiece instanceof KnightData) {
            previewView = new KnightView((KnightData) selectedPiece, true);
        } else if (selectedPiece instanceof HighTowerData) {
            previewView = new HighTowerView((HighTowerData) selectedPiece, true);
        } else if (selectedPiece instanceof WallData) {
            previewView = new WallView((WallData) selectedPiece, true);
        }
    }
    
    private boolean checkWallCount() {
        int wallCount = Game.getInstance().challengeManager.getChallengeData().walls.size();
        return wallCount < 4;
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
        
        clickables.clear();
        clickables.addAll(paletteElementViews);
        clickables.addAll(wallViews);
        clickables.addAll(knightViews);
        clickables.addAll(highTowerViews);
    }
    
    private void exportChallenge() throws IOException {
        ChallengeData exportedChallenge = Game.getInstance().challengeManager.getChallengeData();
        SolutionManager solutionManager = Game.getInstance().solutionManager;
        ArrayList<KnightData> incorrectRedKnights = solutionManager.checkSolution(exportedChallenge);

        int max_LENGTH = 20;
        if((descriptionField.getText().length() <= max_LENGTH && nameField.getText().length() <= max_LENGTH &&  creatorField.getText().length() <= max_LENGTH && nameField.getText().length() != 0 && descriptionField.getText().length() != 0 && creatorField.getText().length() != 0 && exportedChallenge.knights.size() != 0 && exportedChallenge.redKnights() && exportedChallenge.blueKnights() && incorrectRedKnights != null) && (incorrectRedKnights.isEmpty())) {
                exportedChallenge.setDescription(descriptionField.getText());
                exportedChallenge.setName(nameField.getText());
                exportedChallenge.setCreator(creatorField.getText());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            if (nameField.getText().length() == 0)
                alert.setContentText("Challenge name cannot be blank");
            else if(descriptionField.getText().length() == 0)
                alert.setContentText("Challenge description cannot be blank");
            else if(creatorField.getText().length() == 0)
                alert.setContentText("Challenge creator cannot be blank");
            else if (max_LENGTH  <= descriptionField.getText().length() && max_LENGTH  <= nameField.getText().length() && max_LENGTH  <= creatorField.getText().length() )
                alert.setContentText("Length of each text cannot be more than " + max_LENGTH + "characters");
            else if(!exportedChallenge.blueKnights())
                alert.setContentText("There are no blue knights in the challenge");
            else if(!exportedChallenge.redKnights())
                alert.setContentText("There are no red knights in the challenge");
            else if(incorrectRedKnights == null) {
                alert.setContentText("Solution is incomplete");
            }
            else if(!incorrectRedKnights.isEmpty())
                alert.setContentText("Solution is not correct");

            alert.setHeaderText(null);
            alert.showAndWait();
            return;
        }

        exportedChallenge.resetWalls();

        TextArea textArea = new TextArea(EncodeUtils.encode(exportedChallenge));
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.add(textArea, 0, 0);
        ButtonType clipboard = new ButtonType("Copy To Clipboard");
        ButtonType addToCustom = new ButtonType( "Add To Custom Challenges");
        ButtonType ok = new ButtonType("OK", ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Copy the exported challenge code.");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(gridPane);
        alert.getButtonTypes().add(clipboard);
        alert.getButtonTypes().add(addToCustom);
        alert.getButtonTypes().add(ok);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == clipboard) {
            CopyUtils.copyToClipboard(textArea.getText());
        }
        else if (result.get() == addToCustom) {
            addToCustomChallenges(textArea.getText());
        }
    }
    
    private void addToCustomChallenges(String challengeData) throws IOException {
        CustomChallengesData customChallengesData = new CustomChallengesData();
        customChallenges = customChallengesData.getCustomChallenges();

        ChallengeData toImp = null;
        try {
            toImp = EncodeUtils.decode(challengeData);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(customChallenges.add(toImp)) {
            customChallengesData.update(toImp);
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Successful");
            alert2.setContentText("The new challenge added to your \"Custom Challenges\" list successfully!");
            alert2.setHeaderText(null);
            alert2.showAndWait();
        }
    }
    
}
