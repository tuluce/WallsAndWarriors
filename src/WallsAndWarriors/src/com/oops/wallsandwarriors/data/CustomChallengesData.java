package com.oops.wallsandwarriors.data;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.game.model.ChallengeData;
import com.oops.wallsandwarriors.util.EncodeUtils;
import com.oops.wallsandwarriors.util.TestUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomChallengesData {


    public static List<File> files = new ArrayList<>();
    public static List<ChallengeData> customChallenges = new ArrayList<>();

    public CustomChallengesData()
    {
        customChallenges.add(TestUtils.CHALLENGE_45);
        customChallenges.add(TestUtils.CHALLENGE_51);

        try {
            for (ChallengeData customChallenge : customChallenges) {
                File challengeFile = new File(Game.f2, customChallenge.getName() + ".dat");

                challengeFile.createNewFile();
                challengeFile.setWritable(true);

                FileWriter fileWriter = new FileWriter(challengeFile);
                fileWriter.write(EncodeUtils.encode(customChallenge));
                fileWriter.close();

                files.add(challengeFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
