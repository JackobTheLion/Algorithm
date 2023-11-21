package ru.praktikum.yandex.sprint05.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int number = readInt(reader);
            List<Participant> participants = readParticipants(reader, number);

            List<Participant> sortedParticipants = sortParticipants(participants);

            StringBuilder sb = new StringBuilder();
            for (Participant participant : sortedParticipants) {
                sb.append(participant.getName()).append("\n");
            }
            System.out.println(sb);
        }
    }

    private static List<Participant> sortParticipants(List<Participant> participants) {
        int heapSize = participants.size() + 1;
        List<Participant> heap = new ArrayList<>(heapSize);

        for (int i = 1; i < heapSize; i++) {
            heapAdd(heap, participants.get(i - 1), i);
        }

        List<Participant> sortedArray = new ArrayList<>();
        while (!heap.isEmpty()) {
            Participant max = popMax(heap);
            sortedArray.add(max);
        }
        return sortedArray;
    }

    private static void heapAdd(List<Participant> heap, Participant participant, int index) {
        heap.add(participant);
        siftUp(heap, index);
    }

    public static void siftUp(List<Participant> heap, int index) {
        if (index == 1) {
            return;
        }

        int parentIndex = index / 2;
        if (compare(heap.get(parentIndex - 1), heap.get(index - 1)) < 1) {
            Participant temp = heap.get(parentIndex - 1);
            heap.set(parentIndex - 1, heap.get(index - 1));
            heap.set(index - 1, temp);
            siftUp(heap, parentIndex);
        }
    }

    public static Participant popMax(List<Participant> heap) {
        Participant result = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        siftDown(heap, 0);
        return result;
    }

    public static void siftDown(List<Participant> heap, int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left >= heap.size()) {
            return;
        }

        int indexLargest = left;
        if (right < heap.size() && compare(heap.get(left), heap.get(right)) < 1) {
            indexLargest = right;
        }

        if (compare(heap.get(index), heap.get(indexLargest)) < 1) {
            Participant temp = heap.get(index);
            heap.set(index, heap.get(indexLargest));
            heap.set(indexLargest, temp);
            siftDown(heap, indexLargest);
        }
    }

    private static int compare(Participant p1, Participant p2) {
        if (p1.getSolved() != p2.getSolved()) {
            return p1.getSolved() - p2.getSolved();
        }
        if (p1.getPenalty() != p2.getPenalty()) {
            return p2.getPenalty() - p1.getPenalty();
        }
        return p2.getName().compareTo(p1.getName());
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<Participant> readParticipants(BufferedReader reader, int number) throws IOException {
        List<Participant> participants = new ArrayList<>(number);

        for (int i = 1; i <= number; i++) {
            String[] s = reader.readLine().split(" ");
            Participant participant = new Participant(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            participants.add(participant);
        }

        return participants;
    }
}

class Participant {
    private final String name;
    private final int solved;
    private final int penalty;

    public Participant(String name, int solved, int penalty) {
        this.name = name;
        this.solved = solved;
        this.penalty = penalty;
    }

    public String getName() {
        return name;
    }

    public int getSolved() {
        return solved;
    }

    public int getPenalty() {
        return penalty;
    }
}
