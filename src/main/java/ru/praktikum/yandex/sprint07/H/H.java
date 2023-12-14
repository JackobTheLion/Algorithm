package ru.praktikum.yandex.sprint07.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class H {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> matrixSize = readMatrixSize(reader); // get(0) - n; get(1) - m
            int[][] matrix = readMatrix(reader, matrixSize.get(0), matrixSize.get(1));

            System.out.println(getMaxFlowers(matrix, matrixSize.get(0), matrixSize.get(1)));
        }
    }

    private static int getMaxFlowers(int[][] matrix, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }

        return dp[0][m];
    }

    private static int[][] readMatrix(BufferedReader reader, int n, int m) throws IOException {
        int[][] matrix = new int[n + 1][m +1];

        for (int i = 0; i < n; i++) {
            String[] split = reader.readLine().split("");
            for (int j = 1; j <= m; j++) {
                matrix[i][j] = Integer.parseInt(split[j - 1]);
            }
        }

        return matrix;
    }

    private static List<Integer> readMatrixSize(BufferedReader reader) throws IOException {
        List<Integer> result = new ArrayList<>();

        String[] split = reader.readLine().split(" ");
        for (String s : split) {
            result.add(Integer.parseInt(s));
        }
        return result;
    }
}
