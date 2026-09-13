package org.fc.ultimatePvP.ability.abilities;

import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.fc.ultimatePvP.ability.AbilityContext;
import org.fc.ultimatePvP.ability.AbilityHandler;

public class PyroAbility implements AbilityHandler {

    private static final int FIRE_TICKS = 80;

    private final AbilityContext context;

    public PyroAbility(AbilityContext context) {
        this.context = context;
    }

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player attacker))
            return;

        LivingEntity victim =
                context.getUtil().getTarget(event);

        if (victim == null)
            return;

        /*
         * 4秒間炎上
         */
        victim.setFireTicks(FIRE_TICKS);

        /*
         * 発動表示
         */
        context.getEffect().actionBar(
                attacker,
                "§6🔥 炎上！"
        );

        /*
         * 発動音
         */
        context.getEffect().sound(
                attacker,
                Sound.ITEM_FIRECHARGE_USE,
                0.8f,
                1.0f
        );

    }

    @Override
    public void onGive(Player player) {

        context.getEffect().message(
                player,
                "§6あなたはパイロになった！"
        );

    }

}