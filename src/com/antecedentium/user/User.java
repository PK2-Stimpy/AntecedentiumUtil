package com.antecedentium.user;

import com.antecedentium.util.CConfig;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class User {
    /* STATIC */

    /* OBJECT */
    public final Player player;
    public final CConfig cConfig;
    public final ArrayList<HomeObj> homes;

    public User(Player player) {
        this.player = player;
        this.cConfig = new CConfig(player.getName().toLowerCase() + ".yml", "player-data");
        this.homes = new ArrayList<>();

        if(cConfig.get("homes") == null) generateDefaultConfiguration();
    }

    public void generateDefaultConfiguration() {
        // List<ConfigurationSection> configurationSections = new List;
    }
}
