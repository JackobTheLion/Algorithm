package ru.praktikum.yandex.sprint05.L;

public class Solution {
    public static int siftDown(int[] heap, int idx) {
        int left = 2 * idx;
        int right = 2 * idx + 1;

        if (left >= heap.length) {
            return idx;
        }

        int indexLargest = left;
        if (right < heap.length && heap[left] < heap[right]) {
            indexLargest = right;
        }

        int newPosition = idx;
        if (heap[idx] < heap[indexLargest]) {
            int temp = heap[idx];
            heap[idx] = heap[indexLargest];
            heap[indexLargest] = temp;
            newPosition = siftDown(heap, indexLargest);
        }
        return newPosition;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        int result = siftDown(sample, 2);
        System.out.println(result);
    }
}
