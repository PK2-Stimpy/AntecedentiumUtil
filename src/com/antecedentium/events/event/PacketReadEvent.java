package com.antecedentium.events.event;

import io.netty.channel.ChannelHandlerContext;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PacketReadEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public final Player player;
    public final ChannelHandlerContext channelHandlerContext;
    public final Object packet;
    public boolean cancelled = false;
    public PacketReadEvent(Player player, ChannelHandlerContext channelHandlerContext, Object packet) {
        this.player = player;
        this.channelHandlerContext = channelHandlerContext;
        this.packet = packet;
    }

    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}