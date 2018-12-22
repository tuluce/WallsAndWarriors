/**An interface to define a generic structure for objects occupying some blocks of the grid.
 * Extends GridPiece
 * @author OOPs
 * @version 21.12.19
 */

package com.oops.wallsandwarriors.model;

import java.util.List;

public interface BlockPiece extends GridPiece {
    
    public List<Coordinate> occupiesBlock();
    
}
