package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.util.FileUtils;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundManager {

    Media musicFile = new Media(FileUtils.getURI("/com/oops/wallsandwarriors/resources/musics/bg_music.mp3"));
    MediaPlayer mediaplayer = new MediaPlayer(musicFile);

    Media primary = new Media(FileUtils.getURI("/com/oops/wallsandwarriors/resources/musics/primary.wav"));
    MediaPlayer primarySound = new MediaPlayer(primary);

    Media secondary = new Media(FileUtils.getURI("/com/oops/wallsandwarriors/resources/musics/secondary.wav"));
    MediaPlayer secondarySound = new MediaPlayer(secondary);

    Media congrats = new Media(FileUtils.getURI("/com/oops/wallsandwarriors/resources/musics/congrats.mp3"));
    MediaPlayer congratsSound = new MediaPlayer(congrats);
    
    Media rotate = new Media(FileUtils.getURI("/com/oops/wallsandwarriors/resources/musics/rotate.wav"));
    MediaPlayer rotateSound = new MediaPlayer(rotate);
    
    Media click = new Media(FileUtils.getURI("/com/oops/wallsandwarriors/resources/musics/click.wav"));
    MediaPlayer clickSound = new MediaPlayer(click);

    public void playCongrats(){
        congratsSound.play();
        congratsSound.seek(Duration.ZERO);
    }

    public void mute(){
        mediaplayer.setVolume(0);
        primarySound.setVolume(0);
        secondarySound.setVolume(0);
        congratsSound.setVolume(0);
    }

    public boolean soundCheck(){
        if(mediaplayer.getVolume()> 0 || primarySound.getVolume() > 0 || secondarySound.getVolume() > 0){
            return true;
        }
        return false;
    }

    public void setInitialVolume(){
        Game.getInstance().settingsManager.readSettings();
        updateMusicVolume();
        updateSoundVolume();
    }

    public void startPlayMusic(){
        mediaplayer.setCycleCount(Integer.MAX_VALUE);
        mediaplayer.setVolume(Game.getInstance().settingsManager.getMusicVolume());
        mediaplayer.play();
    }

    public void updateMusicVolume(){
        mediaplayer.setVolume(Game.getInstance().settingsManager.getMusicVolume());
    }

    public void updateSoundVolume(){
        primarySound.setVolume(Game.getInstance().settingsManager.getVolume());
        secondarySound.setVolume(Game.getInstance().settingsManager.getVolume());
        congratsSound.setVolume(Game.getInstance().settingsManager.getVolume());
        rotateSound.setVolume(Game.getInstance().settingsManager.getVolume());
        clickSound.setVolume(Game.getInstance().settingsManager.getVolume());
    }

    public void playPrimary(){
        primarySound.seek(Duration.ZERO);
        primarySound.play();
    }

    public void playSecondary(){
        secondarySound.seek(Duration.ZERO);
        secondarySound.play();
    }
    
    public void playRotate() {
        rotateSound.seek(Duration.ZERO);
        rotateSound.play();
    }
    
    public void playClick() {
        clickSound.seek(Duration.ZERO);
        clickSound.play();
    }

}
