package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.data.CampaignChallengesData;
import com.oops.wallsandwarriors.data.CustomChallengesData;
import com.oops.wallsandwarriors.data.SettingsData;

public class StorageManager {

    private CampaignChallengesData campaignChallengesData;
    private CustomChallengesData customChallengesData;
    private SettingsData settingsData;


    public StorageManager()
    {
        campaignChallengesData = new CampaignChallengesData();
        customChallengesData = new CustomChallengesData();
        //settingsData;
    }
}
