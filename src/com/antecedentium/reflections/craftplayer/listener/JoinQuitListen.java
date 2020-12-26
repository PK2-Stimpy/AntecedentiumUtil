package com.antecedentium.reflections.craftplayer.listener;

import com.antecedentium.reflections.craftplayer.ReflectionUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListen implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void join(PlayerJoinEvent event) {
        ReflectionUser.add(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void quit(PlayerQuitEvent event) {
        ReflectionUser.remove(event.getPlayer());
    }
}