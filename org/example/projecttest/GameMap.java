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
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class GameMap extends JFrame {

    private ImageIcon playerIcon;

    private static final int MAP_SIZE = 12; // The size of the game map (number of cells per row/column)
    private static final int SIZE = 10; // The size of each individual cell in pixels
    private static final int CELL_SIZE = 50; // The size of the cells on the map
    private static final Color COLOR_BORDER = Color.LIGHT_GRAY; // The color for the map border
    private static final Color COLOR_CASTLE = Color.YELLOW; // The color for the castle on the map
    private static final Color COLOR_WALL = Color.BLACK; // The color for the walls on the map.
    private static final Color COLOR_MARKET = Color.ORANGE; // The color for the market on the map
    private static final Color COLOR_LOST_ITEM = Color.BLUE; // The color for the lost items on the map
    private static final Color COLOR_TRAP = Color.RED; // The color for the traps on the map.
    private static final Color COLOR_STARTER_HOUSE = Color.CYAN; // Color for the starter house
    private static final Color COLOR_TREASURE = Color.GREEN; // The color for the treasures on the map

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

    private List<Player> players; // List to store player objects
    private JLabel[][] labels; // Two-dimensional array of JLabels representing the map cells
    private JButton diceButton; // Button for rolling the dice
    private JButton endTurnButton; // Button for ending the current player's turn
    private List<int[][]> playerMaps; // List to store individual player maps
    private int currentPlayer = 0; // Index of the current player in the players list
    private JLabel playerTurnLabel; // JLabel for displaying the current player's turn
    private int diceRollResult = 0;
    private boolean firstMoveOutsideBorder = false;


    public GameMap() {
        setTitle("Game Map"); // Set the title of the game map window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation for the game map window
        setResizable(true); // Allow the game map window to be resizable

        // Create the player icon with a custom color and size
        playerIcon = createPlayerIcon(Color.magenta, 20, 20);

        // Initialize the list of players and their maps
        players = createPlayers();
        initializeMaps();

        // Create a JPanel with a grid layout to hold the game map cells
        JPanel panel = new JPanel(new GridLayout(MAP_SIZE, MAP_SIZE));
        labels = new JLabel[MAP_SIZE][MAP_SIZE];

        // Create a JLabel for displaying the current player's turn in the center of the window
        playerTurnLabel = new JLabel("Player One's Turn", SwingConstants.CENTER);
        playerTurnLabel = new JLabel("Player One's Turn", SwingConstants.CENTER);


        // Loop through each cell of the game map
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                // Create a new JLabel centered with empty text
                JLabel label = new JLabel("", SwingConstants.CENTER);
                // Set the label to be opaque (allows background color to be visible)
                label.setOpaque(true);
                // Set the preferred size of the label to CELL_SIZE x CELL_SIZE pixels
                label.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                // Set border for the label to create grid lines
                Border border = BorderFactory.createLineBorder(Color.BLACK);
                label.setBorder(border);
                // Add the label to the panel for display
                panel.add(label);
                // Store the label in the labels array for future reference
                labels[i][j] = label;
            }
        }


        // Update the display of the game map
        updateMapDisplay();
        // Add the panel containing the game map to the center of the window
        add(panel, BorderLayout.CENTER);
        // Set the window visible to the user
        setVisible(true);

        // Add the dice button
        diceButton = new JButton("Roll dice");
        // Add an ActionListener to the diceButton to handle button clicks
        diceButton.addActionListener(new ActionListener() {
            // Override the actionPerformed method in the ActionListener interface to define custom behavior
            @Override                                    //override actionPerformed method in parent class to define our own behavior
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        // Create a JButton for ending the current player's turn and set its label
        endTurnButton = new JButton("End Your Turn");
        // Add an ActionListener to the endTurnButton to handle button clicks
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
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Set the window visible to the user

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("KeyPressed Detected for Player: " + currentPlayer);

                handlePlayerMovement(e);
            }
        });
        this.setFocusable(true);

    }



    private void handlePlayerMovement(KeyEvent e) {
        if (diceRollResult > 0) {
            Player currentPlayer = players.get(this.currentPlayer);
            int x = currentPlayer.getPawnX();
            int y = currentPlayer.getPawnY();

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    x--;
                    System.out.println("Moving UP: X = " + x + ", Y = " + y);
                    break;
                case KeyEvent.VK_DOWN:
                    x++;
                    System.out.println("Moving DOWN: X = " + x + ", Y = " + y);
                    break;
                case KeyEvent.VK_LEFT:
                    y--;
                    System.out.println("Moving LEFT: X = " + x + ", Y = " + y);
                    break;
                case KeyEvent.VK_RIGHT:
                    y++;
                    System.out.println("Moving RIGHT: X = " + x + ", Y = " + y);
                    break;
            }

            if (isValidMove(x, y, firstMoveOutsideBorder)) {
                currentPlayer.setPawnX(x);
                currentPlayer.setPawnY(y);
                diceRollResult--; // Decrease the remaining dice roll count
                updateMapDisplay();
                if (!firstMoveOutsideBorder) {
                    firstMoveOutsideBorder = true;
                }
            }
        }
    }


    //Can also check if its a wall or trap here and what not
    private boolean isValidMove(int x, int y, boolean firstMoveOutsideBorder){
        if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE) {
            return false;
        }
        if (firstMoveOutsideBorder && (x == 0 || y == 0 || x == MAP_SIZE - 1 || y == MAP_SIZE - 1)) {
            return false;
        }
        return true;
    }
    private List<Player> createPlayers() {
        List<Player> players = new ArrayList<>(); // Initialize the list of players
        ImageIcon playerOneIcon = createPlayerIcon(Color.MAGENTA, 20, 20); // Create an icon for player one
        ImageIcon playerTwoIcon = createPlayerIcon(Color.PINK, 20, 20); // Create an icon for player two

        // Add player one and player two to the list of players with their icons and starting positions
        players.add(new Player("Player 1", MAP_SIZE - 2, 0,playerOneIcon));
        players.add(new Player("Player 2", MAP_SIZE - 2, 0,playerTwoIcon));
        return players;
    }
    private ImageIcon createPlayerIcon(Color color, int width, int height){
        // Create a BufferedImage with the specified width, height, and transparency type
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics(); // Get the Graphics2D object for drawing on the image
        g2d.setColor(color); // Set the color for drawing
        g2d.fillRect(0, 0, width, height); // Fill the entire image with the specified color
        g2d.dispose(); // Dispose of the Graphics2D object to release resources
        return new ImageIcon(image); // Return the ImageIcon representing the player icon
    }
    // Method to simulate rolling a die and display the result
    private void rollDice() {
        Random random = new Random(); // Create a new Random object
        diceRollResult = random.nextInt(6) + 1; // Generate a random value between 1 and 6 (inclusive)
        JOptionPane.showMessageDialog(this, "You rolled: " + diceRollResult); // Display a message dialog showing the rolled value
        this.requestFocusInWindow();
    }

    private void playerSwitch(){
        currentPlayer = (currentPlayer + 1)%players.size(); // Increment the current player index and wrap around if necessary
        diceRollResult = 0;
        updateMapDisplay(); // Update the display of the game map to reflect changes
        // Update the label to reflect the current player's turn
        if (currentPlayer == 0) {
            playerTurnLabel.setText("Player One's Turn");
        } else {
            playerTurnLabel.setText("Player Two's Turn");
        }
        this.requestFocusInWindow();
    }

    // Method to generate the initial game map
    private int[][] generateMap() {
        int[][] map = new int[MAP_SIZE][MAP_SIZE]; // Create a 2D array to represent the game map

        // Initialize borders of the map with wall cells
        for (int i = 0; i < MAP_SIZE; i++) {
            map[0][i] = map[MAP_SIZE - 1][i] = map[i][0] = map[i][MAP_SIZE - 1] = 1; // assigning to 1 = enum for light grey?
        }

        // Place castle in the center of the map
        map[SIZE / 2][SIZE / 2] = 2; //Assigning 2 to represent the castle cell


        // Place treasures randomly on the map
        Random random = new Random(); // Create a new Random object for generating random positions
        int treasureCount = 0;
        // Loop until 8 treasures are placed
        while (treasureCount < 8) {
            int x = random.nextInt(MAP_SIZE); // Generate a random x-coordinate within the map boundaries
            int y = random.nextInt(MAP_SIZE); // Generate a random y-coordinate within the map boundaries

            // Check if the randomly generated position is empty, not adjacent to the castle, and not adjacent to existing treasures
            if (map[x][y] == 0 && !isAdjacentToCastle(map, x, y) && !isAdjacentToTreasure(map, x, y)) {
                map[x][y] = 3; // Treasure cell represented by the 3
                treasureCount++;
            }
        }

        // Place markets
        int marketCount = 0;
        while (marketCount < 5) {
            int x = random.nextInt(MAP_SIZE); // Generate a random x-coordinate within the map boundaries
            int y = random.nextInt(MAP_SIZE); // Generate a random y-coordinate within the map boundaries
            if (map[x][y] == 0 && !isAdjacentToCastle(map, x, y) && !isAdjacentToMarket(map, x, y)) {
                map[x][y] = 5; // Market
                marketCount++;
            }
        }

        // Place lost items
        int lostItemCount = 0;
        while (lostItemCount < 13) {
            int x = random.nextInt(MAP_SIZE); // Generate a random x-coordinate within the map boundaries
            int y = random.nextInt(MAP_SIZE); // Generate a random y-coordinate within the map boundaries
            if (map[x][y] == 0 && !isAdjacentToCastle(map, x, y) && !isAdjacentToLostItem(map, x, y)) {
                map[x][y] = 6; // Lost Item
                lostItemCount++;
            }
        }

        // Place traps
        int trapCount = random.nextInt(2) + 3; // Randomly choose trap count between 2 and 7
        for (int i = 0; i < trapCount; i++) {
            int x = random.nextInt(MAP_SIZE); // Generate a random x-coordinate within the map boundaries
            int y = random.nextInt(MAP_SIZE); // Generate a random y-coordinate within the map boundaries
            if (map[x][y] == 0 && !isAdjacentToCastle(map, x, y)) {
                map[x][y] = 7; // Trap
            }
        }

        // Place walls
        int wallCount = random.nextInt(2) + 3; // Randomly choose wall count between 2 and 7
        for (int i = 0; i < wallCount; i++) {
            int x = random.nextInt(MAP_SIZE); // Generate a random x-coordinate within the map boundaries
            int y = random.nextInt(MAP_SIZE); // Generate a random y-coordinate within the map boundaries
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
        Player player = players.get(currentPlayer); //Get the current player
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                labels[i][j].setIcon(null); // Clear existing icons from labels
                labels[i][j].setBackground(getColorForCell(currentMap[i][j])); // Set background color based on cell value

                }
            }
        labels[player.getPawnX()][player.getPawnY()].setIcon(player.getIcon());
        getContentPane().revalidate();
        getContentPane().repaint();
        }
        // Revalidate and repaint the content pane to update the display



    private void initializeMaps() {
        playerMaps = new ArrayList<>(); //Initialize the list of player maps
        int[][] baseMap = generateMap(); // Generate the base map once

        // Clone the base map for each player to ensure they start with identical maps
        for (int i = 0; i < players.size(); i++) {
            int[][] clonedMap = cloneMap(baseMap); // Create a clone of the base map for each player
            playerMaps.add(clonedMap); // Add the cloned map to the list of player maps
        }
    }

    private int[][] cloneMap(int[][] baseMap) {
        int[][] clonedMap = new int[MAP_SIZE][MAP_SIZE]; // Create a new two-dimensional array for the cloned map
        for (int i = 0; i < baseMap.length; i++) {
            System.arraycopy(baseMap[i], 0, clonedMap[i], 0, baseMap[i].length);
        }
        return clonedMap;
    }



    // Check if the given position is adjacent to any market
    private boolean isAdjacentToMarket(int[][] map, int x, int y) {
        // Loop through neighboring cells of the given position and check if any of them is a market
        for (int i = Math.max(0, x - 1); i <= Math.min(MAP_SIZE - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(MAP_SIZE - 1, y + 1); j++) {
                if (map[i][j] == 5) {
                    return true; // Return true if adjacent to a market
                }
            }
        }
        return false; // Return false if not adjacent to any market
    }

    // Check if the given position is adjacent to any treasure
    private boolean isAdjacentToTreasure(int[][] map, int x, int y) {
        // Loop through neighboring cells of the given position and check if any of them is a treasure
        for (int i = Math.max(0, x - 1); i <= Math.min(MAP_SIZE - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(MAP_SIZE - 1, y + 1); j++) {
                if (map[i][j] == 3) {
                    return true; // Return true if adjacent to a treasure
                }
            }
        }
        return false; // Return false if not adjacent to any treasure
    }

    // Check if the given position is adjacent to any lost item
    private boolean isAdjacentToLostItem(int[][] map, int x, int y) {
        // Loop through neighboring cells of the given position and check if any of them is a lost item
        for (int i = Math.max(0, x - 1); i <= Math.min(MAP_SIZE - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(MAP_SIZE - 1, y + 1); j++) {
                if (map[i][j] == 6) {
                    return true;// Return true if adjacent to a lost item
                }
            }
        }
        return false; // Return false if not adjacent to any lost item
    }

    // Check if the given position is adjacent to any wall
    private boolean isAdjacentToWall(int[][] map, int x, int y) {
        // Loop through neighboring cells of the given position and check if any of them is a wall
        for (int i = Math.max(0, x - 1); i <= Math.min(MAP_SIZE - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(MAP_SIZE - 1, y + 1); j++) {
                if (map[i][j] == 4) {
                    return true; // Return true if adjacent to a wall
                }
            }
        }
        return false; // Return false if not adjacent to any wall
    }
    private boolean isAdjacentToCastle(int[][] map, int x, int y) {
        // Check if the absolute difference between the position and the center of the map is less than or equal to 1 in both x and y directions
        return (Math.abs(x - SIZE / 2) <= 1 && Math.abs(y - SIZE / 2) <= 1);
    }

    private Color getColorForCell(int value) {
        switch (value) {
            case 1: return COLOR_BORDER; //colour of boarder
            case 2: return COLOR_CASTLE; //colour of castle
            case 3: return COLOR_TREASURE; // colour for treasures
            case 4: return COLOR_WALL; // colour of wall
            case 5: return COLOR_MARKET; //colour of market
            case 6: return COLOR_LOST_ITEM; //colour of lost item
            case 7: return COLOR_TRAP; //colour of trap
            case 9: return COLOR_STARTER_HOUSE; // Color for the starter house
            default: return Color.WHITE; //default colour
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
