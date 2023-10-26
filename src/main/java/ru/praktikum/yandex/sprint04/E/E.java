package ru.praktikum.yandex.sprint04.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class E {

    public static int getMaxSubStrLength(String s) {
        Map<Character, Integer> characters = new HashMap<>();
        int maxLength = 0;
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (characters.containsKey(s.charAt(i))) {
                i = characters.get(s.charAt(i));
                characters = new HashMap<>();
                counter = 0;
                continue;
            }
            counter++;
            if (counter > maxLength) {
                maxLength = counter;
            }
            characters.put(s.charAt(i), i);
        }
        return maxLength;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();

            System.out.println(getMaxSubStrLength(s));
        }
    }
}
