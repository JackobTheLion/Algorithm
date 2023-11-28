package ru.praktikum.yandex.sprint06.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class H {

    private static List<String> colors;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> numberOfVertexAndRibs = readNumberOfRibs(reader);
            initializeColor(numberOfVertexAndRibs.get(0));
            List<List<Integer>> vertices = readVertices(reader, numberOfVertexAndRibs.get(1), numberOfVertexAndRibs.get(0));
            StringBuilder sb = new StringBuilder();
/*            for (int i = 1; i < vertices.size(); i++) {
                sb.append(DFS(vertices, i));
            }*/
            sb.append(DFS(vertices, 1));
            System.out.println(sb);
        }
    }

    private static String DFS(List<List<Integer>> vertices, int startVertex) {
        Stack<Integer> stack = new Stack<>();
        int time = 0;
        List<Integer> entry = initTimer(vertices.size());
        List<Integer> leave = initTimer(vertices.size());

        StringBuilder sb = new StringBuilder();

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (colors.get(v).equals("white")) {
                entry.set(v, time);
                time += 1;
                colors.set(v, "gray");
                stack.push(v);
                List<Integer> adjacentVertices = vertices.get(v);
                adjacentVertices.sort((o1, o2) -> Integer.compare(o2, o1));
                for (Integer adjacentVertex : adjacentVertices) {
                    if (colors.get(adjacentVertex).equals("white")) {
                        stack.push(adjacentVertex);
                    }
                }
            } else if (colors.get(v).equals("gray")) {
                leave.set(v, time);
                time += 1;
                colors.set(v, "black");
            }
        }

        for (int i = 1; i < entry.size(); i++) {
            if (entry.get(i) == null) continue;
            sb.append(entry.get(i)).append(" ").append(leave.get(i)).append("\n");
        }

        return sb.toString();
    }

    private static List<Integer> initTimer(int size) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size + 1; i++) {
            result.add(null);
        }
        return result;
    }

    private static void initializeColor(int numVertices) {
        colors = new ArrayList<>(numVertices + 1);
        for (int i = 0; i < numVertices + 1; i++) {
            colors.add("white");
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