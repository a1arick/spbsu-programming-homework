package com.spbsu.a1arick.homework3.task1;

import com.spbsu.a1arick.homework3.task1.hash.HashFunction;
import com.spbsu.a1arick.homework3.task1.hash.impl.LengthHash;
import com.spbsu.a1arick.homework3.task1.hash.impl.PolynomialHash;
import com.spbsu.a1arick.homework3.task1.hash.impl.SumHash;
import com.spbsu.a1arick.homework3.task1.hashtable.HashTable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        help();
        HashTable<String, Integer> hashTable = new HashTable<>(37, chosenFunction(in));
        System.out.println("Hast table created");
        help();
        String choice;
        do {
            choice = in.next();
            switch (choice) {
                case "add":
                    hashTable.add(in.next(), in.nextInt());
                    System.out.println("Ok");
                    break;
                case "delete":
                    String word = in.next();
                    boolean deleted = hashTable.delete(word);
                    if (deleted) {
                        System.out.println("Deleted");
                    } else {
                        System.out.println("Word is not found");
                    }
                    break;
                case "get":
                    System.out.println(hashTable.get(in.next()));
                    break;
                case "stats":
                    printStats(hashTable);
                    break;
                case "fill":
                    fillFromFile(hashTable);
                    System.out.println("Filled");
                    break;
                case "help":
                    help();
                    break;
            }
        } while (!choice.equals("exit"));
    }

    private static void fillFromFile(HashTable<String, Integer> hashTable) {
        Scanner file;
        try {
            file = new Scanner(new FileInputStream("input.txt"));
        } catch (FileNotFoundException exception) {
            System.out.println("Sorry, file is not found");
            return;
        }
        while (file.hasNext()) {
            hashTable.add(file.next(), file.nextInt());
        }
    }

    private static void printStats(HashTable hashTable) {
        System.out.println("STATS");
        System.out.printf("Size: %d\n", hashTable.size());
        System.out.printf("Conflict number: %d\n", hashTable.getConflictNumber());
        System.out.printf("Load factor: %f\n", hashTable.loadFactor());
        System.out.printf("Max list size:: %d\n", hashTable.getMaxListSize());
    }

    public static void help() {
        System.out.println("HASH TABLE");
        System.out.println("Supported commands:");
        System.out.println("add <word> <integer> (add mapping from word to int)");
        System.out.println("delete <word> (delete word mapping)");
        System.out.println("get <word> (get word mapping)");
        System.out.println("stats (shows statistic information)");
        System.out.println("fill (fills table from file)");
        System.out.println("exit (exit program)");
        System.out.println("------------------------------------------------------");
    }

    public static HashFunction<String> chosenFunction(Scanner in) {
        System.out.println("Choose hash function:");
        System.out.println("length");
        System.out.println("polynomial");
        System.out.println("sum");
        System.out.println("usual");
        switch (in.next()) {
            case "length":
                return new LengthHash();
            case "polynomial":
                return new PolynomialHash();
            case "sum":
                return new SumHash();
            default:
                return Object::hashCode;
        }
    }


}