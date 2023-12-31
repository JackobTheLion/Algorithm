package ru.praktikum.yandex.sprint06.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class H {

    private static int[] colors;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> numberOfVertexAndRibs = readNumberOfRibs(reader);
            List<List<Integer>> vertices = readVertices(reader, numberOfVertexAndRibs.get(1), numberOfVertexAndRibs.get(0));
            colors = new int[vertices.size()];

            System.out.println(DFS(vertices, 1));
        }
    }

    private static String DFS(List<List<Integer>> vertices, int startVertex) {
        Stack<Integer> stack = new Stack<>();
        int time = 0;
        int[] entry = new int[vertices.size()];
        int[] leave = new int[vertices.size()];

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (colors[v] == 0) {
                entry[v] = time;
                time += 1;
                colors[v] = 1;
                stack.push(v);
                List<Integer> adjacentVertices = vertices.get(v);
                adjacentVertices.sort((o1, o2) -> Integer.compare(o2, o1));
                for (Integer adjacentVertex : adjacentVertices) {
                    if (colors[adjacentVertex] == 0) {
                        stack.push(adjacentVertex);
                    }
                }
            } else if (colors[v] == 1) {
                leave[v] = time;
                time += 1;
                colors[v] = 2;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < entry.length; i++) {
            if (entry[i] == 0 && leave[i] == 0) continue;
            sb.append(entry[i]).append(" ").append(leave[i]).append("\n");
        }

        return sb.toString();
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

    private static List<List<Integer>> readVertices(BufferedReader reader, int numberOfRibs, int numberOfVertices) throws IOException {
        List<List<Integer>> vertices = new ArrayList(numberOfVertices);
        for (int i = 0; i < numberOfVertices + 1; i++) {
            vertices.add(new ArrayList());
        }

        for (int i = 0; i < numberOfRibs; i++) {
            String[] s = reader.readLine().split(" ");
            vertices.get(Integer.parseInt(s[0])).add(Integer.parseInt(s[1]));
        }
        return vertices;
    }
}