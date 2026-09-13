package org.fc.ultimatePvP.manager;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GameRuleManager {

    private final RoundManager roundManager;

    /*
     * この試合でGameRuleManagerが効果を付けたプレイヤー
     */
    private final Set<UUID> speedPlayers = new HashSet<>();
    private final Set<UUID> lowGravityPlayers = new HashSet<>();

    public GameRuleManager(RoundManager roundManager) {
        this.roundManager = roundManager;
    }

    /**
     * 試合開始時に全員へ現在のルールを適用
     */
    public void applyRuleToAll(
            Iterable<? extends Player> players
    ) {

        for (Player player : players) {
            applyRule(player);
        }

    }

    /**
     * プレイヤー1人に現在のルールを適用
     */
    public void applyRule(Player player) {

        GameRule rule = roundManager.getCurrentRule();

        if (rule == null)
            return;

        switch (rule) {

            case SPEED -> applySpeed(player);

            case LOW_GRAVITY -> applyLowGravity(player);

            case DOUBLE_DAMAGE -> {
                // ダメージ処理はGameRuleListenerで行う
            }

            case NO_RULE -> {
                // 何もしない
            }

        }

    }

    /**
     * スピードルール
     */
    private void applySpeed(Player player) {

        player.addPotionEffect(
                new PotionEffect(
                        PotionEffectType.SPEED,
                        Integer.MAX_VALUE,
                        0,
                        false,
                        false,
                        true
                )
        );

        speedPlayers.add(player.getUniqueId());

    }

    /**
     * 低重力ルール
     */
    private void applyLowGravity(Player player) {

        player.addPotionEffect(
                new PotionEffect(
                        PotionEffectType.JUMP_BOOST,
                        Integer.MAX_VALUE,
                        1,
                        false,
                        false,
                        true
                )
        );

        lowGravityPlayers.add(player.getUniqueId());

    }

    /**
     * 試合終了時に全員から
     * GameRuleによって付けた効果だけを解除
     */
    public void removeRuleFromAll(
            Iterable<? extends Player> players
    ) {

        for (Player player : players) {
            removeRule(player);
        }

        speedPlayers.clear();
        lowGravityPlayers.clear();

    }

    /**
     * プレイヤー1人からGameRuleの効果を解除
     */
    public void removeRule(Player player) {

        UUID uuid = player.getUniqueId();

        if (speedPlayers.contains(uuid)) {

            player.removePotionEffect(
                    PotionEffectType.SPEED
            );

        }

        if (lowGravityPlayers.contains(uuid)) {

            player.removePotionEffect(
                    PotionEffectType.JUMP_BOOST
            );

        }

    }

}