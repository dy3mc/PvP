package io.github.dy3mc.ultimatePvP.ability.abilities;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import io.github.dy3mc.ultimatePvP.ability.AbilityContext;
import io.github.dy3mc.ultimatePvP.ability.AbilityHandler;

public class VampireAbility implements AbilityHandler {

    private final AbilityContext context;

    public VampireAbility(AbilityContext context) {
        this.context = context;
    }

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player player))
            return;

        /*
         * 最大HPを取得
         */
        AttributeInstance attribute =
                player.getAttribute(
                        Attribute.GENERIC_MAX_HEALTH
                );

        if (attribute == null)
            return;

        double maxHealth =
                attribute.getValue();

        /*
         * HPを1回復
         *
         * 最大HPを超えないようにする
         */
        player.setHealth(
                Math.min(
                        maxHealth,
                        player.getHealth() + 1.0
                )
        );

        /*
         * 回復表示
         */
        context.getEffect().actionBar(
                player,
                "§c❤ +1"
        );

    }

    @Override
    public void onGive(Player player) {

        context.getEffect().message(
                player,
                "§cあなたはヴァンパイアになった！"
        );

    }

}