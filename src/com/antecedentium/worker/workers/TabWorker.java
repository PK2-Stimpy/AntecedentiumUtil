package com.antecedentium.worker.workers;

import com.antecedentium.AnteCedentium;
import com.antecedentium.util.MathUtil;
import com.antecedentium.worker.Worker;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.List;

public class TabWorker extends Worker {
    public TabWorker() {
        super(AnteCedentium.INSTANCE.getConfig().getInt("modules.tab.config.updated-every")*100);
    }

    private String getServerVersion() {
        String version;
        try { version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3]; } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return "unknown";
        }
        return version;
    }

    @Override
    public void run() {
        if(!AnteCedentium.INSTANCE.getConfig().getBoolean("modules.tab.enabled"))
            return;
        this.delay = AnteCedentium.INSTANCE.getConfig().getInt("modules.tab.config.updated-every")*100;
        try {
            StringBuilder headerString = new StringBuilder();
            List<String> headerArgs = AnteCedentium.INSTANCE.getConfig().getStringList("modules.tab.config.info");

            for(int i = 0; i < headerArgs.size(); i++)
                headerString.append("§6" + headerArgs.get(i)+((i==headerArgs.size()-1)?"":"\n"));

            Class<?> chatTextClass = Class.forName("net.minecraft.server." + getServerVersion() + ".ChatComponentText");
            Object header = chatTextClass.getConstructors()[0].newInstance("\n" +
                    "" +
                    "§6Antece§edentium\n" +
                    "\n" +
                    headerString.toString() + "\n");

            Class<?> packetClass = Class.forName("net.minecraft.server." + getServerVersion() + ".PacketPlayOutPlayerListHeaderFooter");

            for(Player player : Bukkit.getOnlinePlayers()) {
                Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + getServerVersion() + ".entity.CraftPlayer");
                Object handle = craftPlayer.cast(player).getClass().getMethod("getHandle").invoke(craftPlayer.cast(player));
                int ping = handle.getClass().getDeclaredField("ping").getInt(handle);

                Object packetPlayOutPlayerListHeaderFooter = packetClass.getConstructors()[0].newInstance();
                Field a = packetPlayOutPlayerListHeaderFooter.getClass().getDeclaredField("a");
                a.setAccessible(true);
                Field b = packetPlayOutPlayerListHeaderFooter.getClass().getDeclaredField("b");
                b.setAccessible(true);

                a.set(packetPlayOutPlayerListHeaderFooter, header);

                double tps = MathUtil.round(Bukkit.getTPS()[0], 2);
                b.set(packetPlayOutPlayerListHeaderFooter, chatTextClass.getConstructors()[0].newInstance("\n" +
                        "" +
                        "§7tps: §a" + ((tps>20D)?20D:tps) + " §7ping: §f" + ping + " §7players: §f" + Bukkit.getOnlinePlayers().size() + " §7uptime: §f" + PlaceholderAPI.setPlaceholders(player, "%server_uptime%") + "\n" +
                        "" +
                        "\n" +
                        "§7contact: " + AnteCedentium.INSTANCE.getConfig().getString("modules.tab.config.footer.contact") + "\n" +
                        "§7discussion: " + AnteCedentium.INSTANCE.getConfig().getString("modules.tab.config.footer.discussion") + "\n" +
                        "§7donate: " + AnteCedentium.INSTANCE.getConfig().getString("modules.tab.config.footer.store") + "\n" +
                        "§7These are the only official antecedentium websites and contacts" +
                        "\n"));

                Class packetMasterClass = Class.forName("net.minecraft.server." + getServerVersion() + ".Packet");
                Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
                playerConnection.getClass().getMethod("sendPacket", packetMasterClass).invoke(playerConnection, packetPlayOutPlayerListHeaderFooter);
            }
        } catch (Exception exception) { exception.printStackTrace(); }
    }
}