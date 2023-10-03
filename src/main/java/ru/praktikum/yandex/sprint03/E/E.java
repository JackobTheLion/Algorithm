package ru.praktikum.yandex.sprint03.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class E {

    public static Integer calcOptions(Integer budget, List<Integer> prices) {
        Integer numberOfOptions = 0;

        prices.sort(Integer::compareTo);

        for (Integer price : prices) {
            if (budget - price >= 0) {
                budget -= price;
                numberOfOptions++;
            } else break;
        }

        return numberOfOptions;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> budget = readList(reader);
            List<Integer> prices = readList(reader);

            System.out.println(calcOptions(budget.get(1), prices));
        }
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
