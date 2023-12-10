package ru.praktikum.yandex.sprint06.fin.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/* --ПРИНЦИП РАБОТЫ--
 * Задача сводится к поиску максимального остовного дерева, что в свою очередь достигается с помощью алгоритма Прима,
 * модифицированного таким образом, чтобы выбирать не минимальное, а максимальное ребро.
 *
 * Для решения нам понадобятся вспомогательные классы вершин (Vertex) и ребер (Edge).
 *
 * 1. Создадим массив булевых значений, для определения, посещали ли мы вершину.
 * 2. Создадим приоритетную очередь для ребер, в вершине которой будет всегда находится ребро с самым большим весом
 * 3. Возьмем первую вершину из списка (с какой начинать неважно, т.к. в остовное дерево попадут все вершины)
 * 4. Далее воспользуемся методом добавления вершин который:
 * 4.1 Отметит в массиве, что вершина посещена
 * 4.2 добавит все исходящие ребра вершины в очередь
 * 5. Заведем счетчик количества посещенных вершин
 * 6. Заведем переменную веса графа
 * 7. Далее в цикле до тех пор, пока существуют недобавленные вершины (счетчик меньше их количества) и
 * нерассмотренные ребра
 * 7.1 достаем самое "тяжелое" ребро из очереди
 * 7.2 если вершина на его конце еще не добавлена в сет добавленных то
 * 7.2.1 добавляем вершину в сет добавленных
 * 7.2.2 все ребра из этой вершины добавляем в очередь и помечаем как посещенную (см. п.5)
 * 7.2.3 увеличиваем вес графа на вес ребра и увеличиваем счетчик посещенных вершин
 * 8. После выхода из цикла проверяем, все ли вершины были добавлены
 * 8.1 если не все (счетчик меньше общего количества вершин), значит граф несвязный, остновное дерево построить нельзя,
 * вернем -1
 * 8.2 если все вершины добавлены, то возвращаем вес графа
 *
 * --ДОКОЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 * Решение основано на алгоритме Прима с поддержанием следующих инвариантов:
 * - в сете добавленных вершин всегда находится актуальный список добавленных вершин
 * - в сете не добавленных вершин всегда находится актуальный список не добавленных вершин
 * - в приоритетной очереди находятся все ребра, доступные из добавленных вершин, при этом в вершине всегда наибольшее
 *
 * Т.о. доставая наибольшее ребро мы либо "приходим" к уже добавленной вершине и удаляем ребро из очереди ничего не
 * делая, либо к новой вершине, которую нужно добавить.
 *
 * Если ребра кончились, а необработанные вершины еще остались, это означает, что они недостижимы из подграфа,
 * следовательно, граф несвязный и остновное дерево построить нельзя.
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Временная сложность совпадает с временной сложностью алгоритма Прима и составляет О(|E| * log |V|), где |E| -
 * количество ребер графа, а |V| - количество вершин.
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * Пространственная сложность складывается из следующего:
 * - Массив булевых значений, размером О(|V|)
 * - Очередь в худшем случае О(|E|), если все ребра графа попадут в нее
 * - Текущая рассматриваемся вершина О(1)
 * - Общий вес графа О(1)
 *
 * Итого: О(|E| + |V| + 1 + 1) ~ О(|E| + |V|)
 *
 */

// Ссылка на посылку: https://contest.yandex.ru/contest/25070/run-report/102326864/

public class A {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<Integer> numberOfVertexAndEdges = readNumberOfEdges(reader);
            List<Vertex> vertices = readVertices(reader, numberOfVertexAndEdges.get(1), numberOfVertexAndEdges.get(0));

            int weight = getWeight(vertices);

            if (weight == -1) {
                System.out.println("Oops! I did it again");
            } else {
                System.out.println(weight);
            }
        }
    }

    private static int getWeight(List<Vertex> vertices) {
        boolean[] visited = new boolean[vertices.size()];

        Queue<Edge> edges = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.weight, o1.weight));

        Vertex vertex = vertices.get(0);

        addVertex(vertex, visited, edges);
        int visitedNumber = 1;

        int weight = 0;
        while (!edges.isEmpty() && visitedNumber < vertices.size()) {
            Edge maximumEdge = edges.poll();
            if (!visited[maximumEdge.end]) {
                addVertex(vertices.get(maximumEdge.end), visited, edges);
                visitedNumber++;
                weight += maximumEdge.weight;
            }
        }

        if (visitedNumber < vertices.size()) {
            return -1;
        } else return weight;
    }

    private static void addVertex(Vertex vertex, boolean[] visited, Queue<Edge> edges) {
        visited[vertex.value - 1] = true;
        for (Edge edge : vertex.edges) {
            if (!visited[edge.end]) {
                edges.add(edge);
            }
        }
    }

    private static List<Integer> readNumberOfEdges(BufferedReader reader) throws IOException {
        List<Integer> result = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()) {
            Integer integer = parseInt(tokenizer.nextToken());
            result.add(integer);
        }
        return result;
    }

    private static List<Vertex> readVertices(BufferedReader reader, int numberOfEdges, int numberOfVertices) throws IOException {
        List<Vertex> vertices = new ArrayList<>(numberOfVertices);
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add(new Vertex(i + 1));
        }

        for (int i = 0; i < numberOfEdges; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = parseInt(tokenizer.nextToken()) - 1;
            int end = parseInt(tokenizer.nextToken()) - 1;
            int weight = parseInt(tokenizer.nextToken());

            vertices.get(start).edges.add(new Edge(start, end, weight));
            vertices.get(end).edges.add(new Edge(end, start, weight));
        }
        return vertices;
    }
}

class Vertex {
    final int value;
    final List<Edge> edges;

    public Vertex(int value) {
        this.value = value;
        this.edges = new ArrayList<>();
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

class Edge {
    final int start;
    final int end;
    final int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}