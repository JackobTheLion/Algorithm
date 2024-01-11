package ru.praktikum.yandex.sprint08.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class L {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            int[] ints = prefixFunction(s);

            StringBuilder sb = new StringBuilder();

            for (Integer integer : ints) {
                sb.append(integer).append(" ");
            }

            System.out.println(sb);
        }
    }

    public static int[] prefixFunction(String s) {
        int N = s.length();
        int[] prefix = new int[N];

        for (int i = 1; i < N; i++) {
            int k = prefix[i - 1];
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = prefix[k - 1];
            }

            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            prefix[i] = k;
        }

        return prefix;
    }
}
