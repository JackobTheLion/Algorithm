package ru.praktikum.yandex.sprint07.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
        int[] dp = new int[capacity + 1];

        for (int i = weights.get(0); i < capacity; i++) {
            dp[i] = weights.get(0);
        }

        for (int i = 1; i < weights.size(); i++) {
            int[] temp = Arrays.copyOf(dp, dp.length);
            for (int j = 0; j <= capacity; j++) {
                if (j - weights.get(i) >= 0) {
                    temp[j] = Math.max(dp[j], weights.get(i) + dp[j - weights.get(i)]);
                }
            }
            dp = temp;
        }

        return dp[capacity];
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
