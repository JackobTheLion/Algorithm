package ru.praktikum.yandex.sprint02.B;

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
    public static void solution(Node<String> head) {
        StringBuilder sb = new StringBuilder();
        Node<String> node = head;
        while (node.next != null) {
            sb.append(node.value).append("\n");
            node = node.next;
        }
        sb.append(node.value);
        System.out.println(sb);
    }

    public static void test() {
        Node<String> node3 = new Node<>("node3", null);
        Node<String> node2 = new Node<>("node2", node3);
        Node<String> node1 = new Node<>("node1", node2);
        Node<String> node0 = new Node<>("node0", node1);
        solution(node0);
        /*
        Output is:
        node0
        node1
        node2
        node3
        */
    }
}