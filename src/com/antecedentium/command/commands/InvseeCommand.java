package com.antecedentium.command.commands;

import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand extends Command {
    public InvseeCommand() {
        super("<player> <inv/ender>", "See what's inside a player inventory.", "antecedentium.command.invsee", "invsee");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);
        if(args.length < 2) throw new InvalidUsageException();

        if(sender instanceof Player) {
            Player player = (Player)sender;
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                player.sendMessage(ChatColor.RED + "The specified player doesn't exists.");
                return;
            }
            if(args[1].equalsIgnoreCase("ender")) player.openInventory(target.getEnderChest());
            else if(args[1].equalsIgnoreCase("inv")) player.openInventory(target.getInventory());
            else throw new InvalidUsageException();
        }
    }
}