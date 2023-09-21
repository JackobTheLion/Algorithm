package ru.praktikum.yandex.sprint02.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class J {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int lines = readInt(reader);
            List<String> commands = readCommands(reader, lines);

            LinkedQue linkedQue = new LinkedQue();

            for (String command : commands) {
                if (command.contains("put")) {
                    String[] commandArr = command.split(" ");
                    Integer toAdd = Integer.parseInt(commandArr[1]);
                    linkedQue.put(toAdd);
                } else if (command.equals("get")) {
                    Integer get = linkedQue.get();
                    if (get == null) {
                        System.out.println("error");
                    } else System.out.println(get);
                } else if (command.equals("size")) {
                    System.out.println(linkedQue.size());
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

class LinkedQue {

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public Integer get() { //вывести элемент, находящийся в голове очереди, и удалить его
        if (head == null) return null;

        Integer value = head.value;

        if (head.next != null) {
            head = head.next;
        } else head = null;
        size--;

        return value;
    }

    public void put(Integer x) {
        Node node = new Node(x, null);

        if (tail != null) {
            tail.next = node;
        }

        tail = node;

        if (head == null) {
            head = tail;
        }

        size++;
    }

    public Integer size() {
        return size;
    }

    class Node {
        public Integer value;
        public Node next;

        public Node(Integer value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
