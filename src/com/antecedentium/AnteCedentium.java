package com.antecedentium;

import com.antecedentium.command.Command;
import com.antecedentium.command.CommandHandler;
import com.antecedentium.events.*;
import com.antecedentium.reflections.craftplayer.CraftPlayerReflections;
import com.antecedentium.reflections.packet.PacketReflections;
import com.antecedentium.worker.workers.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AnteCedentium extends JavaPlugin {
    public static AnteCedentium INSTANCE;
    public WorldStatsWorker statsWorker;
    public CommandHandler commandHandler;
    public PacketReflections packetReflections;
    public CraftPlayerReflections craftPlayerReflections;

    @Override
    public void onEnable() {
        super.onEnable();

        INSTANCE = this;
        getConfig().options().copyDefaults(true);
        saveConfig();

        /* Reflections */
        this.packetReflections = new PacketReflections();
        this.craftPlayerReflections = new CraftPlayerReflections();

        /* Workers */
        statsWorker = new WorldStatsWorker();
        new TabWorker();
        new ChestLagWorker();
        // SPIGOT WORKERS \\
        new NetherRoofWorker(this);
        new LagWorker(this);

        /* Commands */
        commandHandler = new CommandHandler();
        for (Command command : Command.commands)
            command.register();

        /* Events */
        Bukkit.getPluginManager().registerEvents(new PacketListener(), this);
        Bukkit.getPluginManager().registerEvents(new DonkeyChestListener(), this);
        Bukkit.getPluginManager().registerEvents(new GodModeListener(), this);
        Bukkit.getPluginManager().registerEvents(new EndGatewayListener(), this);
        Bukkit.getPluginManager().registerEvents(new VanishListener(), this);
        Bukkit.getPluginManager().registerEvents(new AntiIllegalListener(), this);
        Bukkit.getPluginManager().registerEvents(new AdditionalCommandsListener(), this);
        Bukkit.getPluginManager().registerEvents(new NoRecipesListener(), this);
        Bukkit.getPluginManager().registerEvents(new AntiChestLagListener(), this);
        Bukkit.getPluginManager().registerEvents(new WaterLagListener(), this);
        Bukkit.getPluginManager().registerEvents(new LightLagListener(this), this);
        if(getConfig().getBoolean("modules.chunk-ban.enabled"))
            Bukkit.getPluginManager().registerEvents(new AntiChunkBanListener(), this);
        Bukkit.getPluginManager().registerEvents(new Anti32kListener(this), this);
        Bukkit.getPluginManager().registerEvents(new LagPreventionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new RedstoneLimitListener(), this);

        Bukkit.getPluginManager().registerEvents(new com.antecedentium.reflections.craftplayer.listener.JoinQuitListen(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();

        statsWorker.stop();
    }
}