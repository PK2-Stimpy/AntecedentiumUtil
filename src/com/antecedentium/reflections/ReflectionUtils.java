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

    public static Object tryInvokeDeclared(Object instance, String name, Class<?>[] parameterTypes, Object... args) {
        try {
            return instance.getClass().getDeclaredMethod(name, parameterTypes).invoke(instance, args);
        } catch (Exception exception) { exception.printStackTrace(); }
        return null;
    }

    public static Object tryInvoke(Object instance, String name, Class<?>[] parameterTypes, Object... args) {
        try {
            return instance.getClass().getMethod(name, parameterTypes).invoke(instance, args);
        } catch (Exception exception) { exception.printStackTrace(); }
        return null;
    }

    public static Object tryGetDeclared(Object instance, String name) {
        try {
            return instance.getClass().getDeclaredField(name).get(instance);
        } catch (Exception exception) { exception.printStackTrace(); }
        return null;
    }

    public static int tryGetIntDeclared(Object instance, String name) {
        try {
            return instance.getClass().getDeclaredField(name).getInt(instance);
        } catch (Exception exception) { exception.printStackTrace(); }
        return -1;
    }

    public static boolean tryGetBoolDeclared(Object instance, String name) {
        try {
            return instance.getClass().getDeclaredField(name).getBoolean(instance);
        } catch (Exception exception) { exception.printStackTrace(); }
        return true;
    }

    public static Object tryGet(Object instance, String name) {
        try {
            return instance.getClass().getField(name).get(instance);
        } catch (Exception exception) { exception.printStackTrace(); }
        return null;
    }

    public static int tryGetInt(Object instance, String name) {
        try {
            return instance.getClass().getField(name).getInt(instance);
        } catch (Exception exception) { exception.printStackTrace(); }
        return -1;
    }

    public static boolean tryGetBool(Object instance, String name) {
        try {
            return instance.getClass().getField(name).getBoolean(instance);
        } catch (Exception exception) { exception.printStackTrace(); }
        return true;
    }

    public static Class<?> tryForName(String name) {
        try {
            return Class.forName(name.replaceAll("%SERVER_VERSION%", getServerVersion()));
        } catch (Exception exception) { exception.printStackTrace(); }
        return null;
    }

    public static Object iChatBaseComponent(String string) {
        try {

        } catch (Exception exception) { exception.printStackTrace(); }
        return null;
    }
}