package com.zenyte.game.world.entity.player.action.combat.melee;

import com.zenyte.cores.WorldThread;
import com.zenyte.game.item.degradableitems.DegradeType;
import com.zenyte.game.util.*;
import com.zenyte.game.world.entity.Entity;
import com.zenyte.game.world.entity.masks.*;
import com.zenyte.game.world.entity.npc.*;
import com.zenyte.game.world.entity.player.Player;
import com.zenyte.game.world.entity.player.action.combat.CombatType;
import com.zenyte.game.world.entity.player.action.combat.MeleeCombat;
import com.zenyte.game.world.entity.player.action.combat.SpecialAttack;
import com.zenyte.game.world.entity.player.action.combat.SpecialAttackScript;
import com.zenyte.game.world.entity.player.container.impl.equipment.EquipmentSlot;
import com.zenyte.game.world.entity.player.perk.*;
import com.zenyte.game.world.region.Area;
import com.zenyte.game.world.region.area.plugins.PlayerCombatPlugin;

/**
 * @author Kris | 17/06/2019 16:27
 * @see <a href="https://www.rune-server.ee/members/kris/">Rune-Server profile</a>
 */
public class DragonSpearCombat extends MeleeCombat {
    public DragonSpearCombat(final Entity target) {
        super(target);
    }

    @Override
    public int processWithDelay() {
        if (!target.startAttacking(player, CombatType.MELEE)) {
            return -1;
        }
        if (!isWithinAttackDistance()) {
            return 0;
        }
        if (!canAttack()) {
            return -1;
        }
        final Area area = player.getArea();
        if (area instanceof PlayerCombatPlugin) {
            ((PlayerCombatPlugin) area).onAttack(player, target, "Melee");
        }
        if (player.getCombatDefinitions().isUsingSpecial()) {
            final SpecialAttack spec = SpecialAttack.SPECIAL_ATTACKS.get(player.getEquipment().getId(EquipmentSlot.WEAPON.getSlot()));
            if (spec == SpecialAttack.SHOVE && target instanceof Player) {
                final long lastShoveTick = ((Player) target).getNumericTemporaryAttribute("Last shove push").longValue();
                if (WorldThread.WORLD_CYCLE < lastShoveTick) {
                    return 0;
                }
            }
        }
        addAttackedByDelay(player, target);
        final int delay = special();
        if (delay != -2) {
            return delay == SpecialAttackScript.WEAPON_SPEED ? getSpeed() : delay;
        }
        sendSoundEffect();
        final Hit hit = getHit(player, target, 1, 1, 1, false);
        extra(hit);
        addPoisonTask(hit.getDamage(), 0);
        delayHit(0, hit);
        animate();
        player.getChargesManager().removeCharges(DegradeType.OUTGOING_HIT);
        resetFlag();
        // ** echo trigger **
        final Perk echo = player.getPerkManager().get(PerkWrapper.PRIMAL_ECHO);
        if (echo != null && echo.valid() && target instanceof NPC && Utils.randomDouble() < echo.getEffect()) {
            player.setGraphics(new Graphics(1982));
            int halfMax = getMaxHit(player,1,1,false)/2;
            final Hit echoHit = new Hit(
                player,
                getRandomHit(player,target,halfMax,1, player.getCombatDefinitions().getAttackType()),
                HitType.MELEE
            );
            delayHit(0, echoHit);
        }
        checkIfShouldTerminate();
        addExtraEffect(hit);
        return getSpeed();
    }
}
