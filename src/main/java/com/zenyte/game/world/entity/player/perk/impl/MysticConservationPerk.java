package com.zenyte.game.world.entity.player.perk.impl;

import com.zenyte.game.world.entity.player.perk.*;

public class MysticConservationPerk extends Perk {

    public static final int MAX_LEVEL = 5;
    private static final double[] SAVE_CHANCE = { 0.10, 0.25, 0.40, 0.60, 0.80 };

    public MysticConservationPerk() {
        super(MAX_LEVEL);
    }

    @Override
    public double getEffect() {
        int lvl = Math.max(1, getLevel());
        return SAVE_CHANCE[Math.min(lvl - 1, SAVE_CHANCE.length - 1)];
    }

    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public String getName() {
        return "Mystic Conservation";
    }

    @Override
    public String getDescription() {
        return "Chance to preserve runes when casting spells or using charges from staff" +
            "1 point = 10% chance<br>" +
            "2 points = 25%<br>" +
            "3 points = 40%<br>" +
            "4 points = 60%<br>" +
            "5 points = 80%<br>";
    }

    @Override
    public PerkCategory getCategory() {
        return PerkCategory.COMBAT;
    }
}
