package io.github.dy3mc.ultimatePvP.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import io.github.dy3mc.ultimatePvP.ability.Ability;
import io.github.dy3mc.ultimatePvP.ability.AbilityManager;
import io.github.dy3mc.ultimatePvP.gui.AbilityGUI;

public class AbilityGUIListener implements Listener {

    private final AbilityManager abilityManager;

    public AbilityGUIListener(AbilityManager abilityManager) {
        this.abilityManager = abilityManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals(AbilityGUI.TITLE))
            return;

        event.setCancelled(true);

        if (!(event.getWhoClicked() instanceof Player player))
            return;

        ItemStack item = event.getCurrentItem();

        if (item == null)
            return;

        if (!item.hasItemMeta())
            return;

        String name = item.getItemMeta().getDisplayName();

        name = name.replace("§e", "");

        for (Ability ability : Ability.values()) {

            if (ability.getDisplayName().equals(name)) {

                abilityManager.setAbility(player, ability);

                player.closeInventory();

                return;
            }

        }

    }

}