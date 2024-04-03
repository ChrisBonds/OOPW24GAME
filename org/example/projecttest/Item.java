package org.example.projecttest;
import java.util.ArrayList;
import java.util.Random;

public class Item {
    private String name;

    Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getNumItemType(ArrayList<Item> heldItems, Class<? extends Item> itemType) { // can pass in any subclass of item
        int instanceCount = 0;
        for (Item item : heldItems) {
            if (itemType.isInstance(item)) {
                instanceCount++;
            }
        }
        return instanceCount;
    }

    public static void removeRandomItem(ArrayList<Item> heldItems, Class<? extends Item> itemType) {
        int numItems = getNumItemType(heldItems, itemType); //get number of specified items
        Random random = new Random();
        int randomIndex = random.nextInt(numItems);
        int count = 0;
        for (int i = 0; i < heldItems.size(); i++) {
            if (itemType.isInstance(heldItems.get(i))) {
                count++;
                if (count == randomIndex) {
                    heldItems.remove(i);
                    break;
                }
            }
        }
    }
}

        //create 3 different subclasses of weapons. can add different features for different weapons later
        class WeaponManager {
            ArrayList<Weapon> allWeapons = new ArrayList<Weapon>();
            public WeaponManager() {
                Sword basicSword = new Sword("Basic Sword", 10, 10);
                Axe basicAxe = new Axe("Basic axe", 10, 10);
                Spear basicSpear = new Spear("Basic spear", 10, 10);

                allWeapons.add(basicSword);
                allWeapons.add(basicAxe);
                allWeapons.add(basicSpear);
            }
            public ArrayList<Weapon> getAllWeapons(){
                return allWeapons;
            }
            public Weapon makeRandomWeapon(){
                Random random = new Random();
                int randomIdx = random.nextInt();
                WeaponManager weaponManager = new WeaponManager();
                return weaponManager.allWeapons.get(randomIdx);
            }
        }





