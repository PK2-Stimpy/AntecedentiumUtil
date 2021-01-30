package com.antecedentium.worker.workers;

import com.antecedentium.worker.Worker;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ChestLagWorker extends Worker {
    public static HashMap<Player, Integer> chestHashMap = new HashMap<>();

    public ChestLagWorker() {
        super(1000);
    }

    @Override
    public void run() {
        super.run();

        for (Map.Entry<Player, Integer> violationEntry : chestHashMap.entrySet())
            if (violationEntry.getValue() > 0)
                violationEntry.setValue(violationEntry.getValue() - 1);
    }
}