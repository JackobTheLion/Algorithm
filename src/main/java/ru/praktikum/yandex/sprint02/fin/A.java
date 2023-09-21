package ru.praktikum.yandex.sprint02.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// id посылки: 91027582

/**
 * -- ПРИНЦИП РАБОТЫ--
 * По условию задачи, дек основан на кольцевом буфере. В своей реализации я использовал массив с фиксированной длиной.
 * Инвариантом является то, что голова (head) и хвост (tail) всегда указывают на крайние элементы (не обрамляют их).
 * При добавлении элемента:
 * - в голову дека значение головы уменьшается;
 * - в хвост дека значение хвоста увеличивается.
 * Другими словами tail движется по часовой стрелке при добавлении и против при извлечении,
 * а head против часовой при добавлении и по часовой при извлечении.
 * <p>
 * Если в массиве одно значение, то head == tail, при извлечении равенство сохраняется.
 * <p>
 * Когда:
 * - head = 0 и добавляется новый элемент, то head = max - 1;
 * - head = max - 1 и извлечении первого элемента, то head = 0;
 * - tail = max - 1 и добавлении нового элемента, то tail = 0;
 * - tail = 0 и извлекается последний элемент, то tail = max - 1;
 * Это позволяет "закольцевать" массив.
 * <p>
 * Если на момент извлечения массив пуст, возвращается null.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Реализация алгоритма допускает извлечение только крайних элементов.
 * Массив "под капотом" гарантирует извлечение данных за O(1), а "движение" указателя головы и хвоста из конца в начало
 * и наоборот обеспечивает закольцованность.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Т.к. "под капотом" стоит массив, а операции извлечения и добавления выполняются по индексу, то сложность 0(1).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Т.к. "под капотом" стоит один массив, то пространственная сложность составляет O(n).
 **/

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int lines = readInt(reader);
            int size = readInt(reader);
            List<String> commands = readCommands(reader, lines);
            MyDeque myDeque = new MyDeque(size);
            StringBuilder sb = new StringBuilder();

            for (String command : commands) {
                if (command.contains("push_front")) {
                    String[] commandArr = command.split(" ");
                    Integer x = Integer.parseInt(commandArr[1]);
                    if (!myDeque.pushFront(x)) {
                        sb.append("error\n");
                    }
                } else if (command.contains("push_back")) {
                    String[] commandArr = command.split(" ");
                    Integer x = Integer.parseInt(commandArr[1]);
                    if (!myDeque.pushBack(x)) {
                        sb.append("error\n");
                    }
                } else if (command.equals("pop_front")) {
                    Integer x = myDeque.popFront();
                    if (x != null) {
                        sb.append(x).append("\n");
                    } else {
                        sb.append("error\n");
                    }
                } else if (command.equals("pop_back")) {
                    Integer x = myDeque.popBack();
                    if (x != null) {
                        sb.append(x).append("\n");
                    } else {
                        sb.append("error\n");
                    }
                }
            }
            System.out.println(sb);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readCommands(BufferedReader reader, int lines) throws IOException {
        List<String> commands = new ArrayList<>();
        for (int i = 1; i <= lines; i++) {
            String line = reader.readLine();
            commands.add(line);
        }
        return commands;
    }
}

class MyDeque {

    private Integer[] deque;
    private int max;
    private int size;
    private int head;
    private int tail;

    public MyDeque(int max) {
        deque = new Integer[max];
        this.max = max;
        size = 0;
        head = 0;
        tail = 0;
    }

    public boolean pushBack(Integer x) {
        if (size == max) return false;
        if (head == tail && size == 0) {
            deque[tail] = x;
            size++;
            return true;
        }
        tail = (tail + 1) % max;
        deque[tail] = x;
        size++;
        return true;
    }

    public boolean pushFront(Integer x) {
        if (size == max) {
            return false;
        } else if (head == tail && size == 0) {
            deque[head] = x;
            size++;
            return true;
        } else if (head != 0) {
            head--;
        } else {
            head = max - 1;
        }
        deque[head] = x;
        size++;
        return true;
    }

    public Integer popBack() {
        if (size == 0) return null;
        Integer pop = deque[tail];
        deque[tail] = null;
        size--;
        if (tail == 0 && size == 0) {
            return pop;
        } else if (tail == 0) {
            tail = max - 1;
        } else {
            tail--;
        }
        return pop;
    }

    public Integer popFront() {
        if (size == 0) return null;
        Integer pop = deque[head];
        deque[head] = null;
        if (head == tail) {
            size--;
            return pop;
        }
        head = (head + 1) % max;
        size--;
        return pop;
    }
}
