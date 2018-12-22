package com.oops.wallsandwarriors.definitions;

import com.oops.wallsandwarriors.model.Coordinate;
import com.oops.wallsandwarriors.model.WallBastion;
import com.oops.wallsandwarriors.model.WallData;
import com.oops.wallsandwarriors.model.WallDefinition;
import com.oops.wallsandwarriors.model.WallPortion;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to define the structure of standard wall types.
 * @author OOPs
 */
public class WallDefinitions {
    
    public static final List<WallData> STANDARD;
    
    static {
        
        List<WallPortion> portions1 = new ArrayList<WallPortion>();
        portions1.add(new WallPortion(new Coordinate(0, 0), new Coordinate(-1, 0)));
        portions1.add(new WallPortion(new Coordinate(0, 0), new Coordinate(0, -1)));
        portions1.add(new WallPortion(new Coordinate(0, 0), new Coordinate(0, 1)));
        List<WallBastion> bastions1 = new ArrayList<WallBastion>();
        bastions1.add(new WallBastion(new Coordinate(0, 1)));
        WallDefinition wall1 = new WallDefinition(portions1, bastions1);
        
        List<WallPortion> portions2 = new ArrayList<WallPortion>();
        portions2.add(new WallPortion(new Coordinate(0, -1), new Coordinate(-1, -1)));
        portions2.add(new WallPortion(new Coordinate(0, 0), new Coordinate(0, -1)));
        portions2.add(new WallPortion(new Coordinate(0, 0), new Coordinate(1, 0)));
        portions2.add(new WallPortion(new Coordinate(1, 0), new Coordinate(1, 1)));
        List<WallBastion> bastions2 = new ArrayList<WallBastion>();
        bastions2.add(new WallBastion(new Coordinate(0, 0)));
        bastions2.add(new WallBastion(new Coordinate(1, 1)));
        WallDefinition wall2 = new WallDefinition(portions2, bastions2);
        
        List<WallPortion> portions3 = new ArrayList<WallPortion>();
        portions3.add(new WallPortion(new Coordinate(-1, 0), new Coordinate(-1, 1)));
        portions3.add(new WallPortion(new Coordinate(0, 0), new Coordinate(-1, 0)));
        portions3.add(new WallPortion(new Coordinate(0, 0), new Coordinate(0, -1)));
        portions3.add(new WallPortion(new Coordinate(0, 0), new Coordinate(1, 0)));
        portions3.add(new WallPortion(new Coordinate(1, 0), new Coordinate(1, 1)));
        portions3.add(new WallPortion(new Coordinate(1, 0), new Coordinate(2, 0)));
        List<WallBastion> bastions3 = new ArrayList<WallBastion>();
        WallDefinition wall3 = new WallDefinition(portions3, bastions3);
        
        List<WallPortion> portions4 = new ArrayList<WallPortion>();
        portions4.add(new WallPortion(new Coordinate(0, -1), new Coordinate(0, -2)));
        portions4.add(new WallPortion(new Coordinate(0, -1), new Coordinate(1, -1)));
        portions4.add(new WallPortion(new Coordinate(0, -1), new Coordinate(0, 0)));
        portions4.add(new WallPortion(new Coordinate(-1, 0), new Coordinate(0, 0)));
        portions4.add(new WallPortion(new Coordinate(-1, 0), new Coordinate(-1, 1)));
        List<WallBastion> bastions4 = new ArrayList<WallBastion>();
        bastions4.add(new WallBastion(new Coordinate(1, 0)));
        WallDefinition wall4 = new WallDefinition(portions4, bastions4);
        
        
        STANDARD = new ArrayList<WallData>();
        STANDARD.add(new WallData(wall1));
        STANDARD.add(new WallData(wall2));
        STANDARD.add(new WallData(wall3));
        STANDARD.add(new WallData(wall4));
        
    }
    
}
