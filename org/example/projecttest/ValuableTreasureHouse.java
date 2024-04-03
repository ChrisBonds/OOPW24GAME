package org.example.projecttest;

class ValuableTreasureHouse extends House {
    Item treasure;

    ValuableTreasureHouse(String name, Item treasure) {
        super(name);
        this.treasure = treasure;
        this.entered = false;
    }

    @Override
    void enters(Player player) {
        entered = true;
        player.getHeldItems().add(treasure);
    }
}
