package com.antecedentium.events.enums.checks;

public enum AICheckEnum {
    NONE(0), LOW(2), NORMAL(5), STRICT(1000), HYPIXEL(182391283);

    public final int lvl;
    AICheckEnum(int lvl) { this.lvl = lvl; }
}