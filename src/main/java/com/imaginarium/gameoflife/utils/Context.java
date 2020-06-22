package com.imaginarium.gameoflife.utils;

import com.imaginarium.gameoflife.LogicOfLife;
import com.imaginarium.gameoflife.components.PrimordialSea;

public class Context {
    
    private final PrimordialSea WORLD;
    private final LogicOfLife LIFE;

    public Context(PrimordialSea world, LogicOfLife life) {
        WORLD = world;
        LIFE = life;
    }

    public PrimordialSea getWorld() {
        return this.WORLD;
    }

    public LogicOfLife getLife() {
        return this.LIFE;
    }
}
