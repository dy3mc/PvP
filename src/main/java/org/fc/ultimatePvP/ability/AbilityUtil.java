package org.fc.ultimatePvP.ability;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.fc.ultimatePvP.UltimatePvP;

public class AbilityUtil {

    private final UltimatePvP plugin;

    public AbilityUtil(UltimatePvP plugin) {
        this.plugin = plugin;
    }

    public LivingEntity getTarget(Entity entity) {

        if (!(entity instanceof LivingEntity living))
            return null;

        if (living instanceof Player)
            return living;

        if (plugin.isDeveloperMode())
            return living;

        return null;
    }

    public LivingEntity getTarget(EntityDamageByEntityEvent event) {
        return getTarget(event.getEntity());
    }

}