package org.fc.ultimatePvP.ability.abilities;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.fc.ultimatePvP.ability.AbilityContext;
import org.fc.ultimatePvP.ability.AbilityHandler;

public class SpeedAbility implements AbilityHandler {

    private static final double SPEED_VALUE = 0.13;
    private static final double DEFAULT_SPEED_VALUE = 0.10;

    private final AbilityContext context;

    public SpeedAbility(AbilityContext context) {
        this.context = context;
    }

    @Override
    public void onGive(Player player) {

        AttributeInstance attribute =
                player.getAttribute(
                        Attribute.GENERIC_MOVEMENT_SPEED
                );

        if (attribute == null)
            return;

        attribute.setBaseValue(
                SPEED_VALUE
        );

        context.getEffect().message(
                player,
                "§bあなたはスピードになった！"
        );

    }

    @Override
    public void onRemove(Player player) {

        AttributeInstance attribute =
                player.getAttribute(
                        Attribute.GENERIC_MOVEMENT_SPEED
                );

        if (attribute == null)
            return;

        attribute.setBaseValue(
                DEFAULT_SPEED_VALUE
        );

    }

}