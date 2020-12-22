package com.antecedentium.command.commands;

import com.antecedentium.AnteCedentium;
import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class BuyCommand extends Command {
    public BuyCommand() {
        super("", "Get the store link.", "", "buy", "shop", "donate");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);

        sender.sendMessage(ChatColor.YELLOW + "You can donate on: " + ChatColor.GREEN + AnteCedentium.INSTANCE.getConfig().getConfigurationSection("links").getString("shop"));
    }
}