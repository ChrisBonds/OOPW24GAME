package org.example.projecttest;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class House {
    String name;
    boolean entered;

    House(String name) {
        this.name = name;
        this.entered = false;
    }

    abstract void enters(Player player);
}


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

class TrapHouse extends House {

    TrapHouse(String name) {
        super(name);
    }

    @Override
    void enters(Player player) {
        entered = true;

        // Ask the user to choose whether they want to lose power or money
        System.out.println("Enter 1 to lose power, enter 2 to lose money: ");
        Scanner input = new Scanner(System.in);
        int choice;

        try {
            choice = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Exiting program.");
            return; //exit method
        }

        if (choice == 1) {
            // Lose 25% of weapons randomly
            int weaponsLost = (Item.getNumItemType(player.getHeldItems(), Weapon.class)) / 4; // Make sure this rounds down
            for (int i = 0; i < weaponsLost; i++) {
                Item.removeRandomItem(player.getHeldItems(), Weapon.class);
            }
        } else if (choice == 2) {
            //lose 25 % of your money
            int amountLost = (int) (player.getWallet().getMoney() * 0.25);
            player.getWallet().deductMoney(amountLost);
        } else {
            System.out.println("Invalid choice entered. Exiting program.");
            // Exit the method gracefully
        }
    }
}

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

