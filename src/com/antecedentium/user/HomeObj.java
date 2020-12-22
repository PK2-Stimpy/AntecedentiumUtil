package com.antecedentium.user;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

public class HomeObj {
    public String name;
    public double x, y, z;
    public World world;

    public HomeObj(String name, World world, int x, int y, int z) {
        this.name = name;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Deprecated
    public HomeObj() {
        this(null, null, 0, 0, 0);
    }

    public HomeObj(ConfigurationSection section) {
        this();
        load(section);
    }

    public void load(ConfigurationSection section) {
        this.name = section.getString("name");
        this.world = Bukkit.getWorld(section.getString("world"));
        this.x = section.getDouble("x");
        this.y = section.getDouble("y");
        this.z = section.getDouble("z");
    }
}