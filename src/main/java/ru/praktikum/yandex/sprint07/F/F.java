package ru.praktikum.yandex.sprint07.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class F {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> integers = readStairs(reader);
            int target = integers.get(0);
            int k = integers.get(1);

            System.out.println(getWaysNumber(target, k));
        }
    }

    private static int getWaysNumber(int target, int k) {
        int[] ways = new int[target + 1];

        ways[1] = 1;
        ways[2] = 1;

        int module = 1000000007;
        for (int i = 3; i <= target; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                ways[i] = (ways[i] + ways[i - j]) % module;
            }
        }

        return ways[target];
    }

    private static List<Integer> readStairs(BufferedReader reader) throws IOException {
        List<Integer> result = new ArrayList<>();

        String[] split = reader.readLine().split(" ");
        for (String s : split) {
            result.add(Integer.parseInt(s));
        }
        return result;
    }
}
