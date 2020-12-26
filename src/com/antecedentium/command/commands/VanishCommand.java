package com.antecedentium.command.commands;

import com.antecedentium.AnteCedentium;
import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import com.antecedentium.reflections.craftplayer.ReflectionUser;
import com.antecedentium.reflections.craftplayer.obj.CraftPlayerObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishCommand extends Command {
    public static ArrayList<Player> vanished = new ArrayList<>();
    public static boolean exists(Player target) {
        for(Player player : vanished)
            if(player.getName().contentEquals(target.getName()))
                return true;
        return false;
    }
    public static void add(Player player) {
        if(!exists(player))
            vanished.add(player);
    }
    public static void rem(Player player) {
        if(exists(player))
            vanished.remove(player);
    }
    public static void vanish(Player target, boolean shouldVanish) {
        for(Player player : Bukkit.getOnlinePlayers())
            if(shouldVanish) player.hidePlayer(AnteCedentium.INSTANCE, target);
            else player.showPlayer(AnteCedentium.INSTANCE, target);
    }

    public VanishCommand() {
        super("", "Vanishes you from other players.", "antecedentium.command.vanish", "vanish");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);

        if(sender instanceof Player) {
            Player player = (Player)sender;
            CraftPlayerObject craftPlayer = ReflectionUser.get(player).craftPlayer;

            if(exists(player)) {
                rem(player);
                vanish(player, false);
                player.sendMessage(ChatColor.RED + "Now people can see you.");
                return;
            }
            add(player);
            vanish(player, true);
            player.sendMessage(ChatColor.GREEN + "Now you are invisible to everyone.");
        }
    }
}