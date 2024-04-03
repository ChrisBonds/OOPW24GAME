package org.example.projecttest;

import java.util.ArrayList;

class Market extends House {
    private ArrayList<Item> itemsForSale;

    Market(String name) {
        super(name);
        itemsForSale = new ArrayList<Item>(3);
        WeaponManager weaponManager = new WeaponManager();
        for(int i = 0; i<2; i++){
            itemsForSale.add(weaponManager.makeRandomWeapon());
        }
    }
    //always store a treasure map in one slot of the market

    @Override
    void enters(Player player) {
        entered = true;
        //display weapon options and treasureMap to user with GUI?

        // upon purchase, add item to users inventory
    }
}