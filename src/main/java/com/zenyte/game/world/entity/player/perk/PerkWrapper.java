package com.zenyte.game.world.entity.player.perk;

import com.zenyte.game.world.entity.player.perk.impl.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import java.util.*;

/**
 * @author Tommeh | 11-1-2019 | 19:56
 * @see <a href="https://www.rune-server.ee/members/tommeh/">Rune-Server profile</a>}
 */
public enum PerkWrapper {
    //Skilling Perks
    SKILLING_XP(SkillingXPPerk.class, 30004, PerkCategory.SKILLING, 1, 5),


    // Combat Perks
    ARCANE_HASTE(ArcaneHastePerk.class, 30006, PerkCategory.COMBAT, 1, 1),
    BALLISTIC_HASTE(BallisticHastePerk.class, 30007, PerkCategory.COMBAT, 1, 1),
    PRIMAL_HASTE(PrimalHastePerk.class, 30008, PerkCategory.COMBAT, 1, 1),
    COMBAT_PRECISION(CombatPrecisionPerk.class, 30012, PerkCategory.COMBAT, 1, 5),

    COMBAT_XP(CombatXPPerk.class, 30005, PerkCategory.COMBAT, 2, 5),
    PRIMAL_ECHO(PrimalEchoPerk.class, 30009, PerkCategory.COMBAT, 2, 5),
    ARCANE_ECHO(ArcaneEchoPerk.class, 30010, PerkCategory.COMBAT, 2, 5),
    BALLISTIC_ECHO(BallisticEchoPerk.class, 30011, PerkCategory.COMBAT, 2, 5),
    MYSTIC_CONSERVATION(MysticConservationPerk.class, 30012, PerkCategory.COMBAT, 2, 5),
    QUIVER_SAVANT(QuiverSavantPerk.class, 30013, PerkCategory.COMBAT, 2, 5),



    ;
    public static final Int2ObjectOpenHashMap<PerkWrapper> PERKS_BY_ID = new Int2ObjectOpenHashMap<>();
    private static final PerkWrapper[] VALUES = values();
    private static final Map<Class<? extends Perk>, PerkWrapper> PERKS_BY_CLASS = new HashMap<>();
    private static final Map<String, PerkWrapper> PERKS_BY_NAME = new HashMap<>();
    private static final Map<PerkCategory, PerkWrapper> PERKS_BY_CATEGORY = new HashMap<>();
    private static final Map<Integer, PerkWrapper> PERKS_BY_TIER = new HashMap<>();


    static {
        for (final PerkWrapper value : VALUES) {
            PERKS_BY_CLASS.put(value.getPerk(), value);
            PERKS_BY_NAME.put(value.name(), value);
            PERKS_BY_ID.put(value.getId(), value);
            PERKS_BY_CATEGORY.put(value.getCategory(), value);
            PERKS_BY_TIER.put(value.getTier(), value);
        }
    }

    private final Class<? extends Perk> perk;
    private final int id;
    private final PerkCategory category;
    private final int tier;
    private final int maxLevel;

    PerkWrapper(final Class<? extends Perk> perk, final int id, final PerkCategory category, final int tier, final int maxLevel) {
        this.perk = perk;
        this.id = id;
        this.category = category;
        this.tier = tier;
        this.maxLevel = maxLevel;
    }

    public static PerkWrapper get(final Class<? extends Perk> perk) {
        return PERKS_BY_CLASS.get(perk);
    }

    public static PerkWrapper get(final String perk) {
        return PERKS_BY_NAME.get(perk);
    }

    public static PerkWrapper get(final int id) {
        return PERKS_BY_ID.get(id);
    }

    public Class<? extends Perk> getPerk() {
        return this.perk;
    }

    public int getId() {
        return this.id;
    }

    public int getTier() {
        return this.tier;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public PerkCategory getCategory() {
        return this.category;
    }
}
