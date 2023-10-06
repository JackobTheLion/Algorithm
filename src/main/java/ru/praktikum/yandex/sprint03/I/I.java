package ru.praktikum.yandex.sprint03.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class I {

    public static String count(List<Integer> ids, int limit) {
        HashMap<Integer, Integer> idCount = new HashMap<>();

        for (Integer id : ids) {
            idCount.put(id, idCount.getOrDefault(id, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

/*        Stream<Map.Entry<Integer, Integer>> limit1 = idCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Integer::compareTo))
                .limit(limit)
                .forEach();*/


        return null;
    }



    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int students = readInt(reader);
            List<Integer> ids = readList(reader);
            int limit = readInt(reader);
            System.out.println(count(ids, limit));
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
