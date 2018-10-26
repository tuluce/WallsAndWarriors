package com.oops.wallsandwarriors.game.data;

public class WallData {
    
    public final WallDefinition definition;
    private Coordinate position;
    
    public WallData(WallDefinition definition, Coordinate position) {
        this.definition = definition;
        this.position = position;
    }
    
    public WallData(WallDefinition definition) {
        this(definition, null);
    }
    
    public Coordinate getPosition() {
        return position;
    }
    
    public void setPosition(Coordinate position) {
        this.position = position;
    }
    
}
