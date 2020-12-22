package com.antecedentium.command;

import com.antecedentium.AnteCedentium;
import com.antecedentium.exception.InvalidUsageException;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class Command {
    public static ArrayList<Command> commands = new ArrayList<>();
    public static Command getCommand(String alias) {
        for(Command command : commands)
            for(String c_alias : command.aliases)
                if(c_alias.equalsIgnoreCase(alias))
                    return command;
        return null;
    }

    public final String[] aliases;
    public final String usage;
    public final String description;
    public final String permission;

    public Command(String usage, String description, String permission, String... aliases) {
        this.aliases = aliases;
        this.usage = usage;
        this.description = description;
        this.permission = permission;

        commands.add(this);
    }

    public void register() {
        for(String alias : aliases)
            AnteCedentium.INSTANCE.getCommand(alias.toLowerCase()).setExecutor(AnteCedentium.INSTANCE.commandHandler);
    }

    public void run(CommandSender sender, String[] args) throws InvalidUsageException { }
}