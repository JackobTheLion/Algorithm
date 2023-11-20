package ru.praktikum.yandex.sprint05.B;

public class Solution {
    public static boolean treeSolution(Node head) {
        if (head == null) return true;

        int leftHeight = getHeight(head.left);
        int rightHeight = getHeight(head.right);

        int heightDiff = Math.abs(leftHeight - rightHeight);

        return heightDiff <= 1 && treeSolution(head.left) && treeSolution(head.right);
    }

    private static int getHeight(Node head) {
        if (head == null) return 0;

        return Math.max(getHeight(head.left), getHeight(head.right)) + 1;
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
    }

    // <template>

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(10);
        Node node5 = new Node(2);
        node5.left = node3;
        node5.right = node4;
        boolean result = treeSolution(node5);
        System.out.println(result);
    }
}
