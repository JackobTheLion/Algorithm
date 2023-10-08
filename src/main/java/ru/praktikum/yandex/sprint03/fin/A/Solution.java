package ru.praktikum.yandex.sprint03.fin.A;

/*
 * --ПРИНЦИП РАБОТЫ--
 * По условию задачи, находить число за О(log n).
 * Попробуем добиться такой асимптотики с помощью бинарного поиска, т.е. на каждом этапе будем отсекать половину
 * значений, а затем запускать поиск на оставшейся части.
 * Алгоритм проверяет, в какой части массива (левой или правой) относительно центра находится элемент и является ли эта
 * часть отсортированной. Если часть отсортирована, то запускаем простой бинарный поиск, если нет, то рекурсивно
 * запускаем поиск на нужной части.
 *
 * Логика следующая:
 * 1. находим средний элемент
 * 2. проверяем, является ли он искомым, если да, поиск закончен.
 * 3. Если нет пробуем определить в какой части массива (левой или правой будет искомое число)
 * 3.1 Искомое меньше центра:
 * 3.1.1 Искомое больше или равно левой границы
 * 3.1.1.1 Искомое Больше или равно правой границе
 * 3.1.1.1.1 Если центр больше левой границы - число в левой отсортированной части, запускаем бинарный поиск
 * 3.1.1.1.2 Если не больше, левая часть сломана и число в ней, запускаем рекурсию
 * 3.1.1.2 Искомое меньше правой границы - левая часть отсортирована, число в ней запускаем на ней бинарный поиск
 * 3.1.2 Искомое меньше левой границы
 * 3.1.2.1 Центральное значение меньше правой границы, левая часть не отсортирована, число в ней, рекурсия
 * 3.1.2.2 Иначе число в правой сломанной части, запускаем рекурсию
 * 3.2 Искомое больше или равно центру
 * 3.2.1 Искомое больше или равно левой границе и правой границе
 * 3.2.1.1 Центр больше левой границы, значит правая часть массива сломана, но число в ней, запускаем рекурсию
 * 3.2.1.2 Центр меньше правой границы, число в левой части, которая сломана, запускаем рекурсию
 * 3.2.1.3 Иначе левая часть отсортирована и число в ней, запускаем бинарный поиск
 * 3.2.2 Искомое меньше левой и правой границы, значит число в правой отсортированной части
 *
 * Таким образом мы перебрали все возможные варианты нахождения искомого числа. См. схему:
 * https://imgur.com/a/vDcyuKz
 *
 * --ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 * На каждом уровне рекурсии диапазон поиска сужается в два раза.
 * В конечном счете суть алгоритма сводится к поиску такого подмассива, который удовлетворял бы двум условиям:
 * 1. Подмассив отсортирован
 * 2. Искомое число в диапазоне подмассива
 * а затем применение к нему простого бинарного поиска, который либо вернет индекс значения, либо вернет -1, если
 * значения нет.
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Алгоритм основан на бинарном поиске, т.о. его временная сложность О(log(n))
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * В алгоритме используется три дополнительных переменных - указателя на левый, правый и центральный элемент, что
 * дает константную сложность О(1).
 * Если учесть рекурсию, то сложность будет равна ее глубине, которая в среднем будет О(log(n)).
 */

// Ссылка на посылку: https://contest.yandex.ru/contest/23815/run-report/92119504/

public class Solution {
    public static int brokenSearch(int[] arr, int k) {
        return brokenBinarySearch(arr, 0, arr.length, k);
    }

    private static int brokenBinarySearch(int[] arr, int left, int right, int k) {
        if (right <= left) return -1;

        int mid = (left + right) / 2;

        if (arr[mid] == k) {
            return mid;
        }

        if (k < arr[mid]) {
            if (k >= arr[left]) {
                if (k >= arr[right - 1]) {
                    if (arr[mid] > arr[left]) {
                        return binarySearch(arr, left, mid, k);
                    } else {
                        return brokenBinarySearch(arr, left, mid, k);
                    }
                } else {
                    return binarySearch(arr, left, mid, k);
                }
            } else {
                if (arr[mid] < arr[right - 1]) {
                    return brokenBinarySearch(arr, left, mid, k);
                } else {
                    return brokenBinarySearch(arr, mid + 1, right, k);
                }
            }
        } else {
            if (k >= arr[left] && k >= arr[right - 1]) {
                if (arr[mid] > arr[left]) {
                    return brokenBinarySearch(arr, mid, right, k);
                } else if (arr[mid] < arr[right - 1]) {
                    return brokenBinarySearch(arr, left, mid, k);
                } else {
                    return binarySearch(arr, left, mid, k);
                }
            } else {
                return binarySearch(arr, mid, right, k);
            }
        }
    }

