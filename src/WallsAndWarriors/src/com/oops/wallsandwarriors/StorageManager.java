package com.oops.wallsandwarriors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StorageManager {

    public File wnwData;
    public File campaignChallengeData;
    public File customChallengeData;
    public File sessionData;

    public StorageManager()
    {
        makeDirectory();
    }

    private void makeDirectory()
    {
        String userHome = System.getProperty("user.home");
        wnwData = new File(userHome + "/.wnwdata");
        wnwData.mkdirs();

        campaignChallengeData= new File(wnwData, "campaign_challenges.dat");

        try {
            campaignChallengeData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        campaignChallengeData.setWritable(true);


        customChallengeData= new File(wnwData, "custom_challenges.dat");

        try {
            customChallengeData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        makeSessionFile();
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

}
