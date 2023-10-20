package ru.praktikum.yandex.sprint04.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class H {

    public static boolean compare(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (sToT.containsKey(s.charAt(i))) {
                if (!sToT.get(s.charAt(i)).equals(t.charAt(i))) {
                    return false;
                }
            } else sToT.put(s.charAt(i), t.charAt(i));
            if (tToS.containsKey(t.charAt(i))) {
                if (!tToS.get(t.charAt(i)).equals((s.charAt(i)))) {
                    return false;
                }
            } else tToS.put((t.charAt(i)), s.charAt(i));
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            String t = reader.readLine();

            boolean result = compare(s, t);

            if (result) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
