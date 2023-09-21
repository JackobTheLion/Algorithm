package ru.praktikum.yandex.sprint02.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class I {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int lines = readInt(reader);
            int size = readInt(reader);
            List<String> commands = readCommands(reader, lines);

            MyQueueSized myQueueSized = new MyQueueSized(size);

            for (String command : commands) {
                if (command.equals("peek")) {
                    Integer peek = myQueueSized.peek();
                    if (peek == null) {
                        System.out.println("None");
                    } else System.out.println(peek);
                } else if (command.contains("push")) {
                    String[] splitCommand = command.split(" ");
                    int toAdd = Integer.parseInt(splitCommand[1]);
                    if (!myQueueSized.push(toAdd)) {
                        System.out.println("error");
                    }
                } else if (command.equals("size")) {
                    System.out.println(myQueueSized.size());
                } else if (command.equals("pop")) {
                    Integer pop = myQueueSized.pop();
                    if (pop == null) {
                        System.out.println("None");
                    } else System.out.println(pop);
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

class MyQueueSized {
    private Integer[] queue;
    private int max_n;
    private int head;
    private int tail;
    private int size;

    public MyQueueSized(int maxSize) {
        queue = new Integer[maxSize];
        max_n = maxSize;
        head = 0;
        tail = 0;
        size = 0;
    }

    public boolean push(Integer x) { //добавить число x в очередь
        if (size != max_n) {
            queue[tail] = x;
            tail = (tail + 1) % max_n;
            size++;
            return true;
        } else return false;
    }

    public Integer pop() { //удалить число из очереди и вывести на печать
        if (isEmpty()) {
            return null;
        } else {
            Integer x = queue[head];
            queue[head] = null;
            head = (head + 1) % max_n;
            size--;
            return x;
        }
    }

    public Integer peek() { //напечатать первое число в очереди
        if (size != 0) {
            return queue[head];
        } else return null;
    }

    public Integer size() { //вернуть размер очереди
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
