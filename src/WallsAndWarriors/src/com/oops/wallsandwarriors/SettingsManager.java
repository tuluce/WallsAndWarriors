package com.oops.wallsandwarriors;
import javafx.scene.paint.Color;

/**
 * A class to manage sound and color settings of the game
 * @author Merve Sagyatanlar
 */

public class SettingsManager {

    Color enemyColor = Color.RED;
    Color allyColor = Color.BLUE;

    double volume = 1;
    double musicVolume = 0.5;

    /**
     * A method to get enemy color
     * @return enemy color
     */
    public Color getEnemyColor(){
        return enemyColor;
    }

    /**
     * A method to set enemy color
     * @param color enemy color
     */
    public void setEnemyColor(Color color){
        enemyColor = color;
    }

    /**
     * A method to get ally color
     * @return ally color
     */
    public Color getAllyColor(){
        return allyColor;
    }

    /**
     * A method to set ally color
     * @param color ally color
     */
    public void setAllyColor(Color color){
        allyColor = color;
    }

    /**
     * A method to get sound volume
     * @return volume
     */
    public double getVolume(){
        return volume;
    }

    /**
     * A method to set sound volume
     * @param volume sound volume
     */
    public void setVolume(double volume){
        this.volume = volume;

    }

    /**
     * A method to set music volume
     * @param volume music volume
     */
    public void setMusicVolume(double volume){
        musicVolume = volume;

    }

    /**
     * A method to get music volume
     * @return music volume
     */
    public double getMusicVolume(){
        return musicVolume;
    }

    /**
     * A method to read sound and music settings from file
     */
    public void readSettings() {
        setVolume(Game.getInstance().storageManager.readSoundSetting());
        setMusicVolume(Game.getInstance().storageManager.readMusicSetting());
    }



}
