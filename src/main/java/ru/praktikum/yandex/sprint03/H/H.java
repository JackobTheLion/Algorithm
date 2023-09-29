package ru.praktikum.yandex.sprint03.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class H {

    public static String getMaxNumber(List<Integer> integers, Comparator<Integer> comparator) {
        for (int i = 1; i < integers.size(); i++) {
            Integer intToMove = integers.get(i);
            int j = i;
            while (j > 0 && comparator.compare(intToMove, integers.get(j - 1)) > 0) {
                integers.set(j, integers.get(j - 1));
                j--;
            }
            integers.set(j, intToMove);
        }

        StringBuilder sb = new StringBuilder();
        for (Integer integer : integers) {
            sb.append(integer);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> integers = readArr(reader);
            System.out.println(getMaxNumber(integers, (a, b) -> {
                if (a.equals(b)) return 0;
                String ab = a.toString() + b;
                String ba = b.toString() + a;
                return Integer.parseInt(ab) - Integer.parseInt(ba);
            }));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().strip().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
