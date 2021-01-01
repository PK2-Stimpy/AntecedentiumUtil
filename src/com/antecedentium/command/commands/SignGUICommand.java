package com.antecedentium.command.commands;

import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import com.antecedentium.reflections.ReflectionUtils;
import com.antecedentium.reflections.craftplayer.ReflectionUser;
import com.antecedentium.reflections.craftplayer.obj.CraftPlayerObject;
import com.antecedentium.reflections.craftplayer.obj.EntityPlayerObject;
import com.antecedentium.reflections.craftplayer.obj.PlayerConnectionObject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SignGUICommand extends Command {
    public SignGUICommand() {
        super("", "Opens SignGUI.", "antecedentium.command.sign", "sign");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        if(sender instanceof Player) {
            Player player = (Player)sender;
            CraftPlayerObject craftPlayer = ReflectionUser.get(player).craftPlayer;
            EntityPlayerObject entityPlayer = craftPlayer.getHandle();

            Block block = player.getWorld().getBlockAt(28174005, 255, 27164093);
            Material oldBlockMaterial = player.getWorld().getBlockAt(28174005, 255, 27164093).getType();

            block.setType(Material.AIR);

            Object world = ReflectionUtils.tryInvokeDeclared(craftPlayer, "getWorld", null);
            Object tileEntity = ReflectionUtils.tryInvokeDeclared(world, "getTileEntity", new Class<?>[] {double.class, double.class, double.class}, 28174005, 255, 27164093);
            Object tileEntitySign = ReflectionUtils.tryForName("net.minecraft.server.%SERVER_VERSION%.TileEntitySign").cast(tileEntity);

            entityPlayer.openSign(tileEntitySign);
            block.setType(oldBlockMaterial);
        }
    }
}