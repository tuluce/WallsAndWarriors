package com.oops.wallsandwarriors.model;

import java.util.ArrayList;

/**
 * A class to store the marked Coordinates on the grid.
 * @author Ali Babayev
 */
public class MarkedCoordinates {
    public final ArrayList<Coordinate> markedCoordinates;

    /**
     * A default constructor that initializes a MarkedCoordinates with no given parameters
     * with an ArrayList of Coordinate objects.
     */
    public MarkedCoordinates() {
        markedCoordinates = new ArrayList<Coordinate>();
    }

    /**
     * Method to add a Coordinate as a MarkedCoordinate to the ArrayList of
     * MarkedCoordinates.
     * @param  newCoordinate Coordinate to be marked.
     */
    public void addMarkedCoordinate(Coordinate newCoordinate) {
        markedCoordinates.add(newCoordinate);
    }

    /**
     * Method to find a Coordinate in the MarkedCoordinates
     * to determine whether it is marked or not.
     * @param  coordinate Coordinate to be searched.
     * @return Result of the search as a boolean value.
     */
    public boolean findCoordinate(Coordinate coordinate) {
        for (int i = 0; i < markedCoordinates.size(); i++) {
            if (coordinate.equals(markedCoordinates.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to clear  marks on all coordinates.
     */
    public void clearMarks() {
        markedCoordinates.clear();
    }

}
