package ru.praktikum.yandex.sprint03.K;

import java.util.Arrays;

public class Solution {
    public static int[] merge(int[] arr, int left, int mid, int right) {
        if (arr.length == 1) {
            return arr;
        }

        int[] result = new int[right - left];

        int lIndex = left;
        int rIndex = mid;
        int resultIndex = 0;

        while (lIndex < mid && rIndex < right) {
            if (arr[lIndex] <= arr[rIndex]) {
                result[resultIndex] = arr[lIndex];
                lIndex++;
            } else {
                result[resultIndex] = arr[rIndex];
                rIndex++;
            }
            resultIndex++;
        }

        while (lIndex < mid) {
            result[resultIndex] = arr[lIndex];
            lIndex++;
            resultIndex++;
        }

        while (rIndex < right) {
            result[resultIndex] = arr[rIndex];
            rIndex++;
            resultIndex++;
        }

        return result;
    }

    public static void merge_sort(int[] arr, int left, int right) {
        if (right - left <= 1) return;
        int mid = (left + right) / 2;
        merge_sort(arr, left, mid);
        merge_sort(arr, mid, right);
        int[] result = merge(arr, left, mid, right);

        int resultIndex = 0;
        int arrIndex = left;
        while (resultIndex < result.length && arrIndex < right) {
            arr[arrIndex] = result[resultIndex];
            resultIndex++;
            arrIndex++;
        }

    }

    public static void main(String[] args) {
        int[] a = {1, 4, 9, 2, 10, 11};
        int[] b = merge(a, 0, 3, 6);
        int[] expected = {1, 2, 4, 9, 10, 11};
        assert Arrays.equals(b, expected);
        int[] c = {1, 4, 2, 10, 1, 2};
        merge_sort(c, 0, 6);
        int[] expected2 = {1, 1, 2, 2, 4, 10};
        assert Arrays.equals(c, expected2);
    }
}
