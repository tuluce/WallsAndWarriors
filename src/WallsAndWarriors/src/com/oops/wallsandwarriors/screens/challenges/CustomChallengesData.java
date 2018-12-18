package com.oops.wallsandwarriors.screens.challenges;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.StorageManager;
import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.util.EncodeUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

public class CustomChallengesData {


    private final List<ChallengeData> customChallenges;

    public CustomChallengesData()
    {
        customChallenges = new ArrayList<>();
        readCustomChallenges();
    }


    private void readCustomChallenges()
    {
        try {
            
            StorageManager storageManager = Game.getInstance().storageManager;
            FileInputStream fileInputStream = new FileInputStream(storageManager.customChallengeData);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String code;
            while ((code = bufferedReader.readLine()) != null) {
                customChallenges.add(EncodeUtils.decode(code));
            }

            bufferedReader.close();
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public List<ChallengeData> getCustomChallenges()
    {
        return customChallenges;
    }

    public void update(ChallengeData challengeData)
    {
        try
        {
            StorageManager storageManager = Game.getInstance().storageManager;
            FileWriter fileWriter = new FileWriter(storageManager.customChallengeData, true);
            fileWriter.write(EncodeUtils.encode(challengeData) + "\n");
            fileWriter.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void remove(ChallengeData challengeData) {
        try {
            StorageManager storageManager = Game.getInstance().storageManager;
            File inputFile = storageManager.customChallengeData;
            File tempFile = new File(storageManager.wnwData, "temp.dat");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile));

            String removeCode = EncodeUtils.encode(challengeData);
            boolean deleted = false;
            for (String lineCode; (lineCode = bufferedReader.readLine()) != null;) {
                String trimmedLineCode = lineCode.trim();
                if((trimmedLineCode.equals(removeCode)) && !(deleted)) {
                    deleted = true;
                }
                else {
                    bufferedWriter.write(lineCode + System.getProperty("line.separator"));
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename file");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
