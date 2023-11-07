package ru.praktikum.yandex.sprint05.A;

public class Solution {
    public static int treeSolution(Node head) {
        return treeSolutionRecursive(head, head.value);
    }

    private static int treeSolutionRecursive(Node node, int max) {
        if (node.value > max) {
            max = node.value;
        }
        int leftMax = -1;
        if (node.left != null) {
            leftMax = treeSolutionRecursive(node.left, max);
        }
        int rigthMax = -1;
        if (node.right != null) {
            rigthMax = treeSolutionRecursive(node.right, max);
        }
        if (leftMax > max) {
            max = leftMax;
        }
        if (rigthMax > max) {
            max = rigthMax;
        }
        return max;
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

    private static void test() {
        Node node1 = new Node(1);
        Node node2 = new Node(-5);
        Node node3 = new Node(3);
        node3.left = node1;
        node3.right = node2;
        Node node4 = new Node(2);
        node4.left = node3;
        assert treeSolution(node4) == 3;
    }
}
