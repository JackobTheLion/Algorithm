package ru.praktikum.yandex.sprint01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class G {

    private static String getBinaryNumber(int n) {
        if (n == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        int devided = n;

        while (devided != 0) {
            sb.append(devided % 2);
            devided = devided / 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            System.out.println(getBinaryNumber(n));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
