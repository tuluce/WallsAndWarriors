package com.oops.wallsandwarriors.game.data;

import java.util.ArrayList;
import java.util.List;

public class ChallengeData {

    public final String name;
    public final String creator;
        
    public final List<Coordinate> blocks;
    public final List<Coordinate> castleKnights;
    public final List<HighTowerData> highTowers;
    public final List<Coordinate> enemyKnights;
    public final List<WallData> walls;
    
    public ChallengeData() {
        name = null;
        creator = null;
        blocks = new ArrayList<Coordinate>();
        castleKnights = new ArrayList<Coordinate>();
        highTowers = new ArrayList<HighTowerData>();
        enemyKnights = new ArrayList<Coordinate>();
        walls = new ArrayList<WallData>();
    }
    
    public ChallengeData(
            String name,
            String creator,
            List<Coordinate> blocks,
            List<Coordinate> castleKnights,
            List<HighTowerData> highTowers,
            List<Coordinate> enemyKnights,
            List<WallData> walls) {
        this.name = name;
        this.creator = creator;
        this.blocks = blocks;
        this.castleKnights = castleKnights;
        this.highTowers = highTowers;
        this.enemyKnights = enemyKnights;
        this.walls = walls;
    }
    
}
