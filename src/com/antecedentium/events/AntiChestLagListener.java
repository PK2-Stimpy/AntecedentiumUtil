package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import com.antecedentium.worker.workers.ChestLagWorker;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.BookMeta;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AntiChestLagListener implements Listener {
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if(AnteCedentium.INSTANCE.getConfig().getBoolean("modules.chest-lag.enabled"))
        try {
            /* Sorry 254n_m. I didn't have time to make it myself. */
            int maxSpam = AnteCedentium.INSTANCE.getConfig().getInt("modules.chest-lag.config.max-spam");
            String kickMessage = ChatColor.translateAlternateColorCodes('&', AnteCedentium.INSTANCE.getConfig().getString("modules.chest-lag.messages.kick"));
            boolean deleteBooks = AnteCedentium.INSTANCE.getConfig().getBoolean("modules.chest-lag.config.books-delete");

            InventoryType inventoryType = event.getInventory().getType();
            Player player = (Player) event.getPlayer();
            if (isCheckedInventory(inventoryType)) {
                if (ChestLagWorker.chestHashMap.containsKey(player)) {
                    ChestLagWorker.chestHashMap.replace(player, ChestLagWorker.chestHashMap.get(player) + 1);
                } else {
                    ChestLagWorker.chestHashMap.put(player, 1);
                }
                if (deleteBooks)
                    deleteNBTBooks(event.getInventory());
                if (ChestLagWorker.chestHashMap.get(player) > maxSpam)
                    player.kickPlayer(kickMessage);
            }
        } catch (Exception exception) { exception.printStackTrace(); }
    }

    public boolean isCheckedInventory(InventoryType type) {
        switch (type) {
            case CHEST:
            case HOPPER:
            case ENDER_CHEST:
            case SHULKER_BOX:
            case DISPENSER:
            case DROPPER:
                return true;
        }
        return false;
    }

    private void deleteNBTBooks(Inventory inventory) {
        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                if (item.getType() == Material.WRITTEN_BOOK || item.getType() == Material.BOOK_AND_QUILL) {
                    BookMeta bookMeta = (BookMeta) item.getItemMeta();
                    if (isBanBook(bookMeta))
                        inventory.remove(item);
                }
                if (item.getItemMeta() instanceof BlockStateMeta) {
                    BlockStateMeta blockStateMeta = (BlockStateMeta) item.getItemMeta();
                    if (blockStateMeta.getBlockState() instanceof ShulkerBox) {
                        ShulkerBox shulker = (ShulkerBox) blockStateMeta.getBlockState();
                        for (ItemStack shulkerItem : shulker.getInventory().getContents()) {
                            if (shulkerItem != null) {
                                if (shulkerItem.getType() == Material.WRITTEN_BOOK || shulkerItem.getType() == Material.BOOK_AND_QUILL) {
                                    BookMeta book = (BookMeta) shulkerItem.getItemMeta();
                                    if (isBanBook(book))
                                        shulker.getInventory().remove(shulkerItem);
                                }
                            }
                        }
                        blockStateMeta.setBlockState(shulker);
                        item.setItemMeta(blockStateMeta);
                    }
                }
            }
        }
    }

    private boolean isBanBook(BookMeta book) {
        for (String bookPages : book.getPages()) {
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(bookPages);
            return matcher.find();
        }
        return false;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(ChestLagWorker.chestHashMap.containsKey(player))
            ChestLagWorker.chestHashMap.remove(player);
    }
}