package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AdditionalCommandsListener implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();

        if(command.startsWith("/home") && AnteCedentium.INSTANCE.getConfig().getBoolean("modules.anti-spawnhome.enabled")) {
            int blocks = AnteCedentium.INSTANCE.getConfig().getInt("modules.anti-spawnhome.blocks");

            if(player.isInsideVehicle()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("modules.anti-spawnhome.messages.vehicle-message")));
                event.setCancelled(true);
            } else if(
                    !player.isOp() &&
                    player.getLocation().getBlockX() < blocks && player.getLocation().getBlockX() > -blocks &&
                    player.getLocation().getBlockZ() < blocks && player.getLocation().getBlockZ() > -blocks) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("modules.anti-spawnhome.messages.spawn-message").replaceAll("%blocks%", String.valueOf(blocks))));
                event.setCancelled(true);
            }
        }
    }
}