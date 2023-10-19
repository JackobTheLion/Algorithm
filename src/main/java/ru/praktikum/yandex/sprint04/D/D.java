package ru.praktikum.yandex.sprint04.D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class D {

    public static void uniqueLines(List<String> lines) {
        HashSet<String> hasLines = new LinkedHashSet<>();

        for (String s : lines) {
            hasLines.add(s);
        }

        StringBuilder sb = new StringBuilder();

        for (String s : hasLines) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int linesNumber = readInt(reader);
            List<String> lines = readLines(reader, linesNumber);

            uniqueLines(lines);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static List<String> readLines(BufferedReader reader, int linesNumber) throws IOException {
        List<String> lines = new ArrayList<>(linesNumber);

        while (linesNumber > 0) {
            lines.add(reader.readLine());
            linesNumber--;
        }
        return lines;
    }
}
