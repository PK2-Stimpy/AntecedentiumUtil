package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockRedstoneEvent;

public class LagPreventionListener implements Listener {
    AnteCedentium plugin;
    public LagPreventionListener(AnteCedentium plugin) {
        super();
        this.plugin = plugin;
    }

    @EventHandler
    public void onRedstoneTick(BlockRedstoneEvent event) {
        if(!plugin.getConfig().getBoolean("modules.lag-prevention.modules.redstone.enabled"))
            return;

        double tps = Bukkit.getTPS()[0];
        if(tps <= plugin.getConfig().getDouble("modules.lag-prevention.modules.redstone.config.tps"))
            event.setNewCurrent(0);
    }

    @EventHandler
    public void onPhisics(BlockPhysicsEvent event) {
        if(!plugin.getConfig().getBoolean("modules.lag-prevention.modules.physics.enabled"))
            return;

        double tps = Bukkit.getTPS()[0];
        if(tps <= plugin.getConfig().getDouble("modules.lag-prevention.modules.physics.config.tps"))
            event.setCancelled(true);
    }
}