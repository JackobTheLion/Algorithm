package ru.praktikum.yandex.sprint07.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/*
 *
 *
 */

// Ссылка на посылку: https://contest.yandex.ru/contest/25597/run-report/103648174/

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int gamesNumber = readGamesNumber(reader);
            List<Integer> score = readScore(reader, gamesNumber);

            String result;
            if (isCanSplit(score)) {
                result = "True";
            } else {
                result = "False";
            }

            System.out.println(result);
        }
    }

    private static boolean isCanSplit(List<Integer> score) {
        int expectedSum = score.get(0);
        if (expectedSum % 2 != 0) {
            return false;
        }
        expectedSum /= 2;

        int[] dp = new int[expectedSum + 1];
        for (int i = 1; i < score.size(); i++) {
            int[] temp = Arrays.copyOf(dp, dp.length);
            for (int j = 0; j <= expectedSum; j++) {
                if (j - score.get(i) >= 0) {
                    temp[j] = Math.max(dp[j], score.get(i) + dp[j - score.get(i)]);
                }
            }
            dp = temp;
        }

        return dp[expectedSum] == expectedSum;
    }

    private static int readGamesNumber(BufferedReader reader) throws IOException {
        return parseInt(reader.readLine());
    }

    private static List<Integer> readScore(BufferedReader reader, int gamesNumber) throws IOException {
        List<Integer> result = new ArrayList<>(gamesNumber + 1);
        result.add(0);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i < gamesNumber + 1; i++) {
            int score = parseInt(tokenizer.nextToken());
            result.add(score);
            result.set(0, result.get(0) + score);
        }

        return result;
    }
}
