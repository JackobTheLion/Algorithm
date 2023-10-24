package ru.praktikum.yandex.sprint04.B;

public class B {

    public static void stringGenerator() {

    }

    public static long hash(String s) {
        if (s.isBlank()) return 0;

        int a = 1000;
        int m = 123987123;

        long hash = (s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            hash = hash * a % m + s.charAt(i);
        }

        return hash % m;
    }

    public static void main(String[] args) {
        stringGenerator();
    }
}
