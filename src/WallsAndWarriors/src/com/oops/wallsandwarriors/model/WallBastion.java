package com.oops.wallsandwarriors.model;

import java.io.Serializable;

public class WallBastion implements Serializable {
    
    // The wall bastion lies on top-left corner of the relative block position
    public final Coordinate relativePos;
    
    public WallBastion(Coordinate relativePos) {
        this.relativePos = relativePos;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof WallBastion) {
            WallBastion otherBastion = (WallBastion) other;
            return (otherBastion.relativePos.equals(this.relativePos));
        }
        return false;
    }
    
}
