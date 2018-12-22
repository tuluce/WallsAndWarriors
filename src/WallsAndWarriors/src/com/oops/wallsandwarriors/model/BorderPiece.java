package com.oops.wallsandwarriors.model;

import com.oops.wallsandwarriors.util.Point;
import java.util.List;

/**
 * An interface to define a generic structure for objects occupying some borders of
 * the grid.
 * Extends GridPiece
 * @author OOPs
 * @version 21.12.19
 */
public interface BorderPiece extends GridPiece {

    /**
     * Method to get the list of Points that a occupies a border.
     * @return  list of Points that a occupies a border
     */
    public List<Point> occupiesBorder();
    
}
