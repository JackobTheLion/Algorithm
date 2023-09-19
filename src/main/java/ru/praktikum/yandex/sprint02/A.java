package ru.praktikum.yandex.sprint02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A {

    public static String transformMatrix(List<List<Integer>> matrix, int rows) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < matrix.size(); j++) {
                sb.append(matrix.get(j).get(i)).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int lines = readInt(reader);
            int rows = readInt(reader);
            List<List<Integer>> matrix = readMatrix(reader, lines);
            System.out.println(transformMatrix(matrix, rows));
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<List<Integer>> readMatrix(BufferedReader reader, int lines) throws IOException {
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 1; i <= lines; i++) {
            String lineString = reader.readLine();
            String[] split = lineString.split(" ");
            List<Integer> line = new ArrayList<>();
            for (int j = 0; j < split.length; j++) {
                line.add(Integer.parseInt(split[j]));
            }
            matrix.add(line);
        }
        return matrix;
    }
}
