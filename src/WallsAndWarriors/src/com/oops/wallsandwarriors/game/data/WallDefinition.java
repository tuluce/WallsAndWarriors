package com.oops.wallsandwarriors.game.data;

import java.util.ArrayList;
import java.util.List;

public class WallDefinition {

    public final List<WallPortion> portions;
    public final List<WallBastion> bastions;
    
    public WallDefinition() {
        portions = new ArrayList<WallPortion>();
        bastions = new ArrayList<WallBastion>();
    }
    
    public WallDefinition(List<WallPortion> portions, List<WallBastion> bastions) {
        this.portions = portions;
        this.bastions = bastions;
    }
    
}
