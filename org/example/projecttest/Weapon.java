package org.example.projecttest;

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
}
