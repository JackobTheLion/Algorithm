package ru.praktikum.yandex.sprint01.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// id посылки: 90797675

public class A {

    public static String findDistance(List<Integer> street) {
        StringBuilder sb = new StringBuilder();
        int[] result = new int[street.size()];

        int distance = street.size();
        for (int i = 0; i < street.size(); i++) {
            if (street.get(i) == 0) {
                distance = 0;
            } else {
                distance++;
            }
            result[i] = distance;
        }

        distance = street.size();
        for (int i = street.size() - 1; i >= 0; i--) {
            if (street.get(i) == 0) {
                distance = 0;
            } else {
                distance++;
            }
            if (distance < result[i]) {
                result[i] = distance;
            }
        }

        for (Integer integer : result) {
            sb.append(integer).append(" ");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            //List<Integer> arr = List.of(0, 1, 4, 9, 0);
            //List<Integer> arr = List.of(0, 7, 9, 4, 8, 20);
            //List<Integer> arr = List.of(10, 7, 0, 4, 8, 0, 50);
            System.out.println(findDistance(arr));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}