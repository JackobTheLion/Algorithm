package ru.praktikum.yandex.sprint03.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class J {

    public static void sort(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        boolean swapped = true;

        for (int i = numbers.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    swapped = true;
                    int temp = numbers[j + 1];
                    numbers[j + 1] = numbers[j];
                    numbers[j] = temp;
                }
            }
            if (swapped) {
                for (int number : numbers) {
                    sb.append(number).append(" ");
                }
                sb.append("\n");
                swapped = false;
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            int[] numbers = readList(reader);
            sort(numbers);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readList(BufferedReader reader) throws IOException {
        String n = reader.readLine();
        String[] numbersString = n.split(" ");
        int[] numbers = new int[numbersString.length];

        for (int i = 0; i < numbersString.length; i++) {
            numbers[i] = Integer.parseInt(numbersString[i]);
        }
        return numbers;
    }
}
