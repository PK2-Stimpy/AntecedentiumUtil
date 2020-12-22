package com.antecedentium.command.commands;

import com.antecedentium.AnteCedentium;
import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class DiscordCommand extends Command {
    public DiscordCommand() {
        super("", "Get the discord invitation link.", "", "discord", "disc");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);

        sender.sendMessage(ChatColor.YELLOW + "The discord link is: " + ChatColor.GREEN + AnteCedentium.INSTANCE.getConfig().getConfigurationSection("links").getString("discord"));
    }
}