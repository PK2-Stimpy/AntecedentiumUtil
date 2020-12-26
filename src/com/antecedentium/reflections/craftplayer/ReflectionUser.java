package com.antecedentium.reflections.craftplayer;

import com.antecedentium.AnteCedentium;
import com.antecedentium.reflections.craftplayer.obj.CraftPlayerObject;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ReflectionUser {
    /* STATIC */
    public static final ArrayList<ReflectionUser> reflectionUsers = new ArrayList<>();
    public static ReflectionUser get(Player player) {
        for(ReflectionUser reflectionUser : reflectionUsers)
            if(reflectionUser.player.getName().contentEquals(player.getName()))
                return reflectionUser;
        return null;
    }
    public static boolean exists(Player player) { return get(player)!=null; }
    public static ReflectionUser add(Player player) {
        ReflectionUser reflectionUser = get(player);

        if(reflectionUser == null) {
            reflectionUser = new ReflectionUser(player);
            reflectionUsers.add(reflectionUser);
            return reflectionUser;
        }
        return reflectionUser;
    }
    public static void remove(Player player) {
        for(int i = 0; i < reflectionUsers.size(); i++)
            if(reflectionUsers.get(i).player.getName().contentEquals(player.getName()))
                reflectionUsers.remove(i);
    }

    /*        */
    public final Player player;
    public final CraftPlayerObject craftPlayer;

    public ReflectionUser(Player player) {
        this.player = player;
        this.craftPlayer = (CraftPlayerObject) AnteCedentium.INSTANCE.craftPlayerReflections.invoke(player);
    }
}