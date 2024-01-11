package ru.praktikum.yandex.sprint08.K;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class K {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();

            int result = customCompare(a, b);

            if (result < 0) {
                System.out.println(-1);
            } else if (result == 0) {
                System.out.println(0);
            } else {
                System.out.println(1);
            }

        }
    }

    private static int customCompare(String a, String b) {
        String newA = makeNewString(a);
        String newB = makeNewString(b);

        return newA.compareTo(newB);
    }

    private static String makeNewString(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) % 2 == 0) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
