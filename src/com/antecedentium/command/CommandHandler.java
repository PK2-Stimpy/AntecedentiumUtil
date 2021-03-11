package com.antecedentium.command;

import com.antecedentium.command.commands.*;
import com.antecedentium.exception.InvalidUsageException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
    public CommandHandler() {
        super();

        new StatsCommand();
        new BuyCommand();
        new DiscordCommand();
        new ReloadCommand(); /* antecedentium.command.reload */
        new InvseeCommand(); /* antecedentium.command.invsee */
        new HelpCommand();
        new KillCommand();
        new StopCommand(); /* antecedentium.command.stop */
        new EveryoneCommand(); /* antecedentium.command.everyone */
        new ForceTPThingCommand(); /* antecedentium.command.forcetp */
        new EnderTPCommand(); /* antecedentium.command.endertp */
        new VanishCommand(); /* antecedentium.command.vanish */
        new SignGUICommand(); /* antecedentium.command.sign */
        // new QuitCommand(); /* antecedentium.command.reflect; Tests reflections. */
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        com.antecedentium.command.Command command = com.antecedentium.command.Command.getCommand(label);
        if(command != null)
            try {
                if(sender instanceof Player)
                    if(command.permission != "" && !((Player)sender).hasPermission(command.permission))
                        sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command!");
                    else command.run(sender, args);
                else command.run(sender, args);
            }
            catch (InvalidUsageException exception) { sender.sendMessage(ChatColor.RED + "/" + label.toLowerCase() + " " + command.usage); }

        return (command != null) ? true : false;
    }
}