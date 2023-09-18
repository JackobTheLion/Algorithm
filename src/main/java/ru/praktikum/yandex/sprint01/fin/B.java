package ru.praktikum.yandex.sprint01.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// id посылки: 90802105

public class B {

    private static int countScore(List<String> matrix, int fingers) {
        HashMap<Integer, Integer> numbersCount = new HashMap<>();

        for (String row : matrix) {
            String[] split = row.split("");
            for (String s : split) {
                if (!s.equals(".")) {
                    Integer parsedInt = Integer.parseInt(s);
                    numbersCount.put(parsedInt, numbersCount.getOrDefault(parsedInt, 0) + 1);
                }
            }
        }

        int result = 0;

        for (Integer integer : numbersCount.keySet()) {
            if (numbersCount.get(integer) <= (fingers * 2)) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int fingersCount = readInt(reader);
            List<String> matrix = readMatrix(reader);
            System.out.println(countScore(matrix, fingersCount));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readMatrix(BufferedReader reader) throws IOException {
        List<String> matrix = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            matrix.add(reader.readLine());
        }
        return matrix;
    }
}
