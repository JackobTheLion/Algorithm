package ru.praktikum.yandex.sprint07.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/* --ПРИНЦИП РАБОТЫ--
 * Принцип решения сводится к тому, чтобы привести исходную задачу к вариации задачи о рюкзаке.
 * Пусть массив можно разделить на две части таким образом, что сумма первой части равна сумме второй. Предположим что
 * сумма всех элементов массива равна S. Тогда сумма одной половины будет равна 1/2 S. Следовательно необходимо
 * определить, возможно ли из каких то элементов массива получить сумму 1/2 S и если да, то оставшиеся тоже сложатся в
 * сумму 1/2 S.
 *
 * 1. Чтобы избежать лишнего прохода по массиву, сразу при считывании данных будем считать сумму всех элементов и
 * запишем ее в ячейку 0 массива.
 * 2. Если сумма всех элементов не делится на 2 без остатка, значит массив разделить нельзя, возвращаем false.
 * 3. Иначе считаем, что ожидаемая сумма каждой из частей равна 1/2 S.
 * 4. Заведем массив dp булевых значений размера 1/2 S, в котором будем ходить промежуточные значения. В массиве мы
 * будем хранить ответ на вопрос "может ли из суммы n чисел исходного массива получиться число j?", где j индекс ячейки
 * dp.
 * 5.1. Для каждого числа i исходного массива начиная с 1
 * 5.1.2 Для каждой ячейки массива dp начиная с конца и до i
 * 5.1.2.1 Проверим, может ли получится число j. Это возможно в двух случаях: если i = j, либо если есть
 * некое число x, которое мы знаем, что можем получить (т.к. мы уже проверили его на предыдущем круге цикла) и для
 * которого выполняется равенство x = i - j
 * 6. Результат алгоритма будет находиться в последней ячейке массива dp.
 *
 * --ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 * 1/2 S = I + J
 * Где 1/2 S - ожидаемая сумма, I - число исходного массива, а J - некое число, которое мы убедились, что можем
 * получить из n чисел исходного массива.
 * В свою очередь J = j + i, и так далее, пока i = j.
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Временная сложность алгоритма составляет О(n * 1/2 S) ~ O(n * S), где n - количество элементов, а S их сумма.
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * В алгоритме используется переменная для ожидаемой суммы, которая требует О(1), результирующий массив О(1/2 S).
 *
 * Т.о. сложность составит: О(1 + 1/2 S) ~ О(S).
 *
 */

// Ссылка на посылку: https://contest.yandex.ru/contest/25597/run-report/103848150/

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int gamesNumber = readGamesNumber(reader);
            int[] score = readScore(reader, gamesNumber);

            String result;
            if (isCanSplit(score)) {
                result = "True";
            } else {
                result = "False";
            }

            System.out.println(result);
        }
    }

    private static boolean isCanSplit(int[] score) {
        int expectedSum = score[0];
        if (expectedSum % 2 != 0) {
            return false;
        }
        expectedSum /= 2;

        boolean[] dp = new boolean[expectedSum + 1];

        for (int i = 1; i < score.length; i++) {
            for (int j = expectedSum; j >= score[i]; j--) {
                if (dp[j - score[i]] || j == score[i]) {
                    dp[j] = true;
                }
            }
        }

        return dp[expectedSum];
    }

    private static int readGamesNumber(BufferedReader reader) throws IOException {
        return parseInt(reader.readLine());
    }

    private static int[] readScore(BufferedReader reader, int gamesNumber) throws IOException {
        int[] result = new int[gamesNumber + 1];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i < gamesNumber + 1; i++) {
            int score = parseInt(tokenizer.nextToken());
            result[0] += score;
            result[i] = score;
        }

        return result;
    }
}
