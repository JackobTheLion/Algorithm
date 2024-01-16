package ru.praktikum.yandex.sprint08.fin.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String T = reader.readLine();
            int wordsNumber = readInt(reader);
            String[] words = readWords(reader, wordsNumber);

            boolean canSplitText = canSplitText(T, words);

            if (canSplitText) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean canSplitText(String t, String[] words) {
        Node root = buildBorTree(words);

        boolean[] dp = new boolean[t.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= t.length(); i++) {
            if (root.vertex.containsKey(t.charAt(i - 1))) {
                Node currentNode = root;
                for (int j = i; j > 0; j--) {
                    currentNode = currentNode.vertex.get(t.charAt(j - 1));
                    if (currentNode == null) {
                        break;
                    }
                    if (currentNode.terminal && dp[j - 1]) {
                        dp[i] = true;
                    }
                }
            }
        }

        return dp[t.length()];
    }


    private static Node buildBorTree(String[] words) {
        Node root = new Node();

        for (String string : words) {
            addString(root, string);
        }

        return root;
    }

    private static void addString(Node root, String string) {
        Node currentNode = root;
        for (int i = string.length() - 1; i >= 0; i--) {
            char symbol = string.charAt(i);
            if (!currentNode.vertex.containsKey(symbol)) {
                Node newNode = new Node();
                currentNode.vertex.put(symbol, newNode);
            }
            currentNode = currentNode.vertex.get(symbol);
        }
        currentNode.terminal = true;
    }

    private static String[] readWords(BufferedReader reader, int wordsNumber) throws IOException {
        String[] result = new String[wordsNumber];

        for (int i = 0; i < wordsNumber; i++) {
            result[i] = reader.readLine();
        }

        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}

class Node {
    Map<Character, Node> vertex;
    boolean terminal;

    public Node() {
        this.vertex = new HashMap<>();
    }
}
