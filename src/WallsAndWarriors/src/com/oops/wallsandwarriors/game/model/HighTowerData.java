package com.oops.wallsandwarriors.game.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class HighTowerData implements BoardPiece {

    public final Coordinate firstPosition;
    public final Coordinate secondPosition;
    
    public HighTowerData(Coordinate firstPosition, Coordinate secondPosition) {
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }
    
    @Override
    public List<Point2D.Double> occupies() {
        List<Point2D.Double> occupies = new ArrayList<Point2D.Double>();
        occupies.add(new Point2D.Double(
                (firstPosition.x + secondPosition.x) / 2.0,
                (firstPosition.y + secondPosition.y) / 2.0));
        return occupies;
    }
    
}
