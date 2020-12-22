package com.antecedentium.events;

import com.antecedentium.events.event.PacketReadEvent;
import com.antecedentium.events.event.PacketWriteEvent;
import io.netty.channel.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PacketListener implements Listener {
    String getServerVersion() {
        String version;
        try { version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3]; } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return "unknown";
        }
        return version;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        injectPlayer(event.getPlayer());
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        detachPlayer(event.getPlayer());
    }

    private void detachPlayer(Player player) {
        try {
            Class<?> strClass = (Class<?>) Class.forName("org.bukkit.craftbukkit." + getServerVersion() + ".entity.CraftPlayer");
            Object object = strClass.cast(player).getClass().getMethod("getHandle").invoke(strClass.cast(player));
            Object playerConnection = object.getClass().getField("playerConnection").get(object);
            Object networkManager = playerConnection.getClass().getField("networkManager").get(playerConnection);
            Channel channel = (Channel)(networkManager.getClass().getField("channel").get(networkManager));

            channel.eventLoop().submit(() -> {
                channel.pipeline().remove(player.getName());
                return null;
            });
        } catch (Exception e) { e.printStackTrace(); }
    }
    private void injectPlayer(Player player) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {
                /* Handle read packet event. */
                PacketReadEvent packetReadEvent = new PacketReadEvent(player, ctx, packet);
                Bukkit.getServer().getPluginManager().callEvent(packetReadEvent);
                if(packetReadEvent.cancelled)
                    return;

                super.channelRead(ctx, packet);
            }

            @Override
            public void write(ChannelHandlerContext ctx, Object packet, ChannelPromise promise) throws Exception {
                /* Handle write packet event */
                PacketWriteEvent packetWriteEvent = new PacketWriteEvent(player, ctx, promise, packet);
                Bukkit.getServer().getPluginManager().callEvent(packetWriteEvent);
                if(packetWriteEvent.cancelled)
                    return;

                super.write(ctx, packet, promise);
            }
        };
        try {
            Class<?> strClass = (Class<?>) Class.forName("org.bukkit.craftbukkit." + getServerVersion() + ".entity.CraftPlayer");
            Object object = strClass.cast(player).getClass().getMethod("getHandle").invoke(strClass.cast(player));
            Object playerConnection = object.getClass().getField("playerConnection").get(object);
            Object networkManager = playerConnection.getClass().getField("networkManager").get(playerConnection);
            Channel channel = (Channel)(networkManager.getClass().getField("channel").get(networkManager));

            ChannelPipeline pipeline = channel.pipeline();
            pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
        } catch (Exception e) { e.printStackTrace(); }
    }
}