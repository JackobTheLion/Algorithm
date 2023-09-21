package ru.praktikum.yandex.sprint02.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int lines = readInt(reader);
            int size = readInt(reader);
            List<String> commands = readCommands(reader, lines);

            MyDeque myDeque = new MyDeque(size);

            for (String command : commands) {
                if (command.contains("push_front")) {
                    String[] commandArr = command.split(" ");
                    Integer x = Integer.parseInt(commandArr[1]);
                    if (!myDeque.pushFront(x)) {
                        System.out.println("error");
                    }
                } else if (command.contains("push_back")) {
                    String[] commandArr = command.split(" ");
                    Integer x = Integer.parseInt(commandArr[1]);
                    if (!myDeque.pushBack(x)) {
                        System.out.println("error");
                    }
                } else if (command.contains("pop_front")) {
                    Integer x = myDeque.popFront();
                    if (x != null) {
                        System.out.println(x);
                    } else System.out.println("error");
                } else if (command.contains("pop_back")) {
                    Integer x = myDeque.popBack();
                    if (x != null) {
                        System.out.println(x);
                    } else System.out.println("error");
                }
            }
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
