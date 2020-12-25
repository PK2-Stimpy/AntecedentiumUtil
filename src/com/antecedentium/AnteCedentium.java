package com.antecedentium;

import com.antecedentium.command.Command;
import com.antecedentium.command.CommandHandler;
import com.antecedentium.events.DonkeyChestListener;
import com.antecedentium.events.EndGatewayListener;
import com.antecedentium.events.GodModeListener;
import com.antecedentium.events.PacketListener;
import com.antecedentium.reflections.packet.PacketReflections;
import com.antecedentium.worker.workers.TabWorker;
import com.antecedentium.worker.workers.WorldStatsWorker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AnteCedentium extends JavaPlugin {
    public static AnteCedentium INSTANCE;
    public WorldStatsWorker statsWorker;
    public CommandHandler commandHandler;
    public PacketReflections packetReflections;

    @Override
    public void onEnable() {
        super.onEnable();

        INSTANCE = this;
        getConfig().options().copyDefaults(true);
        saveConfig();

        /* Reflections */
        this.packetReflections = new PacketReflections();

        /* Workers */
        statsWorker = new WorldStatsWorker();
        new TabWorker();

        /* Commands */
        commandHandler = new CommandHandler();
        for(Command command : Command.commands)
            command.register();

        /* Events */
        Bukkit.getPluginManager().registerEvents(new PacketListener(), this);
        Bukkit.getPluginManager().registerEvents(new DonkeyChestListener(), this);
        Bukkit.getPluginManager().registerEvents(new GodModeListener(), this);
        Bukkit.getPluginManager().registerEvents(new EndGatewayListener(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();

        statsWorker.stop();
    }
}