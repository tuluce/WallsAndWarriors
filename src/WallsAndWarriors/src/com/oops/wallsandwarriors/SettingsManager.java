package com.oops.wallsandwarriors;
import javafx.scene.paint.Color;

public class SettingsManager {

    Color enemyColor = Color.RED;
    Color allyColor = Color.BLUE;

    double volume = 1;
    double musicVolume = 0.5;

    public Color getEnemyColor(){
        return enemyColor;
    }
    
    public void setEnemyColor(Color color){
        enemyColor = color;
    }

    public Color getAllyColor(){
        return allyColor;
    }

    public void setAllyColor(Color color){
        allyColor = color;
    }

    public double getVolume(){
        return volume;
    }

    public void setVolume(double volume){
        this.volume = volume;

    }

    public void setMusicVolume(double volume){
        musicVolume = volume;

    }

    public double getMusicVolume(){
        return musicVolume;
    }
    
    public void readSettings() {
        setVolume(Game.getInstance().storageManager.readSoundSetting());
        setMusicVolume(Game.getInstance().storageManager.readMusicSetting());
    }



}
