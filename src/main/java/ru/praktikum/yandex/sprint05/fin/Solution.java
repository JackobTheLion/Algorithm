package ru.praktikum.yandex.sprint05.fin;

/* --ПРИНЦИП РАБОТЫ--
 * Алгоритм работает в несколько этапов
 * Для начала заведем две переменные:
 * - deleteParent предполагаемый родитель удаляемого элемента, изначально null
 * - delete - элемент, который будем проверять на первом этапе (и удалять на втором). Изначально пришедший на вход
 * элемент, от которого будем искать.
 *
 * I этап: поиск удаляемого элемента
 * В цикле будем проверять ключ элемента delete, и если его значение не равно удаляемому ключу то будем искать в его
 * детях, а для этого
 * 1. присваиваем элементу deleteParent значение delete
 * 2. Определим, в какую и ветвей необходимо двигаться:
 * 2.1 Если ключ элемента delete меньше искомого, значит идем в правую ветвь и присваиваем элементу delete его значение
 * 2.2 иначе в левую
 * 3. Если окажется, что элемент delete равен null, значит такого элемента нет в дереве.
 *
 * II этап: удаление элемента и обновление ссылок
 * Если удаляемый элемент существует, то дальнейшая работа алгоритма зависит от того, сколько у элемента потомков.
 * Варианты:
 * - нет потомков (элемент - лист)
 * - один потомок (правый либо левый)
 * - два потомка
 *
 * Необходимо рассмотреть каждый из вариантов:
 * 1. Нет потомков
 * Если потомков нет и удаляемый элемент не является единственным, то необходимо в родительском элементе присвоить
 * соответствующей ветви значение null.
 * Если удаляемый элемент - единственный, то просто вернем null.
 *
 * 2. Два потомка
 * Если потомка два, то заменим значение удаляемого элемента на значение самого правого элемента из левой подветви.
 * Для этого:
 * а. Найдем преемника. Преемником будем считать самый правый элемент левой ветви, т.к. он позволит сохранить свойство
 * бинарного дерева, где все левые потомки не более корня, а все правые потомки не менее корня.
 * б. Обновим значение
 * в. Рекурсивно удалим элемент преемник, попадая в этом случае в сценарий с одним потомком, либо без потомков на
 * этапе II.
 *
 * 3. Один потомок
 * Если потомок один, то дальнейшая работа зависит от того, является ли потомок левым или правым
 * 3.1 Если потомок левый
 * 3.1.1 Если удаляемый узел - корень, возвращаем ссылку на его левого потомка
 * 3.1.2 Если удаляемый узел - левый потомок, заменяем в родителе ссылку на левого потомка, ссылкой на левого потомка
 * удаляемого узла
 * 3.1.3 Если удаляемый узел - правый потомок, заменяем в родителе ссылку на правого потомка, ссылкой на левого потомка
 * удаляемого узла
 *
 * 3.2 Если потомок правый, то действуем зеркально
 * 3.2.1 Если удаляемый узел - корень, возвращаем ссылку на его правого потомка
 * 3.1.2 Если удаляемый узел - левый потомок, заменяем в родителе ссылку на левого потомка, ссылкой на правого потомка
 * удаляемого узла
 * 3.1.3 Если удаляемый узел - правый потомок, заменяем в родителе ссылку на правого потомка, ссылкой на правого потомка
 * удаляемого узла
 *
 * --ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 * Ключом для корректности алгоритма удаления является восстановление алгоритмом свойств бинарного дерева:
 * - у каждого элемента не более двух потомков
 * - все левые потомки не более корня
 * - все правые потомки не менее корня
 *
 * Изменение ссылок на потомков происходит только на втором этапе. Рассмотрим его.
 * 1. Нет потомков
 * В данном случае ссылка в родительском элементе меняется на null. Это не нарушает свойств дерева.
 *
 * 2. Два потомка
 * Предположим, что:
 * d - удаляемый элемент
 * d.l - левый потомок удаляемого элемента
 * d.r - правый потомок удаляемого элемента
 * d.l.r...r - самый правый элемент левой ветви удаляемого элемента
 *
 * В дереве выполняется следующее условие d.l < d < d.r
 * Все левые потомки d.l будут меньше, а правые больше чем d.l
 * Отсюда можно сделать вывод, что d.l < d.l.r...r < d < d.r
 *
 * Таким образом, заменяя значение d на d.l.r...r корректность дерева не нарушается: d.l < d.l.r...r < d.r
 *
 * 3. Один потомок
 * Если удаляемый элемент - корень дерева, то меняется только корень (новым корнем становится единственный потомок),
 * следовательно, корректность дерева не нарушается.
 *
 * Если удаляемый элемент не корень дерева, то:
 * d - удаляемый элемент
 * p - родитель удаляемого элемента
 * NODE.l - левый потомок элемента
 * NODE.r - правый потомок элемента
 *
 * Рассмотрим варианты:
 * а. У удаляемого элемента есть только левый потомок:
 * - Предположим, что d = p.l, тогда:
 * d.l < d < p
 *
 * - Предположим, что d = p.r, тогда:
 * p < d < d.l
 *
 * Следовательно, удалив элемент, мы можем поставить на его место d.l
 *
 * б. У удаляемого узла есть только правый потомок:
 * - Предположим, что d = p.l, тогда:
 * d < d.p < p, т.к. по условию все левые элементы не более корня.
 *
 * - Предположим, что d = p.r, тогда:
 * p < d.r < d, т.к. по условию все правые элементы не менее корня.
 *
 * Следовательно, удалив элемент, мы можем поставить на его место d.p
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Временная сложность алгоритма складывается из временных сложностей каждого из этапов:
 * Временная сложность I этапа в худшем случае составит О(h) (где h - высота дерева), если удаляемый элемент является
 * листом.
 *
 * Временная сложность II этапа зависит от количества потомков у удаляемого элемента: если потомков менее двух, то
 * сложность О(1).
 * Если потомков два, то необходимо запустить удаление рекурсивно. Это будет являться худшим случаем. В рекурсивном
 * запуске мы снова будем искать удаляемый элемент за О(h), однако второй раз в рекурсию мы не попадем, т.к. у
 * рекурсивно удаляемого элемента будет менее двух потомков.
 *
 * Таким образом сложность алгоритма в худшем случае составит О(h + h) = O(2h) = O(h).
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * Пространственная сложность складывается из следующего:
 * - переменные для родительского узла и удаляемого узла составляют О(1).
 * - глубина рекурсии: т.к. рекурсивно метод будет вызываться не более 1 раза, то О(1).
 *
 * Итого пространственная сложность составит О(1).
 */

// Ссылка на посылку: https://contest.yandex.ru/contest/24810/run-report/98522468/

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
        } else if (delete.getLeft() != null && delete.getRight() != null) {
            Node successor = getSuccessor(delete.getLeft());
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
}
