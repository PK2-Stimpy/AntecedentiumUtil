package com.antecedentium.command.commands;

import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import com.antecedentium.reflections.craftplayer.ReflectionUser;
import com.antecedentium.reflections.craftplayer.obj.CraftPlayerObject;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class QuitCommand extends Command {
    public QuitCommand() {
        super("", "Tests reflection.", "antecedentium.command.reflect", "testreflect");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);
        if(!(sender instanceof Player))
            return;
        Player target = Bukkit.getPlayer(args[0]);

        CraftPlayerObject craftPlayer = ReflectionUser.get((Player)sender).craftPlayer;

        sender.sendMessage(craftPlayer.getHandle()._locale());
        sender.sendMessage(String.valueOf(craftPlayer.getHandle()._ping()));
        sender.sendMessage(String.valueOf(craftPlayer.getHandle()._playerConnection()._networkManager()._protocolVersion()));
        sender.sendMessage(String.valueOf(craftPlayer.getHandle()._playerConnection()._networkManager()._preparing()));
        sender.sendMessage(String.valueOf(craftPlayer.getHandle()._playerConnection()._networkManager().isConnected()));
        sender.sendMessage(String.valueOf(craftPlayer.getHandle()._playerConnection().isDisconnected()));
        craftPlayer.removeDisconnectingPlayer(craftPlayer.player);
    }
}