package ru.praktikum.yandex.sprint06.K;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class K {


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> numberOfVertexAndRibs = readNumberOfRibs(reader);
            List<Vertex> vertices = readVertices(reader, numberOfVertexAndRibs.get(1), numberOfVertexAndRibs.get(0));

            StringBuilder sb = new StringBuilder();

            for (int i = 1; i < vertices.size(); i++) {
                int[] dijkstra = dijkstra(vertices, i);
                for (int j = 1; j < dijkstra.length; j++) {
                    if (dijkstra[j] == Integer.MAX_VALUE) {
                        sb.append(-1).append(" ");
                    } else {
                        sb.append(dijkstra[j]).append(" ");
                    }
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }
    }

    private static int[] dijkstra(List<Vertex> vertices, int startPoint) {
        int[] distance = new int[vertices.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);

        int[] previous = new int[vertices.size()];
        int[] visited = new int[vertices.size()]; // 0 - не посетили, 1 - посетили

        distance[startPoint] = 0;

        while (true) {
            Integer u = getMinDistNotVisitedVertex(vertices, visited, distance);
            if (u == null || distance[u] == Integer.MAX_VALUE) {
                break;
            }
            visited[u] = 1;

            List<Rib> uRibs = vertices.get(u).ribs;
            for (Rib rib : uRibs) {
                if (distance[rib.end] > distance[u] + rib.weight) {
                    distance[rib.end] = distance[u] + rib.weight;
                    previous[rib.end] = u;
                }
            }
        }

        return distance;
    }

    private static Integer getMinDistNotVisitedVertex(List<Vertex> vertices, int[] visited, int[] distance) {
        Integer currentMinimum = Integer.MAX_VALUE;
        Integer currentMinimumVertex = null;

        for (int i = 1; i < vertices.size(); i++) {
            if (visited[i] == 0 && distance[i] < currentMinimum) {
                currentMinimum = distance[i];
                currentMinimumVertex = i;
            }
        }
        return currentMinimumVertex;
    }

    private static List<Integer> readNumberOfRibs(BufferedReader reader) throws IOException {
        List<Integer> result = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()) {
            Integer integer = parseInt(tokenizer.nextToken());
            result.add(integer);
        }
        return result;
    }

    private static List<Vertex> readVertices(BufferedReader reader, int numberOfRibs, int numberOfVertices) throws IOException {
        List<Vertex> vertices = new ArrayList(numberOfVertices);
        for (int i = 0; i <= numberOfVertices; i++) {
            vertices.add(new Vertex());
        }

        for (int i = 0; i < numberOfRibs; i++) {
            String[] s = reader.readLine().split(" ");
            int start = parseInt(s[0]);
            int end = parseInt(s[1]);
            int weight = parseInt(s[2]);

            vertices.get(start).ribs.add(new Rib(end, weight));
            vertices.get(end).ribs.add(new Rib(start, weight));
        }
        return vertices;
    }

}

class Rib {
    final int end;
    final int weight;

    public Rib(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}

class Vertex {
    final List<Rib> ribs;

    public Vertex() {
        this.ribs = new ArrayList<>();
    }
}
