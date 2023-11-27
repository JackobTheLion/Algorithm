package ru.praktikum.yandex.sprint06.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> ribsNumber = readNumberOfRibs(reader);
            List<Rib> ribs = readRibs(reader, ribsNumber.get(1));

            makeAdjacencyList(ribs, ribsNumber.get(0));
        }
    }

    private static void makeAdjacencyList(List<Rib> ribs, int knotsNumber) {
        List<List<Integer>> adjacencyList = new ArrayList<>(knotsNumber);

        for (int i = 0; i < knotsNumber; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (Rib rib : ribs) {
            List<Integer> integers = adjacencyList.get(rib.start - 1);
            integers.add(rib.end);
        }

        StringBuilder sb = new StringBuilder();
        for (List<Integer> integers : adjacencyList) {
            integers.sort(Integer::compareTo);
            sb.append(integers.size()).append(" ");
            for (Integer integer : integers) {
                sb.append(integer).append(" ");
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
