package com.antecedentium.command.commands;

import com.antecedentium.AnteCedentium;
import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand extends Command {
    public KillCommand() {
        super("", "Kills you.", "", "kill", "suicide");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);

        if(sender instanceof Player)
            ((Player)sender).setHealth(0.0D);

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getConfigurationSection("messages").getString("suicide-message")));
    }
}