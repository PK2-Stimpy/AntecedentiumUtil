package com.antecedentium.command.commands;

import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import com.antecedentium.reflections.craftplayer.ReflectionUser;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderTPCommand extends Command {
    public EnderTPCommand() {
        super("<x> <y> <z>", "EnderTp testing", "antecedentium.command.endertp", "endertp");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);

        if(args.length < 3 || !(sender instanceof Player)) throw new InvalidUsageException();
        Double x = Double.parseDouble(args[0]),
               y = Double.parseDouble(args[1]),
               z = Double.parseDouble(args[2]);
        ReflectionUser.get((Player)sender).craftPlayer.getHandle().enderTeleportTo(x, y, z);
    }
}