package org.example.projecttest;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

public class GameMap extends JFrame {

    private ImageIcon playerIcon;

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

    private List<Player> players;
    private JLabel[][] labels;
    private JButton diceButton; // Button for rolling the dice
    private JButton endTurnButton;
    private List<int[][]> playerMaps;
    private int currentPlayer = 0;
    private JLabel playerTurnLabel;


    public GameMap() {
        setTitle("Game Map");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        playerIcon = createPlayerIcon(Color.magenta, 20, 20);

        players = createPlayers();
        initializeMaps();

        JPanel panel = new JPanel(new GridLayout(MAP_SIZE, MAP_SIZE));
        labels = new JLabel[MAP_SIZE][MAP_SIZE];

        playerTurnLabel = new JLabel("Player One's Turn", SwingConstants.CENTER);
        playerTurnLabel = new JLabel("Player One's Turn", SwingConstants.CENTER);

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                JLabel label = new JLabel("", SwingConstants.CENTER);
                label.setOpaque(true);
                label.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                panel.add(label);
                labels[i][j] = label;
            }
        }

        updateMapDisplay();
        add(panel, BorderLayout.CENTER);
        setVisible(true);

        // Add the dice button
        diceButton = new JButton("Roll dice");
        diceButton.addActionListener(new ActionListener() {
            @Override                                    //override actionPerformed method in parent class to define our own behavior
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        endTurnButton = new JButton("End Your Turn");
        endTurnButton.addActionListener(new ActionListener() {
            @Override                                    //override actionPerformed method in parent class to define our own behavior
            public void actionPerformed(ActionEvent e) {
                playerSwitch();
            }
        });

        // Create a new JPanel to hold the playerTurnLabel and add it to the NORTH
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(playerTurnLabel, BorderLayout.CENTER); // This ensures the label is centered within the panel

        // Add components to the content pane
        Container contentPane = getContentPane(); // allows access to window that allows base for UI elements to be placed on
        contentPane.setLayout(new BorderLayout());
        contentPane.add(labelPanel, BorderLayout.NORTH);
        contentPane.add(panel, BorderLayout.CENTER); // panel where map is implemented

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(diceButton);
        buttonPanel.add(endTurnButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);


        pack(); //formats window
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private ImageIcon createPlayerIcon(Color color, int width, int height){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, width, height);
        g2d.dispose();
        return new ImageIcon(image);
    }

    private void rollDice() {
        Random random = new Random();
        int diceValue = random.nextInt(6) + 1; // Generate a random value from 1 to 6
        JOptionPane.showMessageDialog(this, "You rolled: " + diceValue); // "This" references the dialogue box currently in use
    }

    private void playerSwitch(){
        currentPlayer = (currentPlayer + 1)%players.size();
        updateMapDisplay();
        // Update the label to reflect the current player's turn
        if (currentPlayer == 0) {
            playerTurnLabel.setText("Player One's Turn");
        } else {
            playerTurnLabel.setText("Player Two's Turn");
        }

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
        while (lostItemCount < 13) {
            int x = random.nextInt(MAP_SIZE);
            int y = random.nextInt(MAP_SIZE);
            if (map[x][y] == 0 && !isAdjacentToCastle(map, x, y) && !isAdjacentToLostItem(map, x, y)) {
                map[x][y] = 6; // Lost Item
                lostItemCount++;
            }
        }

        // Place traps
        int trapCount = random.nextInt(2) + 3; // Randomly choose trap count between 2 and 7
        for (int i = 0; i < trapCount; i++) {
            int x = random.nextInt(MAP_SIZE);
            int y = random.nextInt(MAP_SIZE);
            if (map[x][y] == 0 && !isAdjacentToCastle(map, x, y)) {
                map[x][y] = 7; // Trap
            }
        }

        // Place walls
        int wallCount = random.nextInt(2) + 3; // Randomly choose wall count between 2 and 7
        for (int i = 0; i < wallCount; i++) {
            int x = random.nextInt(MAP_SIZE) ;
            int y = random.nextInt(MAP_SIZE)  ;
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
        map[MAP_SIZE - 2][0] = 9; // Starter house

        return map;
    }

    private void updateMapDisplay(){
        int[][] currentMap = playerMaps.get(currentPlayer); // Get the current player's map
        Player player = players.get(currentPlayer);
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                labels[i][j].setIcon(null);
                labels[i][j].setBackground(getColorForCell(currentMap[i][j]));
                if(i == player.getPawnX() && j == player.getPawnY()){
                    labels[i][j].setIcon(player.getIcon());
                }
            }
        }
        getContentPane().revalidate();
        getContentPane().repaint();
    }

    private void initializeMaps() {
        playerMaps = new ArrayList<>();
        int[][] baseMap = generateMap(); // Generate the base map once

        // Clone the base map for each player to ensure they start with identical maps
        for (int i = 0; i < players.size(); i++) {
            int[][] clonedMap = cloneMap(baseMap); // Create a clone of the base map for each player
            playerMaps.add(clonedMap);
        }
    }

    private int[][] cloneMap(int[][] baseMap) {
        int[][] clonedMap = new int[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < baseMap.length; i++) {
            System.arraycopy(baseMap[i], 0, clonedMap[i], 0, baseMap[i].length);
        }
        return clonedMap;
    }


    private List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        ImageIcon playerOneIcon = createPlayerIcon(Color.MAGENTA, 20, 20);
        ImageIcon playerTwoIcon = createPlayerIcon(Color.PINK, 20, 20);
        players.add(new Player("Player 1", MAP_SIZE - 2, 0,playerOneIcon));
        players.add(new Player("Player 2", MAP_SIZE - 2, 0,playerTwoIcon));
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
// Assuming currentPlayer is the current player index
//Player currentPlayer = players.get(currentPlayer);
//currentPlayer.getWallet().addMoney(100); // Add 100 money to the current player's wallet
// Assuming currentPlayer is the current player index
//Player currentPlayer = players.get(currentPlayer);
//currentPlayer.getWallet().deductMoney(50); // Deduct 50 money from the current player's wallet
