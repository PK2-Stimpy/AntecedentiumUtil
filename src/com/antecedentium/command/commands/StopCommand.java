package com.antecedentium.command.commands;

import com.antecedentium.AnteCedentium;
import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopCommand extends Command {
    public StopCommand() {
        super("", "Stops the server.", "antecedentium.command.stop", "stop");
    }

    private Thread thread = null;
    private boolean stopping = false;

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);
        if(stopping) return;
        AnteCedentium.INSTANCE.getLogger().info("Server has told to stop, stopping...");
        stopping = true;
        Thread thread = new Thread(() -> {
            try {
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.min5")));
                Thread.sleep(60000);
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.min4")));
                Thread.sleep(60000);
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.min3")));
                Thread.sleep(60000);
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.min2")));
                Thread.sleep(60000);
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.min1")));
                Thread.sleep(60000);
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.now")));
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.now")));
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.now")));
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.now")));
                Thread.sleep(500);
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.now")));
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.now")));
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.now")));
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.now")));
                Thread.sleep(500);
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.now")));
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.now")));
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.now")));
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("command-config.stop-command.now")));
                Thread.sleep(1000);
                Bukkit.shutdown();
            } catch (Exception exception) { exception.printStackTrace(); }
        });
        thread.start();
    }
}