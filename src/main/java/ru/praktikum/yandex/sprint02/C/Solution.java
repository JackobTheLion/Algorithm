//package ru.praktikum.yandex.sprint02.C;

// <template>
class Node<V> {
    public V value;
    public Node<V> next;

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}
// <template>

public class Solution {
    public static Node<String> solution(Node<String> head, int idx) {
        if (idx == 0) {
            return head.next;
        }

        Node<String> nodeBeforeDeleted = head;
        for (int i = 1; i < idx; i++) {
            nodeBeforeDeleted = nodeBeforeDeleted.next;
        }

        if (nodeBeforeDeleted.next.next != null) {
            nodeBeforeDeleted.next = nodeBeforeDeleted.next.next;
        } else nodeBeforeDeleted.next = null;

        return head;
    }

    public static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        Node<String> newHead = solution(node0, 1);
        assert newHead == node0;
        assert newHead.next == node2;
        assert newHead.next.next == node3;
        assert newHead.next.next.next == null;
        // result is : node0 -> node2 -> node3
    }
}