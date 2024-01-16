package ru.praktikum.yandex.sprint08.fin.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* --ПРИНЦИП РАБОТЫ--
 * Работа алгоритма состоит из двух этапов:
 * 1. Построение бора
 * 1.1 Для каждого слова
 * 1.1.1 Для каждой буквы слова проверяется, есть ли ребро с такой буквой. Если ребра нет, то оно создается
 * 1.1.2 Если ребро есть, то переходим в него
 * 2. Проверка текста
 * 2.1 Создадим массив dp булевых значений размера text.size() + 1, в нем мы будем отмечать конец найденных слов. Первый
 * элемент пометим как true, обозначив начало строки.
 * 2.2. Далее пойдем по символам текста слева направо
 * 2.2.1 Если из корневой ноды бора есть ребро с текущим символом, значит потенциально мы нашли слово из бора.
 * 2.2.1.1. запустим внутренний цикл от этого символа и достанем ту ноду, к которой ведет ребро.
 * 2.2.1.2. Если ноды нет, значит слова нет подошло, продолжим поиск со следующего символа
 * 2.2.1.3. Если нода есть и она терминальная, а поиск в 2.2.1 мы начали с символа следующего за окончанием предыдущего
 * слова, значит мы нашли новое возможное окончание. Отметим это в dp.
 * 2.3 ответом на исходный вопрос будет последнее значение в массиве dp.
 *
 * --ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 * По условию строка текста T = sk1, sk2...skr, где s1...sn - набор строк, k - индексы строк, которые могут повторяться.
 * Лишних букв или строк в тексте Т нет, следовательно, она должна гарантированно заканчиваться на одно из слов из
 * набора, а начинаться гарантированно вслед за предыдущим словом, либо с первого символа текста.
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Временная сложность состоит из:
 * 1. Построение бора за О(L), где L общее количество символов в наборе строк.
 *
 * 2. Проверка слов в тексте. В худшем случае, когда с каждым следующим символом мы будем запускать внутренний цикл
 * до конца строки мы пройдем по тексту за O(n^2) раз, где n - длинна текста.
 *
 * Итого: O(n^2 + L)
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * Пространственная сложность складывается из:
 * 1. Бора. Для построения бора потребуется О(L) дополнительной памяти, где L общее количество символов в наборе строк.
 *
 * 2. Массива булевых значений dp потребует O(n) дополнительной памяти, где n = количество символов в тексте.
 *
 * Итого: О(L + n)
 *
 */

//Ссылка на посылку: https://contest.yandex.ru/contest/26133/run-report/105045145/

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine();
            int wordsNumber = readInt(reader);
            String[] words = readWords(reader, wordsNumber);

            boolean canSplitText = canSplitText(text, words);

            System.out.println(canSplitText ? "YES" : "NO");
        }
    }

    private static boolean canSplitText(String text, String[] words) {
        Node root = buildTrie(words);

        boolean[] dp = new boolean[text.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= text.length(); i++) {
            if (root.vertex.containsKey(text.charAt(i - 1))) {
                Node currentNode = root;
                for (int j = i; j <= text.length(); j++) {
                    currentNode = currentNode.vertex.get(text.charAt(j - 1));
                    if (currentNode == null) {
                        break;
                    }
                    if (currentNode.terminal && dp[i - 1]) {
                        dp[j] = true;
                    }
                }
            }
        }

        return dp[text.length()];
    }


    private static Node buildTrie(String[] words) {
        Node root = new Node();

        for (String word : words) {
            Node currentNode = root;
            for (int i = 0; i < word.length(); i++) {
                if (!currentNode.vertex.containsKey(word.charAt(i))) {
                    Node newNode = new Node();
                    currentNode.vertex.put(word.charAt(i), newNode);
                }
                currentNode = currentNode.vertex.get(word.charAt(i));
            }
            currentNode.terminal = true;
        }

        return root;
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
    Map<Character, Node> vertex = new HashMap<>();
    boolean terminal;
}
