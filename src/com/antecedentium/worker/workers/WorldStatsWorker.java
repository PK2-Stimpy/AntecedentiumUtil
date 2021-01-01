package com.antecedentium.worker.workers;

import static com.antecedentium.util.FileUtil.*;
import com.antecedentium.worker.Worker;
import org.bukkit.Bukkit;

import java.io.File;
import java.nio.file.Files;

public class WorldStatsWorker extends Worker {
    private String worldSize;
    private String freeSpace;
    private long world_all;

    public WorldStatsWorker() {
        super(300000); /* 5 Minutes */
        worldSize = "0B";
        freeSpace = "0B";
        world_all = 0;
    }

    @Override
    public void run() {
        super.run();
        try {
            long world = getSize(new File("./world"));
            long world_nether = getSize(new File("./world_nether"));
            long world_end = getSize(new File("./world_the_end"));
            world_all = world+world_nether+world_end;
            worldSize = getReadableSize(world_all);
            freeSpace = getReadableSize(new File("/").getFreeSpace());
        } catch (Exception e) { e.printStackTrace(); }
    }

    public String getWorldSize() { return worldSize; }
    public long getLongWorldSize() { return world_all; }
    public String freeSpace() { return freeSpace; }
}