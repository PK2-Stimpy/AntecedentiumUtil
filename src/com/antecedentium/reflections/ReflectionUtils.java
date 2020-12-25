package com.antecedentium.reflections;

import org.bukkit.Bukkit;

public class ReflectionUtils {
    public static String getServerVersion() {
        String version;
        try { version = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3]; } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return "unknown";
        }
        return version;
    }
}