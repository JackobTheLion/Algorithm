package ru.praktikum.yandex.sprint03.N;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class N {

    public static List<int[]> formBeds(List<int[]> beds) {
        beds.sort(Comparator.comparingInt(o -> o[0]));

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < beds.size(); i++) {
            int[] bed = beds.get(i);
            for (int j = i + 1; j < beds.size(); j++) {
                if (bed[1] >= beds.get(j)[0]) {
                    if (bed[1] < beds.get(j)[1]) {
                        bed[1] = beds.get(j)[1];
                    }
                    i++;
                } else break;
            }
            result.add(bed);
        }
        return result;
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
