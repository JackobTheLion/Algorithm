package ru.praktikum.yandex.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class K {

    public static Integer getFibonacci(int index) {
        if (index <= 1) {
            return 1;
        } else {
            return getFibonacci(index - 1) + getFibonacci(index - 2);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int index = readInt(reader);
            System.out.println(getFibonacci(index));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
