package ru.praktikum.yandex.sprint08.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class D {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int linesNumber = readInt(reader);
            List<String> lines = readLines(reader, linesNumber);

            System.out.println(getMaxPrefixLength(lines));
        }
    }

    private static int getMaxPrefixLength(List<String> lines) {
        int prefixLength = 0;
        int minLength = Math.min(lines.get(0).length(), lines.get(lines.size() - 1).length());
        for (int i = 0; i < minLength; i++) {
            if (lines.get(0).charAt(i) == lines.get(lines.size() - 1).charAt(i)) {
                prefixLength++;
            } else {
                break;
            }
        }
        return prefixLength;
    }

    private static List<String> readLines(BufferedReader reader, int linesNumber) throws IOException {
        List<String> result = new ArrayList<>(linesNumber);

        for (int i = 0; i < linesNumber; i++) {
            result.add(reader.readLine());
        }

        result.sort(String::compareTo);

        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
