package org.example.projecttest;

public class Trap extends Building{
    public Trap(String name) {
        super(name);
    }

    @Override
    public void interact(Player player, int x, int y) {
        // Logic for losing a small amount of assets (power or wealth)
    }
}
