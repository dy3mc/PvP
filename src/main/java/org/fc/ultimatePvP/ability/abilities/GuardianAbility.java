package org.fc.ultimatePvP.ability.abilities;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.fc.ultimatePvP.ability.AbilityContext;
import org.fc.ultimatePvP.ability.AbilityHandler;

public class GuardianAbility implements AbilityHandler {

    private static final double DAMAGE_REDUCTION = 0.20;

    private final AbilityContext context;

    public GuardianAbility(AbilityContext context) {
        this.context = context;
    }

    @Override
    public void onDamaged(EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof Player player))
            return;

        /*
         * ダメージを20%軽減
         *
         * 100%の確率で常時発動
         */
        event.setDamage(
                event.getDamage()
                        * (1.0 - DAMAGE_REDUCTION)
        );

    }

    @Override
    public void onGive(Player player) {

        context.getEffect().message(
                player,
                "§bあなたはガーディアンになった！"
        );

    }

}