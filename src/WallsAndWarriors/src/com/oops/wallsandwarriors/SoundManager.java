package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.util.FileUtils;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * A class to manage sound settings of the game
 * @author Merve Sagyatanlar
 * @author Emin Bahadir Tuluce
 */
public class SoundManager {

    private final Media musicFile = new Media(FileUtils.getURI(
            "/com/oops/wallsandwarriors/resources/musics/bg_music.mp3"));
    private final MediaPlayer mediaplayer = new MediaPlayer(musicFile);

    private final Media primary = new Media(FileUtils.getURI(
            "/com/oops/wallsandwarriors/resources/musics/primary.wav"));
    private final MediaPlayer primarySound = new MediaPlayer(primary);

    private final Media secondary = new Media(FileUtils.getURI(
            "/com/oops/wallsandwarriors/resources/musics/secondary.wav"));
    private final MediaPlayer secondarySound = new MediaPlayer(secondary);

    private final Media congrats = new Media(FileUtils.getURI(
            "/com/oops/wallsandwarriors/resources/musics/congrats.mp3"));
    private final MediaPlayer congratsSound = new MediaPlayer(congrats);

    private final Media rotate = new Media(FileUtils.getURI(
            "/com/oops/wallsandwarriors/resources/musics/rotate.wav"));
    private final MediaPlayer rotateSound = new MediaPlayer(rotate);

    private final Media click = new Media(FileUtils.getURI(
            "/com/oops/wallsandwarriors/resources/musics/click.wav"));
    private final MediaPlayer clickSound = new MediaPlayer(click);

    /**
     * A method to set music and sound files volume to mute
     */
    public void mute() {
        mediaplayer.setVolume(0);
        primarySound.setVolume(0);
        secondarySound.setVolume(0);
        congratsSound.setVolume(0);
        rotateSound.setVolume(0);
        clickSound.setVolume(0);
    }

    /**
     * A method to check music and sound volume
     * @return true if both volume is greater than 0
     */
    public boolean soundCheck() {
        return mediaplayer.getVolume() > 0 ||
               primarySound.getVolume() > 0 ||
               secondarySound.getVolume() > 0;
    }

    /**
     * A method to set initial volume of the music and sound
     */
    public void setInitialVolume() {
        Game.getInstance().settingsManager.readSettings();
        updateMusicVolume();
        updateSoundVolume();
    }

    /**
     * A method to update music volume
     */
    public void updateMusicVolume(){
        mediaplayer.setVolume(Game.getInstance().settingsManager.getMusicVolume());
    }

    /**
     * A method to update sound volume
     */
    public void updateSoundVolume() {
        primarySound.setVolume(Game.getInstance().settingsManager.getVolume());
        secondarySound.setVolume(Game.getInstance().settingsManager.getVolume());
        congratsSound.setVolume(Game.getInstance().settingsManager.getVolume());
        rotateSound.setVolume(Game.getInstance().settingsManager.getVolume());
        clickSound.setVolume(Game.getInstance().settingsManager.getVolume());
    }
    
    /**
     * A method to start playing music file
     */
    public void startPlayMusic() {
        mediaplayer.setCycleCount(Integer.MAX_VALUE);
        mediaplayer.setVolume(Game.getInstance().settingsManager.getMusicVolume());
        mediaplayer.play();
    }
    
    /**
     * A method to start playing sound file
     */
    public void playCongrats() {
        congratsSound.seek(Duration.ZERO);
        congratsSound.play();
    }

    /**
     * A method to start playing primary sound file
     */
    public void playPrimary() {
        primarySound.seek(Duration.ZERO);
        primarySound.play();
    }

    /**
     * A method to start playing secondary sound file
     */
    public void playSecondary() {
        secondarySound.seek(Duration.ZERO);
        secondarySound.play();
    }

    /**
     * A method to start playing rotate sound file
     */
    public void playRotate() {
        rotateSound.seek(Duration.ZERO);
        rotateSound.play();
    }

    /**
     * A method to start playing click sound file
     */
    public void playClick() {
        clickSound.seek(Duration.ZERO);
        clickSound.play();
    }

}
