package ru.praktikum.yandex.sprint07.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int classNumber = readInt(reader);
            Queue<Lesson> lessons = readLessons(reader, classNumber);

            List<Lesson> schedule = scheduleLessons(lessons);
            StringBuilder sb = new StringBuilder();
            sb.append(schedule.size()).append("\n");
            for (Lesson lesson : schedule) {
                sb.append(lesson).append("\n");
            }

            System.out.println(sb);
        }
    }

    public static List<Lesson> scheduleLessons(Queue<Lesson> lessons) {
        List<Lesson> result = new ArrayList<>(lessons.size());

        result.add(lessons.poll());

        int pointer = 0;
        while (!lessons.isEmpty()) {
            Lesson poll = lessons.poll();
            if (result.get(pointer).end <= poll.start) {
                result.add(poll);
                pointer++;
            }
        }
        return result;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return parseInt(reader.readLine());
    }

    private static Queue<Lesson> readLessons(BufferedReader reader, int classNumber) throws IOException {
        Queue<Lesson> lessons = new PriorityQueue<>((o1, o2) -> {
            if (!o1.end.equals(o2.end)) {
                return Double.compare(o1.end, o2.end);
            }
            return Double.compare(o1.start, o2.start);
        });

        for (int i = 0; i < classNumber; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            Double start = Double.parseDouble(tokenizer.nextToken());
            Double end = Double.parseDouble(tokenizer.nextToken());
            Lesson lesson = new Lesson(start, end);
            lessons.add(lesson);
        }

        return lessons;
    }
}

class Lesson {
    public Double start;
    public Double end;

    public Lesson(Double start, Double end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String start;
        if (this.start % 1 == 0) {
            start = String.valueOf(this.start.intValue());
        } else start = String.valueOf(this.start);

        String end;
        if (this.end % 1 == 0) {
            end = String.valueOf(this.end.intValue());
        } else end = String.valueOf(this.end);

        return start + " " + end;
    }
}
