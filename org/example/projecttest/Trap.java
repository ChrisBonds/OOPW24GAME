package org.example.projecttest;

public class Trap extends Building{
    public Trap(String name) {
        super(name);
    }

    @Override
    void interact(Player player) {
        // Logic for losing a small amount of assets (power or wealth)
    }
}
