package org.example.projecttest;
import javax.swing.ImageIcon;
import java.util.ArrayList;

class Player {
    private String name;
    private int pawnX;
    private int pawnY;
    private ImageIcon icon;
    private ArrayList<Weapon> heldWeapons;
    private Wallet wallet;

    public Player(String name, int startX, int startY, ImageIcon icon) {
        this.name = name;
        this.pawnX = startX;
        this.pawnY = startY;
        this.icon = icon;
        this.wallet = new Wallet(); // Initialize the wallet
        this.heldWeapons = new ArrayList<>(); //automatically initializes as empty
    }

    public ImageIcon getIcon(){
        return icon;
    }

    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }

    public int getPawnX(){
        return pawnX;
    }

    public int getPawnY(){
        return pawnY;
    }
    public Wallet getWallet() {return wallet;}
    // Getters and setters for player's name and pawn position

    public ArrayList<Weapon> getHeldWeapons() {
        return heldWeapons;
    }
    public void setHeldWeapons(ArrayList<Weapon> heldWeapons) { //just in case we need to change entire ArrayList
        this.heldWeapons = heldWeapons;
    }


    //method to add a weapon to players inventory
    public void addWeapon(Weapon weapon){
        this.heldWeapons.add(weapon);
    }
}
