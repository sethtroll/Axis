package com.zenyte.game.world.entity.player.perk;

import com.zenyte.game.world.entity.player.Player;

public abstract class Perk {
    /** current rank of this perk (1…maxLevel) */
    private int level = 1;

    /** The tier of this perk */
    private int tier = 1;
    /** each concrete Perk subclass defines this in its ctor */
    private final int maxLevel;

    protected Perk(final int maxLevel) {
        this.maxLevel = maxLevel;
    }

    /** how many times you can rank this perk up */
    public int getMaxLevel() {
        return maxLevel;
    }

    /** where the player is right now (1…maxLevel) */
    public int getLevel() {
        return level;
    }

    /** where the player is right now (1…maxLevel) */
    public int getTier() {
        return level;
    }

    /** bump it by one, up to maxLevel */
    public boolean levelUp() {
        if (level < maxLevel) {
            level++;
            return true;
        }
        return false;
    }

    /** bump it by up to maxLevel */
    public boolean maxLevelUp() {
        if (level < maxLevel) {
            level = maxLevel;
            return true;
        }
        return false;
    }

    /** the overall “multiplier” this perk currently applies */
    public abstract double getEffect();

    /** cost in perk‐points / currency to unlock or rank up */
    public abstract int getCost();

    public abstract String getName();
    public abstract String getDescription();
    public abstract PerkCategory getCategory();

    /** you can still override valid/consume if needed */
    public boolean valid() {
        return level > 0;
    }

    public void consume(final Player player) {
    }
}
