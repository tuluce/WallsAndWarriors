/**
 * A class  used to define and store the positions of game objects on the grid.
 * Implements Serializable
 * @author OOPs
 * @version 21.12.19
 */

package com.oops.wallsandwarriors.model;

import java.io.Serializable;

public class Coordinate implements Serializable{
    
    public final int x;
    public final int y;
    
    public static final Coordinate ZERO = new Coordinate(0, 0);

    /**
     * A constructor that initializes a Coordinate with the x and y values as screen coordinates.
     * @param x  x coordinate on the screen.
     * @param y y coordinate on the screen.
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * An overriden equals method to check the equality of the Coordinate objects.
     * Equality for Coordinate objects require equality of both x and y values.
     * @param other Coordinate to be compared.
     * @return A boolean value to indicate whether the objects are equal or not.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Coordinate) {
            Coordinate otherCoordinate = (Coordinate) other;
            return (otherCoordinate.x == this.x) &&
                   (otherCoordinate.y == this.y);
        }
        return false;
    }

    /**
     * An overriden hashCode method to generate a hashCode
     * for the Object according to x and y values.
     * @return An integer value as the hash code of the Coordinate object.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        return hash;
    }

    /**
     * A method to shift Coordinates by values in a given Coordinate.
     * In other words, a method to sum two Coordinates.
     * @param other Coordinate given to add to the existing Coordinate.
     * @return new Coordinate as a result of the summation.
     */
    public Coordinate plus(Coordinate other) {
        return new Coordinate(other.x + x,
                other.y + y);
    }

    /**
     * A method to rotate Coordinates by replacing x value with y and vice-versa.
     * @return new Coordinate as a result of the rotation.
     */
    public Coordinate rotate() {
        return new Coordinate(this.y, -this.x);
    }
    
}
