package ru.praktikum.yandex.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class F {

    private static boolean isPalindrome(String s) {
        String lower = s.toLowerCase();
        int leftIndex = 0;
        int rightIndex = s.length() - 1;
        while (leftIndex < rightIndex) {
            while (!Character.isLetterOrDigit(lower.charAt(leftIndex))) {
                leftIndex++;
            }
            while (!Character.isLetterOrDigit(lower.charAt(rightIndex))) {
                rightIndex--;
            }
            if (lower.charAt(leftIndex) != lower.charAt(rightIndex)) {
                return false;
            }
            leftIndex++;
            rightIndex--;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine();
            if (isPalindrome(text)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }
}
