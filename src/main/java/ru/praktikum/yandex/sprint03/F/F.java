package ru.praktikum.yandex.sprint03.F;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class F {

    public static Integer getPerimeter(List<Integer> sections) {
        int result = 0;
        sections.sort(Integer::compareTo);

        for (int i = sections.size() - 1; i >= 2; i--) {
            if (sections.get(i) < sections.get(i - 1) + sections.get(i - 2)) {
                result = sections.get(i) + sections.get(i - 1) + sections.get(i - 2);
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> sections = readList(reader);
            System.out.println(getPerimeter(sections));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
