package ru.praktikum.yandex.sprint06.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class C {

    private static List<String> colors;
    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> numberOfRibs = readNumberOfRibs(reader);
            List<Rib> ribs = readRibs(reader, numberOfRibs.get(1));
            int start = readInt(reader);
            initializeColor(numberOfRibs.get(0));
            List<Vertex> vertices = makeAdjacencyList(ribs, numberOfRibs.get(0));
            System.out.println(DFS(vertices, start));
        }
    }

    private static String DFS(List<Vertex> vertices, int startVertex) {
        StringBuilder sb = new StringBuilder();
        stack.push(startVertex);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (colors.get(v).equals("white")) {
                sb.append(v).append(" ");
                colors.set(v, "gray");
                stack.push(v);
                List<Integer> adjacentVertices = vertices.get(v).adjacentVertices;
                for (Integer adjacentVertex : adjacentVertices) {
                    if (colors.get(adjacentVertex).equals("white")) {
                        stack.push(adjacentVertex);
                    }
                }
            } else if (colors.get(v).equals("gray")){
                colors.set(v, "black");
            }
        }

        return sb.toString();
    }

    private static void initializeColor(int numVertices) {
        colors = new ArrayList<>(numVertices + 1);
        for (int i = 0; i < numVertices + 1; i++) {
            colors.add("white");
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Vertex> makeAdjacencyList(List<Rib> ribs, int knotsNumber) {
        List<Vertex> vertices = new ArrayList<>(knotsNumber + 1);

        for (int i = 0; i < knotsNumber + 1; i++) {
            vertices.add(new Vertex());
        }

        for (Rib rib : ribs) {
            List<Integer> integers = vertices.get(rib.start).adjacentVertices;
            integers.add(rib.end);
            integers = vertices.get(rib.end).adjacentVertices;
            integers.add(rib.start);
        }

        for(Vertex vertex: vertices) {
            vertex.adjacentVertices.sort((o1, o2) -> Integer.compare(o2, o1));
        }

        return vertices;
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

class Vertex {
    List<Integer> adjacentVertices;

    public Vertex() {
        adjacentVertices = new ArrayList<>();
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
