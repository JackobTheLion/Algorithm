package ru.praktikum.yandex.sprint04.I;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class I {

    public static int getMaxSubArrLength(List<Integer> arr1, List<Integer> arr2) {
        int maxLength = 0;
        int temp = 0;

        for (int i = 0; i < arr1.size(); i++) {
            for (int j = 0; j < arr2.size(); j++) {
                if (i >= arr1.size()) break;
                if (!arr1.get(i).equals(arr2.get(j))) {
                    temp = 0;
                    break;
                }
                temp++;
                i++;
                if (temp > maxLength) {
                    maxLength = temp;
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<Integer> arr1 = readArr(reader);
            int m = readInt(reader);
            List<Integer> arr2 = readArr(reader);

            System.out.println(getMaxSubArrLength(arr1, arr2));
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
