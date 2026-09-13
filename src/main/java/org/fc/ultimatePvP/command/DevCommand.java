package org.fc.ultimatePvP.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.fc.ultimatePvP.UltimatePvP;
import org.fc.ultimatePvP.ability.Ability;
import org.fc.ultimatePvP.ability.AbilityManager;
import org.fc.ultimatePvP.manager.AwakenManager;
import org.fc.ultimatePvP.manager.BountyManager;
import org.fc.ultimatePvP.manager.KillStreakManager;
import org.fc.ultimatePvP.manager.RoundManager;
import org.fc.ultimatePvP.gui.AbilityGUI;

public class DevCommand implements CommandExecutor {

    private final AbilityManager abilityManager;
    private final KillStreakManager killStreakManager;
    private final BountyManager bountyManager;
    private final AwakenManager awakenManager;
    private final RoundManager roundManager;
    private final UltimatePvP plugin;

    public DevCommand(
            AbilityManager abilityManager,
            KillStreakManager killStreakManager,
            BountyManager bountyManager,
            AwakenManager awakenManager,
            RoundManager roundManager,
            UltimatePvP plugin
    ) {

        this.abilityManager = abilityManager;
        this.killStreakManager = killStreakManager;
        this.bountyManager = bountyManager;
        this.awakenManager = awakenManager;
        this.roundManager = roundManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        if (!(sender instanceof Player player))
            return true;

        if (args.length == 0) {

            player.sendMessage("§6====== UltimatePvP Dev ======");
            player.sendMessage("§e/dev ability <能力>");
            player.sendMessage("§e/dev random");
            player.sendMessage("§e/dev awaken");
            player.sendMessage("§e/dev streak <数>");
            player.sendMessage("§e/dev bounty");
            player.sendMessage("§e/dev reload");
            player.sendMessage("§e/dev gui");

            return true;
        }

        switch (args[0].toLowerCase()) {

            case "ability" -> {

                if (args.length < 2) {

                    player.sendMessage("§c/dev ability <能力名>");

                    return true;
                }

                try {

                    Ability ability =
                            Ability.valueOf(args[1].toUpperCase());

                    abilityManager.setAbility(player, ability);

                } catch (IllegalArgumentException e) {

                    player.sendMessage("§cその能力は存在しません。");

                }

            }

            case "random" -> {

                abilityManager.giveRandomAbility(player);

            }

            case "awaken" -> {

                awakenManager.awaken(player);

            }

            case "streak" -> {

                if (args.length < 2)
                    return true;

                try {

                    int streak =
                            Integer.parseInt(args[1]);

                    killStreakManager.setStreak(
                            player,
                            streak
                    );

                    player.sendMessage("§aストリーク変更");

                } catch (NumberFormatException ignored) {
                }

            }

            case "bounty" -> {

                if (bountyManager.isBounty(player)) {

                    bountyManager.removeBounty(player);

                    player.sendMessage("§c賞金首解除");

                } else {

                    bountyManager.addBounty(player);

                    player.sendMessage("§6賞金首になりました");

                }

            }

            case "reload" -> {

                plugin.reloadConfig();

                player.sendMessage("§aConfig Reload");

            }

            case "gui" -> {

                AbilityGUI.open(player);

            }

            case "mode" -> {

                boolean mode =
                        plugin.isDeveloperMode();

                plugin.getConfig().set(
                        "developer-mode",
                        !mode
                );

                plugin.saveConfig();

                player.sendMessage(
                        "DeveloperMode : "
                                + (!mode)
                );

            }

        }

        return true;
    }

}