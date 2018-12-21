package com.oops.wallsandwarriors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StorageManager {

    public File wnwData;
    public File campaignChallengeData;
    public File customChallengeData;
    public File sessionData;
    public File progressData;
    public File settingsData;

    public StorageManager()
    {
        makeDirectory();
    }

    private void makeDirectory()
    {
        String userHome = System.getProperty("user.home");
        wnwData = new File(userHome + "/.wnwdata");
        wnwData.mkdirs();

        makeCampaignChallengesFile();
        makeCustomChallengesFile();
        makeProgressFile();
        makeSettingsFile();
    }

    public void makeSettingsFile(){
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

    public void writeSettings(double sound, double music){
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

    public double readSoundSetting() {
        try {
            BufferedReader soundReader = new BufferedReader(new FileReader(new File(wnwData, "settings.dat")));
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

    public double readMusicSetting() {
        try {
            BufferedReader soundReader = new BufferedReader(new FileReader(new File(wnwData, "settings.dat")));
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



    public void makeCampaignChallengesFile()
    {
        campaignChallengeData= new File(wnwData,   "campaign_challenges.dat");


        try {
            campaignChallengeData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        campaignChallengeData.setWritable(true);
    }


    public void makeCustomChallengesFile()
    {
        customChallengeData= new File(wnwData,   "custom_challenges.dat");


        try {
            customChallengeData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        makeSessionFile();
    }




    public void makeProgressFile()
    {
        progressData = new File(wnwData, "progress.dat");

        try {
            progressData.createNewFile();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        progressData.setWritable(true);
    }


    public void makeSessionFile()
    {
        sessionData = new File(wnwData, "session.dat");

        try {
            sessionData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sessionData.setWritable(true);
    }

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

    public BufferedReader getSessionReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(new File(wnwData, "session.dat")));
    }


    public File getProgressData()
    {
        return progressData;
    }
}
