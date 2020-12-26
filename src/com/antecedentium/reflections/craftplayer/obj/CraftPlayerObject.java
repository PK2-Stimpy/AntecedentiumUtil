package com.antecedentium.reflections.craftplayer.obj;

import com.antecedentium.reflections.ReflectionUtils;
import com.mojang.authlib.GameProfile;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import java.util.Map;

public class CraftPlayerObject {
    public final Player player;

    private Class<?> craftPlayer;
    private Object instance;
    private EntityPlayerObject entityPlayer = null;

    public CraftPlayerObject(Player player, Class<?> craftPlayer) {
        this.player = player;
        this.craftPlayer = craftPlayer;
        this.instance = craftPlayer.cast(player);
        this.entityPlayer = new EntityPlayerObject(player, ReflectionUtils.tryInvokeDeclared(instance, "getHandle", null));
    }

    public EntityPlayerObject getHandle() { return entityPlayer; }
    public void addChannel(String channel) { ReflectionUtils.tryInvokeDeclared(instance, "addChannel", new Class<?>[] {String.class}, channel); }
    public void disconnect(String reason) { ReflectionUtils.tryInvokeDeclared(instance, "disconnect", new Class<?>[] {String.class}, reason); }
    public GameProfile getProfile() { return (GameProfile)ReflectionUtils.tryInvokeDeclared(instance, "getProfile", null); }
    public float getScaledHealth() { return (float)ReflectionUtils.tryInvokeDeclared(instance, "getScaledHealth", null); }
    public void readExtraData(Object nbtTagCompound) { ReflectionUtils.tryInvokeDeclared(instance, "readExtraData", new Class<?>[]{ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.NBTTagCompound")}, nbtTagCompound); }
    public void removeChannel(String channel) { ReflectionUtils.tryInvokeDeclared(instance, "removeChannel", new Class<?>[]{String.class}, channel); }
    public void removeDisconnectingPlayer(Player player) { ReflectionUtils.tryInvokeDeclared(instance, "removeDisconnectingPlayer", new Class<?>[] {Player.class}, player); }
    public void sendHealthUpdate() { ReflectionUtils.tryInvokeDeclared(instance, "sendHealthUpdate", null); }
    public void sendSupportedChannels() { ReflectionUtils.tryInvokeDeclared(instance, "sendSupportedChannels", null); }
    public Map<String, Object> serialize() { return (Map<String, Object>)ReflectionUtils.tryInvokeDeclared(instance, "serialize", null); }
    public void setExtraData(Object nbtTagCompound) { ReflectionUtils.tryInvokeDeclared(instance, "setExtraData", new Class<?>[]{ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.NBTTagCompound")}, nbtTagCompound); }
    public void setHandle(Object entityPlayer) { ReflectionUtils.tryInvokeDeclared(instance, "setHandle", new Class<?>[]{ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.EntityPlayer")}, entityPlayer); }
    public void setRealHealth(double health) { ReflectionUtils.tryInvokeDeclared(instance, "setRealHealth", new Class<?>[] {double.class}, health); }
    public void setResourcePackStatus(PlayerResourcePackStatusEvent.Status status) { ReflectionUtils.tryInvokeDeclared(instance, "setResourcePackStatus", new Class<?>[] {PlayerResourcePackStatusEvent.Status.class}, status); }

}