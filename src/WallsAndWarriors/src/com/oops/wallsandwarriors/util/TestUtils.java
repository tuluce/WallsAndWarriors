package com.oops.wallsandwarriors.util;

import com.oops.wallsandwarriors.definitions.GridDefinitions;
import com.oops.wallsandwarriors.game.model.ChallengeData;
import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.definitions.WallDefinitions;
import com.oops.wallsandwarriors.game.model.HighTowerData;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

//TODO there is no locked/unlocked property for ChallengeData

public class TestUtils {

    public static final ChallengeData CHALLENGE_45;
    public static final ChallengeData CHALLENGE_51;
    
    static {
        
        List<Coordinate> castleKnights45 = new ArrayList<Coordinate>();
        castleKnights45.add(new Coordinate(1, 0));
        castleKnights45.add(new Coordinate(3, 1));
        castleKnights45.add(new Coordinate(0, 2));
        List<HighTowerData> highTowers45 = new ArrayList<HighTowerData>();
        highTowers45.add(new HighTowerData(new Coordinate(1, 2), new Coordinate(2, 2)));
        List<Coordinate> enemyKnights45 = new ArrayList<Coordinate>();
        enemyKnights45.add(new Coordinate(2, 0));
        enemyKnights45.add(new Coordinate(0, 1));
        enemyKnights45.add(new Coordinate(3, 2));
        CHALLENGE_45 = new ChallengeData(
                "Challenge 45", "OOPs",
                GridDefinitions.SMALL,
                castleKnights45, highTowers45, enemyKnights45,
                WallDefinitions.STANDARD);
        
        List<Coordinate> castleKnights51 = new ArrayList<Coordinate>();
        castleKnights51.add(new Coordinate(1, 3));
        castleKnights51.add(new Coordinate(3, 3));
        List<HighTowerData> highTowers51 = new ArrayList<HighTowerData>();
        highTowers51.add(new HighTowerData(new Coordinate(1, 1), new Coordinate(2, 1)));
        List<Coordinate> enemyKnights51 = new ArrayList<Coordinate>();
        enemyKnights51.add(new Coordinate(1, 2));
        CHALLENGE_51 = new ChallengeData(
                "Challenge 51", "OOPs",
                GridDefinitions.SMALL,
                castleKnights51, highTowers51, enemyKnights51,
                WallDefinitions.STANDARD);
        
    }
    
}
