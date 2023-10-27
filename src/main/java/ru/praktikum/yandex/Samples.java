package ru.praktikum.yandex;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Samples {

    public void readWriteOps() throws IOException {
        List<Integer> arr;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().strip());
            arr = Arrays.asList(reader.readLine().strip().split(" "))
                    .stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i : arr) {
            writer.write(String.valueOf(i));
            writer.write(" ");
        }
        writer.flush();
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static List<Integer> readArrWithTokenizer(BufferedReader reader) throws IOException {
        List<Integer> result = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()) {
            Integer integer = Integer.parseInt(tokenizer.nextToken());
            result.add(integer);
        }
        return result;
    }
}
