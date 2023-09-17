package ru.praktikum.yandex.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class D {

    private static int getWeatherRandomness(List<Integer> temperatures) {
        if (temperatures.size() == 1) {
            return 1;
        }

        int result = 0;

        if (temperatures.get(0) > temperatures.get(1)) {
            result++;
        }
        if (temperatures.get(temperatures.size() - 2) < temperatures.get(temperatures.size() - 1)) {
            result++;
        }

        boolean previousTempLower = temperatures.get(0) < temperatures.get(1);

        for (int i = 1; i < temperatures.size() - 1; i++) {
            if (temperatures.get(i) > temperatures.get(i + 1)) {
                if (previousTempLower) {
                    result++;
                }
                previousTempLower = false;
            } else if (temperatures.get(i) < temperatures.get(i + 1)) {
                previousTempLower = true;
            } else if (temperatures.get(i).equals(temperatures.get(i + 1))) {
                previousTempLower = false;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int numberOfDays = readInt(reader);
            List<Integer> temperatures = readList(reader);
            int chaosNumber = getWeatherRandomness(temperatures);
            System.out.println(chaosNumber);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }
}