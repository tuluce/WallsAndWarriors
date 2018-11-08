package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.game.model.WallData;
import java.awt.geom.Point2D;
import java.util.List;

public class GridManager {
    
    public boolean isWallPlacable(Coordinate block, WallData wall) {
        List<Coordinate> blocks = Game.getInstance().getChallengeManager().getChallengeData().blocks;
        List<WallData> walls = Game.getInstance().getChallengeManager().getChallengeData().walls;
        
        List<Point2D.Double> candidatePoints = wall.occupies(block);
        List<Point2D.Double> occupiedPoints = Game.getInstance()
                .getChallengeManager().getChallengeData().getOccupiedPoints();
        
        for (Point2D.Double candidatePoint : candidatePoints) {
            boolean isInside = false;
            for (Coordinate gridCoordinate : blocks) {
                isInside = isInside || isInside(candidatePoint, gridCoordinate);
            }
            if (!isInside) {
                return false;
            }
            
            for (Point2D.Double occupiedPoint : occupiedPoints) {
                if (pointsConflict(candidatePoint, occupiedPoint)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean attemptWallPlacement(Coordinate block, WallData wall) {
        if (isWallPlacable(block, wall)) {
            List<WallData> walls = Game.getInstance().getChallengeManager().getChallengeData().walls;
            wall.setPosition(block);
            return true;
        }
        return false;
    }
    
    private boolean isInside(Point2D.Double point, Coordinate gridCoordinate) {
        return (gridCoordinate.x - 0.5 <= point.x && point.x <= gridCoordinate.x + 0.5
             && gridCoordinate.y - 0.5 <= point.y && point.y <= gridCoordinate.y + 0.5);
    }
    
    private boolean pointsConflict(Point2D.Double point1, Point2D.Double point2) {
        return Point2D.distance(point1.x, point1.y, point2.x, point2.y) < (0.35);
    }
    
}
