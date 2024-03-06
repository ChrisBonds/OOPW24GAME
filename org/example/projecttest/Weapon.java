package org.example.projecttest;

public class Weapon{
    private String name;
    private int price;
    private int powerLevel;
    private boolean isMainHand;

    public Weapon(String name, int price, int powerLevel) {
        this.name = name;
        this.price = price;
        this.powerLevel = powerLevel;
        this.isMainHand = false;
    }
    /*//method to use special ability during battle defined when creating each ability
    //call it within weapon class to reduce confusion --> ability class is a part of the weapon but not an extension of weapon class
    public void useAbility(){
        this.ability.useAbility();
    }*/

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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