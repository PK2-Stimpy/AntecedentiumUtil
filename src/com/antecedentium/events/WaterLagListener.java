package com.antecedentium.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class WaterLagListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPhysics(BlockPhysicsEvent event) {
        if(Bukkit.getTPS()[0] < 15) {
            event.setCancelled(true);
            return;
        }
        Block block = event.getSourceBlock();
        if(block.getY() > 254)
            if(block.getType() == Material.WATER || block.getType() == Material.LAVA)
                event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if(block.getY() > 254)
            if(block.getType() == Material.WATER || block.getType() == Material.LAVA)
                event.setCancelled(true);
    }
}