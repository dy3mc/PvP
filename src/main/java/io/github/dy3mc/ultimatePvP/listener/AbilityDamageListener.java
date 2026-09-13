package io.github.dy3mc.ultimatePvP.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import io.github.dy3mc.ultimatePvP.ability.Ability;
import io.github.dy3mc.ultimatePvP.ability.AbilityHandler;
import io.github.dy3mc.ultimatePvP.ability.AbilityManager;
import io.github.dy3mc.ultimatePvP.ability.AbilityRegistry;

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