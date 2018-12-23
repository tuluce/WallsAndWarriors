package com.oops.wallsandwarriors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class to manage writing and reading file operations
 * @author Merve Sagyatanlar
 * @author Emin Bahadir Tuluce
 * @author Ali Babayev
 * @author Tunar Mahmudov
 */
public class StorageManager {

    public File wnwData;
    public File campaignChallengeData;
    public File customChallengeData;
    public File sessionData;
    public File progressData;
    public File settingsData;

    /**
     * A default constructor that calls makeDirectory
     */
    public StorageManager() {
        makeDirectory();
    }

    /**
     * A method to create .wnwdata folder into the user's home directory
     */
    private void makeDirectory() {
        String userHome = System.getProperty("user.home");
        wnwData = new File(userHome + "/.wnwdata");
        wnwData.mkdirs();

        makeCampaignChallengesFile();
        makeCustomChallengesFile();
        makeProgressFile();
        makeSettingsFile();
    }

    /**
     * A method to create settings.dat file into the .wnwdata folder
     */
    public void makeSettingsFile() {
        settingsData = new File (wnwData,"settings.dat");
        try {
            if (!settingsData.exists()) {
                settingsData.createNewFile();
                BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(settingsData));
                bufferedWriter.write("1 0");
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        settingsData.setWritable(true);
    }

    /**
     * A method to write latest data to the settings.dat file
     * @param sound double value representing volume(level) of the sound
     * @param music double value representing volume(level) of the sound
     */
    public void writeSettings(double sound, double music) {
        try {
            settingsData = new File(wnwData, "settings.dat");
            settingsData.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(settingsData));
            bufferedWriter.write(sound + " " + music);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to read sound settings from the settings.dat file
     * @return sound setting
     */
    public double readSoundSetting() {
        try {
            BufferedReader soundReader = new BufferedReader(
                    new FileReader(new File(wnwData, "settings.dat")));
            String soundSetting = soundReader.readLine();
            Scanner soundScanner = new Scanner(soundSetting);
            return Double.parseDouble(soundScanner.next());
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * A method to read latest music setting from the settings.dat file
     * @return music setting
     */
    public double readMusicSetting() {
        try {
            BufferedReader soundReader = new BufferedReader(
                    new FileReader(new File(wnwData, "settings.dat")));
            String soundSetting = soundReader.readLine();
            Scanner soundScanner = new Scanner(soundSetting);
            soundScanner.next();
            return Double.parseDouble(soundScanner.next());
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * A method to create campaign_challenges.dat file into the .wnwdata folder
     */
    public void makeCampaignChallengesFile() {
        campaignChallengeData = new File(wnwData, "campaign_challenges.dat");
        try {
            campaignChallengeData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        campaignChallengeData.setWritable(true);
    }

    /**
     * A method to create custom_challenges.dat file into the .wnwdata folder
     */
    public void makeCustomChallengesFile() {
        customChallengeData = new File(wnwData, "custom_challenges.dat");
        try {
            customChallengeData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        makeSessionFile();
    }

    /**
     * A method to create progress.dat file into the .wnwdata folder
     */
    public void makeProgressFile() {
        progressData = new File(wnwData, "progress.dat");
        try {
            progressData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        progressData.setWritable(true);
    }

    /**
     * A method to create session.dat file into the .wnwdata folder
     */
    public void makeSessionFile() {
        sessionData = new File(wnwData, "session.dat");
        try {
            sessionData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sessionData.setWritable(true);
    }

    /**
     * A method to remove data from session.dat file
     */
    public void clearSessionFile() {
        try {
            sessionData = new File(wnwData, "session.dat");
            sessionData.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(new File(wnwData, "session.dat")));
            bufferedWriter.write("");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to read session from session.dat file
     * @return BufferReader for the session data
     * @throws FileNotFoundException if file is not found
     */
    public BufferedReader getSessionReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(new File(wnwData, "session.dat")));
    }

    /**
     * A method to get progress data
     * @return progress data file
     */
    public File getProgressData() {
        return progressData;
    }
}
