package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class GodModeListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(!AnteCedentium.INSTANCE.getConfig().getBoolean("modules.god-mode.enabled"))
            return;
        Player player = event.getPlayer();
        if(player.getVehicle() == null || !player.isInsideVehicle()) return;
        Vehicle vehicle = (Vehicle) player.getVehicle();
        Chunk playerChunk = player.getChunk();
        Chunk vehicleChunk = vehicle.getChunk();
        if(playerChunk != vehicleChunk) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("modules.god-mode.messages.god-mode-disabled")));
            event.setCancelled(true);
        }
    }
}