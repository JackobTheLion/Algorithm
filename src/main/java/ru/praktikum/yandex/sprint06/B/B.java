package ru.praktikum.yandex.sprint06.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> ribsNumber = readNumberOfRibs(reader);
            List<Rib> ribs = readRibs(reader, ribsNumber.get(1));

            makeMatrix(ribs, ribsNumber.get(0));
        }
    }

    private static void makeMatrix(List<Rib> ribs, int knotsNumber) {
        int[][] matrix = new int[knotsNumber][knotsNumber];

        for (Rib rib : ribs) {
            matrix[rib.start - 1][rib.end - 1] = 1;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < knotsNumber; i++) {
            for (int j = 0; j < knotsNumber; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static List<Integer> readNumberOfRibs(BufferedReader reader) throws IOException {
        List<Integer> result = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()) {
            Integer integer = Integer.parseInt(tokenizer.nextToken());
            result.add(integer);
        }
        return result;
    }

    private static List<Rib> readRibs(BufferedReader reader, int numberOfRibs) throws IOException {
        List<Rib> ribs = new ArrayList<>(numberOfRibs);
        for (int i = 0; i < numberOfRibs; i++) {
            String[] s = reader.readLine().split(" ");
            ribs.add(new Rib(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
        }
        return ribs;
    }
}

class Rib {
    int start;
    int end;

    public Rib(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
