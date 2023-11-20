package ru.praktikum.yandex.sprint05.M;

public class Solution {
    public static int siftUp(int[] heap, int idx) {
        if (idx == 1) return idx;

        int head = idx / 2;

        int newPosition = idx;

        if (heap[head] < heap[idx]) {
            int temp = heap[head];
            heap[head] = heap[idx];
            heap[idx] = temp;
            newPosition = siftUp(heap, head);
        }
        return newPosition;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        int result = siftUp(sample, 5);
        System.out.println(result);
    }
}
