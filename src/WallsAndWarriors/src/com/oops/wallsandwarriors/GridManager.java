package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.model.Coordinate;
import com.oops.wallsandwarriors.model.GridPiece;
import com.oops.wallsandwarriors.model.HighTowerData;
import com.oops.wallsandwarriors.model.KnightData;
import com.oops.wallsandwarriors.model.WallData;
import com.oops.wallsandwarriors.util.Point;
import java.awt.geom.Point2D;

import java.util.List;

public class GridManager {
    
    public boolean isPiecePlacable(Coordinate block, GridPiece piece) {
        if (piece instanceof WallData) {
            return isWallPlacable(block, (WallData) piece);
        } else if (piece instanceof KnightData) {
            return isKnightPlacable(block, (KnightData) piece);
        } else if (piece instanceof HighTowerData) {
            return isHighTowerPlacable(block, (HighTowerData) piece);
        }
        return false;
    }
    
    public boolean attemptPlacement(Coordinate block, GridPiece piece) {
        if (piece instanceof WallData) {
            return attemptWallPlacement(block, (WallData) piece);
        } else if (piece instanceof KnightData) {
            return attemptKnightPlacement(block, (KnightData) piece);
        } else if (piece instanceof HighTowerData) {
            return attemptHighTowerPlacement(block, (HighTowerData) piece);
        }
        return false;
    }
    
    private boolean isWallPlacable(Coordinate block, WallData wall) {
        List<Coordinate> blocks = Game.getInstance().challengeManager.getChallengeData().blocks;
        
        List<Point> candidatePoints = wall.occupies(block);
        List<Point> occupiedPoints = Game.getInstance().challengeManager.getChallengeData().getOccupiedBorderPoints();
        
        for (Point candidatePoint : candidatePoints) {
            boolean isInside = false;
            for (Coordinate gridCoordinate : blocks) {
                isInside = isInside || isInside(candidatePoint, gridCoordinate);
            }
            if (!isInside) {
                return false;
            }
            
            for (Point occupiedPoint : occupiedPoints) {
                if (pointsConflict(candidatePoint, occupiedPoint)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isKnightPlacable(Coordinate block, KnightData knight) {
        ChallengeData challenge = Game.getInstance().challengeManager.getChallengeData();
        return challenge.blocks.contains(block) &&
              !challenge.getOccupiedBlockPoints().contains(block);
    }
    
    private boolean isHighTowerPlacable(Coordinate block, HighTowerData highTower) {
        ChallengeData challenge = Game.getInstance().challengeManager.getChallengeData();
        Coordinate firstPosition = block;
        Coordinate difference = highTower.isVertical ? new Coordinate(0, 1) : new Coordinate(1, 0);
        Coordinate secondPosition = firstPosition.plus(difference);
        boolean blockSuitable = challenge.blocks.contains(firstPosition) &&
                challenge.blocks.contains(secondPosition) &&
               !challenge.getOccupiedBlockPoints().contains(firstPosition) &&
               !challenge.getOccupiedBlockPoints().contains(secondPosition);
        
        boolean borderSuitable = true;
        Point candidatePoint = new Point(
                (firstPosition.x + secondPosition.x) / 2.0,
                (firstPosition.y + secondPosition.y) / 2.0);
        List<Point> occupiedPoints = challenge.getOccupiedBorderPoints();
        for (Point occupiedPoint : occupiedPoints) {
            if (pointsConflict(candidatePoint, occupiedPoint)) {
                borderSuitable = false;
            }
        }
        return blockSuitable && borderSuitable;
    }
    
    private boolean attemptWallPlacement(Coordinate block, WallData wall) {
        if (isWallPlacable(block, wall)) {
            wall.setPosition(block);
            return true;
        }
        return false;
    }
    
    private boolean attemptKnightPlacement(Coordinate block, KnightData knight) {
        if (isKnightPlacable(block, knight)) {
            knight.setPosition(block);
            return true;
        }
        return false;
    }
    
    private boolean attemptHighTowerPlacement(Coordinate block, HighTowerData highTower) {
        if (isHighTowerPlacable(block, highTower)) {
            Coordinate firstPosition = block;
            Coordinate difference = highTower.isVertical ? new Coordinate(0, 1) : new Coordinate(1, 0);
            Coordinate secondPosition = firstPosition.plus(difference);
            highTower.setPosition(firstPosition, secondPosition);
            return true;
        }
        return false;
    }
    
    private boolean isInside(Point point, Coordinate gridCoordinate) {
        return (gridCoordinate.x - 0.5 <= point.x && point.x <= gridCoordinate.x + 0.5
             && gridCoordinate.y - 0.5 <= point.y && point.y <= gridCoordinate.y + 0.5);
    }
    
    private boolean pointsConflict(Point point1, Point point2) {
        return Point2D.distance(point1.x, point1.y, point2.x, point2.y) < (0.35);
    }
    
}
