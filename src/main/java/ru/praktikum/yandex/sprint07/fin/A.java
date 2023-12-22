package ru.praktikum.yandex.sprint07.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *
 *
 */


// Ссылка на посылку: https://contest.yandex.ru/contest/25597/run-report/103609035/

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();

            System.out.println(getLevenshteinDistance(a, b));
        }
    }

    private static int getLevenshteinDistance(String a, String b) {
        int[] dp = new int[b.length() + 1];

        for (int i = 1; i <= b.length(); i++) {
            dp[i] = i;
        }

        for (int i = 1; i <= a.length(); i++) {
            int[] tempDp = new int[dp.length];
            tempDp[0] = i;

            for (int j = 1; j <= b.length(); j++) {
                int replaceCost = dp[j - 1];
                int addCost = tempDp[j - 1] + 1;
                int deleteCost = dp[j] + 1;

                if (a.charAt(i - 1) != b.charAt(j - 1)) {
                    replaceCost += 1;
                }

                tempDp[j] = Math.min(Math.min(replaceCost, addCost), deleteCost);
            }
            dp = tempDp;
        }

        return dp[b.length()];
    }
}
