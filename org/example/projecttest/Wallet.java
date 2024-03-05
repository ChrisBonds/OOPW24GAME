package org.example.projecttest;

public class Wallet {
    private int money;

    public Wallet() {
        this.money = 0;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void deductMoney(int amount) {
        money -= amount;
    }
}
