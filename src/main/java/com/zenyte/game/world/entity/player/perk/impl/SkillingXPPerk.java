package com.zenyte.game.world.entity.player.perk.impl;

import com.zenyte.game.world.entity.player.perk.*;

public class SkillingXPPerk extends Perk {
    // allow up to 5 tiers of XP boost, for example
    public static final int MAX_LEVEL = 5;

    public SkillingXPPerk() {
        super(MAX_LEVEL);
    }

    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public String getName() {
        return "Starter XP";
    }

    @Override
    public String getDescription() {
        // show current %, and current/max level for the UI
        final int pct = (int)((getEffect() - 1.0) * 100);
        return String.format("Provides you +%d%% XP in all skills (Tier %d/%d).",
            pct, getLevel(), getMaxLevel());
    }

    @Override
    public PerkCategory getCategory() {
        return PerkCategory.SKILLING;
    }

    @Override
    public int getTier() {
        return 1;
    }

    @Override
    public double getEffect() {
        // 5% per tier
        return 1.0 + 0.05 * getLevel();
    }
}
