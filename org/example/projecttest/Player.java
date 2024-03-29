package org.example.projecttest;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Objects;

class Player {
    private String name;
    public int pawnX;
    public int pawnY;
    private ImageIcon icon;
    private ArrayList<Weapon> heldWeapons;
    private Wallet wallet;
    private ArrayList<Coordinate> previousCoordinatePairs;


    public Player(String name, int startX, int startY, ImageIcon icon) {
        this.name = name;
        this.pawnX = startX;
        this.pawnY = startY;
        this.icon = icon;
        this.wallet = new Wallet(); // Initialize the wallet
        this.heldWeapons = new ArrayList<>();
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
    // Getters and setters for player's name and pawn position
}
