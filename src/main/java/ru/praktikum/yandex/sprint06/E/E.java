package ru.praktikum.yandex.sprint06.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class E {

    private static int[] colors;


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> numberOfVertexAndRibs = readNumberOfRibs(reader);
            List<List<Integer>> vertices = readVertices(reader, numberOfVertexAndRibs.get(1), numberOfVertexAndRibs.get(0));

            colors = new int[vertices.size()];
            int color = 0;

            for (int i = 1; i < vertices.size(); i++) {
                if (colors[i] == 0) {
                    color++;
                    findConnectedComponent(vertices, i, color);
                }
            }

            Map<Integer, List<Integer>> components = new HashMap<>();
            for (int i = 1; i < vertices.size(); i++) {
                List<Integer> orDefault = components.getOrDefault(colors[i], new ArrayList<>());
                orDefault.add(i);
                components.put(colors[i], orDefault);
            }

            StringBuilder sb = new StringBuilder();
            sb.append(color).append("\n");
            for(int i = 1; i <= color; i++) {
                List<Integer> integers = components.get(i);
                for (Integer integer : integers) {
                    sb.append(integer).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }
    }

    private static void findConnectedComponent(List<List<Integer>> vertices, int v, int color) {
        Stack<Integer> stack = new Stack<>();

        stack.push(v);
        while (!stack.isEmpty()) {
            v = stack.pop();
            colors[v] = color;
            List<Integer> adjacentVertices = vertices.get(v);
            adjacentVertices.sort((o1, o2) -> Integer.compare(o2, o1));
            for (Integer adjacentVertex : adjacentVertices) {
                if (colors[adjacentVertex] == 0) {
                    stack.push(adjacentVertex);
                }
            }
        }
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
            vertices.get(Integer.parseInt(s[1])).add(Integer.parseInt(s[0]));
        }
        return vertices;
    }
}
