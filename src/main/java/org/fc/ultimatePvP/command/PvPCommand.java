package org.fc.ultimatePvP.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.fc.ultimatePvP.manager.RoundManager;

public class PvPCommand implements CommandExecutor {

    private final RoundManager roundManager;

    public PvPCommand(RoundManager roundManager) {
        this.roundManager = roundManager;
    }

    @Override
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {

        if (args.length == 0) {
            sender.sendMessage("§c/pvp start");
            return true;
        }

        if (args[0].equalsIgnoreCase("start")) {

            roundManager.startRound();

            sender.sendMessage("§a試合を開始しました。");

            return true;
        }

        if (args[0].equalsIgnoreCase("stop")) {

            roundManager.stopRound();

            sender.sendMessage("§c試合を終了しました。");

            return true;
        }

        return true;
    }
}