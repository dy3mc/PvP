package io.github.dy3mc.ultimatePvP.manager;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import io.github.dy3mc.ultimatePvP.UltimatePvP;

import java.util.HashMap;
import java.util.UUID;

public class ActionBarManager {

    private final UltimatePvP plugin;

    // trueなら一時メッセージ表示中
    private final HashMap<UUID, Boolean> locked = new HashMap<>();

    public ActionBarManager(UltimatePvP plugin) {
        this.plugin = plugin;
    }

    public boolean isLocked(Player player) {
        return locked.getOrDefault(player.getUniqueId(), false);
    }

    public void sendTemporary(Player player, String message, int ticks) {

        locked.put(player.getUniqueId(), true);

        player.sendActionBar(message);

        new BukkitRunnable() {

            @Override
            public void run() {

                locked.put(player.getUniqueId(), false);

            }

        }.runTaskLater(plugin, ticks);

    }

}