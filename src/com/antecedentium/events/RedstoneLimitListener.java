package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class RedstoneLimitListener implements Listener {
    public static int blocksInChunk(Chunk chunk, Material material) {
        int count = 0;
        for (int x = 0; x < 16; x++)
            for (int z = 0; z < 16; z++)
                for (int y = 0; y < 256; y++)
                    if (chunk.getBlock(x, y, z).getType() == material)
                        count++;

        return count;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(!AnteCedentium.INSTANCE.getConfig().getBoolean("modules.redstone-limit.enabled"))
            return;
        int max = AnteCedentium.INSTANCE.getConfig().getInt("modules.redstone-limit.config.max-redstone");
        Chunk chunk = event.getBlockPlaced().getChunk();
        int count = blocksInChunk(chunk, Material.REDSTONE_WIRE);
        if(count > max)
            event.getBlockPlaced().breakNaturally();
    }
}