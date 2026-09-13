package org.fc.ultimatePvP.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.fc.ultimatePvP.manager.GameRule;
import org.fc.ultimatePvP.manager.RoundManager;

public class GameRuleListener implements Listener {

    private final RoundManager roundManager;

    public GameRuleListener(RoundManager roundManager) {
        this.roundManager = roundManager;
    }

    @EventHandler(
            priority = EventPriority.HIGH,
            ignoreCancelled = true
    )
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!roundManager.isRunning())
            return;

        if (!(event.getDamager() instanceof Player))
            return;

        if (!(event.getEntity() instanceof Player))
            return;

        if (roundManager.getCurrentRule() == GameRule.DOUBLE_DAMAGE) {

            event.setDamage(event.getDamage() * 2.0);

        }

    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {

        if (!roundManager.isRunning())
            return;

        if (!(event.getEntity() instanceof Player))
            return;

        if (roundManager.getCurrentRule() != GameRule.LOW_GRAVITY)
            return;

        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {

            event.setDamage(event.getDamage() * 0.5);

        }

    }

}