package ru.praktikum.yandex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {
        System.out.println(getLeastPrimesLinear(15));
    }

    public static boolean eratosthenes(int n) {
        int[] array = new int[n];
        System.out.println(Arrays.toString(array));
        return false;
    }

    public static Pair<List<Integer>, int[]> getLeastPrimesLinear(int n) {
        int[] lp = new int[n + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (lp[i] == 0) {
                lp[i] = i;
                primes.add(i);
            }
            for (int j = 0; j < primes.size(); j++) {
                int p = primes.get(j);
                int x = p * i;
                if (p > lp[i] || x > n) {
                    break;
                }
                lp[x] = p;
            }
        }
        return new Pair<>(primes, lp);
    }
}

class Pair<T, V> {
    T key;
    V value;

    public Pair(T key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
