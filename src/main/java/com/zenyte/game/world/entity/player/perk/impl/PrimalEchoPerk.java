package com.zenyte.game.world.entity.player.perk.impl;

import com.zenyte.game.world.entity.player.perk.*;

public class PrimalEchoPerk extends Perk {

    public static final int MAX_LEVEL = 5;
    private static final double[] CHANCES = { 0.05, 0.10, 0.15, 0.20, 0.25 };

    public PrimalEchoPerk() {
        super(MAX_LEVEL);
    }

    @Override
    public double getEffect() {
        int lvl = getLevel();       // 1 through MAX_LEVEL
        return CHANCES[Math.min(lvl - 1, CHANCES.length - 1)];
    }

    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public String getName() {
        return "Primal Echo";
    }

    @Override
    public String getDescription() {
        return "Melee hits have a chance to generate an echo hit<br>" +
            "1 point → 5% chance<br>" +
            "2 points → 10%<br>" +
            "3 points → 15%<br>" +
            "4 points → 20%<br>" +
            "5 points → 25%";
    }

    @Override
    public PerkCategory getCategory() {
        return PerkCategory.COMBAT;
    }

}
