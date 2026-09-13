package io.github.dy3mc.ultimatePvP.manager;

import io.github.dy3mc.ultimatePvP.ability.Ability;

public class PlayerData {

    private Ability ability;

    private int killStreak;

    private boolean bounty;

    private boolean awaken;

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public int getKillStreak() {
        return killStreak;
    }

    public void setKillStreak(int killStreak) {
        this.killStreak = killStreak;
    }

    public boolean isBounty() {
        return bounty;
    }

    public void setBounty(boolean bounty) {
        this.bounty = bounty;
    }

    public boolean isAwaken() {
        return awaken;
    }

    public void setAwaken(boolean awaken) {
        this.awaken = awaken;
    }
}