package ru.praktikum.yandex.sprint05.fin;

// <template>
class Node {
    private int value;
    private Node left;
    private Node right;

    Node(Node left, Node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
// <template>

public class Solution {
    public static Node remove(Node root, int key) {
        if (root == null) {
            return null;
        }

        Node deleteParent = null;
        Node delete = root;

        while (delete.getValue() != key) {
            deleteParent = delete;

            if (delete.getValue() < key) {
                delete = delete.getRight();
            } else {
                delete = delete.getLeft();
            }

            if (delete == null) {
                return root;
            }
        }

        if (delete.getLeft() == null && delete.getRight() == null) {
            if (!(delete == root)) {
                if (deleteParent.getLeft() == delete) {
                    deleteParent.setLeft(null);
                } else {
                    deleteParent.setRight(null);
                }
            } else {
                root = null;
            }
        } else if (delete.getLeft() != null && delete.getRight() != null) { //если удаляемая нода имеет два потомка
            Node successor = getSuccessor(delete.getLeft()); //ищем ее преемника
            int value = successor.getValue();
            remove(delete, value);
            delete.setValue(value);
        } else {
            if (delete.getLeft() != null) {
                if (delete == root) {
                    root = delete.getLeft();
                } else if (deleteParent.getLeft() == delete) {
                    deleteParent.setLeft(delete.getLeft());
                } else deleteParent.setRight(delete.getLeft());
            } else {
                if (delete == root) {
                    root = delete.getRight();
                } else if (deleteParent.getLeft() == delete) {
                    deleteParent.setLeft(delete.getRight());
                } else deleteParent.setRight(delete.getRight());
            }
        }
        return root;
    }

    private static Node getSuccessor(Node root) {
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root;
    }

    public static void main(String[] args) {
        test3();
    }

    private static void test() {
        Node node1 = new Node(null, null, 2);
        Node node2 = new Node(node1, null, 3);
        Node node3 = new Node(null, node2, 1);
        Node node4 = new Node(null, null, 6);
        Node node5 = new Node(node4, null, 8);
        Node node6 = new Node(node5, null, 10);
        Node node7 = new Node(node3, node6, 5);
        Node newHead = remove(node7, 10);
        System.out.println("done");
        assert newHead.getValue() == 5;
        assert newHead.getRight() == node5;
        assert newHead.getRight().getValue() == 8;
    }

    private static void test2() {
        Node node1 = new Node(null, null, 32);
        Node node2 = new Node(null, node1, 29);
        Node node3 = new Node(null, null, 11);
        Node node4 = new Node(node3, node2, 20);
        Node node5 = new Node(null, null, 72);
        Node node6 = new Node(null, null, 99);
        Node node7 = new Node(node5, node6, 91);
        Node node8 = new Node(null, null, 50);
        Node node9 = new Node(node8, node7, 65);
        Node node10 = new Node(node4, node9, 41);
        Node newHead = remove(node10, 41);
        System.out.println("done");
    }

    private static void test3() {
        Node node1 = new Node(null, null, 15);
        Node node2 = new Node(node1, null, 20);
        Node node3 = new Node(null, node2, 10);

        Node newHead = remove(node3, 10);
        System.out.println("done");
    }

    private static void test4() {
        Node node1 = new Node(null, null, 7);
        Node node2 = new Node(null, null, 5);
        Node node3 = new Node(null, null, 3);
        Node node4 = new Node(null, null, 1);
        Node node5 = new Node(node2, node1, 6);
        Node node6 = new Node(node4, node3, 2);
        Node node7 = new Node(node6, node5, 4);

        Node newHead = remove(node7, 6);
        System.out.println("done");
    }
}
