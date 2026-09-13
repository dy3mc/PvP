package org.fc.ultimatePvP.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.fc.ultimatePvP.ability.Ability;
import org.fc.ultimatePvP.ability.AbilityHandler;
import org.fc.ultimatePvP.ability.AbilityManager;
import org.fc.ultimatePvP.ability.AbilityRegistry;

public class AbilityMoveListener implements Listener {

    private final AbilityManager abilityManager;
    private final AbilityRegistry registry;

    public AbilityMoveListener(
            AbilityManager abilityManager,
            AbilityRegistry registry
    ) {

        this.abilityManager = abilityManager;
        this.registry = registry;

    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        Ability ability =
                abilityManager.getAbility(player);

        if (ability == null)
            return;

        AbilityHandler handler =
                registry.getHandler(ability);

        if (handler == null)
            return;

        handler.onMove(event);

    }

}