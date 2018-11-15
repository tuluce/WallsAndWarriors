package com.oops.wallsandwarriors;

import java.io.File;
import java.io.IOException;

public class StorageManager {

    public static File wnwData;
    public static File campaignChallengeData;
    public static File customChallengeData;


    public StorageManager()
    {
        makeDirectory();
    }


    private void makeDirectory()
    {
        wnwData = new File("C:\\W&W");
        wnwData.mkdirs();

        campaignChallengeData= new File(wnwData,   "campaignChallenges.dat");

        try {
            campaignChallengeData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        campaignChallengeData.setWritable(true);



        customChallengeData= new File(wnwData,   "customChallenges.dat");

        try {
            customChallengeData.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}