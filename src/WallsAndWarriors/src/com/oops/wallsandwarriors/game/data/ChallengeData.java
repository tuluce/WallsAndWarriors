package com.oops.wallsandwarriors.game.data;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ChallengeData {

    public final String name;
    public final String creator;
    public boolean isSolved;
    public Image image;
        
    public final List<Coordinate> blocks;
    public final List<Coordinate> castleKnights;
    public final List<HighTowerData> highTowers;
    public final List<Coordinate> enemyKnights;
    public final List<WallData> walls;
    
    public ChallengeData() {
        name = null;
        creator = null;
        image = null;
        isSolved = false;
        blocks = new ArrayList<Coordinate>();
        castleKnights = new ArrayList<Coordinate>();
        highTowers = new ArrayList<HighTowerData>();
        enemyKnights = new ArrayList<Coordinate>();
        walls = new ArrayList<WallData>();
    }
    
    public ChallengeData(
            String name,
            String creator,
            Image image,
            List<Coordinate> blocks,
            List<Coordinate> castleKnights,
            List<HighTowerData> highTowers,
            List<Coordinate> enemyKnights,
            List<WallData> walls) {
        this.name = name;
        this.creator = creator;
        this.image = image;
        isSolved = false;
        this.blocks = blocks;
        this.castleKnights = castleKnights;
        this.highTowers = highTowers;
        this.enemyKnights = enemyKnights;
        this.walls = walls;
    }
    
}
