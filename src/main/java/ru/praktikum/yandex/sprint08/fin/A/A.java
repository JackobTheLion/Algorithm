package ru.praktikum.yandex.sprint08.fin.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

/* --ПРИНЦИП РАБОТЫ--
 * Для того, чтобы найти общий префикс строк, для начала нужно их распаковать.
 * Для распаковки:
 * 1. Создадим стек стригбилдеров, в каждом из которых будем хранить компоненты строки
 * 2. Создадим стек чисел - множителей для строк
 * 3. Создадим билдер, с которого начнем и добавим его в стопку
 * 4. Создадим билдер для множителя
 * 5. Далее идем по строке и смотрим на символы:
 * 5.1. Если символ - буква, добавляем в текущий компонент
 * 5.2. если символ - число, значит текущий компонент закончился и начинается новый. По условию множитель -
 * однозначное натуральное число. При этом натуральным числам в таблице UTF соответствуют числа 48-57, где 48 означает
 * ноль. Следовательно, нужный нам множитель это Символ строки - 48. Пушим его в стопку, создаем новый билдер для
 * компонента и добавим число в билдер множителя. Следующий символ будет '[', пропустим его.
 * 5.3. Если символ ']' значит компонент закончился. Можем собрать его, повторив столько раз, какое число лежит сверху
 * стопки и присоединить к компоненту наверху стопки.
 * 6. В конце мы вытаскиваем все компоненты строки из стопки и собираем их в единую строку.
 *
 * Предположим, что первая строка является наибольшим общим префиксом и присвоим ее значение переменной bestPrefix.
 * Прочтем вторую строку и найдем их наибольший префикс, запишем результат в переменную bestPrefix. Повторим до конца
 * списка строк, каждый раз считывая по одной строке.
 *
 * --ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 * На каждом этапе мы будем улучшать результат, находя общий префикс уже найденного общего префикса и новой строки.
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Временная сложность складывается из двух компонентов:
 * 1. Распаковка строк: для распаковки нужно пройтись по всем символам каждой из запакованных строк. Т.о. Сложность
 * составит О(L), где L общее количество символов запакованных строк.
 *
 * 2. Поиск префикса. Общий префикс не может быть длиннее чем самая короткая строка. В худшем случае строки будут равны
 * между собой и будут максимально запакованы, тогда сложность составит O(2^n), где n - длина запакованной строки.
 *
 * Итого: O(L + 2^n)
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * Пространственная сложность складывается из:
 * 1. Распакованных строк.
 * Запакованная строка выражена n символами. В худшем случае запакованная строка будет иметь 1 буквенный символ, а
 * остальное будет потрачено на 9, '[' и ']'. Итого максимум запаковок M = (n - 1) / 3.
 * При распаковке:
 * 0. На нулевом этапе у нас 1 буквенный символ
 * 1. На первом этапе у нас 9 буквенных символов
 * 2. На втором этапе у нас 81 буквенный символ
 * ...
 * M. на этапе М у нас 9^M буквенных символов
 *
 * Итого получим O(9 ^ ((n-1) / 3) * K) ~ О(2^n * K), где К - количество строк.
 *
 * 2. Длины итогового префикса, которая составит не более чем длина минимальной строки после распаковки, в худшем
 * случае это O(2^n).
 *
 * Итого: О(2^n * K + 2^n) ~ О(2^n * K).
 *
 */

//Ссылка на посылку: https://contest.yandex.ru/contest/26133/run-report/105128182/

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int linesNumber = readInt(reader);
            String bestPrefix = unpackString(reader.readLine());
            for (int i = 1; i < linesNumber; i++) {
                String nextLine = unpackString(reader.readLine());
                bestPrefix = getMaxPrefix(bestPrefix, nextLine);
            }

            System.out.println(bestPrefix);
        }
    }

    private static String getMaxPrefix(String A, String B) {
        StringBuilder maxPrefix = new StringBuilder();
        int minLength = Math.min(A.length(), B.length());
        for (int i = 0; i < minLength; i++) {
            if (A.charAt(i) == B.charAt(i)) {
                maxPrefix.append(A.charAt(i));
            } else {
                break;
            }
        }

        return maxPrefix.toString();
    }

    private static String unpackString(String packedString) {
        StringBuilder result = new StringBuilder();
        Stack<StringBuilder> components = new Stack<>();
        Stack<Integer> digits = new Stack<>();

        StringBuilder component = new StringBuilder();
        components.push(component);
        for (int i = 0; i < packedString.length(); i++) {
            if (isAlphabetic(packedString.charAt(i))) {
                component.append(packedString.charAt(i));
                continue;
            }
            if (isDigit(packedString.charAt(i))) {
                components.push(component);
                component = new StringBuilder();
                digits.push(packedString.charAt(i) - 48);
                i++;
                continue;
            }
            if (packedString.charAt(i) == ']') {
                StringBuilder sb = components.pop();
                sb.append(component.toString().repeat(digits.pop()));
                component = sb;
            }
        }

        while (!components.isEmpty()) {
            result.append(components.pop());
        }

        return result.toString();
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
