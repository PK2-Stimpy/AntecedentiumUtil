package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class LightLagListener implements Listener {
    HashMap<Player, Location> lastPlaced = new HashMap<>();
    HashMap<Player, Integer> amount = new HashMap<>();

    AnteCedentium plugin;
    int maxSpam;
    boolean enabled;
    String kick;

    public LightLagListener(AnteCedentium plugin) {
        this.plugin = plugin;
        this.maxSpam = plugin.getConfig().getInt("modules.light-lag.config.max-spam");
        this.enabled = plugin.getConfig().getBoolean("modules.light-lag.enabled");
        this.kick = plugin.getConfig().getString("modules.light-lag.messages.kick");
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!enabled)
            return;

        Player player = event.getPlayer();
        lastPlaced.put(player, event.getBlock().getLocation());
        amount.put(player, (amount.contains(player)?amount.get(player):0)+1);
        
    }
}