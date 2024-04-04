package org.example.projecttest;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

class Player {
    private int moveCount = 0;
    private String name;
    private int power;
    public int pawnX;
    public int pawnY;
    private ImageIcon icon;
    private Wallet wallet;
    private ArrayList<Coordinate> previousCoordinatePairs;
    private List<Treasure> heldTreasures = new ArrayList<>();
    private int score;
    private int money;

    public void addHeldTreasure(Treasure treasure){
        heldTreasures.add(treasure);
        System.out.println("Held for verification: " + treasure.getName());
    }

    public void verifyTreasures(Castle castle) {
        // Check if player is at the same location as the castle
        if (this.pawnX == castle.getX() && this.pawnY == castle.getY()) {
            for (Treasure treasure : heldTreasures) {
                System.out.println("Verified: " + treasure.getName());
                this.score++; // Increase the score for each verified treasure
                this.wallet.addMoney(treasure.getValue()); // Add treasure's value to the player's wallet
            }
            heldTreasures.clear(); // Clear the treasures list after verification
        } else {
            System.out.println("You must reach the Castle to verify treasures.");
        }
    }


    public Player(String name, int startX, int startY, ImageIcon icon, int initialPower, int initialMoney) {
        this.name = name;
        this.pawnX = startX;
        this.pawnY = startY;
        this.icon = icon;
        this.power = initialPower;
        this.wallet = new Wallet(); // Initialize the wallet
        this.wallet.addMoney(initialMoney);
        this.money = 0;
        this.previousCoordinatePairs = new ArrayList<Coordinate>();
    }


    //coordinate class to store coordinates as single object
    static class Coordinate{
        private int x;
        private int y;

        Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) { //override equals method so the 'contains' method will work for different coordinate objects containing the same x and y values
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Coordinate coordinate = (Coordinate) obj;
            return x == coordinate.x && y == coordinate.y;
        }
        @Override
        public int hashCode() { //override method to return hashcode based on x and y values of coordinate object
            return Objects.hash(x, y);
        } //makes sure equal coordinates return the same hashcode


        public int getCoordinateX() {
            return x;
        }
        public void setCoordinateX(int x) {
            this.x = x;
        }
        public int getCoordinateY() {
            return y;
        }
        public void setCoordinateY(int y) {
            this.y = y;
        }
    }

    public int getMoveCount() {
        return moveCount;
    }
    public void incrementMoveCount(){
        moveCount++;
    }

    public void purchaseWeapon(Weapon_Items weapon) {
        // Check if the player has enough money in the wallet to purchase the weapon
        if (this.wallet.getMoney() >= weapon.getCost()) {
            // Deduct the cost of the weapon from the wallet
            this.wallet.deductMoney(weapon.getCost());

            // Increase the player's power by the weapon's power bonus
            this.power += weapon.getPowerBonus();

            // Optionally, add the weapon to the player's inventory here
            // (This step depends on how you're managing inventory)

            // Provide feedback for successful purchase
            System.out.println(name + " has purchased a " + weapon.getType() + " for $" + weapon.getCost() + " and gained " + weapon.getPowerBonus() + " power.");

        } else {
            // Provide feedback if the player doesn't have enough money
            System.out.println(name + " does not have enough money to purchase " + weapon.getType() + ".");
        }
    }

    public ImageIcon getIcon(){
        return icon;
    }

    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }

    public int getPawnX() {
        return pawnX;
    }

    public void setPawnX(int pawnX) {
        this.pawnX = pawnX;
    }

    public int getPawnY() {
        return pawnY;
    }

    public void setPawnY(int pawnY) {
        this.pawnY = pawnY;
    }

    public ArrayList<Coordinate> getPreviousCoordinatePairs() {
        return previousCoordinatePairs;
    }

    public void addCoordinatePair(Coordinate coordinate) {
        this.getPreviousCoordinatePairs().add(coordinate);
    }

    public Wallet getWallet() {return wallet;}

    public int getScore() {
        // Assuming score calculation is done elsewhere or simply returning a placeholder value
        return score; // Replace this with actual score calculation logic
    }

    public int getMoney() {
        return wallet.getMoney(); // Directly access money from the wallet
    }

    public int getPower() {
        return power;
    }

    public String getName() {
        return name;
    }
    public void move(int x, int y) {
        setPawnX(x);
        setPawnY(y);
        addCoordinatePair(new Player.Coordinate(x, y));
        incrementMoveCount(); // Increment the move count each time the player moves
    }

}
