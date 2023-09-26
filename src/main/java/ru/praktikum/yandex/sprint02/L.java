package ru.praktikum.yandex.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class L {

    public static long getLastNumbers(long n, int k) {
        return 0;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            String[] lineArr = line.split(" ");
            long n = Long.parseLong(lineArr[0]);
            int k = Integer.parseInt(lineArr[1]);
            System.out.println(getLastNumbers(n, k));
        }
    }
}
