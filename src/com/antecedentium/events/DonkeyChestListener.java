package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class DonkeyChestListener implements Listener {
    private ArrayList<Entity> arrayList = new ArrayList<>();

    @EventHandler
    public void onChest(PlayerInteractAtEntityEvent event) {
        if(!AnteCedentium.INSTANCE.getConfig().getBoolean("modules.donkey-dupe.enabled"))
            return;
        if(arrayList.contains(event.getRightClicked()))
            return;

        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if(entity instanceof Llama || entity instanceof Donkey) {
            if(!(player.getInventory().getItemInMainHand().getType() == Material.CHEST || player.getInventory().getItemInOffHand().getType() == Material.CHEST))
                return;
            player.sendMessage(ChatColor.translateAlternateColorCodes(
                    '&',
                    AnteCedentium.INSTANCE.getConfig().getString("modules.donkey-dupe.messages.dupe-disabled"))
            );
            if(entity instanceof Llama) {
                Llama llama = (Llama)entity;
                for(ItemStack item : llama.getInventory().getContents())
                    if(item != null)
                        llama.getWorld().dropItemNaturally(llama.getLocation(), item);
                if(llama.isCarryingChest())
                    llama.setCarryingChest(false);
            }
            if(entity instanceof Donkey) {
                Donkey donkey = (Donkey)entity;
                for(ItemStack item : donkey.getInventory().getContents())
                    if(item != null)
                        donkey.getWorld().dropItemNaturally(donkey.getLocation(), item);
                if(donkey.isCarryingChest())
                    donkey.setCarryingChest(false);
            }

            arrayList.add(entity);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void handleOnChestEvent(PlayerInteractEntityEvent event) {
        if(!arrayList.contains(event.getRightClicked()))
            return;
        arrayList.remove(event.getRightClicked());

        Entity entity = event.getRightClicked();
        if(entity instanceof Donkey) ((Donkey)entity).setCarryingChest(false);
        if(entity instanceof Llama) ((Llama)entity).setCarryingChest(false);

        Player player = event.getPlayer();
        ItemStack item = (player.getInventory().getItemInMainHand().getType() == Material.CHEST) ? player.getInventory().getItemInMainHand() : player.getInventory().getItemInOffHand();
        if(item.getAmount() == 1) player.getInventory().remove(item);
        else item.setAmount(item.getAmount()-1);

        event.setCancelled(true);
    }
}