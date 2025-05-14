package com.zenyte.game.world.entity.player.perk.impl;

import com.zenyte.game.world.entity.player.perk.*;

public class CombatPrecisionPerk extends Perk {

    public static final int MAX_LEVEL = 5;
    private static final double[] ACCURACY_BONUS = { 0.10, 0.20, 0.35, 0.40, 0.50 };

    public CombatPrecisionPerk() {
        super(MAX_LEVEL);
    }

    @Override
    public double getEffect() {
        int lvl = getLevel();
        return ACCURACY_BONUS[Math.min(lvl - 1, ACCURACY_BONUS.length - 1)];
    }

    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public String getName() {
        return "Combat Precision";
    }

    @Override
    public String getDescription() {
        return "Boosts accuracy for all combat styles<br>" +
            "1 point → +10% Accuracy<br>" +
            "2 points → +20%<br>" +
            "3 points → +30%<br>" +
            "4 points → +40%<br>" +
            "5 points → +60%";
    }

    @Override
    public PerkCategory getCategory() {
        return PerkCategory.COMBAT;
    }

}
