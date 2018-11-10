package com.oops.wallsandwarriors;

import com.oops.wallsandwarriors.game.model.ChallengeData;

public class ChallengeManager {
    
    private ChallengeData challengeData;
    
    public void setChallengeData(ChallengeData challengeData) {
        this.challengeData = challengeData;
    }
    
    public ChallengeData getChallengeData() {
        return challengeData;
    }
    
    public void initChallengeData() {
        challengeData = new ChallengeData();
        challengeData.walls.clear();
    }
    
}
