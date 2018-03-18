package com.spbsu.a1arick.sample.io.phone.book;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FilePhoneBook filePhoneBook = new FilePhoneBook("book.txt");
        filePhoneBook.load();
        Scanner scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("exit")) {
            if (line.equals("get")) {
                String name = scanner.nextLine();
                System.out.println(filePhoneBook.getNumber(name));
            } else if(line.equals("add")){
                String name = scanner.nextLine();
                String number = scanner.nextLine();
                filePhoneBook.add(name, number);
                filePhoneBook.save();
                System.out.println("add: " + name + " " + number);
            }
        }
    }
}
