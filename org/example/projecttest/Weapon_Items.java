package org.example.projecttest;

public class Weapon_Items {
    private WeaponType type;
    private int cost;
    private int powerBonus;

    public Weapon_Items(WeaponType type, int cost, int powerBonus) {
        this.type = type;
        this.cost = cost;
        this.powerBonus = powerBonus;
    }

    // Getters
    public WeaponType getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public int getPowerBonus() {
        return powerBonus;
    }
}

