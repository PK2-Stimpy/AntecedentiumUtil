package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

public class LightLagListener implements Listener {
    public class Vector3D {
        int     x = 0,
                y = 0,
                z = 0;
        public Vector3D(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public Vector3D() {}

        public boolean equals(int x, int y, int z) { return(this.x == x && this.y == y && this.z == z); }
        public void set(int x, int y, int z) { this.x = x; this.y = y; this.z = z; }
    }
    public class BLOCK_DATA {
        public final Vector3D pos;
        public int amount;

        public BLOCK_DATA(int x, int y, int z, int amount) {
            this.pos = new Vector3D();
            pos.x = x;
            pos.y = y;
            pos.z = z;
            this.amount = amount;
        }

        public BLOCK_DATA() {
            this.pos = new Vector3D();
            this.amount = 0;
        }
    }
    private HashMap<Player, BLOCK_DATA> playerData;

    AnteCedentium plugin;

    public LightLagListener(AnteCedentium plugin) {
        this.plugin = plugin;
        this.playerData = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void blockPlace(BlockPlaceEvent event) {
        if(!plugin.getConfig().getBoolean("modules.light-lag.enabled"))
            return;
        int maxSpam = plugin.getConfig().getInt("modules.light-lag.config.max-spam");
        String kickMessage = plugin.getConfig().getString("modules.light-lag.messages.kick");

        Player player = event.getPlayer();
        Block block = event.getBlock();
        if(!playerData.containsKey(player))
            playerData.put(player, new BLOCK_DATA(block.getX(), block.getY(), block.getZ(), 1));

        BLOCK_DATA blockData = playerData.get(player);
        if(!blockData.pos.equals(block.getX(), block.getY(), block.getZ())) {
            blockData.pos.set(block.getX(), block.getY(), block.getZ());
            blockData.amount = 1;
            return;
        }
        if(blockData.amount > maxSpam) {
            playerData.remove(player);
            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', kickMessage));
            return;
        }
        blockData.amount++;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerQuit(PlayerQuitEvent event) {
        if(playerData.containsKey(event.getPlayer()))
            playerData.remove(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerKick(PlayerKickEvent event) {
        if(playerData.containsKey(event.getPlayer()))
            playerData.remove(event.getPlayer());
    }
}