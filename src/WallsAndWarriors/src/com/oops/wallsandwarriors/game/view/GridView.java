package com.oops.wallsandwarriors.game.view;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.game.model.HighTowerData;
import com.oops.wallsandwarriors.game.model.WallData;
import com.oops.wallsandwarriors.util.DrawUtils;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridView implements ViewObject {

    private final WallView tempWallView;
    
    private final double x;
    private final double y;
    private final double margin;
    private final double blockLength;
    
    public GridView(double x, double y, double margin, double blockLength) {
        this.x = x;
        this.y = y;
        this.margin = margin;
        this.blockLength = blockLength;
        tempWallView = new WallView();
    }
    
    @Override
    public void draw(GraphicsContext graphics, double deltaTime) {
        drawGrid(graphics, deltaTime);
        drawPlacedWalls(graphics, deltaTime);
        drawKnights(graphics, deltaTime);
        drawHighTowers(graphics, deltaTime);
    }
    
    private void drawGrid(GraphicsContext graphics, double deltaTime) {
        graphics.setFill(Color.BEIGE);
        graphics.fillRoundRect(x - margin,
                y - margin,
                5 * blockLength + 2 * margin,
                4 * blockLength + 2 * margin, 50, 50);
        List<Coordinate> blocks = Game.getInstance().getChallengeManager().getChallengeData().blocks;
        DrawUtils.setAttributes(graphics, Color.GRAY, Color.LIGHTGRAY, 6);
        for (Coordinate block : blocks) {
            DrawUtils.drawRect(graphics, 
                    x + block.x * blockLength,
                    y + block.y * blockLength,
                    blockLength, blockLength);
        }
    }
    
    private void drawPlacedWalls(GraphicsContext graphics, double deltaTime) {
        graphics.setStroke(Color.BLACK);
        List<WallData> walls = Game.getInstance().getChallengeManager().getChallengeData().walls;
        for (int i = 0; i < walls.size(); i++) {
            WallData wall = walls.get(i);
            if (wall.getPosition() != null) {
                tempWallView.update(x, y, blockLength, wall);
                tempWallView.draw(graphics, deltaTime);
            }
        }
    }
    
    private void drawKnights(GraphicsContext graphics, double deltaTime) {
        List<Coordinate> castleKnights = Game.getInstance().getChallengeManager().getChallengeData().castleKnights;
        List<Coordinate> enemyKnights = Game.getInstance().getChallengeManager().getChallengeData().enemyKnights;
        graphics.setLineWidth(4);
        graphics.setFill(Color.BLUE);
        for (Coordinate castleKnight : castleKnights) {
            drawKnight(graphics, castleKnight);
        }
        graphics.setFill(Color.RED);
        for (Coordinate enemyKnight : enemyKnights) {
            drawKnight(graphics, enemyKnight);
        }
    }
    
    private void drawKnight(GraphicsContext graphics, Coordinate block) {
        double a = translateToScreenX(block.x + 0.5);
        double b = translateToScreenY(block.y + 0.5);
        double l = blockLength;
        final double RAD_RATIO = 0.5;
        DrawUtils.drawOval(graphics, a - l * RAD_RATIO * 0.5,
                b - l * RAD_RATIO * 0.5, l * RAD_RATIO, l * RAD_RATIO);
    }
    
    private void drawHighTowers(GraphicsContext graphics, double deltaTime) {
        List<HighTowerData> highTowers = Game.getInstance().getChallengeManager().getChallengeData().highTowers;
        graphics.setFill(Color.BLUE);
        graphics.setStroke(Color.BLACK);
        for (HighTowerData highTower : highTowers) {
            graphics.setLineWidth(blockLength / 3.0);
            drawHighTowerWall(graphics, highTower.firstPosition, highTower.secondPosition);
            graphics.setLineWidth(6);
            drawHighTowerPart(graphics, highTower.firstPosition);
            drawHighTowerPart(graphics, highTower.secondPosition);
        }
    }
    
    private void drawHighTowerPart(GraphicsContext graphics, Coordinate block) {
        double a = translateToScreenX(block.x + 0.5);
        double b = translateToScreenY(block.y + 0.5);
        double l = blockLength;
        final double RAD_RATIO = 0.6;
        DrawUtils.drawOval(graphics, a - l * RAD_RATIO * 0.5,
                b - l * RAD_RATIO * 0.5, l * RAD_RATIO, l * RAD_RATIO);
    }
    
    private void drawHighTowerWall(GraphicsContext graphics, Coordinate block1, Coordinate block2) {
        double a = translateToScreenX(block1.x + 0.5);
        double b = translateToScreenY(block1.y + 0.5);
        double c = translateToScreenX(block2.x + 0.5);
        double d = translateToScreenY(block2.y + 0.5);
        graphics.strokeLine(a, b, c, d);
    }
    
    public double translateToScreenY(double gridY) {
        return y + gridY * blockLength;
    }
    
    public double translateToScreenX(double gridX) {
        return x + gridX * blockLength;
    }

}
