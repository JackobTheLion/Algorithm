package ru.praktikum.yandex.sprint04.G;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class G {

    public static Integer tieSequence(List<Integer> results) {
        Map<Integer, Integer> firstMet = new HashMap<>();

        int sumFromStart = 0;
        int maxTie = 0;
        firstMet.put(maxTie, -1);

        for (int i = 0; i < results.size(); i++) {
            sumFromStart += results.get(i);
            if (!firstMet.containsKey(sumFromStart)) {
                firstMet.put(sumFromStart, i);
            }
            if (maxTie < i - firstMet.get(sumFromStart)) {
                maxTie = i - firstMet.get(sumFromStart);
            }
        }

        return maxTie;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int rounds = readInt(reader);
            List<Integer> results = readList(reader, rounds);

            System.out.println(tieSequence(results));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static List<Integer> readList(BufferedReader reader, Integer rounds) throws IOException {
        List<Integer> result = new ArrayList<>(rounds);
        String[] line = reader.readLine().split(" ");
        for (String s : line) {
            if (s.equals("0")) {
                result.add(-1);
            } else result.add(1);
        }
        return result;
    }
}
