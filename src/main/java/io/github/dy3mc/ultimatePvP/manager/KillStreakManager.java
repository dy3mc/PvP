package io.github.dy3mc.ultimatePvP.manager;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class KillStreakManager {

    private final HashMap<UUID, Integer> streaks = new HashMap<>();

    public int getStreak(Player player) {
        return streaks.getOrDefault(player.getUniqueId(), 0);
    }

    public void addKill(Player player) {

        int streak = getStreak(player) + 1;

        streaks.put(player.getUniqueId(), streak);

        player.sendMessage("§6キルストリーク: §e" + streak);

        updateEffects(player, streak);
    }

    public void reset(Player player) {

        streaks.remove(player.getUniqueId());

        player.removePotionEffect(PotionEffectType.SPEED);
        player.removePotionEffect(PotionEffectType.STRENGTH);
        player.removePotionEffect(PotionEffectType.REGENERATION);
    }

    private void updateEffects(Player player, int streak) {

        if (streak >= 3) {
            player.addPotionEffect(
                    new PotionEffect(
                            PotionEffectType.SPEED,
                            Integer.MAX_VALUE,
                            0,
                            false,
                            false
                    )
            );
        }

        if (streak >= 5) {
            player.addPotionEffect(
                    new PotionEffect(
                            PotionEffectType.STRENGTH,
                            Integer.MAX_VALUE,
                            0,
                            false,
                            false
                    )
            );
        }

        if (streak >= 10) {
            player.addPotionEffect(
                    new PotionEffect(
                            PotionEffectType.REGENERATION,
                            Integer.MAX_VALUE,
                            0,
                            false,
                            false
                    )
            );
        }

    }

    public void setStreak(Player player, int streak) {

        streaks.put(player.getUniqueId(), streak);

        updateEffects(player, streak);
    }

}