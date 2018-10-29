package com.oops.wallsandwarriors.definitions;

import com.oops.wallsandwarriors.game.data.Coordinate;
import java.util.ArrayList;
import java.util.List;

public class GridDefinitions {
    
    public static final List<Coordinate> SMALL;
    
    static {
        
        SMALL = new ArrayList<Coordinate>();
        for (int column = 0; column < 5; column++) {
            for (int row = 0; row < 4; row++) {
                if (! ((column == 0 || column == 4) && (row == 0 || row == 3))) {
                    SMALL.add(new Coordinate(column, row));
                }
            }
        }
        
    }
    
}
