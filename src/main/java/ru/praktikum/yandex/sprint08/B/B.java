package ru.praktikum.yandex.sprint08.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();

            if (compare(a, b)) {
                System.out.println("OK");
            } else {
                System.out.println("FAIL");
            }
        }
    }

    public static boolean compare(String a, String b) {
        int aPointer = 0;
        int bPointer = 0;
        boolean hasMisMatch = true;

        while (aPointer < a.length() && bPointer < b.length()) {
            if (a.charAt(aPointer) != b.charAt(bPointer)) {
                if (!hasMisMatch) {
                    return false;
                }
                hasMisMatch = false;
                if (a.length() < b.length()) {
                    bPointer++;
                } else if (a.length() > b.length()) {
                    aPointer++;
                }
            }
            aPointer++;
            bPointer++;
        }

        return true;
    }
}
