package ru.praktikum.yandex.sprint03.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class B {

    public static void generator(String n) {
        String[] numbersString = n.split("");
        int[] numbers = new int[numbersString.length];

        for (int i = 0; i < numbersString.length; i++) {
            numbers[i] = Integer.parseInt(numbersString[i]);
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder finalSB = new StringBuilder();
        System.out.println(generatorReq(numbers, sb, 0, finalSB));
    }

    private static StringBuilder generatorReq(int[] numbers, StringBuilder sb, int numberPointer, StringBuilder finalSb) {
        if (numberPointer == numbers.length) {
            finalSb.append(sb).append(" ");
            return finalSb;
        }

        List<String> list = List.of(
                "",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        );

        for (int i = 0; i < list.get(numbers[numberPointer]).length(); i++) {
            StringBuilder sb2 = new StringBuilder(sb);
            sb2.append(list.get(numbers[numberPointer]).charAt(i));
            generatorReq(numbers, new StringBuilder(sb2), numberPointer + 1, finalSb);
        }
        return finalSb;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String n = reader.readLine();
            generator(n);
        }
    }
}
