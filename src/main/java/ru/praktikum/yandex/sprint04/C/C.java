package ru.praktikum.yandex.sprint04.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class C {

    public static void printSubHash(String s, List<String> indexes, int a, int m, int t) {
        if (s.isEmpty()) {
            for (int i = 0; i < t; i++) {
                System.out.println(0);
            }
            return;
        }

        long[] hashes = getSubHashes(s, a, m);

        long[] pows = new long[s.length() + 1];
        pows[0] = 1;
        for (int i = 1; i < s.length() + 1; i++) {
            pows[i] = (pows[i - 1] * a) % m;
        }

        for (String i : indexes) {
            List<Integer> index = Arrays.stream(i.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            long hash = (hashes[index.get(1)] + m - hashes[index.get(0) - 1] * pows[index.get(1) - index.get(0) + 1] % m) % m;

            System.out.println(hash);
        }
    }

    public static long[] getSubHashes(String s, int a, int m) {
        long[] hashes = new long[s.length() + 1];

        hashes[1] = (s.charAt(0));

        for (int i = 2; i < s.length() + 1; i++) {
            long temp = hashes[i - 1] * a % m + s.charAt(i - 1);
            hashes[i] = temp % m;
        }

        return hashes;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int a = readInt(reader);
            int m = readInt(reader);
            String s = reader.readLine();
            int t = readInt(reader);
            List<String> indexes = readIndexes(reader, t);
            printSubHash(s, indexes, a, m, t);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readIndexes(BufferedReader reader, int t) throws IOException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            result.add(reader.readLine());
        }
        return result;
    }
}
