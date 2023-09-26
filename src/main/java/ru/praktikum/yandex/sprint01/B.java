package ru.praktikum.yandex.sprint01;

import java.util.Scanner;

import static java.lang.Math.abs;

public class B {

    private static boolean checkParity(int a, int b, int c) {
        int y = abs(a % 2) + abs(b % 2) + abs(c % 2);
        return y == 0 || y == 3;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if (checkParity(a, b, c)) {
            System.out.println("WIN");
        } else {
            System.out.println("FAIL");
        }
        scanner.close();
    }
}
