package com.antecedentium.command.commands;

import com.antecedentium.AnteCedentium;
import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends Command {
    public ReloadCommand() {
        super("", "Reload the antecedentium plugin.", "antecedentium.command.reload", "antecedentium-reload");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);

        AnteCedentium.INSTANCE.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
    }
}
