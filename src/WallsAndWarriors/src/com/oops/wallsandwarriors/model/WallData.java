package com.oops.wallsandwarriors.model;

import com.oops.wallsandwarriors.util.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to store the structure and the position of a wall as a game object. This
 * object has two attributes, definition in type WallDefinition and position in
 * type Coordinate. These two attributes define the shape of a particular wall as
 * described in the WallDefinition and its positioning on the grid.
 * Implements BorderPiece, Serializable
 * @author OOPs
 * @version 21.12.19
 */
public class WallData implements BorderPiece, Serializable {

    private static final int ROTATION = 4;

    private WallDefinition definition;
    private Coordinate position;

    /**
     * A constructor that initializes WallData with the given definition and position.
     * @param definition  Definition of the wall as a WallDefinition.
     * @param position Position of the wall as a Coordinate.
     */
    public WallData(WallDefinition definition, Coordinate position) {
        this.definition = definition;
        this.position = position;
    }

    /**
     * A constructor that initializes WallData with the given definition and with a null position.
     * @param definition  Definition of the wall as a WallDefinition.
     */
    public WallData(WallDefinition definition) {
        this(definition, null);
    }

    /**
     * A get method to return the definition of the WallData.
     * @return definition of the WallData
     */
    public WallDefinition getWallDefinition() {
        return definition;
    }

    /**
     * A get method to return the position of the WallData.
     * @return position of the WallData
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * An overriden method to set the position of the Wall on the grid as a GridPiece.
     * @param position Coordinate on the grid to set the position of the Wall.
     */
    @Override
    public void setPosition(Coordinate position) {
        this.position = position;
    }

    /**
     * A set method to modify the definition of the WallData.
     * @param definition new definition of the WallData
     */
    public void setWallDefinition(WallDefinition definition){
        this.definition = definition;
    }

    /**
     * An overriden method to create a copy of the WallData as a GridPiece.
     * @return the copy of the WallData.
     */
    @Override
    public WallData createCopy() {
        return new WallData(definition, position);
    }

    /**
     * An overriden method to rotate a Wall as a GridPiece. As a result of the rotation,
     * wall definition is modified.
     */
    public void rotate() {
        List<WallPortion> rotatedPortions = new ArrayList<WallPortion>();
        List<WallBastion> rotatedBastions = new ArrayList<WallBastion>();
        for (WallPortion portion : definition.portions) {
            rotatedPortions.add(new WallPortion(portion.firstRelativePos.rotate(),
                    portion.secondRelativePos.rotate()));
        }
        for (WallBastion bastion : definition.bastions) {
            double exactX = bastion.relativePos.x - 0.5;
            double exactY = bastion.relativePos.y - 0.5;
            Coordinate rotated = new Coordinate((int) (exactY + 0.5), (int) (-exactX + 0.5));
            rotatedBastions.add(new WallBastion(rotated));
        }
        WallDefinition rotatedDefinition = new WallDefinition(rotatedPortions, rotatedBastions);
        this.definition = rotatedDefinition;
    }

    /**
     * An overriden method to get the list of Points that a occupies a border
     * by the Wall as a BorderPiece.
     * @return  list of Points that a occupies a border by the Wall.
     */
    @Override
    public List<Point> occupiesBorder() {
        return occupies(position);
    }

    /**
     * A method to get the list of Points that a is occupied
     * by a Wall according to the position of the Wall.
     * @param position position of the Wall.
     * @return  list of Points that is occupied by the Wall.
     */
    public List<Point> occupies(Coordinate position) {
        List<Point> occupies = new ArrayList<Point>();
        if (position == null) {
            return occupies;
        }
        for (WallPortion portion : definition.portions) {
            Coordinate coord1 = position.plus(portion.firstRelativePos);
            Coordinate coord2 = position.plus(portion.secondRelativePos);
            double middleX = (coord1.x + coord2.x) / 2.0;
            double middleY = (coord1.y + coord2.y) / 2.0;
            if (coord1.y == coord2.y) {
                occupies.add(new Point(middleX, middleY - 1.0 / 6.0));
                occupies.add(new Point(middleX, middleY + 1.0 / 6.0));
            } else {
                occupies.add(new Point(middleX - 1.0 / 6.0, middleY));
                occupies.add(new Point(middleX + 1.0 / 6.0, middleY));
            }
        }
        for (WallBastion bastion : definition.bastions) {
            Coordinate coord = position.plus(bastion.relativePos);
            occupies.add(new Point(coord.x - 0.5, coord.y - 0.5));
        }
        return occupies;
    }

    /**
     * A method to check if the WallData is equal to the given WallData
     * regardless of the changes in the definition as a result of the rotations.
     * @param otherWall WallData to be compared.
     * @return  A boolean value that indicates whether the WallDatas are equal or not.
     */
    public boolean isPieceEqual( WallData otherWall )
    {
        WallData copyWall = otherWall.createCopy();
        for ( int i = 0; i < ROTATION; i++) {
            if( this.getWallDefinition().equals(copyWall.getWallDefinition()) )
                return true;
            copyWall.rotate();
        }
        return false;
    }
    
}
