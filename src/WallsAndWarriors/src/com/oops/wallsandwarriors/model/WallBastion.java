package com.oops.wallsandwarriors.model;

import java.io.Serializable;

/**
 * A class to be used to define the bastions of a wall. In this class, relativePos
 * attribute of type Coordinate defines the relative positioning of the bastion with
 * respect to the wall origin.
 * Implements Serializable.
 * @author OOPs
 */
public class WallBastion implements Serializable {
    
    // The wall bastion lies on top-left corner of the relative block position
    public final Coordinate relativePos;

    /**
     * A constructor that initializes a WallBastion with the Coordinate as
     * its relative position on the wall.
     * @param relativePos  The Coordinate of the bastion relatively
     *                     on the wall.
     */
    public WallBastion(Coordinate relativePos) {
        this.relativePos = relativePos;
    }

    /**
     * An overriden equals method to check the equality of the WallBastion objects.
     * Equality for WallBastion objects require equality of relativePosition values.
     * @param other Object to be compared, expected to be a WallBastion.
     * @return A boolean value to indicate whether the objects are equal or not.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof WallBastion) {
            WallBastion otherBastion = (WallBastion) other;
            return (otherBastion.relativePos.equals(this.relativePos));
        }
        return false;
    }
    
}
