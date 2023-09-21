package ru.praktikum.yandex.sprint02.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// id посылки: 91052934

/**
 * -- ПРИНЦИП РАБОТЫ --
 * Алгоритм разбивает пришедшую строку на массив строк, каждая из которых является числом или оператором.
 * Стек алгоритма основан на списке, размер которого равен длине массива строк. Это необходимо на тот случай, если на
 * вход придет строка только из чисел.
 * Каждый элемент массива начиная с 0 проверяется:
 * - если он является числом, то кладется в стек
 * - если является знаком '+', то производится сложение последнего и предпоследнего элемента стека, результат кладется в стек
 * - если является знаком '-', то производится вычитание последнего элемента стека из предпоследнего, результат кладется в стек
 * - если является знаком '*', то производится умножение последнего элемента стека на предпоследний, результат кладется в стек
 * - если является знаком '/', то выполняется проверка на то, является ли делимое отрицательным числом:
 * - если является, то умножаем его на -1, а затем производим деление с округлением вверх (идею взял тут: https://tinyurl.com/4mda5ky6).
 * Результат умножаем на -1 и вносим в стек.
 * - если оба числа положительные, то просто делим и добавляем результат в стек.
 * <p>
 * После того как пройдемся по всем элементам, возвращаем последнее значение стека.
 * <p>
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Стек гарантирует, что операции будут выполняться только над необходимыми числами, т.к. операции в нем работают по
 * принципу LIFO.
 * <p>
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * Для выполнения алгоритма мы проходимся по каждому элементу массива один раз, поэтому сложность будет O(n).
 * <p>
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * Алгоритм хранит все значения исходной строки в массиве, а числовые значения и резултат операций в стеке, который в
 * худшем случае будет равен по размеру массиву со значениями строки (если все элементы - числа). Т.о. пространственная
 * сложность O(n)
 */
public class B {

    public static Integer calculate(String expr) {
        String[] values = expr.split(" ");
        List<Integer> numbers = new ArrayList<>(values.length);

        for (String s : values) {
            if (s.equals("+")) {
                int a = numbers.remove(numbers.size() - 1);
                int b = numbers.remove(numbers.size() - 1);
                int result = b + a;
                numbers.add(result);
            } else if (s.equals("-")) {
                int a = numbers.remove(numbers.size() - 1);
                int b = numbers.remove(numbers.size() - 1);
                int result = b - a;
                numbers.add(result);
            } else if (s.equals("*")) {
                int a = numbers.remove(numbers.size() - 1);
                int b = numbers.remove(numbers.size() - 1);
                int result = b * a;
                numbers.add(result);
            } else if (s.equals("/")) {
                int a = numbers.remove(numbers.size() - 1);
                int b = numbers.remove(numbers.size() - 1);
                int result;
                if (b < 0) {
                    b *= -1;
                    result = ((b + a - 1) / a) * -1;
                } else result = b / a;
                numbers.add(result);
            } else {
                numbers.add(Integer.parseInt(s));
            }
        }
        return numbers.remove(numbers.size() - 1);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String expr = reader.readLine();
            Integer result = calculate(expr);
            System.out.println(result);
        }
    }
}
