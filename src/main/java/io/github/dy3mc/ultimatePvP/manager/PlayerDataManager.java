package io.github.dy3mc.ultimatePvP.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager {

    private final HashMap<UUID, PlayerData> data = new HashMap<>();

    public PlayerData get(Player player) {

        return data.computeIfAbsent(
                player.getUniqueId(),
                uuid -> new PlayerData()
        );
    }

    public void remove(Player player) {
        data.remove(player.getUniqueId());
    }

}