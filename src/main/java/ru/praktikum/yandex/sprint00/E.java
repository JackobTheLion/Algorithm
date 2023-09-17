package ru.praktikum.yandex.sprint00;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class E {

    // Если ответ существует, верните список из двух элементов
    // Если нет - то верните пустой список
/*    private static List<Integer> twoSum(List<Integer> arr, int targetSum) {
        List<Integer> result = new ArrayList<>();

        int left = 0;
        int right = arr.size() - 1;

        while (left < right) {
            int a = arr.get(left);
            int b = arr.get(right);
            if (a + b == targetSum) {
                result.add(a);
                result.add(b);
                return result;
            } else if (a + b > targetSum) {
                right--;
            } else if (a + b < targetSum) {
                left++;
            }
        }
        return result;
    }*/

    private static List<Integer> twoSum(List<Integer> arr, int targetSum) {
        List<Integer> result = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.size(); i++) {
            int y = targetSum - arr.get(i);
            if (set.contains(y)) {
                result.add(arr.get(i));
                result.add(y);
                return result;
            } else set.add(arr.get(i));
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> arr = readList(reader);
            int targetSum = readInt(reader);
            List<Integer> result = twoSum(arr, targetSum);
            if (result.isEmpty()) {
                System.out.println("None");
            } else {
                System.out.println(result.get(0) + " " + result.get(1));
            }
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().split(" "))
                .stream()
                .map(elem -> Integer.parseInt(elem))
                .collect(Collectors.toList());
    }

}
