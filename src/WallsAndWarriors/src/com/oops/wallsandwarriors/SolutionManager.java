package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.model.WallData;
import com.oops.wallsandwarriors.model.WallPortion;
import com.oops.wallsandwarriors.model.MarkedCoordinates;
import com.oops.wallsandwarriors.model.Coordinate;
import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.model.KnightData;
import java.util.ArrayList;

/**
 * A class for checking a ChallengeData’s correctly solved/unsolved status.
 * @author Ali Babayev
 */
public class SolutionManager {

    private final MarkedCoordinates markedCoordinatelist;
    private final Coordinate leftCoordinate = new Coordinate(-1,0);
    private final Coordinate rightCoordinate = new Coordinate(1,0);
    private final Coordinate upperCoordinate = new Coordinate(0,-1);
    private final Coordinate downCordinate = new Coordinate(0,1);
    private final ArrayList<KnightData> IncorrectRedKnightList;

    private ChallengeData challengeData;


    /**
     * A default constructor that initializes lists of marked coordinates and incorrect enemy knights
     */
    public SolutionManager() {
        markedCoordinatelist = new MarkedCoordinates();
        IncorrectRedKnightList = new ArrayList<KnightData>();
    }

    /**
     * A method to check whether solution of the user to the particular challenge is correct
     * @param newChallengeData ChallengeData drew on the canvas.
     * @return null if walls are not in their correct places on the grid or list of red(enemy) knights that are marked
     */
    public ArrayList<KnightData> checkSolution(ChallengeData newChallengeData) {

        reset();
        challengeData = newChallengeData;

        //if the knight is BLUE, then check for Coordinates to mark
        for( int i = 0; i < challengeData.knights.size(); i++){
            if( !(challengeData.knights.get(i).isEnemy)){
                markMapCoordinates(challengeData.knights.get(i).getPosition());
            }
        }

        //mark high towers
        for(int i = 0; i < challengeData.highTowers.size(); i++){
            markMapCoordinates(challengeData.highTowers.get(i).getFirstPosition());
            markMapCoordinates(challengeData.highTowers.get(i).getSecondPosition());
        }

        //check whether RED knights are marked, if there is, then add them to the list(IncorrectRedKnightList)
        for(KnightData redKnight : challengeData.knights) {
            if (redKnight.isEnemy) {
                if(markedCoordinatelist.findCoordinate(redKnight.getPosition())) {
                    IncorrectRedKnightList.add(redKnight);
                }
            }
        }
        boolean areWallsClosed = true;
        //Check whether walls are closed if none of RED knights' coordinates marked
        if(IncorrectRedKnightList.size() == 0){
            for(Coordinate aCoordinate : challengeData.blocks) {
                //checking whether marked coordinates are on the edge without a cover wall
                if (markedCoordinatelist.findCoordinate(aCoordinate) && areWallsClosed) {
                    Coordinate newTempCoordinate;
                    WallPortion newPortion;
                    for(int i = 0; i < 4 && areWallsClosed; i++) {
                        if(i == 0)
                            newTempCoordinate = aCoordinate.plus(leftCoordinate);
                        else if(i == 1)
                            newTempCoordinate = aCoordinate.plus(rightCoordinate);
                        else if( i == 2)
                            newTempCoordinate = aCoordinate.plus(upperCoordinate);
                        else
                            newTempCoordinate = aCoordinate.plus(downCordinate);
                        if(! (isProperCoordinate(newTempCoordinate))) {
                            boolean isThereWallonBorder = false;
                            for (WallData wallData : challengeData.walls) {
                                if (wallData.getPosition() != null && areWallsClosed) {
                                    Coordinate tempWallCoordinate = new Coordinate(aCoordinate.x - wallData.getPosition().x, aCoordinate.y - wallData.getPosition().y);
                                    newPortion = new WallPortion(tempWallCoordinate, (new Coordinate(newTempCoordinate.x - wallData.getPosition().x,newTempCoordinate .y - wallData.getPosition().y)));
                                    for (int j = 0; j < wallData.getWallDefinition().portions.size(); j++) {
                                        WallPortion portion = wallData.getWallDefinition().portions.get(j);
                                        if (newPortion.equals(portion))
                                            isThereWallonBorder = true;
                                    }
                                }
                            }
                            if(!(isThereWallonBorder))
                                areWallsClosed = false;
                        }
                    }
                }
            }
        }

        if(areWallsClosed)
            return IncorrectRedKnightList;
        return null;
    }

    /**
     * A method to mark the given coordinate
     * @param coordinate coordinate to get marked
     */
    private void markMapCoordinates(Coordinate coordinate) {
        if( !(markedCoordinatelist.findCoordinate(coordinate)) && isProperCoordinate(coordinate)) {
            markedCoordinatelist.addMarkedCoordinate(coordinate);

            boolean isThereWallLeft = false;
            boolean isThereWallRight = false;
            boolean isThereWallUpper = false;
            boolean isThereWallDown = false;

            for (WallData wallData : challengeData.walls) {
                if (wallData.getPosition() != null) {
                    Coordinate tempCoordinate = new Coordinate(coordinate.x - wallData.getPosition().x, coordinate.y - wallData.getPosition().y);
                    WallPortion newPortionLeft = new WallPortion(tempCoordinate, tempCoordinate.plus(leftCoordinate));
                    WallPortion newPortionRight = new WallPortion(tempCoordinate, tempCoordinate.plus(rightCoordinate));
                    WallPortion newPortionUpper = new WallPortion(tempCoordinate, tempCoordinate.plus(upperCoordinate));
                    WallPortion newPortionDown = new WallPortion(tempCoordinate, tempCoordinate.plus(downCordinate));
                    for (int i = 0; i < wallData.getWallDefinition().portions.size(); i++) {
                        WallPortion portion = wallData.getWallDefinition().portions.get(i);
                        if (newPortionLeft.equals(portion))
                            isThereWallLeft = true;
                        else if (newPortionRight.equals(portion))
                            isThereWallRight = true;
                        else if (newPortionUpper.equals(portion))
                            isThereWallUpper = true;
                        else if (newPortionDown.equals(portion))
                            isThereWallDown = true;
                    }
                }
            }
            if(!(isThereWallLeft))
                markMapCoordinates(coordinate.plus(leftCoordinate));
            if(!(isThereWallRight))
                markMapCoordinates(coordinate.plus(rightCoordinate));
            if(!(isThereWallUpper))
                markMapCoordinates(coordinate.plus(upperCoordinate));
            if(!(isThereWallDown))
                markMapCoordinates(coordinate.plus(downCordinate));
        }
    }

    /**
     * A method to check whether given coordinate is belong to the grid
     * @param coordinate coordinate to check its belonging
     * @return boolean value representing belonging status of the given coordinate
     */
    private boolean isProperCoordinate(Coordinate coordinate) {
        for(Coordinate aCoordinate : challengeData.blocks) {
            if(coordinate.equals(aCoordinate))
                return true;
        }
        return false;
    }

    /**
     * A method to reset both coordinates marked and red(enemy) knights listed as incorrect placed
     */
    private void reset() {
        IncorrectRedKnightList.clear();
        markedCoordinatelist.clearMarks();
    }
}
