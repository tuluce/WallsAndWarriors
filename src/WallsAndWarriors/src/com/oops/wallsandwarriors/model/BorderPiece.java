/**
 * An interface to define a generic structure for objects occupying some borders of
 * the grid. 
 * Extends GridPiece
 * @author OOPs
 * @version 21.12.19
 */
package com.oops.wallsandwarriors.model;

import com.oops.wallsandwarriors.util.Point;
import java.util.List;

public interface BorderPiece extends GridPiece {
    
    public List<Point> occupiesBorder();
    
}
