package org.example.projecttest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MarketWindow extends JFrame {
    private Player player; // Ensure this is defined properly

    public MarketWindow(Player player) {
        this.player = player;
        setTitle("Market");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // One column, any number of rows

        // Correctly using the enum values to create Weapon_Items
        Weapon_Items sword = new Weapon_Items(WeaponType.SWORD, 100, 5);
        Weapon_Items dagger = new Weapon_Items(WeaponType.DAGGER, 80, 4);
        Weapon_Items mace = new Weapon_Items(WeaponType.MACE, 120, 6);

        // Create and add buttons for each weapon
        addButtonForWeapon(panel, sword);
        addButtonForWeapon(panel, dagger);
        addButtonForWeapon(panel, mace);

        add(panel);
        setVisible(true);
    }

    private void addButtonForWeapon(JPanel panel, Weapon_Items weapon) {
        JButton button = new JButton(weapon.getType() + " - $" + weapon.getCost());
        button.addActionListener((ActionEvent e) -> {
            purchaseWeapon(weapon);
        });
        panel.add(button);
    }

    private void purchaseWeapon(Weapon_Items weapon) {
        if (player.getWallet().getMoney() >= weapon.getCost()) {
            player.purchaseWeapon(weapon);
            JOptionPane.showMessageDialog(this, "Purchased: " + weapon.getType());
            // Close the market window or update accordingly
        } else {
            JOptionPane.showMessageDialog(this, "Not enough money to purchase: " + weapon.getType(), "Purchase Failed", JOptionPane.ERROR_MESSAGE);
            // Provide feedback for failed purchase
        }
    }

    public static void display(Player player) {
        SwingUtilities.invokeLater(() -> new MarketWindow(player));
    }
}
