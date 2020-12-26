package com.antecedentium.reflections.craftplayer.obj;

import com.antecedentium.reflections.ReflectionUtils;
import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class EntityPlayerObject {
    public final Player player;

    private Object handle;
    private PlayerConnectionObject playerConnection = null;

    public EntityPlayerObject(Player player, Object handle) {
        this.player = player;
        this.handle = handle;
        this.playerConnection = new PlayerConnectionObject(player, ReflectionUtils.tryGet(handle, "playerConnection"));
    }

    public void collide(Object entity) { ReflectionUtils.tryInvoke(handle, "collide", new Class<?>[] {ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.Entity")}, entity); }

    public void broadcastCarriedItem() { ReflectionUtils.tryInvokeDeclared(handle, "broadcastCarriedItem", null); }
    public void closeInventory() { ReflectionUtils.tryInvokeDeclared(handle, "closeInventory", null); }
    public void closeInventory(InventoryCloseEvent.Reason reason) { ReflectionUtils.tryInvokeDeclared(handle, "closeInventory", new Class<?>[]{InventoryCloseEvent.Reason.class}, reason); }
    public void copyFrom(Object entityPlayer, boolean flag) { ReflectionUtils.tryInvokeDeclared(handle, "copyFrom", new Class<?>[]{ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.EntityPlayer"), boolean.class}, entityPlayer, flag); }
    public boolean damageEntity(Object damageSource, float f) { Object object = ReflectionUtils.tryInvokeDeclared(handle, "damageEntity", new Class<?>[]{ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.DamageSource"), float.class}, f); return object==null?true:(boolean)object; }
    public void die(Object damageSource) { ReflectionUtils.tryInvokeDeclared(handle, "die", new Class<?>[]{ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.DamageSource")}, damageSource); }
    public void enchantDone(Object itemStack, int i) { ReflectionUtils.tryInvokeDeclared(handle, "enchantDone", new Class<?>[] {ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.ItemStack"), int.class}, itemStack, i); }
    public void enderTeleportTo(double d0, double d1, double d2) { ReflectionUtils.tryInvokeDeclared(handle, "enderTeleportTo", new Class<?>[] {double.class, double.class, double.class}, d0, d1, d2); }
    public void enterCombat() { ReflectionUtils.tryInvokeDeclared(handle, "enterCombat", null); }
    public void exitCombat() { ReflectionUtils.tryInvokeDeclared(handle, "exitCombat", null); }
    public void forceSetPositionRotation(double x, double y, double z, float yaw, float pitch) { ReflectionUtils.tryInvokeDeclared(handle, "forceSetPositionRotation", new Class<?>[]{double.class, double.class, double.class, float.class, float.class}, x, y, z, yaw, pitch); }
    public Object getBukkitEntity() { return ReflectionUtils.tryInvokeDeclared(handle, "getBukkitEntity", null); }
    public void levelDown(int i) { ReflectionUtils.tryInvokeDeclared(handle, "levelDown", new Class<?>[]{int.class}, i); }
    public int nextContainerCounter() { Object object = ReflectionUtils.tryInvokeDeclared(handle, "nextContainerCounter", null); return object==null?-1:(int)object; }
    public void openContainer(Object iInventory) { ReflectionUtils.tryInvokeDeclared(handle, "openContainer", new Class<?>[] {ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.IInventory")}, iInventory); }
    public void openHorseInventory(Object entityHorseAbstract) { ReflectionUtils.tryInvokeDeclared(handle, "openHorseInventory", new Class<?>[] {ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.EntityHorseAbstract")}, entityHorseAbstract); }
    public void openSign(Object tileEntitySign) { ReflectionUtils.tryInvokeDeclared(handle, "openSign", new Class<?>[]{ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.TileEntitySign")}, tileEntitySign); }
    public void openTileEntity(Object tileEntityContainer) { ReflectionUtils.tryInvokeDeclared(handle, "openTileEntity", new Class<?>[]{ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.ITileEntityContainer")}, tileEntityContainer); }
    public void openTrade(Object iMerchant) { ReflectionUtils.tryInvokeDeclared(handle, "openTrade", new Class<?>[]{ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.IMerchant")}, iMerchant); }
    public void receive(Object entity, int i) { ReflectionUtils.tryInvokeDeclared(handle, "receive", new Class<?>[]{ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.Entity"), int.class}, entity, i); }
    public void reset() { ReflectionUtils.tryInvokeDeclared(handle, "reset", null); }
    public void resetIdleTimer() { ReflectionUtils.tryInvokeDeclared(handle, "resetIdleTimer", null); }
    public void resetPlayerWeather() { ReflectionUtils.tryInvokeDeclared(handle, "resetPlayerWeather", null); }
    public void setContainerData(Object container, int i, int j) { ReflectionUtils.tryInvokeDeclared(handle, "setContainerData", new Class<?>[] {ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.Container"), int.class, int.class}, container, i, j); }
    public void setPlayerWeather(WeatherType weatherType, boolean plugin) { ReflectionUtils.tryInvokeDeclared(handle, "setPlayerWeather", new Class<?>[] {WeatherType.class, boolean.class}, weatherType, plugin); }
    public void setResourcePack(String s, String s1) { ReflectionUtils.tryInvokeDeclared(handle, "setResourcePack", new Class<?>[] {String.class, String.class}, s, s1); }
    public void setSpectatorTarget(Object entity) { ReflectionUtils.tryInvokeDeclared(handle, "setSpectatorTarget", new Class<?>[]{ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.Entity")}, entity); }
    public void setViewDistance(int viewDistance) { ReflectionUtils.tryInvokeDeclared(handle, "setViewDistance", new Class<?>[] {int.class}, viewDistance); }
    public void spawnIn(Object world) { ReflectionUtils.tryInvokeDeclared(handle, "spawnIn", new Class<?>[] {ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.World")}, world); }
    public void stopRiding() { ReflectionUtils.tryInvokeDeclared(handle, "stopRiding", null); }
    public void syncInventory() { ReflectionUtils.tryInvokeDeclared(handle, "syncInventory", null); }
    public void tickWeather() { ReflectionUtils.tryInvokeDeclared(handle, "tickWeather", null); }
    public String toString() { return (String)ReflectionUtils.tryInvokeDeclared(handle, "toString", null); }
    public void triggerHealthUpdate() { ReflectionUtils.tryInvokeDeclared(handle, "triggerHealthUpdate", null); }
    public void updateAbilities() { ReflectionUtils.tryInvokeDeclared(handle, "updateAbilities", null); }
    public void updateInventory(Object container) { ReflectionUtils.tryInvokeDeclared(handle, "updateInventory", new Class<?>[] {ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.Container")}, container); }
    public void updateWeather(float oldRain, float newRain, float oldThunder, float newThunder) { ReflectionUtils.tryInvokeDeclared(handle, "updateWeather", new Class<?>[]{float.class, float.class, float.class, float.class}, oldRain, newRain, oldThunder, newThunder); }
    public Object x() { return ReflectionUtils.tryInvokeDeclared(handle, "x", null); }
    public boolean z() { Object object = ReflectionUtils.tryInvokeDeclared(handle, "z", null); return object==null?true:(boolean)object; }

    public int _ping() { return ReflectionUtils.tryGetInt(handle, "ping"); }
    public PlayerConnectionObject _playerConnection() { return playerConnection; }
    public String _locale() { return (String)ReflectionUtils.tryGetDeclared(handle, "locale"); }

    /*
    public Object _abilities() { return ReflectionUtils.tryGet(handle, "abilities"); }
    public long _activatedTick() { return (long)ReflectionUtils.tryGet(handle, "activatedTick"); }
    public byte _activationType() { return (byte)ReflectionUtils.tryGet(handle, "activationType"); }
    public Object _activeContainer() { return ReflectionUtils.tryGet(handle, "activeContainer"); }
    public boolean _affectsSpawning() { Object object = ReflectionUtils.tryGet(handle, "affectsSpawning"); return object==null?true:(boolean)object; }
    public boolean _attachedToPlayer() { Object object = ReflectionUtils.tryGet(handle, "attachedToPlayer"); return object==null?true:(boolean)object; }
    public Object _bedPosition() { return ReflectionUtils.tryGet(handle, "bedPosition"); }
    public boolean _canPickUpLoot() { Object object = ReflectionUtils.tryGet(handle, "canPickUpLoot"); return object==null?true:(boolean)object; }
    public boolean _collides() { Object object = ReflectionUtils.tryGet(handle, "collides"); return object==null?true:(boolean)object; }
     */
}