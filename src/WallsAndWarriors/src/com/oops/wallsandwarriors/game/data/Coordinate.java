package com.oops.wallsandwarriors.game.data;

public class Coordinate {
    
    public final int x;
    public final int y;
    
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Coordinate) {
            Coordinate otherCoordinate = (Coordinate) other;
            return (otherCoordinate.x == this.x) &&
                   (otherCoordinate.y == this.y);
        }
        return false;
    }
    
}
