package com.antecedentium.command.commands;

import com.antecedentium.AnteCedentium;
import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.awt.*;
import java.util.List;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("", "Shows help page.", "", "help");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);

        List<String> list = AnteCedentium.INSTANCE.getConfig().getStringList("command-config.help-command");
        StringBuilder builder = new StringBuilder();

        builder.append("\n");
        for(String cmd : list)
            builder.append(ChatColor.translateAlternateColorCodes('&', " " + cmd + "\n"));
        builder.append(ChatColor.translateAlternateColorCodes('&', "&b "));
        sender.sendMessage(builder.toString());
    }
}