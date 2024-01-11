package ru.praktikum.yandex.sprint08.E;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class E {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s = reader.readLine();
            int presentNumber = readInt(reader);
            List<PresentString> presentStrings = readPresentStrings(reader, presentNumber);

            System.out.println(combineStrings(s, presentStrings));
        }
    }

    public static String combineStrings(String s, List<PresentString> presentStrings) {
        int sPointer = 0;
        int presentPointer = 0;
        StringBuilder sb = new StringBuilder();

        while (sPointer < s.length() && presentPointer < presentStrings.size()) {
            if (presentStrings.get(presentPointer).position == sPointer) {
                sb.append(presentStrings.get(presentPointer).string);
                presentPointer++;
            } else {
                sb.append(s.charAt(sPointer));
                sPointer++;
            }
        }

        if (sPointer < s.length()) {
            sb.append(s.substring(sPointer));
        }

        if (presentStrings.get(presentStrings.size() - 1).position == s.length()) {
            sb.append(presentStrings.get(presentStrings.size() - 1).string);
        }

        return sb.toString();
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static List<PresentString> readPresentStrings(BufferedReader reader, int presentNumber) throws IOException {
        List<PresentString> presentStrings = new ArrayList<>(presentNumber);

        for (int i = 0; i < presentNumber; i++) {
            String[] split = reader.readLine().split(" ");
            PresentString presentString = new PresentString(split[0], Integer.parseInt(split[1]));
            presentStrings.add(presentString);
        }
        presentStrings.sort(Comparator.comparingInt(o -> o.position));
        return presentStrings;
    }
}

class PresentString {
    String string;
    int position;

    public PresentString(String string, int position) {
        this.string = string;
        this.position = position;
    }
}
