package ru.praktikum.yandex.sprint05.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* --ПРИНЦИП РАБОТЫ--
 * Работа алгоритма основана на сортировке кучей. По условию задачи, в решении реализована куча.
 * Сначала каждый элемент исходного списка добавляется в кучу, а затем последовательно извлекается и добавляется в
 * результирующий список. Благодаря реализации кучи извлекать мы будем всегда наибольший элемент.
 *
 * Принцип работы кучи:
 * Под капотом кучи лежит массив, с доступом к элементу по индексу за О(1).
 *
 * При добавлении элемент попадает в конец списка, а затем просеивается до нужной позиции
 * вверх.
 * Просеивание вверх реализовано рекурсивно. На вход метода передается индекс просеиваемого элемента.
 * 1. если индекс равен 1 - значит мы дошли до самого верха кучи, дальше просеивать некуда
 * 2. Рассчитаем индекс родительского элемента
 * 2.1 если родительский элемент меньше добавляемого, значит нужно поменять их местами и рекурсивно запустить
 * просеивание от новой позиции добавляемого элемента.
 * 2.2 если родительский элемент не больше добавляемого, значит новый элемент находится на своем месте.
 *
 * Благодаря добавлению с просеиванием наибольший элемент всегда будет лежать в начале списка под индексом 0.
 * Извлечем его и поместим на его место наименьший элемент, который лежит в самом конце списка, а затем просеем его
 * вниз, чтобы сохранить корректность кучи.
 *
 * Просеивание вниз также реализовано рекурсивно, на вход подается индекс просеиваемого элемента:
 * 1. Рассчитаем индексы левого и правого ребенка
 * 2. Если индекс левого ребенка больше размера кучи, значит мы дошли до нижнего слоя, элемент на свое месте.
 * 3. Определим, в какую сторону будет опускаться элемент:
 * 3.1 Если существует правый потомок (правый индекс меньше размера кучи) и его элемент меньше правого, то двигаемся
 * вправо
 * 3.2 Иначе влево
 * 4. Если элемент в правом потомке больше просеиваемого, то меняем их местами и рекурсивно запускаем просеивание от
 * новой позиции элемента.
 *
 * --ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 * Ключевым в работе алгоритма является поддержания приоритетной очереди, которая удовлетворяет следующим условиям:
 *  - ключ в любой вершине не меньше ключей дочерних элементов
 *  - дерево является почти полным
 *  - все слои кроме последнего заполнены, а последний слой заполняется элементами слева направо.
 *
 * Таким образом сверху всегда будет наибольший элемент, который мы и будем извлекать.
 *
 * Для поддержания этих условий при добавлении (удалении) необходимо менять местами элементы до тех пор, пока описанный
 * порядок не будет восстановлен.
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Куча основана на массиве, в котором доступ к элементу по индексу осуществляется за О(1).
 * Куча является бинарным деревом. Вставляя любой элемент, нужно проделывать операцию обмена до тех пор, пока куча не
 * станет упорядоченной. В худшем случае новый элемент встанет на вершину пирамиды. Т.к. высота пирамиды составляет
 * log n, то вставка элемента происходит за O(log n). Т.к. операция производится для n элементов, то в сумме получаем
 * О(n log n).
 *
 * Аналогичная ситуация с удалением, в худшем случае элемент опустится в самый низ пирамиды, что произойдет за O(log n),
 * а в сумме для всех элементов О(n log n).
 *
 * Итого получаем:
 *  О(n log n + n log n) = О(2 (n log n)) = О(n log n).
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * В алгоритме используется дополнительная память для хранения массива-кучи. Его размер равен количеству элементов, т.е.
 * О(n).
 * Глубина рекурсии в худшем случае (как при вставке, так и при удалении) будет равна высоте пирамиды, т.е. О(log n).
 *
 * Таким образом пространственная сложность:
 * О(n + log n)
 */

// Ссылка на посылку: https://contest.yandex.ru/contest/24810/run-report/98469613/

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int number = readInt(reader);
            List<Participant> participants = readParticipants(reader, number);

            List<Participant> sortedParticipants = sortParticipants(participants);

            StringBuilder sb = new StringBuilder();
            for (Participant participant : sortedParticipants) {
                sb.append(participant.getName()).append("\n");
            }
            System.out.println(sb);
        }
    }

    private static List<Participant> sortParticipants(List<Participant> participants) {
        MyHeap heap = new MyHeap(participants.size() + 1);

        for (Participant participant : participants) {
            heap.heapAdd(participant);
        }

        List<Participant> sortedArray = new ArrayList<>();
        while (!heap.isEmpty()) {
            sortedArray.add(heap.popMax());
        }
        return sortedArray;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Participant> readParticipants(BufferedReader reader, int number) throws IOException {
        List<Participant> participants = new ArrayList<>(number);

        for (int i = 1; i <= number; i++) {
            String[] s = reader.readLine().split(" ");
            Participant participant = new Participant(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            participants.add(participant);
        }

        return participants;
    }
}

class MyHeap {
    List<Participant> heap;

    public MyHeap(int arraySize) {
        heap = new ArrayList<>(arraySize);
    }

    public void heapAdd(Participant participant) {
        heap.add(participant);
        siftUp(heap.size());
    }

    public Participant popMax() {
        Participant result = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        siftDown(0);
        return result;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void siftUp(int index) {
        if (index == 1) {
            return;
        }

        int parentIndex = index / 2;
        if (compare(heap.get(parentIndex - 1), heap.get(index - 1)) < 1) {
            Participant temp = heap.get(parentIndex - 1);
            heap.set(parentIndex - 1, heap.get(index - 1));
            heap.set(index - 1, temp);
            siftUp(parentIndex);
        }
    }

    private void siftDown(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left >= heap.size()) {
            return;
        }

        int indexLargest = left;
        if (right < heap.size() && compare(heap.get(left), heap.get(right)) < 1) {
            indexLargest = right;
        }

        if (compare(heap.get(index), heap.get(indexLargest)) < 1) {
            Participant temp = heap.get(index);
            heap.set(index, heap.get(indexLargest));
            heap.set(indexLargest, temp);
            siftDown(indexLargest);
        }
    }

    private int compare(Participant p1, Participant p2) {
        if (p1.getSolved() != p2.getSolved()) {
            return p1.getSolved() - p2.getSolved();
        }
        if (p1.getPenalty() != p2.getPenalty()) {
            return p2.getPenalty() - p1.getPenalty();
        }
        return p2.getName().compareTo(p1.getName());
    }
}

class Participant {
    private final String name;
    private final int solved;
    private final int penalty;

    public Participant(String name, int solved, int penalty) {
        this.name = name;
        this.solved = solved;
        this.penalty = penalty;
    }

    public String getName() {
        return name;
    }

    public int getSolved() {
        return solved;
    }

    public int getPenalty() {
        return penalty;
    }
}
