package com.oops.wallsandwarriors.game.model;

public class Coordinate {
    
    public final int x;
    public final int y;
    
    public static final Coordinate ZERO = new Coordinate(0, 0);
    
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
    
    public Coordinate plus(Coordinate other) {
        return new Coordinate(other.x + x, other.y + y);
    }
    
    public Coordinate rotate() {
        return new Coordinate(this.y, -this.x);
    }
    
}
