package org.example.projecttest;

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

    class Weapon extends Item {
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
            public Sword(String WeaponName, int price, int powerLevel) {
                super(WeaponName, price, powerLevel);
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


        class Weapons {
            //create 1 default weapon for each type
            Sword BasicSword = new Sword("Basic Sword", 10, 1);
            Axe BasicAxe = new Axe("Basic Axe", 10, 1);
            Spear BasicSpear = new Spear("Basic Spear", 10, 1);
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


