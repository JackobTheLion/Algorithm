package ru.praktikum.yandex.sprint07.K;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class K {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int lengthA = readSequenceLength(reader);
            int[] sequenceA = readSequence(reader, lengthA);
            int lengthB = readSequenceLength(reader);
            int[] sequenceB = readSequence(reader, lengthB);

            System.out.println(getSubSequence(sequenceA, sequenceB));
        }
    }

    private static String getSubSequence(int[] sequenceA, int[] sequenceB) {
        int[][] dp = getDP(sequenceA, sequenceB);

        List<Integer> subSequenceA = new ArrayList<>(sequenceA.length);
        List<Integer> subSequenceB = new ArrayList<>(sequenceB.length);
        int i = sequenceA.length - 1;
        int j = sequenceB.length - 1;
        while (dp[i][j] != 0) {
            if (sequenceA[i] == sequenceB[j]) {
                subSequenceA.add(i);
                subSequenceB.add(j);
                i--;
                j--;
            } else {
                if (dp[i][j] == dp[i - 1][j]) {
                    i--;
                } else {
                    j--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[sequenceA.length - 1][sequenceB.length - 1]).append("\n");
        appendSubSequence(subSequenceA, sb);
        appendSubSequence(subSequenceB, sb);

        return sb.toString();
    }

    private static void appendSubSequence(List<Integer> subSequence, StringBuilder sb) {
        Collections.reverse(subSequence);
        for (Integer integer : subSequence) {
            sb.append(integer).append(" ");
        }
        sb.append("\n");
    }

    private static int[][] getDP(int[] sequenceA, int[] sequenceB) {
        int[][] dp = new int[sequenceA.length][sequenceB.length];
        for (int i = 1; i < sequenceA.length; i++) {
            for (int j = 1; j < sequenceB.length; j++) {
                if (sequenceA[i] == sequenceB[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp;
    }


    private static int readSequenceLength(BufferedReader reader) throws IOException {
        return parseInt(reader.readLine());
    }

    private static int[] readSequence(BufferedReader reader, int length) throws IOException {
        int[] sequence = new int[length + 1];

        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        int i = 1;
        while (stringTokenizer.hasMoreTokens()) {
            sequence[i] = parseInt(stringTokenizer.nextToken());
            i++;
        }

        return sequence;
    }
}
