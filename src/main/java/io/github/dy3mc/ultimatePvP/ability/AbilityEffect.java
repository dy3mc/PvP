package io.github.dy3mc.ultimatePvP.ability;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class AbilityEffect {

    private final AbilityContext context;

    public AbilityEffect(AbilityContext context) {
        this.context = context;
    }

    /**
     * プレイヤーにメッセージを表示
     */
    public void message(
            Player player,
            String message
    ) {

        player.sendMessage(message);

    }

    /**
     * ActionBarを表示
     */
    public void actionBar(
            Player player,
            String message
    ) {

        player.sendActionBar(message);

    }

    /**
     * プレイヤーにサウンドを再生
     */
    public void sound(
            Player player,
            Sound sound,
            float volume,
            float pitch
    ) {

        player.playSound(
                player.getLocation(),
                sound,
                volume,
                pitch
        );

    }

    /**
     * エンティティの位置にパーティクルを表示
     */
    public void particle(
            LivingEntity entity,
            Particle particle,
            int count
    ) {

        entity.getWorld().spawnParticle(
                particle,
                entity.getLocation(),
                count
        );

    }

}