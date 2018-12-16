package com.oops.wallsandwarriors.model;

import com.oops.wallsandwarriors.util.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HighTowerData implements BorderPiece, BlockPiece, Serializable {

    private Coordinate firstPosition;
    private Coordinate secondPosition;
    
    public transient boolean isVertical;
    
    public HighTowerData(Coordinate firstPosition, Coordinate secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }
    
    public Coordinate getFirstPosition() {
        return firstPosition;
    }
    
    public Coordinate getSecondPosition() {
        return secondPosition;
    }
    
    public void setPosition(Coordinate firstPosition, Coordinate secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }
    
    @Override
    public void setPosition(Coordinate position) {
        setPosition(position, null);
    }
    
    @Override
    public HighTowerData createCopy() {
        return new HighTowerData(firstPosition, secondPosition);
    }
    
    @Override
    public List<Point> occupiesBorder() {
        List<Point> occupies = new ArrayList<Point>(1);
        occupies.add(new Point(
                (firstPosition.x + secondPosition.x) / 2.0,
                (firstPosition.y + secondPosition.y) / 2.0));
        return occupies;
    }

    @Override
    public List<Coordinate> occupiesBlock() {
        List<Coordinate> occupies = new ArrayList<Coordinate>(2);
        occupies.add(firstPosition);
        occupies.add(secondPosition);
        return occupies;
    }

    @Override
    public void rotate() {
        isVertical = !isVertical;
    }
    
}
