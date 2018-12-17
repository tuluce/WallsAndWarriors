package com.oops.wallsandwarriors.model;

import com.oops.wallsandwarriors.definitions.GridDefinitions;
import com.oops.wallsandwarriors.definitions.WallDefinitions;
import com.oops.wallsandwarriors.util.AlgorithmUtils;
import com.oops.wallsandwarriors.util.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChallengeData implements Serializable {

    private String name;
    private String description;
    private String creator;
        
    public final List<Coordinate> blocks;
    public final List<KnightData> knights;
    public final List<HighTowerData> highTowers;
    public final List<WallData> walls;
    
    private transient final List<BorderPiece> borderPieces;
    private transient final List<BlockPiece> blockPieces;
    
    public ChallengeData(List<Coordinate> blocks, List<WallData> walls) {
        this.name = "<name>";
        this.creator = "<creator>";
        this.description = "<description>";
        this.blocks = new ArrayList<Coordinate>();
        this.knights = new ArrayList<KnightData>();
        this.highTowers = new ArrayList<HighTowerData>();
        this.walls = new ArrayList<WallData>();
        this.borderPieces = new ArrayList<BorderPiece>();
        this.blockPieces = new ArrayList<BlockPiece>();
        
        for (Coordinate block : blocks) {
            this.blocks.add(block);
        }
        for (WallData wall : walls) {
            addPiece(wall.createCopy());
        }
        resetWalls();
    }
    
    public ChallengeData() {
        this(GridDefinitions.SMALL, WallDefinitions.STANDARD);
    }
    
    public void addPiece(GridPiece piece) {
        if (piece instanceof WallData) {
            walls.add((WallData) piece);
        } else if (piece instanceof KnightData) {
            knights.add((KnightData) piece);
        } else if (piece instanceof HighTowerData) {
            highTowers.add((HighTowerData) piece);
        }
        
        if (piece instanceof BlockPiece) {
            blockPieces.add((BlockPiece) piece);
        }
        if (piece instanceof BorderPiece) {
            borderPieces.add((BorderPiece) piece);
        }
    }
    
    public void removePiece(GridPiece piece) {
        if (piece instanceof WallData) {
            AlgorithmUtils.concurrentRemove(walls, piece);
        } else if (piece instanceof KnightData) {
            AlgorithmUtils.concurrentRemove(knights, piece);
        } else if (piece instanceof HighTowerData) {
            AlgorithmUtils.concurrentRemove(highTowers, piece);
        }
        
        if (piece instanceof BlockPiece) {
            AlgorithmUtils.concurrentRemove(blockPieces, piece);
        }
        if (piece instanceof BorderPiece) {
            AlgorithmUtils.concurrentRemove(borderPieces, piece);
        }
    }
    
    public void resetWalls() {
        for (WallData wall : walls) {
            wall.setPosition(null);
        }
    }
    
    public void resetAll() {
        knights.clear();
        highTowers.clear();
        walls.clear();
        borderPieces.clear();
        blockPieces.clear();
    }
    
    public List<Point> getOccupiedBorderPoints() {
        List<Point> occupiedList = new ArrayList<Point>();
        for (BorderPiece borderPiece : borderPieces) {
            occupiedList.addAll(borderPiece.occupiesBorder());
        }
        return occupiedList;
    }
    
    public List<Coordinate> getOccupiedBlockPoints() {
        List<Coordinate> occupiedList = new ArrayList<Coordinate>();
        for (BlockPiece blockPiece : blockPieces) {
            occupiedList.addAll(blockPiece.occupiesBlock());
        }
        return occupiedList;
    }
    
    public ChallengeData createCopy()  {
        ChallengeData copyChallenge = new ChallengeData(blocks, walls);
        copyChallenge.name = name;
        copyChallenge.description = description;
        copyChallenge.creator = creator;
        for (KnightData knight : knights) {
            copyChallenge.addPiece(knight.createCopy());
        }
        for (HighTowerData highTower : highTowers) {
            copyChallenge.addPiece(highTower.createCopy());
        }
        copyChallenge.resetWalls();
        
        return copyChallenge;
    }

    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean blueKnights(){
        for( int i = 0; i < knights.size(); i++)
        {
            if ( !knights.get(i).isEnemy )
                return true;
        }
        return false;
    }


    public boolean redKnights(){
        for( int i = 0; i < knights.size(); i++)
        {
            if ( knights.get(i).isEnemy )
                return true;
        }
        return false;
    }

}
