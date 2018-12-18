package com.oops.wallsandwarriors.screens.challenges;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.StorageManager;
import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.screens.game.GameScreen;
import com.oops.wallsandwarriors.util.EncodeUtils;
import com.oops.wallsandwarriors.util.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CampaignChallengesData {

    public static List<ChallengeData> campaignChallenges;
    StorageManager storageManager;
    public static List<String> campaignChallengesProgress;

    public CampaignChallengesData()
    {
        storageManager = Game.getInstance().storageManager;
        campaignChallenges = new ArrayList<>();
        readProgressData();
        writeCampaignChallenges();
    }

    private void readProgressData() {
        campaignChallengesProgress = new ArrayList<>();

        if(storageManager.getProgressData().length() == 0)
        {
            writeProgressDataToFile(storageManager);
        }
        else{
            campaignChallengesProgress.clear();

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(storageManager.progressData));
                String text = bufferedReader.readLine();

                for(int i = 0; i < text.length(); i++)
                {
                    if(text.charAt(i) != '[' && text.charAt(i) != ']' && text.charAt(i) != ',' && text.charAt(i) != ' ')
                    {
                        campaignChallengesProgress.add(String.valueOf(text.charAt(i)));
                    }
                }
                System.out.println(campaignChallengesProgress.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            System.out.println(campaignChallengesProgress.toString());

    }

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
        }    }



    public void writeProgressDataToFile(StorageManager storageManager)
    {

        FileWriter fileWriter = null;

        if(storageManager.getProgressData().length() == 0)
        {
            try {
                fileWriter = new FileWriter(storageManager.progressData);

                Scanner campaignChallengeScanner = new Scanner(FileUtils.getInputStream
                        ("/com/oops/wallsandwarriors/resources/challenges/campaign_challenges.dat"));

                int index = 0;
                while (campaignChallengeScanner.hasNext()) {
                    if(index == 0)
                    {
                        campaignChallengesProgress.add("1");
                    }
                    else
                    {
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


    public List<ChallengeData> getCampaignChallenges()
    {
        return campaignChallenges;
    }
}
