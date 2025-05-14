package com.zenyte.plugins.renewednpc;

import com.zenyte.game.constants.GameInterface;
import com.zenyte.game.world.entity.npc.actions.NPCPlugin;
import com.zenyte.game.world.entity.player.*;
import com.zenyte.game.world.entity.player.teleportsystem.PortalTeleport;
import com.zenyte.game.world.object.*;

/**
 * @author Kris | 25/11/2018 16:11
 * @see <a href="https://www.rune-server.ee/members/kris/">Rune-Server profile</a>
 */
public class ZenyteTeleporter implements ObjectAction {
    @Override
    public void handleObjectAction(Player player, WorldObject object, String name, int optionId, String option) {
        if (option.equals("Teleport")) {
            GameInterface.TELEPORT_MENU.open(player);
        } else if (option.equals("Teleport-previous")) {
            final PortalTeleport teleport = player.getTeleportManager().getLastTeleport();
            if (teleport == null) {
                player.sendMessage("The wizard hasn't teleported you anywhere yet.");
                return;
            }
            player.getTeleportManager().teleportTo(teleport);
        }
    }

    @Override
    public Object[] getObjects() {
        return new Object[]{33384};
    }
}
