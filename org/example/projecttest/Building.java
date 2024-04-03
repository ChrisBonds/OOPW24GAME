package org.example.projecttest;

public abstract class Building {
    protected String name;

    public Building(String name){
        this.name = name;
    }

    abstract void interact(Player player);
}
