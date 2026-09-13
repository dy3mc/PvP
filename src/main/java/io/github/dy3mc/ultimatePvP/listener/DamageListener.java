package io.github.dy3mc.ultimatePvP.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import io.github.dy3mc.ultimatePvP.manager.RoundManager;

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