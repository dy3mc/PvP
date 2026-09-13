package io.github.dy3mc.ultimatePvP.manager;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import io.github.dy3mc.ultimatePvP.UltimatePvP;
import io.github.dy3mc.ultimatePvP.ability.AbilityManager;

import java.util.Random;

public class RoundManager {

    private final UltimatePvP plugin;
    private final AbilityManager abilityManager;

    private GameRuleManager gameRuleManager;

    private boolean running = false;

    private GameRule currentRule;

    public RoundManager(
            UltimatePvP plugin,
            AbilityManager abilityManager
    ) {

        this.plugin = plugin;
        this.abilityManager = abilityManager;

    }

    public boolean isRunning() {
        return running;
    }

    public GameRule getCurrentRule() {
        return currentRule;
    }

    public void setGameRuleManager(
            GameRuleManager gameRuleManager
    ) {

        this.gameRuleManager = gameRuleManager;

    }

    public void startRound() {

        if (running)
            return;

        currentRule =
                GameRule.values()[
                        new Random().nextInt(
                                GameRule.values().length
                        )
                        ];

        Bukkit.broadcastMessage("§6====================");
        Bukkit.broadcastMessage("§e今回のルール");
        Bukkit.broadcastMessage(
                currentRule.getDisplayName()
        );
        Bukkit.broadcastMessage("§6====================");

        new BukkitRunnable() {

            int count = 5;

            @Override
            public void run() {

                if (count == 0) {

                    running = true;

                    Bukkit.broadcastMessage(
                            "§cPvP START!"
                    );

                    /*
                     * 能力を配布
                     */
                    abilityManager.giveRandomAbilityToAll(
                            Bukkit.getOnlinePlayers()
                    );

                    /*
                     * ゲームルールを適用
                     */
                    if (gameRuleManager != null) {

                        gameRuleManager.applyRuleToAll(
                                Bukkit.getOnlinePlayers()
                        );

                    }

                    /*
                     * START演出
                     */
                    for (Player player :
                            Bukkit.getOnlinePlayers()) {

                        player.sendTitle(
                                "§cSTART",
                                "",
                                0,
                                30,
                                10
                        );

                    }

                    cancel();

                    return;
                }

                /*
                 * カウントダウン
                 */
                for (Player player :
                        Bukkit.getOnlinePlayers()) {

                    player.sendTitle(
                            "§e" + count,
                            "準備してください",
                            0,
                            20,
                            0
                    );

                    player.playSound(
                            player.getLocation(),
                            Sound.BLOCK_NOTE_BLOCK_PLING,
                            1,
                            1
                    );

                }

                count--;

            }

        }.runTaskTimer(
                plugin,
                0L,
                20L
        );

    }

    public void stopRound() {

        if (!running)
            return;

        running = false;

        /*
         * ゲームルールの効果を解除
         */
        if (gameRuleManager != null) {

            gameRuleManager.removeRuleFromAll(
                    Bukkit.getOnlinePlayers()
            );

        }

        /*
         * 能力を解除
         */
        abilityManager.removeAllAbilities(
                Bukkit.getOnlinePlayers()
        );

        Bukkit.broadcastMessage(
                "§7試合終了"
        );

        /*
         * 現在のルールをリセット
         */
        currentRule = null;

    }

}