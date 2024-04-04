package org.example.projecttest;

public abstract class Building {
    protected String name;
    public Building(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public abstract void interact(Player player, int x, int y);
}

class Treasure extends Building {
    private int value;
    private boolean isDiscovered;
public Treasure(String name, int value) {
    super(name);
    this.value = value;
    this.isDiscovered = false;
}

@Override
public void interact(Player player, int x, int y) {
    if (!isDiscovered) {
        System.out.println(player.getName() + " discovered " + name + "! Move to the castle to verify.");
        //player.discoverTreasure(this, x, y);
        player.addHeldTreasure(this);
        isDiscovered = true;
    } else {
        System.out.println("This treasure has already been discovered. Move to the castle to verify.");
    }
}

public int getValue(){
    return value;
}

public boolean getIsDiscovered(){
    return isDiscovered;
}

// Getters and setters for value, isDiscovered, etc., as needed.
}

class Castle extends Building {
    private int x;
    private int y;
    public Castle(String name, int x, int y) {
        super(name);
        this.x = x;
        this.y = y;
    }

    @Override
    public void interact(Player player, int x, int y) {
        player.verifyTreasures(this);
        System.out.println("Treasures verified for " + player.getName());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}