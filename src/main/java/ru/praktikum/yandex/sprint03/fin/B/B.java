package ru.praktikum.yandex.sprint03.fin.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class B {

    public static List<Participant> sortParticipants(List<Participant> participants) {
        sortReq(participants, 0, participants.size() - 1);
        return participants;
    }

    private static void sortReq(List<Participant> participants, int left, int right) {
        if (right - left <= 1) return;

        int initialLeft = left;
        int initialRight = right;

        Participant pivot = participants.get((left + right) / 2);

        while (left < right) {
            while (compare(pivot, participants.get(left)) >= 0) {
                left++;
            }
            while (compare(pivot, participants.get(right)) < 0) {
                right--;
            }
            swap(participants, left, right);

            if (left - right > 1) {
                left++;
                right--;
            } else break;
        }

        sortReq(participants, initialLeft, left);
        sortReq(participants, right, initialRight);
    }

    private static void swap(List<Participant> participants, int left, int right) {
        Participant temp = participants.get(left);
        participants.set(left, participants.get(right));
        participants.set(right, temp);
    }

    private static int compare(Participant p1, Participant p2) {
        if (p1.getSolved() != p2.getSolved()) {
            return p1.getSolved() - p2.getSolved();
        } else if (p1.getFine() != p2.getFine()) {
            return p1.getFine() - p2.getFine();
        } else {
            return p1.getName().compareTo(p2.getName());
        }
    }

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
    private final int fine;

    public Participant(String name, int solved, int fine) {
        this.name = name;
        this.solved = solved;
        this.fine = fine;
    }

    public String getName() {
        return name;
    }

    public int getSolved() {
        return solved;
    }

    public int getFine() {
        return fine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participant that = (Participant) o;

        if (solved != that.solved) return false;
        if (fine != that.fine) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + solved;
        result = 31 * result + fine;
        return result;
    }
}
