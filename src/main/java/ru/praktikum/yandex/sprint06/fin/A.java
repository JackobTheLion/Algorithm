package ru.praktikum.yandex.sprint06.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

public class A {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> numberOfVertexAndRibs = readNumberOfRibs(reader);
            List<Vertex> vertices = readVertices(reader, numberOfVertexAndRibs.get(1), numberOfVertexAndRibs.get(0));

            int weight = getWeight(vertices);

            if (weight == -1) {
                System.out.println("Oops! I did it again");
            } else {
                System.out.println(weight);
            }
        }
    }

    private static int getWeight(List<Vertex> vertices) {
        Set<Vertex> addedVertices = new HashSet<>();
        Set<Vertex> notAddedVertices = new HashSet<>(vertices);
        PriorityQueue<Rib> edges = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.weight, o1.weight));

        Vertex vertex = vertices.get(0);

        addVertex(vertex, addedVertices, notAddedVertices, vertices, edges);

        int weight = 0;
        while (!notAddedVertices.isEmpty() && !edges.isEmpty()) {
            Rib maximumRib = edges.poll();
            if (!addedVertices.contains(vertices.get(maximumRib.end))) {
                addedVertices.add(vertices.get(maximumRib.end));
                addVertex(vertices.get(maximumRib.end), addedVertices, notAddedVertices, vertices, edges);
                weight += maximumRib.weight;
            }
        }

        if (!notAddedVertices.isEmpty()) {
            return -1;
        } else return weight;
    }

    private static void addVertex(Vertex vertex, Set<Vertex> addedVertices, Set<Vertex> notAddedVertices,
                                  List<Vertex> vertices, PriorityQueue<Rib> edges) {
        addedVertices.add(vertex);
        notAddedVertices.remove(vertex);
        for (Rib rib : vertex.ribs) {
            if (notAddedVertices.contains(vertices.get(rib.end))) {
                edges.add(rib);
            }
        }
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
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add(new Vertex(i + 1));
        }

        for (int i = 0; i < numberOfRibs; i++) {
            String[] s = reader.readLine().split(" ");
            int start = parseInt(s[0]) - 1;
            int end = parseInt(s[1]) - 1;
            int weight = parseInt(s[2]);

            vertices.get(start).ribs.add(new Rib(start, end, weight));
            vertices.get(end).ribs.add(new Rib(end, start, weight));
        }
        return vertices;
    }
}

class Vertex {
    final int value;
    final List<Rib> ribs;

    public Vertex(int value) {
        this.value = value;
        this.ribs = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return value == vertex.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}

class Rib {
    final int start;
    final int end;
    final int weight;

    public Rib(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}