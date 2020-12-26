package com.antecedentium.reflections.craftplayer.obj;

import com.antecedentium.reflections.ReflectionUtils;
import com.mojang.authlib.properties.Property;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.bukkit.entity.Player;

import javax.crypto.SecretKey;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.UUID;

public class NetworkManagerObject {
    public final Player player;

    private Object instance;

    public NetworkManagerObject(Player player, Object instance) {
        this.player = player;
        this.instance = instance;
    }

    public Channel _channel() { return (Channel) ReflectionUtils.tryGetDeclared(instance, "channel"); }
    public SocketAddress _l() { return (SocketAddress) ReflectionUtils.tryGetDeclared(instance, "l"); }
    public boolean _preparing() { return ReflectionUtils.tryGetBoolDeclared(instance, "preparing"); }
    public int _protocolVersion() { return ReflectionUtils.tryGetIntDeclared(instance, "protocolVersion"); }
    public Property[] _spoofedProfile() { return (Property[])ReflectionUtils.tryGetDeclared(instance, "spoofedProfile"); }
    public UUID _spoofedUUID() { return (UUID) ReflectionUtils.tryGetDeclared(instance, "spoofedUUID"); }
    public InetSocketAddress _virtualHost() { return (InetSocketAddress)ReflectionUtils.tryGetDeclared(instance, "virtualHost"); }

    public void a() { ReflectionUtils.tryInvokeDeclared(instance, "a", null); }
    public void a(SecretKey secretKey) { ReflectionUtils.tryInvokeDeclared(instance, "a", new Class<?>[] {secretKey.getClass()}, secretKey); }
    public void channelActive(ChannelHandlerContext channelHandlerContext) { ReflectionUtils.tryInvokeDeclared(instance, "channelActive", new Class<?>[]{channelHandlerContext.getClass()}, channelHandlerContext); }
    public void channelInactive(ChannelHandlerContext channelHandlerContext) { ReflectionUtils.tryInvokeDeclared(instance, "channelInactive", new Class<?>[]{channelHandlerContext.getClass()}, channelHandlerContext); }
    public void close(Object iChatBaseComponent) { ReflectionUtils.tryInvokeDeclared(instance, "close", new Class<?>[] {ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.IChatBaseComponent")}, iChatBaseComponent); }
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) { ReflectionUtils.tryInvokeDeclared(instance, "exceptionCaught", new Class<?>[] {channelHandlerContext.getClass(), throwable.getClass()}, channelHandlerContext, throwable); }
    public SocketAddress getRawAddress() { return (SocketAddress)ReflectionUtils.tryInvokeDeclared(instance, "getRawAddress", null); }
    public SocketAddress getSocketAddress() { return (SocketAddress)ReflectionUtils.tryInvokeDeclared(instance, "getSocketAddress", null); }
    public boolean h() { Object object = ReflectionUtils.tryInvokeDeclared(instance, "h", null); return object==null?true:(boolean)object; }
    public void handleDisconnection() { ReflectionUtils.tryInvokeDeclared(instance, "handleDisconnection", null); }
    public Object i() { return ReflectionUtils.tryInvokeDeclared(instance, "i", null); }
    public boolean isConnected() { Object object = ReflectionUtils.tryInvokeDeclared(instance, "isConnected", null); return object==null?true:(boolean)object; }
    public boolean isLocal() { Object object = ReflectionUtils.tryInvokeDeclared(instance, "isLocal", null); return object==null?true:(boolean)object; }
    public Object j() { return ReflectionUtils.tryInvokeDeclared(instance, "j", null); }
    public void sendPacket(Object packet) { ReflectionUtils.tryInvokeDeclared(instance, "sendPacket", new Class<?>[]{ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.Packet").getClass()}, packet); }
    public void sendPacket(Object packet, GenericFutureListener<? extends Future<? super Void>> genericFutureListener, GenericFutureListener<? extends Future<? super Void>>... aGenericFutureListener) { ReflectionUtils.tryInvokeDeclared(instance, "sendPacket", new Class<?>[] {ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.Packet").getClass(), genericFutureListener.getClass(), aGenericFutureListener.getClass()}, packet, genericFutureListener, aGenericFutureListener); }
    public void setCompressionLevel(int i) { ReflectionUtils.tryInvokeDeclared(instance, "setCompressionLevel", new Class<?>[] {int.class}, i); }
    public void setPacketListener(Object packetListener) { ReflectionUtils.tryInvokeDeclared(instance, "setPacketListener", new Class<?>[] { ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.PacketListener") }, packetListener); }
    public void setProtocol(Object enumProtocol) { ReflectionUtils.tryInvokeDeclared(instance, "setProtocol", new Class<?>[] {ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.EnumProtocol")}, enumProtocol); }
    public void stopReading() { ReflectionUtils.tryInvokeDeclared(instance, "stopReading", null); }
}