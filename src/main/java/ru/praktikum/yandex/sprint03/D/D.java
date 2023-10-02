package ru.praktikum.yandex.sprint03.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class D {

    public static int feedChildren(List<Integer> childrenHunger, List<Integer> cookiesSize) {
        childrenHunger.sort(Integer::compare);
        cookiesSize.sort(Integer::compare);

        int child = 0;
        int cookie = 0;
        int happyChildren = 0;

        while (child < childrenHunger.size() && cookie < cookiesSize.size()) {
            if (childrenHunger.get(child) <= cookiesSize.get(cookie)) {
                happyChildren++;
                child++;
                cookie++;
            } else cookie++;
        }

        return happyChildren;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int childrenCount = readInt(reader);
            List<Integer> childrenHunger = readList(reader);
            int cookiesCount = readInt(reader);
            List<Integer> cookiesSize = readList(reader);

            int fedChildren = feedChildren(childrenHunger, cookiesSize);
            System.out.println(fedChildren);
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
