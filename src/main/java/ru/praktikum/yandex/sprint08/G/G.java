package ru.praktikum.yandex.sprint08.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> measurements = readArr(reader);
            int m = readInt(reader);
            List<Integer> pattern = readArr(reader);

            List<Integer> result = findPattern(measurements, pattern);

            StringBuilder sb = new StringBuilder();
            for (Integer integer : result) {
                sb.append(integer + 1).append(" ");
            }

            System.out.println(sb);
        }
    }

    public static List<Integer> findPattern(List<Integer> measurements, List<Integer> pattern) {
        List<Integer> result = new ArrayList<>();
        if (measurements.size() < pattern.size()) {
            return result;
        }

        for (int i = 0; i <= measurements.size() - pattern.size(); i++) {

            int constant = measurements.get(i) - pattern.get(0);
            boolean match = true;

            for (int j = 0; j < pattern.size(); j++) {
                if (measurements.get(i + j) != pattern.get(j) + constant) {
                    match = false;
                    break;
                }
            }

            if (match) {
                result.add(i);
            }
        }

        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readArr(BufferedReader reader) throws IOException {
        List<Integer> result = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()) {
            Integer integer = Integer.parseInt(tokenizer.nextToken());
            result.add(integer);
        }
        return result;
    }
}
