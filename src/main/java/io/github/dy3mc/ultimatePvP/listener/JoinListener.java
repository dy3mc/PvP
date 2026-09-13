package io.github.dy3mc.ultimatePvP.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import io.github.dy3mc.ultimatePvP.ability.AbilityManager;

public class JoinListener implements Listener {

    private final AbilityManager abilityManager;

    public JoinListener(AbilityManager abilityManager) {
        this.abilityManager = abilityManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        abilityManager.giveRandomAbility(event.getPlayer());
    }
}