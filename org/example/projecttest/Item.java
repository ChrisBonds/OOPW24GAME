package org.example.projecttest;
import java.util.ArrayList;
import java.util.Random;

public class Item {
    private String name;

    Item(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static int getNumItemType(ArrayList<Item> heldItems, Class<? extends Item> itemType){ // can pass in any subclass of item
        int instanceCount = 0;
        for(Item item : heldItems){
            if(itemType.isInstance(item)){
                instanceCount++;
            }
        }
        return instanceCount;
    }

    public static void removeRandomItem(ArrayList<Item> heldItems, Class<? extends Item> itemType){
        int numItems = getNumItemType(heldItems, itemType); //get number of specified items
        Random random = new Random();
        int randomIndex = random.nextInt(numItems);
        int count = 0;
        for (int i = 0; i < heldItems.size(); i++) {
            if(itemType.isInstance(heldItems.get(i))){
                count++;
                if(count == randomIndex){
                    heldItems.remove(i);
                    break;
                }
            }
        }
    }

    public static class Weapon extends Item {
        private int price;
        private int powerLevel;
        private boolean isMainHand;

        public Weapon(String name, int price, int powerLevel) {
            super(name);
            this.price = price;
            this.powerLevel = powerLevel;
            this.isMainHand = false;
        }
        public int getPowerLevel() {
            return powerLevel;
        }

        public void setPowerLevel(int powerLevel) {
            this.powerLevel = powerLevel;
        }

        public boolean checkMainHand() {
            return isMainHand;
        }

        public void setMainHand(boolean mainHand) {
            isMainHand = mainHand;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }


        //create 3 different subclasses of weapons. can add different features for different weapons later
        class Sword extends Weapon {
            public Sword(String weaponName, int price, int powerLevel) {
                super( weaponName,price, powerLevel);
            }
        }

         class Axe extends Weapon {
            public Axe(String WeaponName, int price, int powerLevel) {
                super(WeaponName, price, powerLevel);
            }
        }

         class Spear extends Weapon {
            public Spear(String WeaponName, int price, int powerLevel) {
                super(WeaponName, price, powerLevel);
            }
        }
        public class WeaponManager {
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
    }


    class Trinket extends Item {
        private int value;

        Trinket(String name, int value) {
            super(name);
            this.value = value;
        }
        class LostItem extends Trinket{

            LostItem(String name, int value) {
                super(name, value);
            }
        }

        class ValuableTreasure extends Trinket {

            ValuableTreasure(String name, int value){
                super(name, value);
            }
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        class TreasureMap extends Trinket{
            private int x;
            private int y;
            TreasureMap(String name, int value){
                super(name, value);
                x = getLostItemHouseX; //need this to return a house that isnt empty
                y = getLostItemHouseY;
            }

            public int getX() {
                return x;
            }
            public void setX(int x) {
                this.x = x;
            }
            public int getY() {
                return y;
            }
            public void setY(int y) {
                this.y = y;
            }
        }
    }


}


