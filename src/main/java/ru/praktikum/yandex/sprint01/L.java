package ru.praktikum.yandex.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class L {

    private static char getExcessiveLetter(String s, String t) {
        HashMap<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            result.put(s.charAt(i), result.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            result.put(t.charAt(i), result.getOrDefault(t.charAt(i), 0) + 1);
        }
        for (char c : result.keySet()) {
            if (result.get(c) % 2 != 0) {
                return c;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            String t = reader.readLine();
            System.out.println(getExcessiveLetter(s, t));
        }
    }
}
