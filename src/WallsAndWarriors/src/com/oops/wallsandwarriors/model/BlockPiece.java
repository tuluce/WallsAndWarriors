package com.oops.wallsandwarriors.model;

import java.util.List;

public interface BlockPiece extends GridPiece {
    
    public List<Coordinate> occupiesBlock();
    
}
