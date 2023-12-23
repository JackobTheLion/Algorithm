package ru.praktikum.yandex.sprint07.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* --ПРИНЦИП РАБОТЫ--
 * Алгоритм работает по методу динамического программирования.
 * В массиве dp мы будем хранить расстояние Левенштейна для подстрок.
 * Базовым случаем будет первая строка (и столбец) результирующего массива, где значение в ячейке будет равно номеру
 * последнего символа подстроки.
 * Переход динамики - расстояние Левенштейна для последних символов строк.
 * Будем вычислять данные в массиве dp для каждой из подстрок.
 * Ответ на задачу будет находиться в конце массива, после вычисления расстояния для последних символов.
 *
 * --ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 * Если мы хотим перевести одну строку в другую, то нам помогут следующие операции:
 * - удаление символа
 * - вставка символа
 * - замена символа
 *
 * При этом число операций для строк длины 0 равно 0 (пустая строка равна сама себе).
 * Из строки размера i можно сделать пустую строку размера 0 за i операций и наоборот.
 * Таким образом для двух строк S1 и S2:
 * - если S1(i) == S2(j), то tempDp(j) = dp(j-1)
 * - если S1(i) != S2(j), то tempDp(j) = min(dp(j), temp(j-1), dp(j-1)) + 1;
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Временная сложность алгоритма составляет О(n * m), где т - длина первой строки, а m - длина второй строки.
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * Пространственная сложность определяется длиной строки, под которую мы создаем массив dp и составляет O(m), где
 * m - длина второй строки.
 *
 */


// Ссылка на посылку: https://contest.yandex.ru/contest/25597/run-report/103609035/

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String a = reader.readLine();
            String b = reader.readLine();

            System.out.println(getLevenshteinDistance(a, b));
        }
    }

    private static int getLevenshteinDistance(String a, String b) {
        int[] dp = new int[b.length() + 1];

        for (int i = 1; i <= b.length(); i++) {
            dp[i] = i;
        }

        for (int i = 1; i <= a.length(); i++) {
            int[] tempDp = new int[dp.length];
            tempDp[0] = i;

            for (int j = 1; j <= b.length(); j++) {
                int replaceCost = dp[j - 1];
                int addCost = tempDp[j - 1] + 1;
                int deleteCost = dp[j] + 1;

                if (a.charAt(i - 1) != b.charAt(j - 1)) {
                    replaceCost += 1;
                }

                tempDp[j] = Math.min(Math.min(replaceCost, addCost), deleteCost);
            }
            dp = tempDp;
        }

        return dp[b.length()];
    }
}
