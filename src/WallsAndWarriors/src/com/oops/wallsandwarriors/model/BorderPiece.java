package com.oops.wallsandwarriors.model;

import com.oops.wallsandwarriors.util.Point;
import java.util.List;

public interface BorderPiece extends GridPiece {
    
    public List<Point> occupiesBorder();
    
}
