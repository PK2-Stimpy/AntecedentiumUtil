package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import com.destroystokyo.paper.event.entity.EntityTeleportEndGatewayEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class EndGatewayListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEndGateway(EntityTeleportEndGatewayEvent event) {
        if(!AnteCedentium.INSTANCE.getConfig().getBoolean("modules.end-gateway-crash.enabled"))
            return;

        boolean bool = false;
        try {
            double randomX = (Math.random() * ((50) + 1)) + 0;
            double randomY = (Math.random() * ((50) + 1)) + 0;
            double randomZ = (Math.random() * ((50) + 1)) + 0;
            int x = event.getFrom().getBlockX();
            int y = event.getGateway().getLocation().getBlockY();
            int z = event.getFrom().getBlockZ();
            Vector vector = new Vector(-randomX, randomY, randomZ);
            Entity entity = event.getEntity();

            for(Player player : entity.getLocation().getNearbyPlayers(30D)) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("modules.end-gateway-crash.messages.disabled")));
                player.teleport(new Location(entity.getWorld(), x, y+5, z+30));
                entity.setVelocity(vector);

                bool = true;
            }
        } catch (Exception exception) { exception.printStackTrace(); }

        if(bool) event.setCancelled(true);
    }
}