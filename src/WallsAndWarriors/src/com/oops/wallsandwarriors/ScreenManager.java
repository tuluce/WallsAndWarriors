package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.screens.challenges.CampaignChallengesScreen;
import com.oops.wallsandwarriors.screens.game.ChallengeEditorScreen;
import com.oops.wallsandwarriors.screens.CreditsScreen;
import com.oops.wallsandwarriors.screens.challenges.CustomChallengesScreen;
import com.oops.wallsandwarriors.screens.game.GameScreen;
import com.oops.wallsandwarriors.screens.HowToPlayScreen;
import com.oops.wallsandwarriors.screens.MainMenuScreen;
import com.oops.wallsandwarriors.screens.Screen;
import com.oops.wallsandwarriors.screens.SettingsScreen;

public class ScreenManager {

    public final Screen challengeEditor = new ChallengeEditorScreen();
    public final Screen gameScreen = new GameScreen();
    public final Screen customChallenges = new CustomChallengesScreen();
    public final Screen campaignChallenges = new CampaignChallengesScreen();
    public final Screen mainMenu = new MainMenuScreen();
    public final Screen settings = new SettingsScreen();
    public final Screen credits = new CreditsScreen();
    public final Screen howToPlay = new HowToPlayScreen();

}
