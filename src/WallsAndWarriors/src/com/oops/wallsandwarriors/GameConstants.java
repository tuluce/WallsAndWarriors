package com.oops.wallsandwarriors;

public class GameConstants {
    
    public static final double SCREEN_RATIO = 0.75;
    
    public static final double SCREEN_WIDTH = 800;
    public static final double SCREEN_HEIGHT = SCREEN_WIDTH * SCREEN_RATIO;
    
    public static final double PALETTE_MARGIN = SCREEN_WIDTH * 0.0125;
    public static final double PALETTE_WIDTH = SCREEN_WIDTH * 0.1875;
    public static final double PALETTE_HEIGHT = SCREEN_WIDTH * 0.575;
    public static final int    PALETTE_ELEMENT_NO = 4;
    public static final double PALETTE_ELEMENT_HEIGHT = PALETTE_HEIGHT / PALETTE_ELEMENT_NO;
    public static final double PALETTE_X = SCREEN_WIDTH * 0.03125;
    public static final double PALETTE_Y = SCREEN_WIDTH * 0.10625;
    public static final double PALETTE_B = SCREEN_WIDTH * 0.03125;
    public static final double PALETTE_GAP = SCREEN_WIDTH * 0.0625;
    
    public static final double EDITOR_PALETTE_X = PALETTE_X + PALETTE_WIDTH + PALETTE_MARGIN;
    public static final double EDITOR_PALETTE_WIDTH = 2 * PALETTE_WIDTH + PALETTE_MARGIN;
    
    public static final double GRID_Y = SCREEN_WIDTH * 0.14375;
    public static final double GRID_X = PALETTE_X + PALETTE_WIDTH + PALETTE_GAP;
    public static final double GRID_B = SCREEN_WIDTH * 0.125;
    public static final double GRID_MARGIN = GRID_B * 0.3;
    
    public static final double EDITOR_GRID_Y = SCREEN_WIDTH * 0.14375;
    public static final double EDITOR_GRID_X = EDITOR_PALETTE_X + PALETTE_WIDTH + PALETTE_GAP;
    public static final double EDITOR_GRID_B = GRID_B * 0.7;
    public static final double EDITOR_GRID_MARGIN = GRID_MARGIN;
    
    public static final double WALL_THICKNESS = 0.15;
    public static final double BASTION_RADIUS = 0.3;
    public static final double KNIGHT_RADIUS = 0.45;
    public static final double HIGH_TOWER_RADIUS = 0.6;
    
}
