package com.antecedentium.reflections.packet;

import com.antecedentium.reflections.ReflectionUtils;
import com.antecedentium.reflections.Reflections;

public class PacketReflections extends Reflections {
    @Override
    public Object invoke(Object... args) {
        if(args[0] instanceof String) {
            String packetName = "net.minecraft.server." + ReflectionUtils.getServerVersion() + "." + (String)args[0];
            try {
                return new PacketObject(Class.forName(packetName));
            } catch (Exception exception) { exception.printStackTrace(); }
        }
        return null;
    }
}