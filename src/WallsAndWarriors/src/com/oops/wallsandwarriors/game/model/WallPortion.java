package com.oops.wallsandwarriors.game.model;

import java.io.Serializable;

public class WallPortion implements Serializable {
    
    // The wall portion lies between two relative block positions
    public final Coordinate firstRelativePos;
    public final Coordinate secondRelativePos;
    
    public WallPortion(Coordinate firstRelativePos, Coordinate secondRelativePos) {
        this.firstRelativePos = firstRelativePos;
        this.secondRelativePos = secondRelativePos;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof WallPortion) {
            WallPortion otherWall = (WallPortion) other;
            return (otherWall.firstRelativePos.equals(this.firstRelativePos) &&
                    otherWall.secondRelativePos.equals(this.secondRelativePos)) ||
                   (otherWall.firstRelativePos.equals(this.secondRelativePos) &&
                    otherWall.secondRelativePos.equals(this.firstRelativePos));
        }
        return false;
    }
    
}
