package ru.praktikum.yandex.sprint04.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {

    public static long hash(String s, int a, int m) {
        if (s.isBlank()) return 0;

        long hash = (s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            hash = hash * a % m + s.charAt(i);
        }

        return hash % m;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = readInt(reader);
            int m = readInt(reader);
            String s = reader.readLine();

            System.out.println(hash(s, a, m));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
