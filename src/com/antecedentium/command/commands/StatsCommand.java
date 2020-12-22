package com.antecedentium.command.commands;

import static com.antecedentium.AnteCedentium.INSTANCE;

import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class StatsCommand extends Command {
    public StatsCommand() {
        super("", "Get stats about the server.", "", "stats", "server");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);

        sender.sendMessage(ChatColor.GREEN + "World size is: " + ChatColor.BOLD + INSTANCE.statsWorker.getWorldSize() + " / " + INSTANCE.getConfig().getString("replacements.max-storage"));
    }
}