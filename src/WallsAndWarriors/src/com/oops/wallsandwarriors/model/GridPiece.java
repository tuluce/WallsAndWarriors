package com.oops.wallsandwarriors.model;

public interface GridPiece {
    
    public GridPiece createCopy();
    public void rotate();
    public void setPosition(Coordinate position);
    
}
