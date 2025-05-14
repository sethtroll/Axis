package com.zenyte.game.world.entity.player.perk.impl;

import com.zenyte.game.world.entity.player.perk.*;

public class BallisticHastePerk extends Perk {
    public static final int MAX_LEVEL = 1;

    public BallisticHastePerk() {
        super(MAX_LEVEL);
    }

    @Override
    public int getCost() {
        return 1; // The cost to unlock this perk
    }

    @Override
    public String getName() {
        return "Ballistic Haste";
    }

    @Override
    public String getDescription() {
        return "Increases range attack speed:\n" +
            "5 ticks → 3\n" +
            "4 ticks → 2\n" +
            "3 ticks → 2\n" +
            "2 ticks → 1";
    }

    @Override
    public PerkCategory getCategory() {
        return PerkCategory.COMBAT;
    }

    @Override
    public double getEffect() {
        // You can return a value here if you want to e.g. multiply some delay by this.
        // For now it’s just a marker perk, so return 1.
        return 1.0;
    }
}
