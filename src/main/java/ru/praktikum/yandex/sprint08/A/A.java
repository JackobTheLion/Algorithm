package ru.praktikum.yandex.sprint08.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] arr = reader.readLine().split(" ");

            StringBuilder sb = new StringBuilder();

            for (int i = arr.length - 1; i >= 0; i--) {
                sb.append(arr[i]).append(" ");
            }

            System.out.println(sb);
        }
    }
}
