package io.github.dy3mc.ultimatePvP.listener;

import io.github.dy3mc.ultimatePvP.ability.Ability;
import io.github.dy3mc.ultimatePvP.ability.AbilityHandler;
import io.github.dy3mc.ultimatePvP.ability.AbilityManager;
import io.github.dy3mc.ultimatePvP.ability.AbilityRegistry;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import io.github.dy3mc.ultimatePvP.ability.*;

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