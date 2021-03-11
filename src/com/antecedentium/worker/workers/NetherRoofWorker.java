package com.antecedentium.worker.workers;

import com.antecedentium.AnteCedentium;
import com.antecedentium.worker.Worker;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class NetherRoofWorker {
    AnteCedentium plugin;
    BukkitTask task;

    public NetherRoofWorker(AnteCedentium plugin) {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if(!plugin.getConfig().getBoolean("modules.nether-roof.enabled"))
                    return;

                for(Player player : Bukkit.getOnlinePlayers())
                    if(player.getWorld().getEnvironment() == World.Environment.NETHER && player.getLocation().getY() > plugin.getConfig().getInt("modules.nether-roof.config.level"))
                        player.damage(plugin.getConfig().getDouble("modules.nether-roof.config.damage"));
            }
        }.runTaskTimer(plugin, 20L, 1L);
    }
}