/**
 * This class will be used to define the portions of a wall. In this class, relativePos1
 * and relativePos2 attributes of type Coordinate define the relative positioning
 * of the wall portion. The wall lies between these two given coordinates. * Implements Serializable
 * @author OOPs
 * @version 21.12.19
 */
package com.oops.wallsandwarriors.model;

import java.io.Serializable;
import java.util.Objects;

public class WallPortion implements Serializable {
    
    // The wall portion lies between two relative block positions
    public final Coordinate firstRelativePos;
    public final Coordinate secondRelativePos;

    /**
     * A constructor that initializes a WallPortion with the firstRelativePosition
     * and secondRelativePosition Coordinates as relative positions of the
     * portions on the Wall.
     * @param firstRelativePos  first position of the portion on the wall.
     * @param secondRelativePos second position of the portion on the wall.
     */
    public WallPortion(Coordinate firstRelativePos, Coordinate secondRelativePos) {
        this.firstRelativePos = firstRelativePos;
        this.secondRelativePos = secondRelativePos;
    }

    /**
     * An overriden equals method to check the equality of the WallPortion objects.
     * Equality for WallPortion objects require equality of each
     * firstRelativePos and secondRelativePos Coordinates with each other or
     * equality of both firstRelativePos and secondRelativePos Coordinates.
     * @param other Object to be compared, expected to be a WallPortion.
     * @return A boolean value to indicate whether the objects are equal or not.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof WallPortion) {
            WallPortion otherWall = (WallPortion) other;
            return (otherWall.firstRelativePos.equals(this.firstRelativePos) &&
                    otherWall.secondRelativePos.equals(this.secondRelativePos)) ||
                   (otherWall.firstRelativePos.equals(this.secondRelativePos) &&
                    otherWall.secondRelativePos.equals(this.firstRelativePos));
        }
        return false;
    }

    /**
     * An overriden hashCode method to generate a hashCode
     * for the Object according to firstRelativePos and secondRelativePos Coordinates.
     * @return An integer value as the hash code of the WallPortion object.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.firstRelativePos);
        hash = 97 * hash + Objects.hashCode(this.secondRelativePos);
        return hash;
    }
    
}
