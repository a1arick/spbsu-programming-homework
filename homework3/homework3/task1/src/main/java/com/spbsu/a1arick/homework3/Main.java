package com.spbsu.a1arick.homework3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("HASH TABLE");
        System.out.println("Supported commands:");
        System.out.println("add <word> (adds word to table)");
        System.out.println("delete <word> (delete word from table)");
        System.out.println("contains <word> (returns true if word contains)");
        System.out.println("stats (shows statistic information)");
        System.out.println("fill (fills table from file)");
        System.out.println("exit (exit program)");
        System.out.println("------------------------------------------------------");
        HashTable hashTable = new HashTable(322, chosenHashFunction(in));
        System.out.println("Hast table created");
        String act;
        do {
            act = in.next();
            switch (act) {
                case "add":
                    hashTable.add(in.next());
                    System.out.println("Added");
                    break;
                case "delete":
                    String word = in.next();
                    if (hashTable.contains(word)) {
                        try {
                            hashTable.remove(word);
                            System.out.println("Deleted");
                        } catch (NoSuchElementException e) {
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
                    Status(hashTable);
                    break;
                case "fill":
                    fillFromFile(hashTable);
                    System.out.println("Filled");
                    break;
            }
        } while (!act.equals("exit"));
    }

    public static HashFunction chosenHashFunction(Scanner in) {
        System.out.println("Choose hash function:");
        System.out.println("length");
        System.out.println("polynomial");
        System.out.println("xor");
        while (true) {
            switch (in.next()) {
                case "length":
                    return new LengthHash();
                case "polynomial":
                    return new PolynomialHash();
                case "sum":
                    return new CharSumHash();
            }
            System.out.println("Wrong choice. Try again");
        }
    }

    private static void Status(HashTable hashTable) {
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
}
