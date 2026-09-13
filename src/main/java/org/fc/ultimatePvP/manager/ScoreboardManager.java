package org.fc.ultimatePvP.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.fc.ultimatePvP.UltimatePvP;
import org.fc.ultimatePvP.ability.Ability;
import org.fc.ultimatePvP.ability.AbilityManager;

public class ScoreboardManager {

    private final UltimatePvP plugin;
    private final AbilityManager abilityManager;
    private final KillStreakManager killStreakManager;
    private final BountyManager bountyManager;
    private final AwakenManager awakenManager;
    private final ActionBarManager actionBarManager;

    public ScoreboardManager(
            UltimatePvP plugin,
            AbilityManager abilityManager,
            KillStreakManager killStreakManager,
            BountyManager bountyManager,
            AwakenManager awakenManager,
            ActionBarManager actionBarManager
    ) {

        this.plugin = plugin;
        this.abilityManager = abilityManager;
        this.killStreakManager = killStreakManager;
        this.bountyManager = bountyManager;
        this.awakenManager = awakenManager;
        this.actionBarManager = actionBarManager;

    }

    public void start() {

        new BukkitRunnable() {

            @Override
            public void run() {

                for (Player player : Bukkit.getOnlinePlayers()) {

                    update(player);

                }

            }

        }.runTaskTimer(plugin,0,20);

    }

    private void update(Player player) {

        if (actionBarManager.isLocked(player))
            return;

        Ability ability = abilityManager.getAbility(player);

        player.sendActionBar(
                "§6能力: §e" +
                        (ability == null ? "なし" : ability.getDisplayName()) +
                        " §7| §c連続キル: " +
                        killStreakManager.getStreak(player)
        );

    }

}