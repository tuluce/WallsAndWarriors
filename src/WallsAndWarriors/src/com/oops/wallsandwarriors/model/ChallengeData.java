package com.oops.wallsandwarriors.model;

import com.oops.wallsandwarriors.definitions.GridDefinitions;
import com.oops.wallsandwarriors.definitions.WallDefinitions;
import com.oops.wallsandwarriors.util.AlgorithmUtils;
import com.oops.wallsandwarriors.util.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to model all different Challenge objects in the game and keeps the
 * regarding information.
 * Implements Serializable
 * @author OOPs
 */
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

    /**
     * A constructor that initializes a ChallengeData with the given blocks and walls.
     * @param blocks  The list of Coordinates for blocks in the challenge.
     * @param walls The list of WallData for walls in the challenge.
     */
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
    }

    /**
     * A default constructor that initializes a ChallengeData with no given parameters
     * with a SMALL grid and STANDARD walls .
     */
    public ChallengeData() {
        this(GridDefinitions.SMALL, WallDefinitions.STANDARD);
    }

    /**
     * Method to add any given grid piece to the ChallengeData.
     * @param  piece  Piece of any kind to be added.
     */
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

    /**
     * Method to remove any grid piece from the ChallengeData.
     * @param  piece  Piece of any kind to be added.
     */
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

    /**
     * Method to reset the positions of the walls in the ChallengeData.
     */
    public void resetWalls() {
        for (WallData wall : walls) {
            wall.setPosition(null);
        }
    }

    /**
     * Method to reset the ChallengeData completely.
     */
    public void resetAll() {
        knights.clear();
        highTowers.clear();
        walls.clear();
        borderPieces.clear();
        blockPieces.clear();
    }
    
    /**
     * Method to get the border points which are occupied currently in the Challenge.
     * @return List of occupied border Points.
     */
    public List<Point> getOccupiedBorderPoints() {
        List<Point> occupiedList = new ArrayList<Point>();
        for (BorderPiece borderPiece : borderPieces) {
            occupiedList.addAll(borderPiece.occupiesBorder());
        }
        return occupiedList;
    }

    /**
     * Method to get the block points which are occupied currently in the Challenge.
     * @return List of occupied block Points.
     */
    public List<Coordinate> getOccupiedBlockPoints() {
        List<Coordinate> occupiedList = new ArrayList<Coordinate>();
        for (BlockPiece blockPiece : blockPieces) {
            occupiedList.addAll(blockPiece.occupiesBlock());
        }
        return occupiedList;
    }

    /**
     * Method to create a copy of the challenge.
     * @param reset A parameter to indicate whether the walls in the copied
     * challenge will be resetted (have null positions).
     * @return copy of the given ChallengeData.
     */
    public ChallengeData createCopy(boolean reset)  {
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
        if (reset) {
            copyChallenge.resetWalls();
        }

        return copyChallenge;
    }

    /**
     * A get method to return the name of the ChallengeData.
     * @return name of the ChallengeData
     */
    public String getName() {
        return name;
    }

    /**
     * A get method to return the description of the ChallengeData.
     * @return description of the ChallengeData
     */
    public String getDescription() {
        return description;
    }

    /**
     * A get method to return the creator of the ChallengeData.
     * @return creator of the ChallengeData
     */
    public String getCreator() {
        return creator;
    }

    /**
     * A set method to modify the name of the ChallengeData.
     * @param name Name to be assigned to the ChallengeData.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A set method to modify the description of the ChallengeData.
     * @param description Description to be assigned to the ChallengeData.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * A set method to modify the creator of the ChallengeData.
     * @param creator Creator to be assigned to the ChallengeData.
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * A method to check if the ChallengeData contains Blue knights or not.
     * @return A boolean value indicating if the ChallengeData contains Blue knights or not.
     */

    public boolean hasBlueKnights(){
        if(0<noOfBlueKnights())
            return true;
        return false;
    }

    public int noOfBlueKnights(){
        int count = 0;

        for( int i = 0; i < knights.size(); i++)
        {
            if ( !knights.get(i).isEnemy )
                count++;
        }
        return count;
    }

    public boolean isWild(){

        int blueKnightsSize = noOfBlueKnights();
        
        //Checking number of Knights -- In Std: 3 Red Knigthts, 4 Blue Knight, 1 High Tower
        if(3 < blueKnightsSize)
            return true;
        else if (4 < knights.size() - blueKnightsSize)
            return true;
        else if (1 < highTowers.size())
            return true;

        //Checking if there are more than two instances of the same wall
        for (int i = 0; i < walls.size(); i++)
        {
            for (int j =  i+1; j < walls.size(); j++) {
                if (walls.get(i).isPieceEqual(walls.get(j))) {
                    return true;
                }
            }
        }
        return false ;
    }

    /**
     * A method to return the type of the ChallengeData as Wild or
     * Standard according to the definitions.
     * @return String representation of the type of the Challenge.
     */
    public String getType() {
        if (isWild())
            return "Wild";
        else
            return "Standard";
    }

}
