package org.fc.ultimatePvP.manager;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameManager {

    private final RoundManager roundManager;

    public GameManager(RoundManager roundManager) {
        this.roundManager = roundManager;
    }

    public void checkWinner() {

        if (!roundManager.isRunning())
            return;

        Player winner = null;
        int alive = 0;

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (player.getGameMode() != GameMode.SURVIVAL)
                continue;

            if (player.isDead())
                continue;

            alive++;
            winner = player;
        }

        if (alive == 1 && winner != null) {

            Bukkit.broadcastMessage("§6======================");
            Bukkit.broadcastMessage("§e優勝: §6" + winner.getName());
            Bukkit.broadcastMessage("§6======================");

            winner.sendTitle(
                    "§6VICTORY",
                    "§eあなたの勝利！",
                    10,
                    60,
                    20
            );

            roundManager.stopRound();
        }

    }

}