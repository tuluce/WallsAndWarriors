package com.oops.wallsandwarriors;

import java.io.File;
import java.io.IOException;

public class StorageManager {

    public File wnwData;
    public File campaignChallengeData;
    public File customChallengeData;

    public StorageManager()
    {
        makeDirectory();
    }

    private void makeDirectory()
    {
        String userHome = System.getProperty("user.home");
        wnwData = new File(userHome + "/.wnwdata");
        wnwData.mkdirs();

        campaignChallengeData= new File(wnwData,   "campaign_challenges.dat");

        try {
            campaignChallengeData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        campaignChallengeData.setWritable(true);



        customChallengeData= new File(wnwData,   "custom_challenges.dat");

        try {
            customChallengeData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
