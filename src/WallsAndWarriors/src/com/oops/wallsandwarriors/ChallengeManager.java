package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.model.ChallengeData;

/**
 * A class to manage challenge settings of the game
 * @author Emin Bahadir Tuluce
 */
public class ChallengeManager {
    
    private ChallengeData challengeData;

    /**
     * A method to set challenge data
     * @param challengeData the new challenge data for the manager
     */
    public void setChallengeData(ChallengeData challengeData) {
        this.challengeData = challengeData;
    }

    /**
     * A method to get challenge data
     * @return current challenge data
     */
    public ChallengeData getChallengeData() {
        return challengeData;
    }

    /**
     * A method to initialize challenge data
     */
    public void initChallengeData() {
        challengeData = new ChallengeData();
        challengeData.walls.clear();
    }
    
}
