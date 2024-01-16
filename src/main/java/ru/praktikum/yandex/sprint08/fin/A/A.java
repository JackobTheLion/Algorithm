package ru.praktikum.yandex.sprint08.fin.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int linesNumber = readInt(reader);
            String[] packedStrings = readLines(reader, linesNumber);

            System.out.println(getMaxPrefix(packedStrings));
        }
    }

    private static String getMaxPrefix(String[] packedStrings) {
        List<String> unpackedStrings = new ArrayList<>(packedStrings.length);
        for (String packedString : packedStrings) {
            unpackedStrings.add(unpackString(packedString));
        }

        unpackedStrings.sort(String::compareTo);

        StringBuilder maxPrefix = new StringBuilder();
        int minLength = Math.min(unpackedStrings.get(0).length(), unpackedStrings.get(unpackedStrings.size() - 1).length());
        for (int i = 0; i < minLength; i++) {
            if (unpackedStrings.get(0).charAt(i) == unpackedStrings.get(unpackedStrings.size() - 1).charAt(i)) {
                maxPrefix.append(unpackedStrings.get(0).charAt(i));
            } else {
                break;
            }
        }

        return maxPrefix.toString();
    }

    private static String unpackString(String packedString) {
        StringBuilder result = new StringBuilder();
        Stack<StringBuilder> components = new Stack<>();
        Stack<Integer> digits = new Stack<>();

        StringBuilder component = new StringBuilder();
        components.push(component);
        StringBuilder digit = new StringBuilder();
        for (int i = 0; i < packedString.length(); i++) {
            if (isAlphabetic(packedString.charAt(i))) {
                component.append(packedString.charAt(i));
                continue;
            }
            if (isDigit(packedString.charAt(i))) {
                components.push(component);
                component = new StringBuilder();
                digit.append(packedString.charAt(i));
                continue;
            }
            if (packedString.charAt(i) == '[') {
                int multiplier = 1;
                if (!digit.toString().isEmpty()) {
                    multiplier = Integer.parseInt(digit.toString());
                }
                digits.push(multiplier);
                digit = new StringBuilder();
                continue;
            }
            if (packedString.charAt(i) == ']') {
                StringBuilder sb = components.pop();
                sb.append(component.toString().repeat(digits.pop()));
                component = sb;
            }
        }

        while (!components.isEmpty()) {
            result.append(components.pop());
        }

        return result.toString();
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
