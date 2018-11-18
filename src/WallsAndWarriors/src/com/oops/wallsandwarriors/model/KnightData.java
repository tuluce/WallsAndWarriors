package com.oops.wallsandwarriors.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class KnightData implements BlockPiece,Serializable {

    public final boolean isEnemy;
    private Coordinate position;
    
    public KnightData(Coordinate firstPosition, boolean isEnemy) {
        this.position = firstPosition;
        this.isEnemy = isEnemy;
    }
    
    public Coordinate getPosition() {
        return position;
    }
    
    public void setPosition(Coordinate position) {
        this.position = position;
    }
    
    @Override
    public KnightData createCopy() {
        return new KnightData(position, isEnemy);
    }
    
    @Override
    public List<Coordinate> occupiesBlock() {
        List<Coordinate> occupies = new ArrayList<Coordinate>(1);
        occupies.add(position);
        return occupies;
    }

    @Override
    public void rotate() { }
    
}
