package ru.praktikum.yandex.sprint01;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class F {

    private static boolean isPalindrome(String text) {
        String toCompare = text.replaceAll("[\\p{Punct}]|[\\s]", "");

        StringBuilder sb = new StringBuilder(toCompare);
        String reverse = sb.reverse().toString();

        return toCompare.equalsIgnoreCase(reverse);
    }

    public static void main(String[] args) throws IOException{
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
