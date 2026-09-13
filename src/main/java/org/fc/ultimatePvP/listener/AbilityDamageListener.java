package org.fc.ultimatePvP.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.fc.ultimatePvP.ability.Ability;
import org.fc.ultimatePvP.ability.AbilityHandler;
import org.fc.ultimatePvP.ability.AbilityManager;
import org.fc.ultimatePvP.ability.AbilityRegistry;

public class AbilityDamageListener implements Listener {

    private final AbilityManager abilityManager;
    private final AbilityRegistry registry;

    public AbilityDamageListener(
            AbilityManager abilityManager,
            AbilityRegistry registry
    ) {

        this.abilityManager = abilityManager;
        this.registry = registry;

    }

    @EventHandler(ignoreCancelled = true)
    public void onDamage(
            EntityDamageByEntityEvent event
    ) {

        /*
         * =========================
         * 攻撃側の能力
         * =========================
         */

        if (event.getDamager() instanceof Player attacker) {

            Ability attackerAbility =
                    abilityManager.getAbility(attacker);

            if (attackerAbility != null) {

                AbilityHandler handler =
                        registry.getHandler(attackerAbility);

                if (handler != null) {

                    handler.onDamage(event);

                }

            }

        }

        /*
         * =========================
         * 防御側の能力
         * =========================
         */

        if (event.getEntity() instanceof Player victim) {

            Ability victimAbility =
                    abilityManager.getAbility(victim);

            if (victimAbility != null) {

                AbilityHandler handler =
                        registry.getHandler(victimAbility);

                if (handler != null) {

                    handler.onDamaged(event);

                }

            }

        }

    }

}