package org.fc.ultimatePvP.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.fc.ultimatePvP.manager.RoundManager;

public class DamageListener implements Listener {

    private final RoundManager roundManager;

    public DamageListener(RoundManager roundManager) {
        this.roundManager = roundManager;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player))
            return;

        if (!(event.getEntity() instanceof Player))
            return;

        if (!roundManager.isRunning()) {

            event.setCancelled(true);

        }

    }

}