package com.oops.wallsandwarriors.data;

import com.oops.wallsandwarriors.game.model.ChallengeData;
import com.oops.wallsandwarriors.util.EncodeUtils;
import com.oops.wallsandwarriors.util.TestUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CampaignChallengesData {

    public static List<File> files = new ArrayList<>();
    public static List<ChallengeData> originalChallenges = new ArrayList<>();

    public CampaignChallengesData()
    {
        originalChallenges.add(TestUtils.CHALLENGE_45);
        originalChallenges.add(TestUtils.CHALLENGE_51);

        try {
            for (ChallengeData originalChallenge : originalChallenges) {
                File challengeFile = new File("C:\\W&W\\CampaignChallenges\\" + originalChallenge.getName() + ".dat");
                challengeFile.createNewFile();

                challengeFile.setWritable(true);

                FileWriter fileWriter = new FileWriter(challengeFile);
                fileWriter.write(EncodeUtils.encode(originalChallenge));
                fileWriter.close();

                files.add(challengeFile);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
