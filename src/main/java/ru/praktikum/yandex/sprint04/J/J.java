package ru.praktikum.yandex.sprint04.J;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;

public class J {

    public static Set<Four> findFours(List<Integer> array, Integer s) {
        int length = array.size();
        array.sort(Integer::compare);

        System.out.println(array);

        Map<Integer, Set<Pair>> history = new HashMap<>();
        Set<Four> fours = new HashSet<>();

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {

                int target = s - array.get(i) - array.get(j);

                if (history.containsKey(target)) {
                    Set<Pair> saved = history.get(target);
                    for (Pair pair : saved) {
                        Four four = new Four(array.get(pair.getFirst()), array.get(pair.getSecond()), array.get(i), array.get(j));

                        fours.add(four);
                    }
                }

                int sum = array.get(i) + array.get(j);
                Set<Pair> pairs = history.getOrDefault(sum, new HashSet<>());
                pairs.add(new Pair(i, j));
                history.put(sum, pairs);
            }
        }
        System.out.println("__________________________________________________________");
        for(Map.Entry<Integer, Set<Pair>> entry : history.entrySet()){
            System.out.println(entry.getKey());
            for (Pair pair : entry.getValue()) {
                System.out.println(pair);
            }
            System.out.println("---------------------------");

        }
        System.out.println("__________________________________________________________");
        return fours;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            int s = readInt(reader);
            List<Integer> arr = readArr(reader);

            Set<Four> fours = findFours(arr, s);

            for (Four four : fours) {
                System.out.println(four.toString());
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readArr(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }
}

class Four {
    private final int[] arr = new int[4];

    public Four(Integer first, Integer second, Integer third, Integer fourth) {
        arr[0] = first;
        arr[1] = second;
        arr[2] = third;
        arr[3] = fourth;
        Arrays.sort(arr);
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

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
