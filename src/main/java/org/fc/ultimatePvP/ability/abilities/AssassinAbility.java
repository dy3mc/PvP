package org.fc.ultimatePvP.ability.abilities;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;
import org.fc.ultimatePvP.ability.AbilityContext;
import org.fc.ultimatePvP.ability.AbilityHandler;

public class AssassinAbility implements AbilityHandler {

    private static final double DAMAGE_MULTIPLIER = 1.5;

    private static final long COOLDOWN = 3000L;
    private static final String COOLDOWN_KEY = "ASSASSIN";

    private final AbilityContext context;

    public AssassinAbility(AbilityContext context) {
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
         * 背後判定
         */
        if (!isBehind(attacker, victim))
            return;

        /*
         * クールダウン中
         */
        if (context.getCooldown().isOnCooldown(
                attacker,
                COOLDOWN_KEY
        )) {

            context.getEffect().actionBar(
                    attacker,
                    "§cバックスタブ: §eあと "
                            + context.getCooldown()
                            .getRemainingText(
                                    attacker,
                                    COOLDOWN_KEY
                            )
            );

            return;
        }

        /*
         * バックスタブ発動
         */
        event.setDamage(
                event.getDamage() * DAMAGE_MULTIPLIER
        );

        /*
         * クールダウン開始
         */
        context.getCooldown().setCooldown(
                attacker,
                COOLDOWN_KEY,
                COOLDOWN
        );

        /*
         * 発動表示
         */
        context.getEffect().actionBar(
                attacker,
                "§c§lバックスタブ！"
        );

        /*
         * 発動音
         */
        context.getEffect().sound(
                attacker,
                Sound.ENTITY_PLAYER_ATTACK_CRIT,
                1.0f,
                0.8f
        );

    }

    private boolean isBehind(
            Player attacker,
            LivingEntity victim
    ) {

        Location victimLoc =
                victim.getLocation();

        Vector victimDirection =
                victimLoc.getDirection()
                        .normalize();

        Vector toAttacker =
                attacker.getLocation()
                        .toVector()
                        .subtract(
                                victimLoc.toVector()
                        )
                        .normalize();

        return victimDirection.dot(toAttacker) < -0.5;

    }

    @Override
    public void onGive(Player player) {

        context.getEffect().message(
                player,
                "§5あなたはアサシンになった！"
        );

    }

}