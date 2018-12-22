package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.model.ChallengeData;

/**
 * A class to manage sound settings of the game
 * @author Cagla Sozen
 * @author Emin Bahadir Tuluce
 */

public class HintManager {
    
    private ChallengeData challengeData;

    /**
     * A method to set challenge data
     */
    public void setChallengeData(ChallengeData challengeData) {
        this.challengeData = challengeData;
    }

    /**
     * A class to get challenge data
     * @return challenge data
     */
    public ChallengeData getChallengeData() {
        return challengeData;
    }
    
}
