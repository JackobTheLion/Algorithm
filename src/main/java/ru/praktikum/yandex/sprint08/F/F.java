package ru.praktikum.yandex.sprint08.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class F {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int wordsNumber = readInt(reader);
            String[] words = readLines(reader, wordsNumber);

            System.out.println(getWord(words));
        }
    }

    private static String getWord(String[] words) {
        Map<String, Integer> wordsCount = new HashMap<>();
        String maxWord = words[0];

        for (String word : words) {
            wordsCount.put(word, wordsCount.getOrDefault(word, 0) + 1);
            if (wordsCount.get(word) >= wordsCount.get(maxWord)) {
                if (word.length() < maxWord.length()) {
                    maxWord = word;
                }
            }
        }

        return maxWord;
    }

    private static String[] readLines(BufferedReader reader, int linesNumber) throws IOException {
        String[] result = new String[linesNumber];

        for (int i = 0; i < linesNumber; i++) {
            result[i] = reader.readLine();
        }

        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
