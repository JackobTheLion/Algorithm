package ru.praktikum.yandex.sprint03.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {

    public static boolean isSubstring(String s, String t) {
        if (t.length() < s.length()) return false;

        int sIndex = 0;
        int tIndex = 0;
        while (sIndex < s.length() && tIndex < t.length()) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
            }
            tIndex++;
        }
        return sIndex == s.length();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            String t = reader.readLine();

            if (isSubstring(s, t)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }

        }
    }
}
