package org.fc.ultimatePvP.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BountyManager {

    private final Set<UUID> bounties = new HashSet<>();

    public boolean isBounty(Player player) {
        return bounties.contains(player.getUniqueId());
    }

    public void addBounty(Player player) {

        if (isBounty(player))
            return;

        bounties.add(player.getUniqueId());

        Bukkit.broadcastMessage("§6⚔ " + player.getName() + " が賞金首になりました！");
    }

    public void removeBounty(Player player) {

        if (!isBounty(player))
            return;

        bounties.remove(player.getUniqueId());

        Bukkit.broadcastMessage("§7" + player.getName() + " の賞金首が解除されました。");
    }

}