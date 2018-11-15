package com.oops.wallsandwarriors.data;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.StorageManager;
import com.oops.wallsandwarriors.game.model.ChallengeData;
import com.oops.wallsandwarriors.util.EncodeUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampaignChallengesData {

    private List<ChallengeData> campaignChallenges;

    public CampaignChallengesData()
    {
        campaignChallenges = new ArrayList<>();
        writeCampaignChallenges();
    }

    private void writeCampaignChallenges()
    {
        try {
            
            StorageManager storageManager = Game.getInstance().storageManager;
            FileWriter fileWriter = new FileWriter(storageManager.campaignChallengeData);
            
            File campaignChallengesFile = new File("resources/challenges/campaign_challenges.dat");
            Scanner campaignChallengeScanner = new Scanner(campaignChallengesFile);
            while (campaignChallengeScanner.hasNext()) {
                String challengeCode = campaignChallengeScanner.nextLine();
                fileWriter.write(challengeCode + "\n");
                campaignChallenges.add(EncodeUtils.decode(challengeCode));
            }
            
            fileWriter.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public List<ChallengeData> getCampaignChallenges()
    {
        return campaignChallenges;
    }

}
