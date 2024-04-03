package org.example.projecttest;

class Trinket extends Item {
    private int value;

    Trinket(String name, int value) {
        super(name);
        this.value = value;
    }

    class LostItem extends Trinket {

        LostItem(String name, int value) {
            super(name, value);
        }
    }

    class ValuableTreasure extends Trinket {

        ValuableTreasure(String name, int value) {
            super(name, value);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
