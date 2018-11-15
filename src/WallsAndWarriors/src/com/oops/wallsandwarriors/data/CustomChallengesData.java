package com.oops.wallsandwarriors.data;

import com.oops.wallsandwarriors.Game;
import com.oops.wallsandwarriors.StorageManager;
import com.oops.wallsandwarriors.game.model.ChallengeData;
import com.oops.wallsandwarriors.util.EncodeUtils;
import com.oops.wallsandwarriors.util.TestUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomChallengesData {


    private List<ChallengeData> customChallenges;

    public CustomChallengesData()
    {
        customChallenges = new ArrayList<>();
        readCustomChallenges();
    }


    private void readCustomChallenges()
    {
        try {
            FileInputStream fileInputStream = new FileInputStream(StorageManager.customChallengeData);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String code = "";
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
            FileWriter fileWriter = new FileWriter(StorageManager.customChallengeData, true);
            fileWriter.write(EncodeUtils.encode(challengeData) + "\n");
            fileWriter.close();

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}
