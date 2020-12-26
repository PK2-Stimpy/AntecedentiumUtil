package com.antecedentium.reflections;

import com.sun.istack.internal.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public class Reflections {
    public static ArrayList<Reflections> reflections = new ArrayList<>();
    public static Reflections getReflections(@NotNull Class<Reflections> reflectionsClass) {
        for(Reflections reflections : Reflections.reflections)
            if(reflections.getClass() == reflectionsClass)
                return reflections;
        return null;
    }

    public Reflections() {
        reflections.add(this);
    }

    public Object invoke() { throw new NotImplementedException(); }
    public Object invoke(Object... args) { throw new NotImplementedException(); }
}