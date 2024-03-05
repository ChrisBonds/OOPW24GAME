package org.example.projecttest; // This package declaration indicates that this class belongs to the org.example.projecttest package.

import javax.swing.*; // Importing the necessary Swing library for GUI components.
import java.awt.*; // Importing the necessary AWT library for basic GUI functionalities.
import java.awt.event.ActionEvent; // Importing ActionEvent for handling GUI events.
import java.awt.event.ActionListener; // Importing ActionListener for handling GUI action events.
import java.util.ArrayList; // Importing ArrayList from the Java Collections Framework to store dynamic arrays.
import java.util.List; // Importing List from the Java Collections Framework for generic list operations.
import java.util.Random; // Importing Random for generating random numbers.

/**
 * The GameMap class represents the main frame for the game map GUI.
 * It extends JFrame to create a window for the game map.
 */
public class GameMap extends JFrame {

    // Constants defining various properties of the game map
    private static final int MAP_SIZE = 12; // The size of the game map (number of cells per row/column).
    private static final int SIZE = 10; // The size of each individual cell in pixels.
    private static final int CELL_SIZE = 50; // The size of the cells on the map.
    private static final Color COLOR_BORDER = Color.LIGHT_GRAY; // The color for the map border.
    private static final Color COLOR_CASTLE = Color.YELLOW; // The color for the castle on the map.
    private static final Color COLOR_WALL = Color.BLACK; // The color for the walls on the map.
    private static final Color COLOR_MARKET = Color.ORANGE; // The color for the market on the map.
    private static final Color COLOR_LOST_ITEM = Color.BLUE; // The color for the lost items on the map.
    private static final Color COLOR_TRAP = Color.RED; // The color for the traps on the map.
    private static final Color COLOR_STARTER_HOUSE = Color.CYAN; // The color for the starter house on the map.
    private static final Color COLOR_TREASURE = Color.GREEN; // The color for the treasures on the map.

    // Array representing different types of treasures along with their names and values
    private static final String[][] TREASURES = {
            {"Diamond Ring", "1500"},
            {"Jewel-encrusted Sword", "2000"},
            {"Golden Goblet", "1000"},
            {"Crystal Goblets", "2500"},
            {"Wooden Bow", "800"},
            {"Paladin’s Shield", "3000"},
            {"Golden Key", "700"},
            {"Dragon’s Scroll", "1200"}
    };



    private int[][] generalMap;
    private List<Player> players;
    private JLabel[][] labels;
    private JButton diceButton; // Button for rolling the dice
    private List<int[][]> playerMaps;

    public GameMap() {
        setTitle("Game Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        //generalMap = generateMap();
        players = createPlayers();
        initializeMaps();
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
        diceButton = new JButton("Roll dice");
        diceButton.addActionListener(new ActionListener() {
            @Override                                    //override actionPerformed method in parent class to define our own behavior
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        // Add components to the content pane
        Container contentPane = getContentPane(); // allows access to window that allows base for UI elements to be placed on
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.CENTER); // panel where map is implemented
        contentPane.add(diceButton, BorderLayout.SOUTH); //adds dice button

        pack(); //formats window
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void rollDice() {
        Random random = new Random();
        int diceValue = random.nextInt(6) + 1; // Generate a random value from 1 to 6
        JOptionPane.showMessageDialog(this, "You rolled: " + diceValue); // "This" references the dialogue box currently in use
    }

    private int[][] generateMap() {
        int[][] map = new int[MAP_SIZE][MAP_SIZE]; // Adjust map size

        // Initialize borders
        for (int i = 0; i < MAP_SIZE; i++) {
            map[0][i] = map[MAP_SIZE - 1][i] = map[i][0] = map[i][MAP_SIZE - 1] = 1; // assigning to 1 = enum for light grey?
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

    private void initializeMaps() {
        playerMaps = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            playerMaps.add(generateMap()); // Generate a separate map for each player
        }
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
    private boolean isAdjacentToCastle(int[][] map, int x, int y) { //saying method is inverted, need to check implementations to confirm
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
        new GameMap(); //lol
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
