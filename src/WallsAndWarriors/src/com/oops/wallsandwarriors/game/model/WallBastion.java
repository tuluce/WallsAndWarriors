package com.oops.wallsandwarriors.game.model;

import java.io.Serializable;

public class WallBastion implements Serializable {
    
    // The wall bastion lies on top-left corner of the relative block position
    public final Coordinate relativePos;
    
    public WallBastion(Coordinate relativePos) {
        this.relativePos = relativePos;
    }
    
}
