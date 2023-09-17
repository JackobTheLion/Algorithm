package ru.praktikum.yandex.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class I {

    private static boolean isPowerOfFour(int n) {
        //if (n == 1) return true;

        while (n % 4 == 0) {
            n /= 4;
            if (n / 4 == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            //int n = readInt(reader);
            int n = 4;

            if (isPowerOfFour(n)) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

}
