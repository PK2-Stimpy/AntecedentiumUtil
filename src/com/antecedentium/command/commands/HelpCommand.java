package com.antecedentium.command.commands;

import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.awt.*;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("", "Shows help page.", "", "help");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "\n&e" +
                " /help &r- Help command.\n&e" +
                " /buy &r- Shows the shop url.\n&e" +
                " /discord &r- Shows the discord url.\n&e" +
                " /stats &r- Shows some info about the server.\n&e" +
                " /joindate <player> &r- Shows the exact join date of a player.\n&e" +
                " /kill &r- Suicide.\n&e "));
    }
}