package org.example.projecttest;

import java.util.InputMismatchException;
import java.util.Scanner;

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