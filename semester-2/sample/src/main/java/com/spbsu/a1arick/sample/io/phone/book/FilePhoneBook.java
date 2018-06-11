package com.spbsu.a1arick.sample.io.phone.book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FilePhoneBook extends MapPhoneBook implements Storage{

    private final String fileName;

    public FilePhoneBook(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save() {
        Map<String, String> book = getAll();
        try (PrintStream out = new PrintStream(fileName)) {
            for (Map.Entry<String, String> entry : book.entrySet()) {
                out.println(String.format("%s|%s", entry.getKey(), entry.getValue()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        try (Scanner in = new Scanner(new File(fileName))) {
            while(in.hasNextLine()){
                String[] split = in.nextLine().split("\\|");
                add(split[0], split[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
