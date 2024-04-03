package org.example.projecttest;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class House {
    String name;
    boolean entered;

    House(String name) {
        this.name = name;
        this.entered = false;
    }

    abstract void enters(Player player);
}



