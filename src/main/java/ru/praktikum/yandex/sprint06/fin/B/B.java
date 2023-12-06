package ru.praktikum.yandex.sprint06.fin.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/* --ПРИНЦИП РАБОТЫ--
 * Представим железную дорогу между городами как направленный граф, в котором по красным дорогам мы можем двигаться
 * только от меньшего города к большему, а по синим только от большего к меньшему. Тогда наличие двух городов, между
 * которыми есть пути разных цветов означает, что в графе есть цикл. Т.о. задача сводится к поиску цикла в графе, что
 * легко сделать с помощью обхода в глубину.
 *
 * * Каждая вершина получит свой цвет в процессе работы алгоритма: изначально 0 (белый) - вершину еще не рассматривали,
 * 1 (серый) - вершина помечена для рассмотрения, 2 (черный) - вершину рассмотрели.
 *
 * 1. При считывании данных инвертируем пути с пометкой B
 * 2. Для каждой вершины проведем поиск в глубину. Необходимо проверить каждую вершину, т.к. некоторые вершины могут не
 * иметь исходящих ребер
 * 3. Если вершину уже рассматривали или пометили для рассмотрения, то берем следующую.
 * 4. Создадим новый стек
 * 5. Положим в него рассматриваемую вершину
 * 6. До тех пор, пока стек не пуст будем выполнять цикл:
 * 6.1 достанем вершину из стека
 * 6.2 если ее цвет белый:
 * 6.2.1 меняем цвет на серый и кладем в стек.
 * 6.2.2 достаем все ребра вершины.
 * 6.2.3 Для каждой конечной вершины проверим, помечали ли мы ее для рассмотрения.
 * 6.2.3.1 если вершина не помечена (цвет белый), добавим ее в стек.
 * 6.2.3.2 если вершина помечена, значит мы уже попадали на нее и граф имеет цикл. Возвращаем false.
 * 6.3 Если цвет серый, то просто меняем его на черный
 *
 * --ДОКОЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Алгоритму необходимо пройтись по каждому ребру каждой вершины, т.о. временная сложность составит О(|V|+|E|),
 * где |V| - количество вершина, а |E| количество ребер.
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * В алгоритме используется стек, для хранения запланированных к посещению вершин. В худшем случае туда попадут все
 * вершины графа, и его размер составит О(|V|).
 *
 */

// Ссылка на посылку: https://contest.yandex.ru/contest/25070/run-report/102144445/

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int numberOfVertices = readInt(reader);
            List<Vertex> vertices = readVertices(reader, numberOfVertices);
            boolean dfs = DFS(vertices);

            if (dfs) {
                System.out.println("YES");
            } else System.out.println("NO");
        }
    }

    public static boolean DFS(List<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            if (vertex.color != 0) continue;

            Stack<Vertex> stack = new Stack<>();

            stack.push(vertex);

            while (!stack.isEmpty()) {
                Vertex pop = stack.pop();
                if (pop.color == 0) {
                    pop.color = 1;
                    stack.push(pop);
                    List<Integer> ribs = pop.ribs;
                    for (Integer rib : ribs) {
                        Vertex adjacentVertex = vertices.get(rib);
                        if (adjacentVertex.color == 0) {
                            stack.push(adjacentVertex);
                        } else if (adjacentVertex.color == 1) {
                            return false;
                        }
                    }
                } else if (pop.color == 1) {
                    pop.color = 2;
                }
            }
        }

        return true;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Vertex> readVertices(BufferedReader reader, int numberOfVertices) throws IOException {
        List<Vertex> vertices = new ArrayList<>(numberOfVertices);
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add(new Vertex(i));
        }

        for (int i = 0; i < numberOfVertices - 1; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'R') {
                    vertices.get(i).ribs.add(i + j + 1);
                } else {
                    vertices.get(i + j + 1).ribs.add(i);
                }
            }
        }
        return vertices;
    }
}

class Vertex {
    final int value;
    final List<Integer> ribs;

    int color; // 0 - белый, 1 - серый, 2 - черный

    public Vertex(int value) {
        this.value = value;
        this.ribs = new ArrayList<>();
        this.color = 0;
    }
}