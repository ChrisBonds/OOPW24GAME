package org.example.projecttest;

class LostItemHouse extends House {
    Item lostItem;

    LostItemHouse(String name, Item lostItem) {
        super(name);
        this.lostItem = lostItem;
    }

    @Override
    void enters(Player player) {
        entered = true;
        player.getHeldItems().add(lostItem);
    }
}