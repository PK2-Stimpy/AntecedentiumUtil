package com.antecedentium.events;

import static com.antecedentium.events.enums.checks.AICheckEnum.*;

import com.antecedentium.AnteCedentium;
import com.antecedentium.events.enums.checks.AICheckEnum;
import com.antecedentium.events.event.PacketReadEvent;
import com.antecedentium.events.event.PacketWriteEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionEffect;

import java.util.List;
import java.util.Map;

public class AntiIllegalListener implements Listener {
    public static AICheckEnum getCurrentCheck() { return AnteCedentium.INSTANCE.getConfig().getBoolean("modules.anti-illegals.enabled")?AICheckEnum.valueOf(AnteCedentium.INSTANCE.getConfig().getString("modules.anti-illegals.mode")):NONE; }

    public static boolean illegalItemCheck(ItemStack itemStack, Material... materials) {
        for(Material material : materials)
            if(itemStack.getType().name() == material.name())
                return true;
        return false;
    }
    public static boolean basicIllegalItemCheck(ItemStack itemStack) {
        return illegalItemCheck(
                itemStack,
                Material.BEDROCK, Material.COMMAND, Material.COMMAND_CHAIN, Material.COMMAND_MINECART,
                Material.COMMAND_REPEATING, Material.BARRIER, Material.END_GATEWAY, Material.ENDER_PORTAL_FRAME,
                Material.ENDER_PORTAL, Material.PORTAL, Material.STRUCTURE_VOID, Material.STRUCTURE_BLOCK,
                Material.DRAGON_EGG, Material.KNOWLEDGE_BOOK, Material.MOB_SPAWNER, Material.MONSTER_EGG);
    }
    public static boolean runItem(Inventory inventory, ItemStack itemStack) {
        int lvl = getCurrentCheck().lvl;
        if(lvl < 1) return false;
        if(itemStack == null || itemStack.getType() == null || itemStack.getType() == Material.AIR) return false;

        boolean illegal = false;

        if(lvl >= LOW.lvl) { /* LOW LEVEL */
            if(itemStack.getAmount() > itemStack.getMaxStackSize() || itemStack.getAmount() < 1) {
                itemStack.setAmount(itemStack.getMaxStackSize());

                illegal = true;
            }

            if(basicIllegalItemCheck(itemStack)) {

                inventory.remove(itemStack);
                illegal = true;
            }

            if(inventory.getType() == InventoryType.SHULKER_BOX && itemStack.getType().name().toUpperCase().endsWith("_SHULKER_BOX")) {
                inventory.remove(itemStack);
                return true;
            }
        }

        if(lvl >= NORMAL.lvl) { /* NORMAL LEVEL */
            Map<Enchantment, Integer> enchantments = itemStack.getEnchantments();
            for(Map.Entry<Enchantment, Integer> enchantmentEntry : enchantments.entrySet()) {
                Enchantment enchantment = enchantmentEntry.getKey();
                int level = enchantmentEntry.getValue();
                if(enchantment == null) continue;
                if(!enchantment.canEnchantItem(itemStack)) {
                    itemStack.removeEnchantment(enchantment);
                    illegal = true;

                    continue;
                }
                if (level > enchantment.getMaxLevel() || level < 1) {
                    itemStack.removeEnchantment(enchantment);
                    itemStack.addEnchantment(enchantment, enchantment.getMaxLevel());

                    illegal = true;
                }
            }

            if(itemStack.getItemMeta() instanceof PotionMeta) {
                PotionMeta potionMeta = (PotionMeta)itemStack.getItemMeta();
                List<PotionEffect> potionEffectList = potionMeta.getCustomEffects();

                /* MAX= [8 Minutes, Level II] */
                for(PotionEffect effect : potionEffectList)
                    if(effect.getAmplifier() > 2 || effect.getDuration() > 9600 ||
                       effect.getAmplifier() < 1 || effect.getDuration() < 1) {
                        inventory.remove(itemStack);
                        illegal = true;
                    }
            }
        }
        if(lvl >= STRICT.lvl) { /* STRICT LEVEL */
            if(itemStack.getItemMeta().hasDisplayName()) {
                ItemMeta meta = itemStack.getItemMeta();
                meta.setDisplayName(ChatColor.stripColor(meta.getDisplayName()));
                itemStack.setItemMeta(meta);
            }

            if(itemStack.getItemMeta().hasLore()) {
                ItemMeta meta = itemStack.getItemMeta();
                meta.setLore(null);
                itemStack.setItemMeta(meta);

                illegal = true;
            }

            if(itemStack.getItemMeta() instanceof BookMeta) {
                BookMeta bookMeta = (BookMeta)itemStack.getItemMeta();
                if(bookMeta.hasAuthor())
                    bookMeta.setAuthor(ChatColor.stripColor(bookMeta.getAuthor()));

                itemStack.setItemMeta(bookMeta);
            }
        }
        if(lvl >= HYPIXEL.lvl) { /* IS YOUR SERVER ANARCHY? */
            if(itemStack.getType() == Material.FIREWORK) {
                FireworkMeta fireworkMeta = (FireworkMeta)itemStack.getItemMeta();
                if(fireworkMeta.getEffects().size() > 10) {
                    inventory.remove(itemStack);

                    illegal = true;
                }
            }

            if(itemStack.getType().name().toUpperCase().endsWith("_SHULKER_BOX")) {
                ShulkerBox shulkerBox = (ShulkerBox)((BlockStateMeta)itemStack.getItemMeta()).getBlockState();
                for(ItemStack shulkerContent : shulkerBox.getInventory().getContents())
                    illegal = runItem(inventory, shulkerContent);

                ((BlockStateMeta) itemStack.getItemMeta()).setBlockState(shulkerBox);
            }
        }

        return illegal;
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent event) {
        for(ItemStack itemStack : event.getInventory().getContents())
            runItem(event.getClickedInventory(), itemStack);
    }

    @EventHandler
    public void openInventory(InventoryOpenEvent event) {
        for(ItemStack itemStack : event.getPlayer().getInventory().getContents())
            runItem(event.getInventory(), itemStack);
        for(ItemStack itemStack : event.getInventory().getContents())
            runItem(event.getInventory(), itemStack);
    }

    @EventHandler
    public void onInteractWithInventory(InventoryInteractEvent event) {
        for(ItemStack itemStack : event.getInventory().getContents())
            runItem(event.getInventory(), itemStack);
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        int lvl = getCurrentCheck().lvl;
        if(lvl < LOW.lvl) return;
        if(event.getItemInHand() == null) return;

        if(basicIllegalItemCheck(event.getItemInHand())) {

            event.getPlayer().getInventory().remove(event.getItemInHand());
            event.setCancelled(true);
        }

        if(lvl >= STRICT.lvl) /* CAN CAUSE LAG */
            event.setCancelled(runItem(event.getPlayer().getInventory(), event.getItemInHand()));
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        int lvl = getCurrentCheck().lvl;
        if(lvl < LOW.lvl) return;
        if(event.getItem() == null) return;

        if(basicIllegalItemCheck(event.getItem())) {

            event.getPlayer().getInventory().remove(event.getItem());
            event.setCancelled(true);
        }

        if(lvl >= STRICT.lvl) /* CAN CAUSE LAG */
            event.setCancelled(runItem(event.getPlayer().getInventory(), event.getItem()));
    }
}