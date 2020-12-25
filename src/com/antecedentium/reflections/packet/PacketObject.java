package com.antecedentium.reflections.packet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class PacketObject {
    private Class<?> packet;
    private Object instance;

    public PacketObject(Class<?> packet, Object... args) {
        this.packet = packet;
        Constructor construct = null;
        try {
            for (Constructor constructor : packet.getConstructors()) {
                Class<?>[] params = constructor.getParameterTypes();
                if (args.length < 2) {
                    if (params.length == 0) {
                        construct = constructor;
                        break;
                    }
                    continue;
                }
                boolean canConstruct = true;
                for (int i = 0; i < params.length; i++) {
                    Class<?> parameter = params[i];
                    if (args.length > i) {
                        if (args[i + 1].getClass() != parameter) {
                            canConstruct = false;
                            break;
                        }
                    } else {
                        canConstruct = false;
                        break;
                    }
                }
                construct = constructor;
                break;
            }
            ArrayList<Object> params = new ArrayList<>();
            for (int i = 1; i < args.length; i++)
                params.add(args[i]);

            instance = construct.newInstance(params.toArray());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Field getDeclaredField(String fieldName) {
        try {
            Field field = instance.getClass().getDeclaredField(fieldName);
            if (!field.isAccessible())
                field.setAccessible(true);
            return field;
        } catch (Exception exception) { exception.printStackTrace(); }
        return null;
    }

    public Object getDeclaredFieldValue(String fieldName) {
        try {
            Field field = getDeclaredField(fieldName);
            return field.get(instance);
        } catch (Exception exception) { exception.printStackTrace(); }
        return null;
    }

    public void setDeclaredField(String fieldName, Object value) {
        try {
            Field field = getDeclaredField(fieldName);
            field.set(instance, value);
        } catch (Exception exception) { exception.printStackTrace(); }
    }

    public Object getInstance() { return instance; }
    public Class<?> getPacket() { return packet; }
}