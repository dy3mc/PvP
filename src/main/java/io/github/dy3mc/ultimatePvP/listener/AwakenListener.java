package io.github.dy3mc.ultimatePvP.listener;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import io.github.dy3mc.ultimatePvP.manager.AwakenManager;

public class AwakenListener implements Listener {

    private final AwakenManager awakenManager;

    public AwakenListener(AwakenManager awakenManager) {
        this.awakenManager = awakenManager;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player player))
            return;

        double health = player.getHealth() - event.getFinalDamage();

        double max = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)
                .getValue();

        if (health <= max * 0.2) {
            awakenManager.awaken(player);
        }

    }

}