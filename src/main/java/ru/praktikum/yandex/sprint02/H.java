package ru.praktikum.yandex.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class H {

    public static boolean is_correct_bracket_seq(String braces) {
        if (braces.isBlank()) {
            return true;
        }

        char[] bracesArr = braces.toCharArray();
        Deque<Character> bracesStack = new LinkedList<>();

        for (char c : bracesArr) {
            if (c == '{' || c == '(' || c == '[') {
                bracesStack.addFirst(c);
            } else {
                if (!bracesStack.isEmpty() && ((bracesStack.peekFirst() == '{' && c == '}')
                        || (bracesStack.peekFirst() == '[' && c == ']')
                        || (bracesStack.peekFirst() == '(' && c == ')'))) {
                    bracesStack.removeFirst();
                } else {
                    return false;
                }
            }
        }
        return bracesStack.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String braces = reader.readLine();
            boolean result = is_correct_bracket_seq(braces);
            if (result) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readCommands(BufferedReader reader, int lines) throws IOException {
        List<String> commands = new ArrayList<>();
        for (int i = 1; i <= lines; i++) {
            String line = reader.readLine();
            commands.add(line);
        }
        return commands;
    }
}
