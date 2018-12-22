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

/**
 * A class to manage grid settings of the game
 * @author Emin Bahadir Tuluce
 */
public class GridManager {

    /**
     * A method to check whether piece is placeable or not
     * @return true if piece is placeable
     */
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

    /**
     * A method to attempt to place piece
     * @return true if placement performed
     */
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

    /**
     * A method to check whether the wall is placeable or not
     * @return true if wall is placeable
     */
    private boolean isWallPlacable(Coordinate block, WallData wall) {
        List<Coordinate> blocks = Game.getInstance().challengeManager.
                getChallengeData().blocks;
        
        List<Point> candidatePoints = wall.occupies(block);
        List<Point> occupiedPoints = Game.getInstance().challengeManager.
                getChallengeData().getOccupiedBorderPoints();
        
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

    /**
     * A method to check whether the knight is placeable or not
     * @return true if knight is placeable
     */
    private boolean isKnightPlacable(Coordinate block, KnightData knight) {
        ChallengeData challenge = Game.getInstance().challengeManager.getChallengeData();
        return challenge.blocks.contains(block) &&
              !challenge.getOccupiedBlockPoints().contains(block);
    }

    /**
     * A method to check whether the high tower is placeable or not
     * @return true if the high tower is placeable
     */
    private boolean isHighTowerPlacable(Coordinate block, HighTowerData highTower) {
        ChallengeData challenge = Game.getInstance().challengeManager.getChallengeData();
        Coordinate firstPosition = block;
        Coordinate difference = highTower.isVertical ?
                new Coordinate(0, 1) : new Coordinate(1, 0);
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

    /**
     * A method to attempt to place a wall
     * @return true placement performed
     */
    private boolean attemptWallPlacement(Coordinate block, WallData wall) {
        if (isWallPlacable(block, wall)) {
            wall.setPosition(block);
            return true;
        }
        return false;
    }

    /**
     * A method to attempt to place a knight
     * @return true placement performed
     */
    private boolean attemptKnightPlacement(Coordinate block, KnightData knight) {
        if (isKnightPlacable(block, knight)) {
            knight.setPosition(block);
            return true;
        }
        return false;
    }

    /**
     * A method to attempt to place a high tower
     * @return true placement performed
     */
    private boolean attemptHighTowerPlacement(Coordinate block, HighTowerData highTower) {
        if (isHighTowerPlacable(block, highTower)) {
            Coordinate firstPosition = block;
            Coordinate difference = highTower.isVertical ?
                    new Coordinate(0, 1) : new Coordinate(1, 0);
            Coordinate secondPosition = firstPosition.plus(difference);
            highTower.setPosition(firstPosition, secondPosition);
            return true;
        }
        return false;
    }

    /**
     * A method to check whether the piece is inside of the grid borders or not
     * @return true if piece is inside
     */
    private boolean isInside(Point point, Coordinate gridCoordinate) {
        return (gridCoordinate.x - 0.5 <= point.x && point.x <= gridCoordinate.x + 0.5
             && gridCoordinate.y - 0.5 <= point.y && point.y <= gridCoordinate.y + 0.5);
    }

    /**
     * A method to check whether the points of the pieces in conflict or not
     * @return true if there is no conflict
     */
    private boolean pointsConflict(Point point1, Point point2) {
        return Point2D.distance(point1.x, point1.y, point2.x, point2.y) < (0.35);
    }
    
}
