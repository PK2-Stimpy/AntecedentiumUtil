package com.antecedentium.events;

import com.antecedentium.AnteCedentium;
import com.antecedentium.events.event.PacketReadEvent;
import com.antecedentium.events.event.PacketWriteEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NoRecipesListener implements Listener {
    @EventHandler
    public void packetRead(PacketReadEvent event) {
        if(!event.packet.toString().contains("PacketPlayInAutoRecipe") && !event.packet.toString().contains("PacketPlayInRecipeDisplayed")) return;

        event.cancelled = AnteCedentium.INSTANCE.getConfig().getBoolean("modules.recipe-lag.enabled");
    }

    @EventHandler
    public void packetWrite(PacketWriteEvent event) {
        if(!event.packet.toString().contains("PacketPlayOutAutoRecipe")) return;

        event.cancelled = AnteCedentium.INSTANCE.getConfig().getBoolean("modules.recipe-lag.enabled");
    }
}