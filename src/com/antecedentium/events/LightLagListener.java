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
    HashMap<Player, Boolean> nigMap = new HashMap<>();

    AnteCedentium plugin;
    int maxSpam;
    boolean enabled;
    String kick;

    public LightLagListener(AnteCedentium plugin) {
        this.plugin = plugin;
        this.maxSpam = plugin.getConfig().getInt("modules.light-lag.config.max-spam");
        this.enabled = plugin.getConfig().getBoolean("modules.light-lag.enabled");
        this.kick = plugin.getConfig().getString("modules.light-lag.messages.kick");

        BukkitTask bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (Map.Entry<Player, Integer> violationEntry : amount.entrySet()) {
                    if (violationEntry.getValue() > 0)
                        violationEntry.setValue(violationEntry.getValue() - 1);
                }
            }
        }.runTaskTimer(plugin, 1L, 19L);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!enabled)
            return;

        Player player = event.getPlayer();
        nigMap.putIfAbsent(player, false);
        nigMap.put(player, !nigMap.get(player));
        if (nigMap.get(player)) {
            lastPlaced.put(player, event.getBlock().getLocation());
        } else {
            if (lastPlaced.get(player).equals(event.getBlock().getLocation())) {
                if (amount.containsKey(player)) {
                    amount.put(player, amount.get(player) + 1);
                } else {
                    amount.put(player, 0);
                }
                if (amount.get(player) >= maxSpam / 2) {
                    player.kickPlayer(kick);
                    lastPlaced.remove(player);
                    amount.remove(player);
                    nigMap.remove(player);
                }
            }
        }
    }
} 
