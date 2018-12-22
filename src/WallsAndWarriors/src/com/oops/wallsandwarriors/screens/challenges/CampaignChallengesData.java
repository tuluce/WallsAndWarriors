package com.oops.wallsandwarriors.screens.challenges;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.StorageManager;
import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.util.EncodeUtils;
import com.oops.wallsandwarriors.util.FileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A class to store the data to be represented in the CampaignChallengesScreen.
 * @author OOPs
 */
public class CampaignChallengesData {
    private static final int ZERO = 0;


    public static List<ChallengeData> campaignChallenges;
    StorageManager storageManager;
    public static List<String> campaignChallengesProgress;

    /**
     * A default constructor that initializes a CampaignChallengesData with no given parameters
     * with a storageManager and an empty ArrayList of ChallengeData. If there was a saved version
     * initializes the data as such.
     */
    public CampaignChallengesData()
    {
        storageManager = Game.getInstance().storageManager;
        campaignChallenges = new ArrayList<>();
        readProgressData();
        writeCampaignChallenges();
    }
    /**
     * A method to read the ProgressData if it exists from previous sessions.
     */
    private void readProgressData() {
        campaignChallengesProgress = new ArrayList<>();

        if (storageManager.getProgressData().length() == ZERO) {
            writeProgressDataToFile(storageManager);
        }
        else {
            campaignChallengesProgress.clear();

            try {
                BufferedReader bufferedReader =
                        new BufferedReader(new FileReader(storageManager.progressData));
                String text = bufferedReader.readLine();

                for (int i = 0; i < text.length(); i++) {
                    if (text.charAt(i) != '[' && text.charAt(i) != ']' &&
                            text.charAt(i) != ',' && text.charAt(i) != ' ') {
                        campaignChallengesProgress.add(String.valueOf(text.charAt(i)));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A method to write the CampaignChallenges according tothe stored challenges if they exist.
     */
    private void writeCampaignChallenges()
    {
        campaignChallenges.clear();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(storageManager.campaignChallengeData);

            Scanner campaignChallengeScanner = new Scanner(FileUtils.getInputStream
                    ("/com/oops/wallsandwarriors/resources/challenges/campaign_challenges.dat"));

            while (campaignChallengeScanner.hasNext()) {
                String challengeCode = campaignChallengeScanner.nextLine();
                fileWriter.write(challengeCode + "\n");
                try {
                    campaignChallenges.add(EncodeUtils.decode(challengeCode));
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to write the progress in the CampaignChallenges to a file to be stored.
     * @param storageManager StorageManager object to be used to manage de storing of the progres..
     */
    public void writeProgressDataToFile(StorageManager storageManager)
    {

        FileWriter fileWriter = null;

        if(storageManager.getProgressData().length() == ZERO)
        {
            try {
                fileWriter = new FileWriter(storageManager.progressData);

                Scanner campaignChallengeScanner = new Scanner(FileUtils.getInputStream
                        ("/com/oops/wallsandwarriors/resources/challenges/campaign_challenges.dat"));

                int index = 0;
                while (campaignChallengeScanner.hasNext()) {
                    if (index == 0) {
                        campaignChallengesProgress.add("1");
                    }
                    else {
                        campaignChallengesProgress.add("0");
                    }
                    index++;
                    campaignChallengeScanner.nextLine();
                }
                fileWriter.write(campaignChallengesProgress.toString() + "\n");
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A get method to return the campaignChallenges.
     * @return  campaignChallenges as a list of ChallengeData.
     */
    public List<ChallengeData> getCampaignChallenges()
    {
        return campaignChallenges;
    }
}
