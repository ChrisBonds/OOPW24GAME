package org.example.projecttest;
import javax.swing.ImageIcon;

class Player {
    private String name;
    private int pawnX;
    private int pawnY;
    private ImageIcon icon;
    //use arraylist of weapons created for each player
    private Wallet wallet;

    public Player(String name, int startX, int startY, ImageIcon icon) {
        this.name = name;
        this.pawnX = startX;
        this.pawnY = startY;
        this.icon = icon;
        this.wallet = new Wallet(); // Initialize the wallet
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
}
