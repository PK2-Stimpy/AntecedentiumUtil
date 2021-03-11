package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TippedArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

public class Anti32kListener implements Listener {
    AnteCedentium plugin;
    public Anti32kListener(AnteCedentium plugin) {
        super();
        this.plugin = plugin;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if(plugin.getConfig().getBoolean("modules.32k-damage.enabled")) {
            if(event.getDamager() instanceof Player && event.getDamage() > 30) {
                Player damager = (Player)event.getDamager();
                if(damager.getItemInHand().getType() != Material.DIAMOND_SWORD) {
                    damager.damage(event.getDamage());
                    event.setCancelled(true);
                } else if(plugin.getConfig().getBoolean("modules.32k-damage.config.apply-sword")) {
                    damager.damage(event.getDamage());
                    event.setCancelled(true);
                }

                return;
            }

            if(event.getDamager() instanceof Arrow && event.getDamage() > 40) {
                Arrow arrow = (Arrow)event.getDamager();
                if(!(arrow instanceof Entity))
                    return;
                Entity entity = (Entity)arrow.getShooter();
                if(entity instanceof Player)
                    ((Player)entity).damage(event.getDamage());

                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onThrow(PotionSplashEvent event) {
        if (!plugin.getConfig().getBoolean("modules.32k-damage.enabled"))
            return;
        if (event.getPotion().getShooter() instanceof Player) {
            Player player = (Player) event.getPotion().getShooter();
            ItemStack pot = event.getPotion().getItem();
            for (PotionEffect effects : event.getPotion().getEffects()) {
                if (effects.getAmplifier() > 5) {
                    event.setCancelled(true);
                    player.getInventory().remove(pot);
                }

            }
        }
    }

    @EventHandler
    public void PlayerInteractEvent(PlayerItemConsumeEvent e) {
        if (!plugin.getConfig().getBoolean("modules.32k-damage.enabled"))
            return;
        if (e.getItem().getType().equals(Material.POTION)) {
            if (e.getItem().hasItemMeta()) {
                PotionMeta potion = (PotionMeta) e.getItem().getItemMeta();
                for (PotionEffect pe : potion.getCustomEffects()) {
                    if (pe.getAmplifier() > 5) {
                        e.getPlayer().getInventory().remove(e.getItem());
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDispense(BlockDispenseEvent event) {
        if (!plugin.getConfig().getBoolean("modules.32k-damage.enabled"))
            return;
        if (event.getItem().getType() == Material.SPLASH_POTION) {
            Dispenser disp = (Dispenser) event.getBlock().getState();
            PotionMeta pot = (PotionMeta) event.getItem().getItemMeta();
            for (PotionEffect effects : pot.getCustomEffects()) {
                if (effects.getAmplifier() > 5) {
                    event.setCancelled(true);
                    disp.getInventory().clear();
                }
            }
        }
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        if (!plugin.getConfig().getBoolean("modules.32k-damage.enabled"))
            return;
        if (event.getEntity() instanceof TippedArrow && event.getEntity().getShooter() instanceof Player
                && event.getHitEntity() instanceof Player) {
            TippedArrow arrow = (TippedArrow) event.getEntity();
            Player shooter = (Player) arrow.getShooter();
            Player vic = (Player) event.getHitEntity();
            ItemStack milk = new ItemStack(Material.MILK_BUCKET);
            for (PotionEffect effects : arrow.getCustomEffects()) {
                if (effects.getAmplifier() > 4) {
                    shooter.damage(shooter.getHealth());
                    shooter.getInventory().remove(Material.TIPPED_ARROW);
                    vic.getInventory().addItem(milk);
                    shooter.chat("Im a faggot who uses illegal arrows in pvp");
                }
            }
        }
    }
}