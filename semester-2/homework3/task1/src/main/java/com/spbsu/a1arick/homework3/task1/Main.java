package com.spbsu.a1arick.homework3.task1;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printMenu();
        HashTable hashTable = new HashTable(37, chosenFunction(in));
        System.out.println("Hast table created");
        info();
        String choice;
        do {
            choice = in.next();
            switch (choice) {
                case "add":
                    hashTable.add(in.next());
                    System.out.println("Ok");
                    break;
                case "delete":
                    String word = in.next();
                    if (hashTable.contains(word)) {
                        try {
                            hashTable.delete(word);
                            System.out.println("Deleted");
                        } catch (TextNotFoundException e) {
                            System.out.println("Element is not found");
                        }
                    } else {
                        System.out.println("Word is not found");
                    }
                    break;
                case "contains":
                    System.out.println(hashTable.contains(in.next()));
                    break;
                case "stats":
                    printStats(hashTable);
                    break;
                case "fill":
                    fillFromFile(hashTable);
                    System.out.println("Filled");
                    break;
                case "info":
                    info();
                    break;
            }
        } while (!choice.equals("exit"));
    }

    private static void info(){
        System.out.println("add");
        System.out.println("delete");
        System.out.println("contains");
        System.out.println("stats");
        System.out.println("fill");
        System.out.println("info");
    }

    private static void fillFromFile(HashTable hashTable) {
        Scanner file;
        try {
            file = new Scanner(new FileInputStream("input.txt"));
        } catch (FileNotFoundException exception) {
            System.out.println("Sorry, file is not found");
            return;
        }
        while (file.hasNext()) {
            hashTable.add(file.next());
        }
    }

    private static void printStats(HashTable hashTable) {
        System.out.println("STATS");
        System.out.print("Element number: ");
        System.out.println(hashTable.getElementNumber());
        System.out.print("Conflict number: ");
        System.out.println(hashTable.getConflictNumber());
        System.out.print("Load factor: ");
        System.out.println(hashTable.loadFactor());
        System.out.print("Max list size: ");
        System.out.println(hashTable.getMaxListSize());
    }

    public static void printMenu() {
        System.out.println("HASH TABLE");
        System.out.println("Supported commands:");
        System.out.println("add <word> (adds word to table)");
        System.out.println("delete <word> (delete word from table)");
        System.out.println("contains <word> (returns true if word contains)");
        System.out.println("stats (shows statistic information)");
        System.out.println("fill (fills table from file)");
        System.out.println("exit (exit program)");
        System.out.println("------------------------------------------------------");
    }

    public static HashFunction chosenFunction(Scanner in) {
        System.out.println("Choose hash function:");
        System.out.println("length");
        System.out.println("polynomial");
        while (true) {
            switch (in.next()) {
                case "length":
                    return new LengthHash();
                case "polynomial":
                    return new PolynomialHash();
            }
            System.out.println("Wrong choice. Try again");
        }
    }


}