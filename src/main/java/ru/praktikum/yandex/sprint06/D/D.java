package ru.praktikum.yandex.sprint06.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class D {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> numberOfVertexAndRibs = readNumberOfRibs(reader);
            List<List<Integer>> vertices = readVertices(reader, numberOfVertexAndRibs.get(1), numberOfVertexAndRibs.get(0));
            int startPoint = readInt(reader);

            Queue<Integer> bfs = BFS(vertices, startPoint);

            StringBuilder sb = new StringBuilder();
            int size = bfs.size();
            for (int i = 0; i < size; i++) {
                sb.append(bfs.poll()).append(" ");
            }
            System.out.println(sb);
        }
    }

    private static Queue<Integer> BFS(List<List<Integer>> vertices, int startPoint) {
        int[] color = new int[vertices.size()]; // 0 - белый, 1 - серый, 2 - черный
        Queue<Integer> visited = new LinkedList<>();
        Queue<Integer> planned = new LinkedList<>();
        planned.add(startPoint);
        color[startPoint] = 1;

        while (!planned.isEmpty()) {
            int u = planned.poll();
            List<Integer> allies = vertices.get(u);
            allies.sort(Integer::compareTo);
            for (Integer ally :  vertices.get(u)) {
                if (color[ally] == 0) {
                    color[ally] = 1;
                    planned.add(ally);
                }
            }
            visited.add(u);
            color[u] = 2;
        }

        return visited;
    }


    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
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
