package ru.praktikum.yandex.sprint03.fin.B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * --ПРИНЦИП РАБОТЫ--
 * Алгоритм работает по принципу быстрой сортировки.
 * 1. Если разница правого и левого указателя меньше 1, остался один элемент, сортировать не нужно
 * 2. Запоминаем изначальное положение левого и правого указателя.
 * 3. Ищем опорный элемент - будем считать, что он находится в центре.
 * 4. Заходим в цикл и проверяем, является ли элемент, на который указывает левый указатель больше опорного. Если
 * меньше - двигаем указатель вперед, иак до тех пор, пока не найдем элемент, который больше.
 * 5. Проверяем, указывает ли правый указатель на элемент, который строго меньше опорного. Если больше - двигаем
 * указатель влево.
 * 6. Если Указатели не встретились, то меняем элементы местами и двигаем указатели.
 * 7. Проверяем встретились ли указатели теперь. Если нет, то повторяем цикл с п.4.,
 * 8. Когда указатели встретились, то прерываем цикл и рекурсивно запускаем сортировку для левой и правой части. Важно,
 * что указатели на последнем круге цикла "поменялись местами", т.е. левый указатель стал больше правого.
 *
 * --ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 * Рассмотрим пример: дан массив {35, 17, 13, 23}
 * Выберем опорный элемент, например число 20. Расположим в исходном массиве слева все числа меньше опорного, а справа
 * больше опорного. Получаем {13, 17 < 20 > 35, 23}.
 * Теперь аналогично поступим с каждой половинкой рекурсивно.
 * Дойдя до этапа, когда в рассматриваемом участке массива останется:
 * - 0 и 1 элемент - уже отсортированы
 * - 2 элемента - можем без проблем отсортировать.
 *
 * Таким образом мы получаем набор из отсортированных частей массива, расположенных друг за другом последовательно.
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Временная сложность в худшем случае (при неудачном выборе опорного элемента) составит O(n^2), например при выборе
 * первого элемента в отсортированном массиве. В этом случае оставшаяся часть будет уменьшаться всего на 1 элемент.
 * В среднем (при удачном выборе опорного элемента, который разделит массив на 2 приблизительно равные части)
 * сложность алгоритма составит O(n log n)
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * Алгоритм внутри себя имеет три дополнительные переменные: изначальное положение левого и правого указателя и
 * опорный элемент. Это О(1).
 * Далее требуемая память зависит от глубины рекурсии, которая, как было рассмотрено выше, зависит от опорного элемента.
 * Т.о. в худшем случае потребуется O(n^2), а в среднем O(n log n).
 * */

// Ссылка на посылку https://contest.yandex.ru/contest/23815/run-report/92369901/

public class B {

    public static void sortParticipants(List<Participant> participants) {
        if (participants.isEmpty()) return;
        sortReq(participants, 0, participants.size() - 1);
    }

    private static void sortReq(List<Participant> participants, int left, int right) {
        if (right - left < 1) return;

        int initialLeft = left;
        int initialRight = right;

        Participant pivot = participants.get((left + right) / 2);

        while (left <= right) {
            while (compare(pivot, participants.get(left)) < 0) {
                left++;
            }

            while (compare(pivot, participants.get(right)) > 0) {
                right--;
            }

            if (left >= right) {
                break;
            }
            swap(participants, left, right);
            left++;
            right--;
        }

        sortReq(participants, initialLeft, left - 1);
        sortReq(participants, right + 1, initialRight);
    }

    private static void swap(List<Participant> participants, int left, int right) {
        Participant temp = participants.get(left);
        participants.set(left, participants.get(right));
        participants.set(right, temp);
    }

    private static int compare(Participant p1, Participant p2) {
        if (p1.getSolved() != p2.getSolved()) {
            return p1.getSolved() - p2.getSolved();
        }
        if (p1.getPenalty() != p2.getPenalty()) {
            return p2.getPenalty() - p1.getPenalty();
        } else {
            return p2.getName().compareTo(p1.getName());
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int number = readInt(reader);
            List<Participant> participants = readParticipants(reader, number);

            sortParticipants(participants);

            StringBuilder sb = new StringBuilder();
            for (Participant participant : participants) {
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
