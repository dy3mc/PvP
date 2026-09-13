package org.fc.ultimatePvP.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.fc.ultimatePvP.ability.*;

public class AbilityInteractListener implements Listener {

    private final AbilityManager abilityManager;
    private final AbilityRegistry registry;

    public AbilityInteractListener(
            AbilityManager abilityManager,
            AbilityRegistry registry) {

        this.abilityManager = abilityManager;
        this.registry = registry;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        Ability ability = abilityManager.getAbility(player);

        if (ability == null)
            return;

        AbilityHandler handler = registry.getHandler(ability);

        if (handler == null)
            return;

        handler.onInteract(event);
    }
}