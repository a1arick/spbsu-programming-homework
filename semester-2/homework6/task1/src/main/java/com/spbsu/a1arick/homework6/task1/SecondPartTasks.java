package com.spbsu.a1arick.homework6.task1;

import com.google.common.io.Files;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream()
                .map(path -> {
                    try {
                        return Files.readLines(new File(path), Charset.defaultCharset());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(List::stream)
                .filter(s -> s.contains(sequence))
                .collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        Random rand = new Random(System.currentTimeMillis());
        int testNumber = 1000000;
        return (double) Stream.generate(() -> new Pair<>(rand.nextDouble(), rand.nextDouble()))
                .limit(testNumber)
                .filter(p -> p.getKey() * p.getKey() + p.getValue() * p.getValue() <= 1)
                .count() / testNumber;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet().stream()
                .map(e -> new Pair<>(e.getKey(), e.getValue().stream().collect(Collectors.joining()).length()))
                .max(Comparator.comparing(Pair::getValue))
                .map(Pair::getKey)
                .orElseThrow(() -> new IllegalArgumentException("Empty map"));
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream()
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .mapToInt(Map.Entry::getValue)
                                        .sum())));
    }
}