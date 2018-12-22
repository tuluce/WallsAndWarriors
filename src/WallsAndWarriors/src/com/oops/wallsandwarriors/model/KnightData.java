/**
 * A class to be used to define the structure of a knight object. The information is
 * stored as a coordinate to define its position on the grid.
 * Implements BlockPiece, Serializable
 * @author OOPs
 * @version 21.12.19
 */

package com.oops.wallsandwarriors.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class KnightData implements BlockPiece,Serializable {

    public final boolean isEnemy;
    private Coordinate position;

    /**
     * A constructor that initializes a KnightData with the given
     * Coordinate as position on the grid and type of the KnightData
     * to indicate if it is an enemy KnightData or not by a boolean
     * value.
     * @param firstPosition  First Coordinate of the KnightData.
     * @param isEnemy  type of the KnightData.
     */
    public KnightData(Coordinate firstPosition, boolean isEnemy) {
        this.position = firstPosition;
        this.isEnemy = isEnemy;
    }

    /**
     * A get method to return the position of the KnightData.
     * @return Position of the KnightData on the grid.
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * An overriden method to set the position of the Knight on the grid as a GridPiece.
     * @param position Coordinate on the grid to set the position of the Knight.
     */
    @Override
    public void setPosition(Coordinate position) {
        this.position = position;
    }

    /**
     * An overriden method to create a copy of the KnightData as a GridPiece.
     * @return the copy of the KnightData.
     */
    @Override
    public KnightData createCopy() {
        return new KnightData(position, isEnemy);
    }

    /**
     * An overriden method to get the list of Points that a occupies a block
     * by the Knight as a BlockPiece.
     * @return  list of Points that a occupies a block by the Knight.
     */
    @Override
    public List<Coordinate> occupiesBlock() {
        List<Coordinate> occupies = new ArrayList<Coordinate>(1);
        occupies.add(position);
        return occupies;
    }

    /**
     * An overriden method to rotate a Knight as a GridPiece.
     * Since Knights are represented by a Single coordinate,
     * rotation is not functional
     */
    @Override
    public void rotate() { }
    
}
