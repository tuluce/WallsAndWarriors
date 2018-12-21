package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.util.FileUtils;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundManager {

    Media musicFile = new Media(FileUtils.getURI("/com/oops/wallsandwarriors/resources/musics/Monk" +
            "ey-Island-Puzzler.mp3"));
    MediaPlayer mediaplayer = new MediaPlayer(musicFile);


    Media correct = new Media(FileUtils.getURI("/com/oops/wallsandwarriors/resources/musics/correct_move.wav"));
    MediaPlayer correctSound = new MediaPlayer(correct);

    Media reset = new Media(FileUtils.getURI("/com/oops/wallsandwarriors/resources/musics/resetSound.mp3"));
    MediaPlayer resetSound = new MediaPlayer(reset);

    Media c = new Media(FileUtils.getURI("/com/oops/wallsandwarriors/resources/musics/congrats.mp3"));
    MediaPlayer congrats = new MediaPlayer(c);

    public void playCongrats(){
        congrats.play();
        congrats.seek(Duration.ZERO);
    }

    public void mute(){
        mediaplayer.setVolume(0);
        correctSound.setVolume(0);
        resetSound.setVolume(0);
    }
    
    public void unmute() {
        updateSoundVolume();
        updateMusicVolume();
    }

    public boolean soundCheck(){
        if(mediaplayer.getVolume()> 0 || correctSound.getVolume() > 0 || resetSound.getVolume() > 0){
            return true;
        }
        return false;
    }

    public void setInitialVolume(){
        mediaplayer.setVolume(0.1);
        correctSound.setVolume(1);
        resetSound.setVolume(1);
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
        correctSound.setVolume(Game.getInstance().settingsManager.getVolume());
        resetSound.setVolume(Game.getInstance().settingsManager.getVolume());
    }

    public void playCorrect(){
        correctSound.setVolume(1);
        correctSound.seek(Duration.ZERO);
        correctSound.play();
    }

    public void playReset(){
        resetSound.play();
        resetSound.seek(Duration.ZERO);
    }


}
