package com.antecedentium.reflections.craftplayer;

import com.antecedentium.reflections.ReflectionUtils;
import com.antecedentium.reflections.Reflections;
import com.antecedentium.reflections.craftplayer.obj.CraftPlayerObject;
import org.bukkit.entity.Player;

public class CraftPlayerReflections extends Reflections {
    @Override
    public Object invoke(Object... args) {
        if(args[0] instanceof Player) {
            try {
                return new CraftPlayerObject((Player) args[0], Class.forName("org.bukkit.craftbukkit." + ReflectionUtils.getServerVersion() + ".entity.CraftPlayer"));
            } catch (Exception exception) { exception.printStackTrace(); }
        }

        return null;
    }
}