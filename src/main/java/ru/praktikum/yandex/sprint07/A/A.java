package ru.praktikum.yandex.sprint07.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int numberOfDays = readNumberOfDays(reader);
            List<Integer> stockPrice = readStockPrice(reader, numberOfDays);

            System.out.println(calcMaxIncome(stockPrice));
        }
    }

    public static int calcMaxIncome(List<Integer> stockPrice) {
        int maxIncome = 0;
        // если цена на следующий день выше, чем в предыдущий, то "продаем"
        for (int i = 1; i < stockPrice.size(); i++) {
            if (stockPrice.get(i - 1) < stockPrice.get(i)) {
                maxIncome += stockPrice.get(i) - stockPrice.get(i - 1);
            }
        }
        return maxIncome;
    }

    public static int readNumberOfDays(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Integer> readStockPrice(BufferedReader reader, int numberOfDays) throws IOException {
        List<Integer> result = new ArrayList<>(numberOfDays);
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        while (tokenizer.hasMoreTokens()) {
            Integer integer = Integer.parseInt(tokenizer.nextToken());
            result.add(integer);
        }
        return result;
    }
}
