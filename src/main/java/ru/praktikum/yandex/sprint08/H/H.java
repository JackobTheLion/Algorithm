package ru.praktikum.yandex.sprint08.H;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class H {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String string = reader.readLine();
            String pattern = reader.readLine();
            String replace = reader.readLine();

            System.out.println(replaceWithPattern(string, pattern, replace));
        }
    }

    private static String replaceWithPattern(String string, String pattern, String replace) {
        List<Integer> searchResult = search(pattern, string);

        StringBuilder sb = new StringBuilder();
        int stringPointer = 0;
        int resultPointer = 0;

        while (stringPointer < string.length() && resultPointer < searchResult.size()) {
            if (stringPointer == searchResult.get(resultPointer)) {
                sb.append(replace);
                stringPointer += pattern.length();
                resultPointer++;
            } else {
                sb.append(string.charAt(stringPointer));
                stringPointer++;
            }
        }

        if (stringPointer < string.length()) {
            for (int i = stringPointer; i < string.length(); i++) {
                sb.append(string.charAt(i));
            }
        }

        return sb.toString();
    }

    public static List<Integer> search(String p, String text) {
        // Функция возвращает все позиции вхождения шаблона в тексте.
        List<Integer> result = new ArrayList<>();
        String s = p + "#" + text;
        int[] π = new int[p.length()];  // Массив длины |p|.
        int π_prev = 0;
        for (int i = 1; i < s.length(); i++) {
            int k = π_prev;
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = π[k - 1];
            }
            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            // Запоминаем только первые |p| значений π-функции.
            if (i < p.length()) {
                π[i] = k;
            }
            // Запоминаем последнее значение π-функции.
            π_prev = k;
            // Если значение π-функции равно длине шаблона, то вхождение найдено.
            if (k == p.length()) {
                // i - это позиция конца вхождения шаблона.
                // Дважды отнимаем от него длину шаблона, чтобы получить позицию начала:
                //  - чтобы «переместиться» на начало найденного шаблона,
                //  - чтобы не учитывать добавленное "pattern#".
                result.add(i - 2 * p.length());
            }
        }
        return result;
    }
}
