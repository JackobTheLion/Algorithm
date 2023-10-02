package ru.praktikum.yandex.sprint03.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class G {

    public static List<Integer> countSort(List<Integer> integers) {
        int[] colorsCount = new int[3];
        for (Integer i : integers) {
            colorsCount[i]++;
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < colorsCount.length; i++) {
            for (int j = 0; j < colorsCount[i]; j++) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> integers = countSort(readList(reader));
            StringBuilder sb = new StringBuilder();
            for (Integer i : integers) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line.isEmpty()) {
            return new ArrayList<>();
        } else {
            String[] s = line.split(" ");
            return Arrays.stream(s).map(Integer::parseInt).collect(Collectors.toList());
        }
    }
}
