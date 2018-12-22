package com.oops.wallsandwarriors.model;

/**
 * An interface to define a generic functionality on grid pieces.
 * @author OOPs
 * @version 21.12.19
 */
public interface GridPiece {

    /**
     * Method to create a copy of the GridPiece.
     * @return the copy of the GridPiece.
     */
    public GridPiece createCopy();

    /**
     * Method to rotate a GridPiece.
     */
    public void rotate();

    /**
     * Method to set the position of the GridPiece on the grid.
     * @param position Coordinate on the grid to set the position of the piece.
     */
    public void setPosition(Coordinate position);
    
}
