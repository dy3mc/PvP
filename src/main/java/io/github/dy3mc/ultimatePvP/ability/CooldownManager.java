package io.github.dy3mc.ultimatePvP.ability;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<String, Map<UUID, Long>> cooldowns =
            new HashMap<>();

    /**
     * クールダウンを設定
     *
     * @param player プレイヤー
     * @param key 能力などの識別名
     * @param milliseconds クールダウン時間（ミリ秒）
     */
    public void setCooldown(
            Player player,
            String key,
            long milliseconds
    ) {

        cooldowns
                .computeIfAbsent(
                        key,
                        k -> new HashMap<>()
                )
                .put(
                        player.getUniqueId(),
                        System.currentTimeMillis()
                                + milliseconds
                );

    }

    /**
     * クールダウン中か確認
     */
    public boolean isOnCooldown(
            Player player,
            String key
    ) {

        Map<UUID, Long> map =
                cooldowns.get(key);

        if (map == null)
            return false;

        Long end =
                map.get(player.getUniqueId());

        if (end == null)
            return false;

        if (System.currentTimeMillis() >= end) {

            map.remove(player.getUniqueId());

            return false;
        }

        return true;

    }

    /**
     * 残り時間をミリ秒で取得
     */
    public long getRemaining(
            Player player,
            String key
    ) {

        Map<UUID, Long> map =
                cooldowns.get(key);

        if (map == null)
            return 0;

        Long end =
                map.get(player.getUniqueId());

        if (end == null)
            return 0;

        long remaining =
                end - System.currentTimeMillis();

        if (remaining <= 0) {

            map.remove(player.getUniqueId());

            return 0;
        }

        return remaining;

    }

    /**
     * 残り時間を秒単位で取得
     *
     * 例：
     * 2500ms → 2.5秒
     */
    public double getRemainingSeconds(
            Player player,
            String key
    ) {

        return getRemaining(
                player,
                key
        ) / 1000.0;

    }

    /**
     * 残り時間を表示用の文字列で取得
     *
     * 例：
     * 2.5秒
     */
    public String getRemainingText(
            Player player,
            String key
    ) {

        double seconds =
                getRemainingSeconds(
                        player,
                        key
                );

        if (seconds <= 0)
            return "準備完了";

        return String.format(
                "%.1f秒",
                seconds
        );

    }

    /**
     * 特定プレイヤーの特定クールダウンを解除
     */
    public void clearCooldown(
            Player player,
            String key
    ) {

        Map<UUID, Long> map =
                cooldowns.get(key);

        if (map == null)
            return;

        map.remove(
                player.getUniqueId()
        );

    }

    /**
     * プレイヤーの全クールダウンを解除
     */
    public void clearPlayer(
            Player player
    ) {

        UUID uuid =
                player.getUniqueId();

        for (Map<UUID, Long> map :
                cooldowns.values()) {

            map.remove(uuid);

        }

    }

}