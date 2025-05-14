package com.zenyte.game.ui.testinterfaces;

import static java.util.Map.entry;

import com.zenyte.game.constants.*;
import com.zenyte.game.ui.*;
import com.zenyte.game.util.*;
import com.zenyte.game.world.entity.player.*;
import com.zenyte.game.world.entity.player.perk.*;
import java.util.*;

public class PerkInterface extends Interface {

    private static final Map<Integer, PerkWrapper> COMPONENT_TO_PERK =
        Map.ofEntries(
            entry(3,  PerkWrapper.PRIMAL_HASTE),
            entry(9, PerkWrapper.BALLISTIC_HASTE),
            entry(15, PerkWrapper.ARCANE_HASTE),
            entry(21, PerkWrapper.COMBAT_PRECISION)
        );

    private static final Map<Integer, Integer> ICON_TO_LEVEL_CID =
        Map.of(
            3,  8,
            9, 14,
            15, 20,
            21, 26
        );


    @Override
    protected void attach() {
        // register one name per component, using the default slot
        COMPONENT_TO_PERK.forEach((cid, pw) -> {
            put(cid, "perk_" + pw.name().toLowerCase());
        });
    }

    @Override
    protected void build() {
        COMPONENT_TO_PERK.forEach((cid, pw) -> {
            String key = "perk_" + pw.name().toLowerCase();
            bind(key, (player, slot, item, option) -> {
                if (option == 1) {
                    // Select / unlock & rank‑up
                    Perk state = player.getPerkManager().get(pw);
                    int cur = (state == null ? 0 : state.getLevel());
                    int max = (state == null ? pw.getMaxLevel() : state.getMaxLevel());
                    if (cur >= max) {
                        player.sendMessage("<red>Already at max (" + max + ").");
                    } else {
                        if (cur == 0) {
                            player.getPerkManager().unlock(pw);
                        } else {
                            player.getPerkManager().rankUp(pw);
                        }
                        updateLevels(player);
                    }
                } else if (option == 2) {
                    // Info
                    player.sendMessage(pw.getPerk().getName());
                }
            });
        });
    }

    @Override
    public GameInterface getInterface() {
        return GameInterface.PERK_INTERFACE;
    }

    @Override
    public void open(Player player) {
        player.getInterfaceHandler().sendInterface(getInterface());
        player.getPacketDispatcher().sendClientScript(25000);

        for (int cid : COMPONENT_TO_PERK.keySet()) {
            // RIGHT: both ops enabled at once
            player.getPacketDispatcher().sendComponentSettings(
                getInterface(),       // numeric interface ID
                cid,        // component ID
                0,          // start slot
                0,          // end slot
                AccessMask.CLICK_OP1,
                AccessMask.CLICK_OP2
            );
        }

        updateLevels(player);
    }

    private void updateLevels(Player player) {
        int ifId = getInterface().getId();
        COMPONENT_TO_PERK.forEach((iconCid, pw) -> {
            Perk state = player.getPerkManager().get(pw);

            int cur = (state == null ? 0 : state.getLevel());
            int max = (state == null ? pw.getMaxLevel() : state.getMaxLevel());

            int lvlCid = ICON_TO_LEVEL_CID.get(iconCid);
            player.getPacketDispatcher()
                .sendComponentText(ifId, lvlCid, cur + "/" + max);
        });
    }
}
