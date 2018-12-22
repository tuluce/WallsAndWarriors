package com.oops.wallsandwarriors.model;

import java.util.List;

/**
 * An interface to define a generic structure for objects occupying some blocks of the grid.
 * Extends GridPiece
 * @author OOPs
 * @version 21.12.19
 */
public interface BlockPiece extends GridPiece {

    /**
     * Method to get the list of Points that a occupies a block.
     * @return  list of Points that a occupies a block.
     */
    public List<Coordinate> occupiesBlock();
    
}
