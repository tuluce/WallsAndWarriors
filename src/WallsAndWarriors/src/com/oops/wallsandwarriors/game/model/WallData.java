package com.oops.wallsandwarriors.game.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class WallData implements BoardPiece {
    
    private WallDefinition definition;
    private Coordinate position;
    
    public WallData(WallDefinition definition, Coordinate position) {
        this.definition = definition;
        this.position = position;
    }
    
    public WallData(WallDefinition definition) {
        this(definition, null);
    }
    
    public WallDefinition getWallDefinition() {
        return definition;
    }
    
    public Coordinate getPosition() {
        return position;
    }
    
    public void setPosition(Coordinate position) {
        this.position = position;
    }
    
    public void rotate() {
        List<WallPortion> rotatedPortions = new ArrayList<WallPortion>();
        List<WallBastion> rotatedBastions = new ArrayList<WallBastion>();
        for (WallPortion portion : definition.portions) {
            rotatedPortions.add(new WallPortion(portion.firstRelativePos.rotate(),
                    portion.secondRelativePos.rotate()));
        }
        for (WallBastion bastion : definition.bastions) {
            double exactX = bastion.relativePos.x - 0.5;
            double exactY = bastion.relativePos.y - 0.5;
            Coordinate rotated = new Coordinate((int) (exactY + 0.5), (int) (-exactX + 0.5));
            rotatedBastions.add(new WallBastion(rotated));
        }
        WallDefinition rotatedDefinition = new WallDefinition(rotatedPortions, rotatedBastions);
        this.definition = rotatedDefinition;
    }
    
    @Override
    public List<Point2D.Double> occupies() {
        return occupies(position);
    }
    
    public List<Point2D.Double> occupies(Coordinate position) {
        List<Point2D.Double> occupies = new ArrayList<Point2D.Double>();
        if (position == null) {
            return occupies;
        }
        for (WallPortion portion : definition.portions) {
            Coordinate coord1 = position.plus(portion.firstRelativePos);
            Coordinate coord2 = position.plus(portion.secondRelativePos);
            double middleX = (coord1.x + coord2.x) / 2.0;
            double middleY = (coord1.y + coord2.y) / 2.0;
            if (coord1.y == coord2.y) {
                occupies.add(new Point2D.Double(
                    middleX,
                    middleY - 1.0 / 6.0));
                occupies.add(new Point2D.Double(
                    middleX,
                    middleY + 1.0 / 6.0));
            } else {
                occupies.add(new Point2D.Double(
                    middleX - 1.0 / 6.0,
                    middleY));
                occupies.add(new Point2D.Double(
                    middleX + 1.0 / 6.0,
                    middleY));
            }
        }
        for (WallBastion bastion : definition.bastions) {
            Coordinate coord = position.plus(bastion.relativePos);
            occupies.add(new Point2D.Double(coord.x - 0.5, coord.y - 0.5));
        }
        return occupies;
    }
    
}
