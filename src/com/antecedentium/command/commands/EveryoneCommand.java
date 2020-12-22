package com.antecedentium.command.commands;

import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EveryoneCommand extends Command {
    public EveryoneCommand() {
        super("<message>", "Makes everyone chat.", "antecedentium.command.everyone", "everyone");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        if(args.length < 1)
            throw new InvalidUsageException();

        StringBuilder message = new StringBuilder();
        for(int i = 0; i < args.length; i++)
            message.append(args[i]+((args.length-1==i)?"":" "));

        String str = message.toString();
        for(Player player : Bukkit.getOnlinePlayers())
            player.chat(str);

        sender.sendMessage(ChatColor.GREEN + "YES!");
    }
}