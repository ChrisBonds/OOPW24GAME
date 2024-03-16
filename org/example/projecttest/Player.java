package org.example.projecttest;
import javax.swing.ImageIcon;
import java.util.ArrayList;

class Player {
    private String name;
    public int pawnX;
    public int pawnY;
    private ImageIcon icon;
    private ArrayList<Weapon> heldWeapons;
    private Wallet wallet;

    public Player(String name, int startX, int startY, ImageIcon icon) {
        this.name = name;
        this.pawnX = startX;
        this.pawnY = startY;
        this.icon = icon;
        this.wallet = new Wallet(); // Initialize the wallet
        this.heldWeapons = new ArrayList<>();
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
    public Wallet getWallet() {return wallet;}
    // Getters and setters for player's name and pawn position
}
