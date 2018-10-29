package com.oops.wallsandwarriors.game.model;

import java.util.List;

public class WallDefinition {

    public final List<WallPortion> portions;
    public final List<WallBastion> bastions;
    
    public WallDefinition(List<WallPortion> portions, List<WallBastion> bastions) {
        this.portions = portions;
        this.bastions = bastions;
    }
    
}
