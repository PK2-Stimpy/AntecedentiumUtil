package com.antecedentium.worker.workers;

import com.antecedentium.AnteCedentium;
import com.antecedentium.worker.Worker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class LagWorker {
    AnteCedentium plugin;
    BukkitTask task;
    public LagWorker(AnteCedentium plugin) {
        this.plugin = plugin;
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if(!plugin.getConfig().getBoolean("modules.lag-prevention.enabled"))
                    return;

                double tps = Bukkit.getTPS()[0];
                if(plugin.getConfig().getBoolean("modules.lag-prevention.modules.elytra.enabled")
                        && tps <= plugin.getConfig().getDouble("modules.lag-prevention.modules.elytra.config.tps"))
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        if(player.getInventory().getChestplate() == null || player.getInventory().getChestplate().getType() != Material.ELYTRA)
                            continue;

                        World world = player.getWorld();
                        if(world.getEnvironment() == World.Environment.NORMAL
                                && plugin.getConfig().getBoolean("modules.lag-prevention.modules.elytra.config.overworld"))
                            takeElytra(player);
                        else if(world.getEnvironment() == World.Environment.NETHER
                                && plugin.getConfig().getBoolean("modules.lag-prevention.modules.elytra.config.nether"))
                            takeElytra(player);
                        else if(world.getEnvironment() == World.Environment.THE_END
                                && plugin.getConfig().getBoolean("modules.lag-prevention.modules.elytra.config.end"))
                            takeElytra(player);
                    }
            }
        }.runTaskTimer(plugin, 20L, 1L);
    }

    private void takeElytra(Player player) {
        if(player.getInventory().getChestplate() == null
                || player.getInventory().getChestplate().getType() != Material.ELYTRA
                || !player.isGliding())
            return;

        World world = player.getWorld();
        Location loc = player.getLocation();
        if(loc.getY() > 254)
            loc = new Location(world, loc.getX(), 254, loc.getZ());
        if(loc.getBlock().getType() == Material.AIR)
            loc.getBlock().setType(Material.OBSIDIAN);
        Location pLoc = new Location(loc.getWorld(), loc.getX(), loc.getY()+1, loc.getZ());

        player.setGliding(false);
        world.dropItem(pLoc, player.getInventory().getChestplate());
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        ItemStack[] armor = player.getInventory().getArmorContents();
        armor[2] = new ItemStack(Material.AIR);
        player.getInventory().setArmorContents(armor);
        player.teleport(pLoc);
    }
}