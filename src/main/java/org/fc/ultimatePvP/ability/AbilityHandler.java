package org.fc.ultimatePvP.ability;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public interface AbilityHandler {

    default void onGive(Player player) {
    }

    // ←追加
    default void onRemove(Player player) {
    }

    default void onDamage(EntityDamageByEntityEvent event) {
    }

    default void onDamaged(EntityDamageByEntityEvent event) {
    }

    default void onMove(PlayerMoveEvent event) {
    }

    default void onInteract(PlayerInteractEvent event) {
    }

}