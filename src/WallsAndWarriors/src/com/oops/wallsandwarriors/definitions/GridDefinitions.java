package com.oops.wallsandwarriors.definitions;

import com.oops.wallsandwarriors.model.Coordinate;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to define the structure of the grid for different sizes.
 * @author Emin Bahadir Tuluce
 */
public class GridDefinitions {
    
    public static final List<Coordinate> SMALL;
    public static final List<Coordinate> MEDIUM;
    public static final List<Coordinate> LARGE;

    private static final int SMALL_ROWS = 4;
    private static final int SMALL_COLUMNS = 5;
    
    private static final int MEDIUM_ROWS = 5;
    private static final int MEDIUM_COLUMNS = 6;
    
    private static final int LARGE_ROWS = 6;
    private static final int LARGE_COLUMNS = 7;
    
    static {
        SMALL = new ArrayList<Coordinate>();
        fillGrid(SMALL, SMALL_ROWS, SMALL_COLUMNS);
        
        MEDIUM = new ArrayList<Coordinate>();
        fillGrid(MEDIUM, MEDIUM_ROWS, MEDIUM_COLUMNS);
        
        LARGE = new ArrayList<Coordinate>();
        fillGrid(LARGE, LARGE_ROWS, LARGE_COLUMNS);
    }
    
    private static void fillGrid(List<Coordinate> grid, int rows, int columns) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                if (! ((column == 0 || column == columns - 1) &&
                        (row == 0 || row == rows - 1))) {
                    grid.add(new Coordinate(column, row));
                }
            }
        }
    }
    
}
