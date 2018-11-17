package com.oops.wallsandwarriors.screens.challenges;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.StorageManager;
import com.oops.wallsandwarriors.model.ChallengeData;
import com.oops.wallsandwarriors.util.EncodeUtils;

import java.io.*;
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

}
