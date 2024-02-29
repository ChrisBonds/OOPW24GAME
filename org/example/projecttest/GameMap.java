package org.example.projecttest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMap extends JFrame {

    private static final int MAP_SIZE = 12; // Increase map size to accommodate the starter house
    private static final int SIZE = 10;
    private static final int CELL_SIZE = 50;
    private static final Color COLOR_BORDER = Color.LIGHT_GRAY;
    private static final Color COLOR_CASTLE = Color.YELLOW;
    private static final Color COLOR_WALL = Color.BLACK;
    private static final Color COLOR_MARKET = Color.ORANGE;
    private static final Color COLOR_LOST_ITEM = Color.BLUE;
    private static final Color COLOR_TRAP = Color.RED;
    private static final Color COLOR_STARTER_HOUSE = Color.CYAN; // Color for the starter house
    private static final Color COLOR_TREASURE = Color.GREEN;
    private static final String[] TREASURES = {
            "Diamond Ring",
            "Jewel-encrusted Sword",
            "Golden Goblet",
            "Crystal Goblets",
            "Wooden Bow",
            "Paladin’s Shield",
            "Golden Key",
            "Dragon’s Scroll"
    };


    private int[][] generalMap;
    private List<Player> players;
    private JLabel[][] labels;
    private JButton diceButton; // Button for rolling the dice

    public GameMap() {
        setTitle("Game Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        generalMap = generateMap();
        players = createPlayers();
        labels = new JLabel[MAP_SIZE][MAP_SIZE]; // Adjust label array size to match the map size

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(MAP_SIZE, MAP_SIZE)); // Use MAP_SIZE for layout

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(getColorForCell(generalMap[i][j]));
                label.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                panel.add(label);
                labels[i][j] = label;
            }
        }

        // Add the dice button
        diceButton = new JButton("Roll Dice");
        diceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        // Add components to the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(diceButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void rollDice() {
        Random random = new Random();
        int diceValue = random.nextInt(6) + 1; // Generate a random value from 1 to 6
        JOptionPane.showMessageDialog(this, "You rolled: " + diceValue);
    }

    private int[][] generateMap() {
        int[][] map = new int[MAP_SIZE][MAP_SIZE]; // Adjust map size

        // Initialize borders
        for (int i = 0; i < MAP_SIZE; i++) {
            map[0][i] = map[MAP_SIZE - 1][i] = map[i][0] = map[i][MAP_SIZE - 1] = 1;
        }

        // Place castle
        map[SIZE / 2][SIZE / 2] = 2;


        // Place treasures
        Random random = new Random();
        int treasureCount = 0;
        while (treasureCount < 8) {
            int x = random.nextInt(MAP_SIZE);
            int y = random.nextInt(MAP_SIZE);
            if (map[x][y] == 0 && !isAdjacentToCastle(map, x, y) && !isAdjacentToTreasure(map, x, y)) {
                map[x][y] = 3; // Treasure
                treasureCount++;
            }
        }

        // Place markets
        int marketCount = 0;
        while (marketCount < 5) {
            int x = random.nextInt(MAP_SIZE);
            int y = random.nextInt(MAP_SIZE);
            if (map[x][y] == 0 && !isAdjacentToCastle(map, x, y) && !isAdjacentToMarket(map, x, y)) {
                map[x][y] = 5; // Market
                marketCount++;
            }
        }

        // Place lost items
        int lostItemCount = 0;
        while (lostItemCount < 6) {
            int x = random.nextInt(MAP_SIZE);
            int y = random.nextInt(MAP_SIZE);
            if (map[x][y] == 0 && !isAdjacentToCastle(map, x, y) && !isAdjacentToLostItem(map, x, y)) {
                map[x][y] = 6; // Lost Item
                lostItemCount++;
            }
        }

        // Place traps
        int trapCount = random.nextInt(6) + 2; // Randomly choose trap count between 2 and 7
        for (int i = 0; i < trapCount; i++) {
            int x = random.nextInt(MAP_SIZE);
            int y = random.nextInt(MAP_SIZE);
            if (map[x][y] == 0 && !isAdjacentToCastle(map, x, y)) {
                map[x][y] = 7; // Trap
            }
        }

        // Place walls
        int wallCount = random.nextInt(6) + 2; // Randomly choose wall count between 2 and 7
        for (int i = 0; i < wallCount; i++) {
            int x = random.nextInt(MAP_SIZE);
            int y = random.nextInt(MAP_SIZE);
            if (map[x][y] == 0 && !isAdjacentToCastle(map, x, y) && !isAdjacentToWall(map, x, y)) {
                map[x][y] = 4; // Wall
            }
        }
        // Fill remaining cells with white spaces
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 8; // White space
                }
            }
        }

        // Place starter house outside the map
        map[MAP_SIZE - 1][0] = 9; // Starter house

        return map;
    }

    private List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        // Assuming we have two players
        for (int i = 0; i < 2; i++) {
            players.add(new Player("Player " + (i + 1), MAP_SIZE - 2, 1)); // Players start near the starter house
        }
        return players;
    }
    // Check if the given position is adjacent to any market
    private boolean isAdjacentToMarket(int[][] map, int x, int y) {
        for (int i = Math.max(0, x - 1); i <= Math.min(MAP_SIZE - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(MAP_SIZE - 1, y + 1); j++) {
                if (map[i][j] == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check if the given position is adjacent to any treasure
    private boolean isAdjacentToTreasure(int[][] map, int x, int y) {
        for (int i = Math.max(0, x - 1); i <= Math.min(MAP_SIZE - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(MAP_SIZE - 1, y + 1); j++) {
                if (map[i][j] == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check if the given position is adjacent to any lost item
    private boolean isAdjacentToLostItem(int[][] map, int x, int y) {
        for (int i = Math.max(0, x - 1); i <= Math.min(MAP_SIZE - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(MAP_SIZE - 1, y + 1); j++) {
                if (map[i][j] == 6) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check if the given position is adjacent to any wall
    private boolean isAdjacentToWall(int[][] map, int x, int y) {
        for (int i = Math.max(0, x - 1); i <= Math.min(MAP_SIZE - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(MAP_SIZE - 1, y + 1); j++) {
                if (map[i][j] == 4) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isAdjacentToCastle(int[][] map, int x, int y) {
        return (Math.abs(x - SIZE / 2) <= 1 && Math.abs(y - SIZE / 2) <= 1);
    }

    private Color getColorForCell(int value) {
        switch (value) {
            case 1: return COLOR_BORDER;
            case 2: return COLOR_CASTLE;
            case 3: return COLOR_TREASURE; // Color for treasures
            case 4: return COLOR_WALL;
            case 5: return COLOR_MARKET;
            case 6: return COLOR_LOST_ITEM;
            case 7: return COLOR_TRAP;
            case 9: return COLOR_STARTER_HOUSE; // Color for the starter house
            default: return Color.WHITE;
        }
    }


    public static void main(String[] args) {
        new GameMap();
    }
}

class Player {
    private String name;
    private int pawnX;
    private int pawnY;

    public Player(String name, int startX, int startY) {
        this.name = name;
        this.pawnX = startX;
        this.pawnY = startY;
    }

    // Getters and setters for player's name and pawn position
}
