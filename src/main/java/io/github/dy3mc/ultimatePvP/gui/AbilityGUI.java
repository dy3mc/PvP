package io.github.dy3mc.ultimatePvP.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import io.github.dy3mc.ultimatePvP.ability.Ability;

public class AbilityGUI {

    public static final String TITLE = "§8能力選択";

    public static void open(Player player) {

        Inventory inv = Bukkit.createInventory(null, 27, TITLE);

        int slot = 9;

        for (Ability ability : Ability.values()) {

            ItemStack item = new ItemStack(Material.NETHER_STAR);

            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName("§e" + ability.getDisplayName());

            meta.setLore(java.util.List.of(
                    "§7" + ability.getDescription(),
                    "",
                    "§aクリックで選択"
            ));

            item.setItemMeta(meta);

            inv.setItem(slot, item);

            slot++;
        }

        player.openInventory(inv);
    }

}