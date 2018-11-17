package com.oops.wallsandwarriors.model;

import java.util.ArrayList;

public class MarkedCoordinates {
    public final ArrayList<Coordinate> markedCoordinates;

    public MarkedCoordinates() {
        markedCoordinates = new ArrayList<Coordinate>();
    }

    public void addMarkedCoordinate(Coordinate newCoordinate) {
        markedCoordinates.add(newCoordinate);
    }

    public boolean findCoordinate(Coordinate coordinate) {
        for(int i = 0; i < markedCoordinates.size(); i++){
            if(coordinate.equals(markedCoordinates.get(i))){
                return true;
            }
        }
        return false;
    }

    public void clearMarks() {
        markedCoordinates.clear();
    }

}
