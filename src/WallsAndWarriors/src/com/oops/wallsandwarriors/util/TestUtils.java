package com.oops.wallsandwarriors.util;

import com.oops.wallsandwarriors.game.model.ChallengeData;
import com.oops.wallsandwarriors.game.model.Coordinate;
import com.oops.wallsandwarriors.game.model.HighTowerData;
import com.oops.wallsandwarriors.game.model.KnightData;


public class TestUtils {

    public static final ChallengeData CHALLENGE_45;
    public static final ChallengeData CHALLENGE_51;
    
    static {
        CHALLENGE_45 = new ChallengeData();
        CHALLENGE_45.addPiece(new KnightData(new Coordinate(1, 0), false));
        CHALLENGE_45.addPiece(new KnightData(new Coordinate(3, 1), false));
        CHALLENGE_45.addPiece(new KnightData(new Coordinate(0, 2), false));
        CHALLENGE_45.addPiece(new KnightData(new Coordinate(2, 0), true));
        CHALLENGE_45.addPiece(new KnightData(new Coordinate(0, 1), true));
        CHALLENGE_45.addPiece(new KnightData(new Coordinate(3, 2), true));
        CHALLENGE_45.addPiece(new HighTowerData(new Coordinate(1, 2), new Coordinate(2, 2)));
        CHALLENGE_45.setName("Challenge 45");
        CHALLENGE_45.setDescription("The original 45th challenge");
        CHALLENGE_45.setCreator("OOPs");
        
        CHALLENGE_51 = new ChallengeData();
        CHALLENGE_51.addPiece(new KnightData(new Coordinate(1, 3), false));
        CHALLENGE_51.addPiece(new KnightData(new Coordinate(3, 3), false));
        CHALLENGE_51.addPiece(new KnightData(new Coordinate(1, 2), true));
        CHALLENGE_51.addPiece(new HighTowerData(new Coordinate(1, 1), new Coordinate(2, 1)));
        CHALLENGE_51.setName("Challenge 51");
        CHALLENGE_51.setDescription("The original 51st challenge");
        CHALLENGE_51.setCreator("OOPs");
    }
    
}
