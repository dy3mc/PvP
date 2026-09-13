package org.fc.ultimatePvP.manager;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AwakenManager {

    private final Set<UUID> awakened = new HashSet<>();

    public boolean isAwakened(Player player) {
        return awakened.contains(player.getUniqueId());
    }

    public void awaken(Player player) {

        if (isAwakened(player))
            return;

        awakened.add(player.getUniqueId());

        player.sendTitle(
                "§6覚醒",
                "§e限界を超えろ！",
                10,
                50,
                20
        );

        player.playSound(
                player.getLocation(),
                Sound.ITEM_TOTEM_USE,
                1,
                1
        );

        player.getWorld().spawnParticle(
                Particle.TOTEM_OF_UNDYING,
                player.getLocation(),
                100
        );

        player.addPotionEffect(new PotionEffect(
                PotionEffectType.REGENERATION,
                20 * 8,
                1
        ));

        player.addPotionEffect(new PotionEffect(
                PotionEffectType.SPEED,
                20 * 8,
                1
        ));

        player.addPotionEffect(new PotionEffect(
                PotionEffectType.HASTE,
                20 * 8,
                1
        ));
    }

    public void reset(Player player) {
        awakened.remove(player.getUniqueId());
    }
}