    private static int binarySearch(int[] arr, int left, int right, int k) {
        if (right <= left) return -1;

        int mid = (left + right) / 2;

        if (arr[mid] == k) {
            return mid;
        } else if (k < arr[mid]) {
            return binarySearch(arr, left, mid, k);
        } else {
            return binarySearch(arr, mid + 1, right, k);
        }
    }

    private static void test() {
        int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
        assert 6 == brokenSearch(arr, 5);
    }

    public static void main(String[] args) {
        int[] arr = {15, 19, 21, 100, 101, 1, 4, 5, 7, 12};
        System.out.println("1: " + (brokenSearch(arr, 15) == 0));

        int[] arr1 = {1};
        System.out.println("2: " + (brokenSearch(arr1, 1) == 0));

        int[] arr2 = {5, 1};
        System.out.println("3: " + (brokenSearch(arr2, 1) == 1));

        int[] arr3 = {19, 21, 100, 101, 1, 4, 5, 7, 12};
        System.out.println("4: " + (brokenSearch(arr3, 19) == 0));

        int[] arr4 = {1, 4, 5, 7, 12, 19, 21, 100, 101};
        System.out.println("5: " + (brokenSearch(arr4, 5) == 2));

        int[] arr5 = {3, 6, 7};
        System.out.println("6: " + (brokenSearch(arr5, 8) == -1));

        int[] arr6 = {3, 6, 7};
        System.out.println("7: " + (brokenSearch(arr6, 1) == -1));

        int[] arr7 = {8, 10, 0, 2, 4};
        System.out.println("8: " + (brokenSearch(arr7, 4) == 4));

        int[] arr8 = {9, 1, 3, 8};
        System.out.println("9: " + (brokenSearch(arr8, 8) == 3));

        int[] arr9 = {3, 5, 6, 7, 9, 1, 2};
        System.out.println("10: " + (brokenSearch(arr9, 4) == -1));

        int[] arr10 = {2472, 2473, 2486, 2534, 2628, 2977, 3016, 3155, 3215, 3383, 3522, 3533, 3572, 3599, 3754, 3856, 3888, 3890, 4082, 4130, 4155, 4207, 4555, 4556, 4594, 4597, 4854, 4861, 4948, 5085, 5107, 5251, 5257, 5378, 5408, 5452, 5484, 5584, 5626, 5701, 5760, 5781, 5851, 5855, 6025, 6047, 6133, 6243, 6296, 6314, 6409, 6516, 6521, 6659, 6735, 6813, 6917, 7059, 7120, 7296, 7310, 7345, 7360, 7379, 7425, 7498, 7693, 7925, 7993, 8035, 8165, 8277, 8310, 8363, 8544, 8562, 8774, 8827, 8860, 8952, 9163, 9177, 9255, 9793, 9894, 199, 213, 227, 429, 465, 498, 728, 939, 1502, 1744, 1768, 1821, 2317, 2428, 2471};
        System.out.println("11: " + (brokenSearch(arr10, 227) == 87));

        int[] arr11 = {1, 5};
        System.out.println("12: " + (brokenSearch(arr11, 1) == 0));

        int[] arr12 = {8158, 8164, 8228, 8296, 8394, 8426, 8719, 8728, 9182, 9220, 9388, 9453, 9512, 9544, 9962, 37, 265, 392, 444, 519, 549, 649, 910, 999, 1056, 1090, 1211, 1429, 1526, 1628, 1688, 1694, 1733, 1816, 1994, 2039, 2290, 2335, 2389, 2667, 2690, 2748, 2799, 2831, 2905, 2927, 2993, 3033, 3048, 3132, 3166, 3330, 3346, 3417, 3457, 3505, 3573, 3599, 3679, 3691, 3839, 4009, 4013, 4151, 4192, 4219, 4305, 4548, 4799, 4862, 4866, 4869, 4976, 5190, 5401, 5452, 5477, 5553, 5938, 5945, 6041, 6099, 6132, 6163, 6437, 6524, 6780, 6801, 6888, 7101, 7187, 7220, 7228, 7346, 7387, 7546, 7762, 7981, 7983, 8120};
        System.out.println("13: " + (brokenSearch(arr12, 9220) == 9));
    }
}