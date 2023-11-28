package ru.praktikum.yandex.sprint06.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class J {

    private static Stack<Integer> stack = new Stack<>();

    private static int[] color;


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> numberOfVertexAndRibs = readNumberOfRibs(reader);
            List<List<Integer>> vertices = readVertices(reader, numberOfVertexAndRibs.get(1), numberOfVertexAndRibs.get(0));

            color = new int[vertices.size()];

            for (int i = vertices.size() - 1; i >= 1; i--) {
                topologicalSort(vertices, i);
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }
            System.out.println(sb);
        }
    }

    private static void topologicalSort(List<List<Integer>> vertices, int v) {
        Stack<Integer> order = new Stack<>();

        order.push(v);
        while (!order.isEmpty()) {
            v = order.pop();
            if (color[v - 1] == 0) {
                color[v - 1] = 1;
                order.push(v);
                List<Integer> adjacentVertices = vertices.get(v);
                adjacentVertices.sort((o1, o2) -> Integer.compare(o2, o1));
                for (Integer adjacentVertex : adjacentVertices) {
                    if (color[adjacentVertex - 1] == 0) {
                        order.push(adjacentVertex);
                    }
                }
            } else if (color[v - 1] == 1) {
                color[v - 1] = 2;
                stack.push(v);
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
        }
        return vertices;
    }
}
