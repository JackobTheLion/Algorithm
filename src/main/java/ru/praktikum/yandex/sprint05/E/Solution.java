package ru.praktikum.yandex.sprint05.E;

public class Solution {
    public static boolean treeSolution(Node head) {
        return treeSolutionRec(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean treeSolutionRec(Node head, int min, int max) {
        if (head == null) {
            return true;
        }

        if (head.value <= min || head.value >= max) {
            return false;
        }

        return treeSolutionRec(head.left, min, head.value) && treeSolutionRec(head.right, head.value, max);
    }

    // <template>
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
    // <template>

    private static void test() {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(4, null, null);
        Node node3 = new Node(3, node1, node2);
        Node node4 = new Node(8, null, null);
        Node node5 = new Node(5, node3, node4);
        assert treeSolution(node5);
        node2.value = 5;
        assert !treeSolution(node5);
    }
}
