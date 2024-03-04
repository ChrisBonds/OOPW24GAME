package org.example.projecttest;
import javax.swing.ImageIcon;

class Player {
    private String name;
    private int pawnX;
    private int pawnY;
    private ImageIcon icon;

    public Player(String name, int startX, int startY, ImageIcon icon) {
        this.name = name;
        this.pawnX = startX;
        this.pawnY = startY;
        this.icon = icon;
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

    // Getters and setters for player's name and pawn position
}
