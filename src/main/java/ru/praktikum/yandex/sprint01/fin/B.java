package ru.praktikum.yandex.sprint01.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// id посылки: 90867486

//Привет, Иван!
//Меня зовут Егор и я буду твоим студентом на этом курсе :)
//Большое спасибо за твое ревью! С нетерпением жду продолжения.


public class B {

    private static int countScore(List<String> matrix, int fingers) {
        int[] numbersCount = new int[9];

        for (String row : matrix) {
            char[] split = row.toCharArray();
            for (char c : split) {
                if (c != '.') {
                    int digit = Character.digit(c, 10);
                    numbersCount[digit - 1] += 1;
                }
            }
        }

        int result = 0;
        int totalFingers = fingers * 2;

        for (int j : numbersCount) {
            if (j <= totalFingers && j != 0) {
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
