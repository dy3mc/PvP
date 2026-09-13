package io.github.dy3mc.ultimatePvP.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import io.github.dy3mc.ultimatePvP.manager.AwakenManager;
import io.github.dy3mc.ultimatePvP.manager.BountyManager;
import io.github.dy3mc.ultimatePvP.manager.GameManager;
import io.github.dy3mc.ultimatePvP.manager.KillStreakManager;

public class DeathListener implements Listener {

    private final KillStreakManager killStreakManager;
    private final BountyManager bountyManager;
    private final AwakenManager awakenManager;
    private final GameManager gameManager;

    public DeathListener(KillStreakManager killStreakManager,
                         BountyManager bountyManager,
                         AwakenManager awakenManager,
                         GameManager gameManager) {

        this.killStreakManager = killStreakManager;
        this.bountyManager = bountyManager;
        this.awakenManager = awakenManager;
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player victim = event.getEntity();

        boolean wasBounty = bountyManager.isBounty(victim);

        killStreakManager.reset(victim);
        bountyManager.removeBounty(victim);
        awakenManager.reset(victim);

        Player killer = victim.getKiller();

        if (killer != null) {

            killStreakManager.addKill(killer);

            if (killStreakManager.getStreak(killer) >= 5) {
                bountyManager.addBounty(killer);
            }

            if (wasBounty) {
                killer.sendMessage("§6賞金首を討伐しました！");
            }
        }

        gameManager.checkWinner();
    }
}