package ru.praktikum.yandex.sprint03.L;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L {

    public static Integer getDay(List<Integer> list, int price) {
        if (list.get(list.size() - 1) < price) {
            return -1;
        } else {
            return getDayRec(list, price, 0, list.size() - 1);
        }
    }

    private static Integer getDayRec(List<Integer> list, int price, int left, int right) {
        if (left == right) {
            return left + 1;
        }

        int mid = (left + right) / 2;
        if (price > list.get(mid)) {
            return getDayRec(list, price, mid + 1, right);
        } else {
            return getDayRec(list, price, left, mid);
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> savings = readList(reader);
            int price = readInt(reader);
            StringBuilder sb = new StringBuilder();
            sb.append(getDay(savings, price)).append(" ");
            sb.append(getDay(savings, price * 2));

            System.out.println(sb);
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
