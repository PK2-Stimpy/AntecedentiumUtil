package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class AntiChunkBanListener implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if (!(player.hasPermission("chunkban.bypass"))) {
            if (isChecked(block)) {
                if (event.getBlock().getChunk().getTileEntities().length > AnteCedentium.INSTANCE.getConfig().getInt("modules.chunk-ban.config.tile-entity-max")) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("modules.chunk-ban.messages.prevent")));
                }
            }
        }
        if (block.getType() == Material.SKULL || block.getType() == Material.SKULL_ITEM) {
            if (block.getChunk().getTileEntities().length > AnteCedentium.INSTANCE.getConfig().getInt("modules.chunk-ban.config.skull-max")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("modules.chunk-ban.messages.prevent")));
            }
        }
    }

    @EventHandler
    public void onSpawn(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getItem() != null) {
            if (event.getItem().getType() == Material.ITEM_FRAME) {
                List<Entity> frameList = new ArrayList<>();
                for (Entity entity : event.getPlayer().getChunk().getEntities()) {
                    if (entity instanceof ItemFrame) {
                        frameList.add(entity);
                    }
                }
                if (frameList.size() > AnteCedentium.INSTANCE.getConfig().getInt("modules.chunk-ban.config.tile-entity-max")) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("modules.chunk-ban.messages.prevent")));
                }
            }
        }
    }

    private boolean isChecked(Block block) {
        switch (block.getType()) {
            case FURNACE:
            case TRAPPED_CHEST:
            case ENCHANTMENT_TABLE:
            case WALL_BANNER:
            case WALL_SIGN:
            case HOPPER:
            case DROPPER:
            case DISPENSER:
            case BREWING_STAND:
            case BEACON:
            case SIGN_POST:
            case ENDER_CHEST:
            case FLOWER_POT:
            case BANNER:
                return true;
        }
        return false;
    }
}