package ru.praktikum.yandex.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class H {

    public static boolean is_correct_bracket_seq(String braces) {
        if (braces.isBlank()) {
            return true;
        }

        char[] bracesArr = braces.toCharArray();
        Stack<Character> bracesStack = new Stack<>();

        for (char c : bracesArr) {
            if (c == '{' || c == '(' || c == '[') {
                bracesStack.add(c);
            } else {
                if (!bracesStack.empty() && ((bracesStack.peek() == '{' && c == '}')
                        || (bracesStack.peek() == '[' && c == ']')
                        || (bracesStack.peek() == '(' && c == ')'))) {
                    bracesStack.pop();
                } else {
                    return false;
                }
            }
        }
        return bracesStack.empty();
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
}
