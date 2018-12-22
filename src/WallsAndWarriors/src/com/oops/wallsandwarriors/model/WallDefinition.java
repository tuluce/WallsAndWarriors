/**
 * A class to be used to define the wall shapes. This class stores Lists of two
 * objects, one of WallBastion type and the other of WallPortion type to define
 * the wall shape.
 * Implements Serializable
 * @author OOPs
 * @version 21.12.19
 */
package com.oops.wallsandwarriors.model;

import java.io.Serializable;
import java.util.List;

public class WallDefinition implements Serializable{

    public final List<WallPortion> portions;
    public final List<WallBastion> bastions;

    /**
     * A constructor that initializes a WallDefinition with the given portions and bastions.
     * @param portions  The list of WallPortions for portions on the Wall.
     * @param bastions The list of WallBastions for bastions on the Wall.
     */
    public WallDefinition(List<WallPortion> portions, List<WallBastion> bastions) {
        this.portions = portions;
        this.bastions = bastions;
    }

    /**
     * An overriden equals method to check the equality of the WallDefinition objects.
     * Equality for WallDefinition objects require equality of both portions and bastions.
     * @param other Object to be compared, expected to be a WallDefinition.
     * @return A boolean value to indicate whether the objects are equal or not.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof WallDefinition) {
            WallDefinition otherWallDef = (WallDefinition) other;
            return ((otherWallDef.portions.equals(this.portions)) &&
                    (otherWallDef.bastions.equals(this.bastions)));
        }
        return false;
    }

}
