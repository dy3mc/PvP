package org.fc.ultimatePvP.ability.abilities;

import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.fc.ultimatePvP.ability.AbilityContext;
import org.fc.ultimatePvP.ability.AbilityHandler;

public class ThunderAbility implements AbilityHandler {

    private static final double CHANCE = 0.15;
    private static final double BONUS_DAMAGE = 2.0;

    private static final long COOLDOWN = 3000L;
    private static final String COOLDOWN_KEY = "THUNDER";

    private final AbilityContext context;

    public ThunderAbility(AbilityContext context) {
        this.context = context;
    }

    @Override
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player player))
            return;

        LivingEntity victim =
                context.getUtil().getTarget(event);

        if (victim == null)
            return;

        /*
         * クールダウン中
         */
        if (context.getCooldown().isOnCooldown(
                player,
                COOLDOWN_KEY
        )) {

            context.getEffect().actionBar(
                    player,
                    "§e⚡ 雷: §cあと "
                            + context.getCooldown()
                            .getRemainingText(
                                    player,
                                    COOLDOWN_KEY
                            )
            );

            return;
        }

        /*
         * 15%の発動判定
         */
        if (context.getRandom().nextDouble() > CHANCE)
            return;

        /*
         * 雷エフェクト
         */
        victim.getWorld().strikeLightningEffect(
                victim.getLocation()
        );

        /*
         * 追加ダメージ
         */
        event.setDamage(
                event.getDamage() + BONUS_DAMAGE
        );

        /*
         * クールダウン開始
         */
        context.getCooldown().setCooldown(
                player,
                COOLDOWN_KEY,
                COOLDOWN
        );

        /*
         * 発動表示
         */
        context.getEffect().actionBar(
                player,
                "§e⚡ 雷が落ちた！"
        );

        /*
         * 発動音
         */
        context.getEffect().sound(
                player,
                Sound.ENTITY_LIGHTNING_BOLT_THUNDER,
                0.8f,
                1.2f
        );

        /*
         * 対象がプレイヤーなら通知
         */
        if (victim instanceof Player target) {

            context.getEffect().message(
                    target,
                    "§e⚡ 雷が落ちた！"
            );

        }

    }

}