package ru.praktikum.yandex.sprint02.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class G {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int lines = readInt(reader);
            List<String> commands = readCommands(reader, lines);

            StackMax stackMax = new StackMax();

            for (String command : commands) {
                if (command.contains("get_max")) {
                    Integer max = stackMax.get_max();
                    if (max == null) {
                        System.out.println("None");
                    } else System.out.println(max);
                } else if (command.contains("pop")) {
                    Integer pop = stackMax.pop();
                    if (pop == null) {
                        System.out.println("error");
                    }
                } else {
                    String[] c = command.split(" ");
                    stackMax.push(Integer.parseInt(c[1]));
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

class StackMax {
    private List<Pair> stack = new ArrayList<>();

    public void push(int x) {
        Integer max;
        if (stack.isEmpty() || stack.get(stack.size() - 1).currentMax < x) {
            max = x;
        } else max = stack.get(stack.size() - 1).currentMax;
        stack.add(new Pair(x, max));
    }

    public Integer pop() {
        if (!stack.isEmpty()) {
            return stack.remove(stack.size() - 1).stack;
        } else return null;
    }

    public Integer get_max() {
        if (stack.isEmpty()) {
            return null;
        } else return stack.get(stack.size() - 1).currentMax;
    }

    class Pair {
        public Integer stack;
        public Integer currentMax;

        public Pair(Integer stack, Integer currentMax) {
            this.stack = stack;
            this.currentMax = currentMax;
        }
    }
}
