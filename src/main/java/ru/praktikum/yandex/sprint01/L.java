package ru.praktikum.yandex.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.abs;

public class L {

    private static char getExcessiveLetter(String s, String t) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            sum -= t.charAt(i);
        }
        return (char) abs(sum);
    }

    private static char getExcessiveLetter2(String s, String t) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result = result ^ s.charAt(i);
        }
        for (int i = 0; i < t.length(); i++) {
            result = result ^ t.charAt(i);
        }
        return (char) result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            String t = reader.readLine();
            System.out.println(getExcessiveLetter2(s, t));
        }
    }
}
