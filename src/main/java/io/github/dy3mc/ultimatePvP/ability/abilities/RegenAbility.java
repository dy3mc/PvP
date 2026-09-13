package io.github.dy3mc.ultimatePvP.ability.abilities;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import io.github.dy3mc.ultimatePvP.ability.AbilityContext;
import io.github.dy3mc.ultimatePvP.ability.AbilityHandler;

public class RegenAbility implements AbilityHandler {

    private final AbilityContext context;

    public RegenAbility(AbilityContext context) {
        this.context = context;
    }

    @Override
    public void onDamaged(EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof Player player))
            return;

        double maxHealth =
                player.getAttribute(
                        Attribute.GENERIC_MAX_HEALTH
                ).getValue();

        player.setHealth(
                Math.min(
                        maxHealth,
                        player.getHealth() + 1.0
                )
        );

        player.sendActionBar("§a❤ +1");

    }

}