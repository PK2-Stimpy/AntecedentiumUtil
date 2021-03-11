package com.antecedentium.worker.workers;

import com.antecedentium.AnteCedentium;
import com.antecedentium.command.commands.VanishCommand;
import com.antecedentium.reflections.craftplayer.ReflectionUser;
import com.antecedentium.reflections.craftplayer.obj.CraftPlayerObject;
import com.antecedentium.reflections.packet.PacketObject;
import com.antecedentium.util.MathUtil;
import com.antecedentium.worker.Worker;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.management.*;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
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
                    AnteCedentium.INSTANCE.getConfig().getString("modules.tab.config.colored-name").replaceAll("&", "§") + "\n" +
                    "\n" +
                    headerString.toString() + "\n");

            for(Player player : Bukkit.getOnlinePlayers()) {
                CraftPlayerObject craftPlayer = ReflectionUser.get(player).craftPlayer;
                if(craftPlayer == null)
                    continue;
                int ping = craftPlayer.getHandle()._ping();

                PacketObject packetPlayOutPlayerListHeaderFooter = (PacketObject)AnteCedentium.INSTANCE.packetReflections.invoke("PacketPlayOutPlayerListHeaderFooter");
                packetPlayOutPlayerListHeaderFooter.setDeclaredField("a", header);

                double tps = MathUtil.round(Bukkit.getTPS()[0], 2);
                packetPlayOutPlayerListHeaderFooter.setDeclaredField("b", chatTextClass.getConstructors()[0].newInstance("\n" +
                        "" +
                        "§7tps: §a" + PlaceholderAPI.setPlaceholders(player, "%server_tps_1_colored%") + " §7ping: §f" + ping + " §7players: §f" + (Bukkit.getOnlinePlayers().size() - VanishCommand.vanished.stream().filter(p -> Bukkit.getPlayer(p) != null).toArray().length) + " §7uptime: §f" + PlaceholderAPI.setPlaceholders(player, "%server_uptime%") + "\n" +
                        "§7ram: §f" + PlaceholderAPI.setPlaceholders(player, "%server_ram_used%") + " MB §7unique_players: §f" + PlaceholderAPI.setPlaceholders(player, "%server_unique_joins%") + "\n" +
                        "" +
                        "\n" +
                        "§7contact: " + AnteCedentium.INSTANCE.getConfig().getString("modules.tab.config.footer.contact") + "\n" +
                        "§7discussion: " + AnteCedentium.INSTANCE.getConfig().getString("modules.tab.config.footer.discussion") + "\n" +
                        "§7donate: " + AnteCedentium.INSTANCE.getConfig().getString("modules.tab.config.footer.store") + "\n" +
                        "§7These are the only official " + AnteCedentium.INSTANCE.getConfig().getString("modules.tab.config.name") + " websites and contacts" +
                        "\n"));

                Class packetMasterClass = Class.forName("net.minecraft.server." + getServerVersion() + ".Packet");
                craftPlayer.getHandle()._playerConnection().sendPacket(packetPlayOutPlayerListHeaderFooter.getInstance());
            }
        } catch (Exception exception) { exception.printStackTrace(); }
    }
}