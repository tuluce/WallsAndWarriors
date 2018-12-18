package com.oops.wallsandwarriors.model;

import java.io.Serializable;
import java.util.List;

public class WallDefinition implements Serializable{

    public final List<WallPortion> portions;
    public final List<WallBastion> bastions;
    
    public WallDefinition(List<WallPortion> portions, List<WallBastion> bastions) {
        this.portions = portions;
        this.bastions = bastions;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof WallDefinition) {
            WallDefinition otherWallDef = (WallDefinition) other;
            System.out.println(otherWallDef.bastions.equals(this.bastions));
            return ((otherWallDef.portions.equals(this.portions)) &&
                    (otherWallDef.bastions.equals(this.bastions)));
        }
        return false;
    }

}
