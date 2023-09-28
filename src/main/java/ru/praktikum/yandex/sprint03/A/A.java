package ru.praktikum.yandex.sprint03.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {

    public static void generator(int n) {
        String s = "";
        generatorReq(n, s, 0, 0);
    }

    private static void generatorReq(int n, String s, int left, int right) {
        if (left + right == n * 2) {
            System.out.println(s);
        } else {
            if (left < n) {
                generatorReq(n, s + "(", left + 1, right);
            }
            if (right < left) {
                generatorReq(n, s + ")", left, right + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            generator(n);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
