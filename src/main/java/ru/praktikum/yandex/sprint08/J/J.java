package ru.praktikum.yandex.sprint08.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class J {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int classNumber = readInt(reader);
            List<String> classNames = readStrings(reader, classNumber);
            int patternNumber = readInt(reader);
            List<String> patterns = readStrings(reader, patternNumber);

            List<String> result = findAny(classNames, patterns);

            StringBuilder sb = new StringBuilder();
            result.forEach(w -> sb.append(w).append("\n"));
            System.out.println(sb);
        }
    }

    private static List<String> findAny(List<String> classes, List<String> patterns) {
        List<String> result = new ArrayList<>();

        Node trie = borTree(patterns);

        for (String classname : classes) {
            boolean nameChecked = false;
            for (int i = 0; i < classname.length(); i++) {
                Node currentNode = trie;
                int offset = 0;
                boolean mismatchNotFound = true;

                if (currentNode.terminal) {
                    result.add(classname);
                    break;
                }

                while (mismatchNotFound && i + offset < classname.length()) {
                    char symbol = classname.charAt(i + offset);

                    if (!Character.isUpperCase(symbol)) {
                        offset++;
                        continue;
                    }
                    if (currentNode.vertex.containsKey(symbol)) {
                        currentNode = currentNode.vertex.get(symbol);
                        if (currentNode.terminal) {
                            nameChecked = true;
                            result.add(classname);
                            break;
                        }
                    } else {
                        mismatchNotFound = false;
                    }
                    offset++;

                    if (nameChecked) {
                        break;
                    }
                }
            }
        }
        result.sort(String::compareTo);
        return result;
    }

    private static Node borTree(List<String> strings) {
        Node root = new Node();

        for (String string : strings) {
            addString(root, string);
        }

        return root;
    }

    private static void addString(Node root, String string) {
        Node currentNode = root;
        for (int i = 0; i < string.length(); i++) {
            char symbol = string.charAt(i);
            if (!currentNode.vertex.containsKey(symbol)) {
                Node newNode = new Node();
                currentNode.vertex.put(symbol, newNode);
            }
            currentNode = currentNode.vertex.get(symbol);
        }
        currentNode.terminal = true;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readStrings(BufferedReader reader, int stringNumber) throws IOException {
        List<String> strings = new ArrayList<>(stringNumber);

        for (int i = 0; i < stringNumber; i++) {
            strings.add(reader.readLine());
        }

        return strings;
    }
}

class Node {
    Map<Character, Node> vertex;
    boolean terminal;

    public Node() {
        this.vertex = new HashMap<>();
    }
}
