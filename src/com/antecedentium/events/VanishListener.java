package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import com.antecedentium.command.commands.VanishCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class VanishListener implements Listener {
    @EventHandler
    public void join(PlayerJoinEvent event) {
        for(String player : VanishCommand.vanished)
            event.getPlayer().hidePlayer(AnteCedentium.INSTANCE, Bukkit.getPlayer(player));

        if(VanishCommand.exists(event.getPlayer())) {
            event.getPlayer().sendMessage(ChatColor.GREEN + "\nYou are currently vanished!\n" + ChatColor.RESET + " ");
            for(Player player : Bukkit.getOnlinePlayers())
                player.hidePlayer(AnteCedentium.INSTANCE, event.getPlayer());
        }
    }
}