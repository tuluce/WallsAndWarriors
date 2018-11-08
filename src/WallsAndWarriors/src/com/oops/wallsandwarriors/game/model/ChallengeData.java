package com.oops.wallsandwarriors.game.model;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChallengeData implements Serializable{

    public final String name;
    public final String creator;
        
    public final List<Coordinate> blocks;
    public final List<Coordinate> castleKnights;
    public final List<HighTowerData> highTowers;
    public final List<Coordinate> enemyKnights;
    public final List<WallData> walls;
    
    public ChallengeData(
            String name,
            String creator,
            List<Coordinate> blocks,
            List<Coordinate> castleKnights,
            List<HighTowerData> highTowers,
            List<Coordinate> enemyKnights,
            List<WallData> walls) {
        this.name = name;
        this.creator = creator;
        this.blocks = blocks;
        this.castleKnights = castleKnights;
        this.highTowers = highTowers;
        this.enemyKnights = enemyKnights;
        this.walls = walls;
    }
    
    public void reset() {
        for (WallData wall : walls) {
            wall.setPosition(null);
        }
    }
    
    public List<Point2D.Double> getOccupiedPoints() {
        List<Point2D.Double> occupiedList = new ArrayList<Point2D.Double>();
        for (HighTowerData highTower : highTowers) {
            occupiedList.addAll(highTower.occupies());
        }
        for (WallData wall : walls) {
            occupiedList.addAll(wall.occupies());
        }
        return occupiedList;
    }
    
    public ChallengeData createCopy() {
        List<Coordinate> blocksCopy = new ArrayList<Coordinate>();
        blocksCopy.addAll(blocks);
        
        List<Coordinate> castleKnightsCopy = new ArrayList<Coordinate>();
        castleKnightsCopy.addAll(castleKnights);
        
        List<HighTowerData> highTowersCopy = new ArrayList<HighTowerData>();
        highTowersCopy.addAll(highTowers);
        
        List<Coordinate> enemyKnightsCopy = new ArrayList<Coordinate>();
        enemyKnightsCopy.addAll(enemyKnights);
        
        List<WallData> wallsCopy = new ArrayList<WallData>();
        for (WallData wall : walls) {
            wallsCopy.add(new WallData(wall.getWallDefinition()));
        }
        
        return new ChallengeData(name, creator, blocksCopy, castleKnightsCopy,
                highTowersCopy, enemyKnightsCopy, wallsCopy);
    }
    
}
