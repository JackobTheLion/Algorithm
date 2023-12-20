package ru.praktikum.yandex.sprint07.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class L {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> numberOfBarsAndCapacity = readNumberOfBarsAndCapacity(reader);
            List<Integer> weghts = readWeghts(reader);

            System.out.println(calcMaxWeight(weghts, numberOfBarsAndCapacity.get(1)));
        }
    }

    private static int calcMaxWeight(List<Integer> weights, int capacity) {
        int[][] dp = new int[weights.size()][capacity + 1];

        int startWeight = weights.get(0);
        while (startWeight <= capacity) {
            dp[0][startWeight] = weights.get(0);
            startWeight++;
        }

        for (int i = 1; i < weights.size(); i++) {
            for (int j = 0; j <= capacity; j++) {
                int weight = j - weights.get(i);
                if (weight >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], weights.get(i) + dp[i - 1][j - weights.get(i)]);
                } else dp[i][j] = dp[i - 1][j];
            }
        }

        return dp[weights.size() - 1][capacity];
    }

    private static List<Integer> readNumberOfBarsAndCapacity(BufferedReader reader) throws IOException {
        List<Integer> numberOfBarsAndCapacity = new ArrayList<>();

        String[] split = reader.readLine().split(" ");
        numberOfBarsAndCapacity.add(parseInt(split[0]));
        numberOfBarsAndCapacity.add(parseInt(split[1]));

        return numberOfBarsAndCapacity;
    }

    private static List<Integer> readWeghts(BufferedReader reader) throws IOException {
        List<Integer> weghts = new ArrayList<>();

        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        while (stringTokenizer.hasMoreTokens()) {
            int weight = parseInt(stringTokenizer.nextToken());
            weghts.add(weight);
        }

        return weghts;
    }
}
