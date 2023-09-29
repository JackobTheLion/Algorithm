package ru.praktikum.yandex.sprint03.N;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class N {

    public static List<int[]> formBeds(List<int[]> beds) {
        beds.sort(Comparator.comparingInt(o -> o[1]));

        for (int i = 0; i < beds.size() - 1; i++) {
            if (isOverlap(beds.get(i), beds.get(i + 1))) {
                int[] left = beds.get(i);
                int[] right = beds.get(i + 1);
                left[0] = Math.min(left[0], right[0]);
                left[1] = Math.max(left[1], right[1]);
                beds.set(i, null);
                beds.set(i + 1, left);
            }
        }
        List<int[]> result = new ArrayList<>();
        for (int[] bed : beds) {
            if (bed != null) {
                result.add(bed);
            }
        }
        return result;
    }

    private static boolean isOverlap(int[] a, int[] b) {
        return !(a[0] > b[1] || b[0] > a[1]);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<int[]> beds = formBeds(readBeds(reader, n));
            StringBuilder sb = new StringBuilder();
            for (int[] arr : beds) {
                sb.append(arr[0]).append(" ").append(arr[1]).append("\n");
            }
            System.out.println(sb);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<int[]> readBeds(BufferedReader reader, int n) throws IOException {
        List<int[]> beds = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String bed = reader.readLine();
            String[] bedArr = bed.split(" ");
            int[] bedInt = new int[2];
            bedInt[0] = Integer.parseInt(bedArr[0]);
            bedInt[1] = Integer.parseInt(bedArr[1]);
            beds.add(bedInt);
        }
        return beds;
    }

}
