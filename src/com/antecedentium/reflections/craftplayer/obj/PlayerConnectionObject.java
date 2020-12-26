package com.antecedentium.reflections.craftplayer.obj;

import com.antecedentium.reflections.ReflectionUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Set;

public class PlayerConnectionObject {
    public final Player player;

    private Object instance;
    private NetworkManagerObject networkManager;

    public PlayerConnectionObject(Player player, Object instance) {
        this.player = player;
        this.instance = instance;
        this.networkManager = new NetworkManagerObject(player, ReflectionUtils.tryGetDeclared(instance, "networkManager"));
    }

    public Object _player() { return ReflectionUtils.tryGetDeclared(instance, "player"); }
    public NetworkManagerObject _networkManager() { return networkManager; }

    public Object a() { return ReflectionUtils.tryInvokeDeclared(instance, "a", null); }
    public void a(Object object) { ReflectionUtils.tryInvokeDeclared(instance, "a", new Class<?>[]{object.getClass()}, object); }
    public void a(double d0, double d1, double d2, float f, float f1) { ReflectionUtils.tryInvokeDeclared(instance, "a", new Class<?>[] {double.class, double.class, double.class, float.class, float.class}, d0, d1, d2, f, f1); }
    public void a(double d0, double d1, double d2, float f, float f1, PlayerTeleportEvent.TeleportCause teleportCause) { ReflectionUtils.tryInvokeDeclared(instance, "a", new Class<?>[]{double.class, double.class, double.class, float.class, float.class, PlayerTeleportEvent.TeleportCause.class}, d0, d1, d2, f, f1, teleportCause); }
    public void a(double d0, double d1, double d2, float f, float f1, Set<?> set) { ReflectionUtils.tryInvokeDeclared(instance, "a", new Class<?>[] {double.class, double.class, double.class, float.class, float.class, Set.class}, d0, d1, d2, f, f1, set); }
    public void a(double d0, double d1, double d2, float f, float f1, Set<?> set, PlayerTeleportEvent.TeleportCause teleportCause) { ReflectionUtils.tryInvokeDeclared(instance, "a", new Class<?>[] {double.class, double.class, double.class, float.class, float.class, Set.class, PlayerTeleportEvent.TeleportCause.class}, d0, d1, d2, f, f1, set, teleportCause); }
    public void chat(String s, boolean async) { ReflectionUtils.tryInvokeDeclared(instance, "chat", new Class<?>[] {String.class, boolean.class}, s, async); }
    public void disconnect(String s) { ReflectionUtils.tryInvokeDeclared(instance, "disconnect", new Class<?>[]{String.class}, s); }
    public void e() { ReflectionUtils.tryInvokeDeclared(instance, "e", null); }
    public Object getPlayer() { return ReflectionUtils.tryInvokeDeclared(instance, "getPlayer", null); }
    public boolean isDisconnected() { Object object = ReflectionUtils.tryInvokeDeclared(instance, "isDisconnected", null); return object==null?true:(boolean)object; }
    public void sendPacket(Object packet) { ReflectionUtils.tryInvokeDeclared(instance, "sendPacket", new Class<?>[] {ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.Packet")}, packet); }
    public void syncPosition() { ReflectionUtils.tryInvokeDeclared(instance, "syncPosition", null); }
    public void teleport(Location dest) { ReflectionUtils.tryInvokeDeclared(instance, "teleport", new Class<?>[]{Location.class}, dest); }
}