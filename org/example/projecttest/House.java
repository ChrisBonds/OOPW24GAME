package org.example.projecttest;

import java.util.ArrayList;
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
        /* ask user to choose whether they would like to lose money or power
        System.out.println("Enter 1 to lose power, enter 2 to lose money : ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        if(choice == 1){
            // lose 25% of weapons randomly
            int weaponsLost = (player.getHeldItems().getNumWeapons) / 4; //make sure this rounds down
            for(int i = 0; i< weaponsLost; i++){
                player.removeRandomWeapon(player.getHeldItems());
            }
        } else if (choice == 2) {
            //roll dice to see how much you lose
            int amountLost = (GameMap.rollDice.diceRollResult) * 10;
            player.getWallet().deductMoney(amountLost);
        }
        else{
            System.out.println("invalid choice entered, exiting program");
            System.exit(1);
        }*/ //should probably do this in try-catch block
    }
}

class Market extends House {
    private ArrayList<Item> itemsForSale;

    Market(String name) {
        super(name);
        itemsForSale = new ArrayList<Item>(3);
        for(int i = 0; i < 2; i++){
            itemsForSale[i] = makeRandomWeapon;
        }
        itemsForSale[3] = new TreasureMap(); //always store a treasure map in one slot of the market
    }

    @Override
    void enters(Player player) {
        entered = true;
        //display weapon options and treasureMap to user with GUI?

        // upon purchase, add item to users inventory
    }
}

