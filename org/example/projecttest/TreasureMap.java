package org.example.projecttest;

public class TreasureMap {
    class TreasureMap extends Trinket{
        private int x;
        private int y;
        TreasureMap(String name, int value){
            super(name, value);
            x = getLostItemHouseX; //need this to return a house that isnt empty
            y = getLostItemHouseY;
        }

        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
    }
}
