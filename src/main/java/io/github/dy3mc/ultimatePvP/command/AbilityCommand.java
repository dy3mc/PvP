package io.github.dy3mc.ultimatePvP.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import io.github.dy3mc.ultimatePvP.ability.Ability;
import io.github.dy3mc.ultimatePvP.ability.AbilityManager;

public class AbilityCommand implements CommandExecutor {

    private final AbilityManager abilityManager;

    public AbilityCommand(AbilityManager abilityManager) {
        this.abilityManager = abilityManager;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (!(sender instanceof Player player))
            return true;

        // /ability
        if (args.length == 0) {

            Ability ability = abilityManager.getAbility(player);

            if (ability == null) {
                player.sendMessage("§c能力を持っていません。");
                return true;
            }

            player.sendMessage("§6あなたの能力: §e" + ability.getDisplayName());
            player.sendMessage("§7" + ability.getDescription());

            return true;
        }

        // /ability random
        if (args[0].equalsIgnoreCase("random")) {

            abilityManager.giveRandomAbility(player);

            return true;
        }

        // /ability set THUNDER
        if (args[0].equalsIgnoreCase("set")) {

            if (args.length < 2) {

                player.sendMessage("§c/ability set <能力名>");

                return true;
            }

            try {

                Ability ability =
                        Ability.valueOf(args[1].toUpperCase());

                abilityManager.setAbility(player, ability);

            } catch (IllegalArgumentException e) {

                player.sendMessage("§cその能力は存在しません。");

            }

            return true;
        }

        return true;
    }

}