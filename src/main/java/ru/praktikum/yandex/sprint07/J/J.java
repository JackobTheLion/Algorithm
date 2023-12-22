package ru.praktikum.yandex.sprint07.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class J {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int sequenceLength = readSequenceLength(reader);
            int[] sequence = readSequence(reader, sequenceLength);

            System.out.println(getLargestIncreasingSubsequence(sequence));
        }
    }

    private static String getLargestIncreasingSubsequence(int[] sequence) {
        int[] dp = new int[sequence.length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int[] position = new int[sequence.length + 1];
        Arrays.fill(dp, -1);

        int[] previous = new int[sequence.length + 1];

        for (int i = 0; i < sequence.length; i++) {

        }

        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }

    private static int readSequenceLength(BufferedReader reader) throws IOException {
        return parseInt(reader.readLine());
    }

    private static int[] readSequence(BufferedReader reader, int length) throws IOException {
        int[] sequence = new int[length];

        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            sequence[i] = parseInt(stringTokenizer.nextToken());
            i++;
        }

        return sequence;
    }
}
