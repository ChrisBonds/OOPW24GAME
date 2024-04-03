package org.example.projecttest;

import java.util.ArrayList;
import java.util.List;

public class MarketHouse extends Building {
    private List<Weapon_Items> itemsForSale;

    public MarketHouse(String name) {
        super(name);
        this.itemsForSale = new ArrayList<>();
        // Initialize with items or weapons
    }

    @Override
    void interact(Player player) {
        // Logic for interacting with the market, such as purchasing items or revealing treasure locations
    }

    // Additional methods specific to the market house
}
