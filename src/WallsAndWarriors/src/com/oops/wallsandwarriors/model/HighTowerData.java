package com.oops.wallsandwarriors.model;

import com.oops.wallsandwarriors.util.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to be used to define the structure of a high tower object. The information
 * is stored as two coordinates which define its position on the grid.
 * Implements BorderPiece, BlockPiece, Serializable
 * @author Emin Bahadir Tuluce
 */
public class HighTowerData implements BorderPiece, BlockPiece, Serializable {

    private Coordinate firstPosition;
    private Coordinate secondPosition;
    
    public transient boolean isVertical;

    /**
     * A constructor that initializes a HighTowerData with the given
     * Coordinates as first and second positions.
     * @param firstPosition  First Coordinate of the HighTower.
     * @param secondPosition  Second Coordinate of the HighTower.
     */
    public HighTowerData(Coordinate firstPosition, Coordinate secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }

    /**
     * A get method to return the first position of the HighTowerData.
     * @return first Position of the HighTowerData
     */
    public Coordinate getFirstPosition() {
        return firstPosition;
    }

    /**
     * A get method to return the second position of the HighTowerData.
     * @return second Position of the HighTowerData
     */
    public Coordinate getSecondPosition() {
        return secondPosition;
    }

    /**
     * A set method to modify the position of the HighTowerData on the grid.
     * @param firstPosition new firstPosition of the HighTower.
     * @param secondPosition new secondPosition of the HighTower.
     */
    public void setPosition(Coordinate firstPosition, Coordinate secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }

    /**
     * An overriden method to set the position of the HighTower on the grid as a GridPiece.
     * @param position Coordinate on the grid to set the position of the HighTower.
     */
    @Override
    public void setPosition(Coordinate position) {
        setPosition(position, null);
    }

    /**
     * An overriden method to create a copy of the HighTower as a GridPiece.
     * @return the copy of the HighTower.
     */
    @Override
    public HighTowerData createCopy() {
        return new HighTowerData(firstPosition, secondPosition);
    }

    /**
     * An overriden method to get the list of Points that a occupies a border
     * by the HighTower as a BorderPiece.
     * @return  list of Points that a occupies a border by the HighTower.
     */
    @Override
    public List<Point> occupiesBorder() {
        List<Point> occupies = new ArrayList<Point>(1);
        occupies.add(new Point(
                (firstPosition.x + secondPosition.x) / 2.0,
                (firstPosition.y + secondPosition.y) / 2.0));
        return occupies;
    }

    /**
     * An overriden method to get the list of Points that a occupies a block
     * by the HighTower as a BlockPiece.
     * @return  list of Points that a occupies a block by the HighTower.
     */
    @Override
    public List<Coordinate> occupiesBlock() {
        List<Coordinate> occupies = new ArrayList<Coordinate>(2);
        occupies.add(firstPosition);
        occupies.add(secondPosition);
        return occupies;
    }

    /**
     * An overriden method to rotate a HighTower as a GridPiece.
     */
    @Override
    public void rotate() {
        isVertical = !isVertical;
    }
    
}
