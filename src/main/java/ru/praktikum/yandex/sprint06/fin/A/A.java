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
 * Для решения нам понадобятся вспомогательные классы вершин (Vertex) и ребер (Rib).
 *
 * 1. Создадим сет для поиска уже добавленных верши за О(1).
 * 2. Создадим сет для поиска еще не добавленных вершин за О(1) и сразу добавим в него все вершины
 * 3. Создадим приоритетную очередь для ребер, в вершине которой будет всегда находится ребро с самым большим весом
 * 4. Возьмем первую вершину из списка (с какой начинать неважно, т.к. в остовное дерево попадут все вершины)
 * 5. Далее воспользуемся методом добавления вершин который:
 * 5.1 добавит вершину в сет добавленных
 * 5.2 удалит из сета не добавленных
 * 5.3 добавит все исходящие ребра вершины в очередь
 * 6. Заведем переменную веса графа
 * 7. Далее в цикле до тех пор, пока существуют недобавленные вершины и нерассмотренные ребра
 * 7.1 достаем самое "тяжелое" ребро из очереди
 * 7.2 если вершина на его конце еще не добавлена в сет добавленных то
 * 7.2.1 добавляем вершину в сет добавленных
 * 7.2.2 все ребра из этой вершины добавляем в очередь и переносим вершину в список добавленных (см. п.5)
 * 7.2.3 увеличиваем вес графа на вес ребра
 * 8. После выхода из цикла проверяем, все ли вершины были добавлены
 * 8.1 если не все и в сете еще есть вершины, значит граф несвязный, остновное дерево построить нельзя, вернем -1
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
 * - Сет добавленных и сет недобавленных вершин в сумме дают О(|V|), т.к. вершина одновременно может лежать только в
 * одном сете.
 * - Очередь в худшем случае О(|E|), если все ребра графа попадут в нее
 * - Текущая рассматриваемся вершина О(1)
 * - Общий вес графа О(1)
 *
 * Итого: О(|E| + |V| + 1 + 1) ~ О(|E| + |V|)
 *
 */

// Ссылка на посылку: https://contest.yandex.ru/contest/25070/run-report/102139266/

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
        Queue<Rib> edges = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.weight, o1.weight));

        Vertex vertex = vertices.get(0);

        addVertex(vertex, addedVertices, notAddedVertices, vertices, edges);

        int weight = 0;
        while (!notAddedVertices.isEmpty() && !edges.isEmpty()) {
            Rib maximumRib = edges.poll();
            if (!addedVertices.contains(vertices.get(maximumRib.end))) {
                addVertex(vertices.get(maximumRib.end), addedVertices, notAddedVertices, vertices, edges);
                weight += maximumRib.weight;
            }
        }

        if (!notAddedVertices.isEmpty()) {
            return -1;
        } else return weight;
    }

    private static void addVertex(Vertex vertex, Set<Vertex> addedVertices, Set<Vertex> notAddedVertices,
                                  List<Vertex> vertices, Queue<Rib> edges) {
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
        List<Vertex> vertices = new ArrayList<>(numberOfVertices);
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add(new Vertex(i + 1));
        }

        for (int i = 0; i < numberOfRibs; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = parseInt(tokenizer.nextToken()) - 1;
            int end = parseInt(tokenizer.nextToken()) - 1;
            int weight = parseInt(tokenizer.nextToken());

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