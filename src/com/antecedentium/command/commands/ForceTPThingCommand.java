package com.antecedentium.command.commands;

import com.antecedentium.command.Command;
import com.antecedentium.exception.InvalidUsageException;
import com.antecedentium.reflections.craftplayer.ReflectionUser;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForceTPThingCommand extends Command {
    public ForceTPThingCommand() {
        super("<x> <y> <z> <yaw> <pitch>", "EntityPlayer thing.", "antecedentium.command.forcetp", "forcetp");
    }

    @Override
    public void run(CommandSender sender, String[] args) throws InvalidUsageException {
        super.run(sender, args);

        if(!(sender instanceof Player)) throw new InvalidUsageException();
        if(args.length < 5) throw new InvalidUsageException();
        double x     = Double.parseDouble(args[0]),
               y     = Double.parseDouble(args[1]),
               z     = Double.parseDouble(args[2]);
        float  yaw   = Float.parseFloat(args[3]),
               pitch = Float.parseFloat(args[4]);
        ReflectionUser.get((Player)sender).craftPlayer.getHandle().forceSetPositionRotation(x, y, z, yaw, pitch);
    }
}