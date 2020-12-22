package com.antecedentium.events.event;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PacketWriteEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public final Player player;
    public final ChannelHandlerContext channelHandlerContext;
    public final ChannelPromise channelPromise;
    public final Object packet;
    public boolean cancelled = false;
    public PacketWriteEvent(Player player, ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise, Object packet) {
        this.player = player;
        this.channelHandlerContext = channelHandlerContext;
        this.channelPromise = channelPromise;
        this.packet = packet;
    }

    public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}