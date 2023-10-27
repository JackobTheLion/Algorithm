package ru.praktikum.yandex.sprint04.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class J {

    public static Set<Four> findFours(List<Integer> array, Integer s) {
        array.sort(Integer::compare);

        Map<Integer, Set<Pair>> history = new HashMap<>();
        Set<Four> fours = new HashSet<>();

        for (int i = 0; i < array.size(); i++) {
            for (int j = i + 1; j < array.size(); j++) {

                int target = s - array.get(i) - array.get(j);

                if (history.containsKey(target)) {
                    Set<Pair> saved = history.get(target);
                    for (Pair pair : saved) {
                        if (pair.getFirst().equals(i) || pair.getSecond().equals(i)
                                || pair.getFirst().equals(j) || pair.getSecond().equals(j)) {
                            continue;
                        }
                        int[] arr = {array.get(pair.getFirst()), array.get(pair.getSecond()), array.get(i), array.get(j)};
                        Arrays.sort(arr);
                        Four four = new Four(arr);
                        fours.add(four);
                    }
                }

                int sum = array.get(i) + array.get(j);
                Set<Pair> pairs = history.getOrDefault(sum, new HashSet<>());
                pairs.add(new Pair(i, j));
                history.put(sum, pairs);
            }
        }
        return fours;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);

            if (n == 0) {
                System.out.println(0);
                return;
            }

            int s = readInt(reader);
            List<Integer> arr = readArr(reader);

            Set<Four> fours = findFours(arr, s);
            System.out.println(fours.size());

            for (Four four : fours) {
                System.out.println(four);
            }
        }
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

class Four {
    private final int[] arr;

    public Four(int[] arr) {
        this.arr = arr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Four fours = (Four) o;

        return Arrays.equals(arr, fours.arr);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arr);
    }

    @Override
    public String toString() {
        return arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3];
    }
}

class Pair {
    private final Integer first;
    private final Integer second;

    public Pair(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public Integer getFirst() {
        return first;
    }

    public Integer getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (!Objects.equals(first, pair.first)) return false;
        return Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}
