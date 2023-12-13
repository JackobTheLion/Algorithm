package ru.praktikum.yandex.sprint07.C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

import static java.lang.Long.parseLong;

public class C {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int capacity = readInt(reader);
            int numberOfPiles = readInt(reader);
            Queue<Pile> piles = readPiles(reader, numberOfPiles);

            long maxIncome = getMaxIncome(piles, capacity);

            System.out.println(maxIncome);
        }
    }

    private static long getMaxIncome(Queue<Pile> piles, int capacity) {
        long maxIncome = 0;
        while (capacity > 0 && !piles.isEmpty()) {
            Pile pile = piles.poll();
            while (capacity > 0 && pile.weight > 0) {
                capacity--;
                pile.weight--;
                maxIncome += pile.cost;
            }
        }
        return maxIncome;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static Queue<Pile> readPiles(BufferedReader reader, int numberOfPiles) throws IOException {
        Queue<Pile> piles = new PriorityQueue<>((o1, o2) -> Long.compare(o2.cost, o1.cost));

        for (int i = 0; i < numberOfPiles; i++) {
            String[] split = reader.readLine().split(" ");
            Pile pile = new Pile(parseLong(split[1]), parseLong(split[0]));
            piles.add(pile);
        }

        return piles;
    }
}

class Pile {
    long weight;
    long cost;

    public Pile(long weight, long cost) {
        this.weight = weight;
        this.cost = cost;
    }
}
