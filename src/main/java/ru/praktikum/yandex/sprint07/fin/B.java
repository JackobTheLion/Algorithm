package ru.praktikum.yandex.sprint07.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

/* --ПРИНЦИП РАБОТЫ--
 * Принцип решения сводится к тому, чтобы привести исходную задачу к задаче о рюкзаке.
 * Пусть массив можно разделить на две части таким образом, что сумма первой части равна сумме второй. Предположим что
 * сумма всех элементов массива равна S. Тогда сумма одной половины будет равна 1/2 S. Следовательно необходимо
 * определить, возможно ли из каких то элементов массива получить сумму 1/2 S и если да, то оставшиеся тоже сложатся в
 * сумму 1/2 S.
 *
 * 1. Чтобы избежать лишнего прохода по массиву, сразу при считывании данных будем считать сумму всех элементов и
 * запишем ее в ячейку 0 массива.
 * 2. Если сумма всех элементов не делится на 2 без остатка, значит массив разделить нельзя, возвращаем false.
 * 3. Иначе считаем, что ожидаемая сумма каждой из частей равна 1/2 S.
 * 4. Заведем массив размера 1/2 S, в котором будем хранить сумму элементов.
 * 5. Для каждого из чисел исходного массива
 * 5.2 Создаем временный массив, скопировав результирующий, в котором будем считать промежуточный результат в целях
 * оптимизации по памяти.
 * 5.2.1 Для каждой ячейки массива для подсчета суммы
 * 5.2.1.1. Если число k "помещается" в ячейку j (k <= j), то добавим в ячейку j максимальное значение, выбрав
 * между: текущего значения в ячейке j массива dp (рассчитанное на предыдущих шагах), либо сумма из числа k и числа
 * из ячейки j - k.
 * 5.3 Присвоим ссылку временного массива результирующему и повторим цикл.
 * 6. В конце цикла нам нужно сравнить число в конце результирующего массива с ожидаемой суммой. Это и будет ответ на
 * задачу.
 *
 * --ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 * На каждом этапе мы выбираем максимальную сумму из возможных чисел, пытаясь заполнить массив-рюкзак размера 1/2 S.
 * Если нам это удается, то оставшиеся числа гарантированно будут равны 1/2 S, т.е. сумма двух подмассивов равна.
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Временная сложность алгоритма составляет О(n * 1/2 S), где n - количество элементов, а S их сумма.
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * В алгоритме используется переменная для ожидаемой суммы, которая требует О(1), результирующий массив О(1/2 S) и
 * временный массив О(1/2 S).
 *
 * Т.о. сложность составит: О(1 + 1/2 S + 1/2 S) ~ О(S).
 *
 */

// Ссылка на посылку: https://contest.yandex.ru/contest/25597/run-report/103648174/

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int gamesNumber = readGamesNumber(reader);
            List<Integer> score = readScore(reader, gamesNumber);

            String result;
            if (isCanSplit(score)) {
                result = "True";
            } else {
                result = "False";
            }

            System.out.println(result);
        }
    }

    private static boolean isCanSplit(List<Integer> score) {
        int expectedSum = score.get(0);
        if (expectedSum % 2 != 0) {
            return false;
        }
        expectedSum /= 2;

        int[] dp = new int[expectedSum + 1];
        for (int i = 1; i < score.size(); i++) {
            int[] temp = Arrays.copyOf(dp, dp.length);
            for (int j = 0; j <= expectedSum; j++) {
                if (j - score.get(i) >= 0) {
                    temp[j] = Math.max(dp[j], score.get(i) + dp[j - score.get(i)]);
                }
            }
            dp = temp;
        }

        return dp[expectedSum] == expectedSum;
    }

    private static int readGamesNumber(BufferedReader reader) throws IOException {
        return parseInt(reader.readLine());
    }

    private static List<Integer> readScore(BufferedReader reader, int gamesNumber) throws IOException {
        List<Integer> result = new ArrayList<>(gamesNumber + 1);
        result.add(0);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i < gamesNumber + 1; i++) {
            int score = parseInt(tokenizer.nextToken());
            result.add(score);
            result.set(0, result.get(0) + score);
        }

        return result;
    }
}
