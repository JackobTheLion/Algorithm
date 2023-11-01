package ru.praktikum.yandex.sprint04.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* --ПРИНЦИП РАБОТЫ--
 * "Под капотом" реализации таблицы лежит массив размера n + 1. Для того чтобы обеспечить добавление, удаление и поиск
 * за O(1) в среднем, будем добавлять в него значения по индексу массива, который в свою очередь будет вычисляться через
 * хеш-код ключа добавляемого элемента.
 * Определение номера корзины производится методом открытой адресации.
 *
 * put(ключ, значение)
 * 1. Введем переменную, в которой мы будем запоминать адрес корзины, помеченной как удаленной и инициализируем ее
 * значение как -1
 * 2. Вычисляем номер корзины, в которой должен лежать добавляемый элемент и проверяем эту корзину:
 * - если корзина пуста и удаленных корзин мы еще не встречали, записываем значение в массив
 * - если корзина пуста и мы встречали удаленное значение, то записываем значение в адрес первой встреченной
 * удаленной корзины
 * - если корзина помечена как удаленная и ранее удаленных корзин мы не встречали, запомним адрес корзины.
 * - если корзина не пуста, ключ совпадает с искомым ключом, а корзина не помечена как удаленная, перезапишем значения
 * - иначе повторяем цикл с новым номером корзины
 *
 * get(ключ)
 * 1. Вычислим номер корзины по ключу и проверим эту корзину:
 * - если в корзине нет значения, значит его нет в массиве. Вернем null.
 * - если значение в корзине не помечено как удаленное, а ключ совпадает с искомым, вернем значение
 * - иначе повторяем поиск с новым номером корзины
 *
 * delete(ключ)
 * 1. Вычислим номер корзины и проверим эту корзину:
 * - если в корзине нет значения, значит его нет в массиве, возвращаем null
 * - если в корзине есть значение не помеченное как удаленное, а его ключ совпадает с искомым, то помечаем корзину как
 * удаленную и возвращаем значение
 * - иначе повторяем цикл с новым номером корзины
 *
 * --ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 * Ключевым для корректности работы алгоритма является способ выбора номера корзины который основан на хэш значении
 * ключа. Т.к. по условию задачи ключом может быть только Integer, а его хэш всегда равен ему самому, то мы можем
 * отказаться от вычисления хэша и использовать сам ключ, а для пробирования использовать линейный способ, что упростит
 * реализацию. Чтобы не выйти за пределы массива будем брать остаток от деления на размер массива.
 *
 * Для того чтобы при добавлении элемента избежать ситуации, в которой все корзины заполнены значениями deleted,
 * в методе put() будем запоминать первое встретившееся значение deleted и как только наткнемся на пустую корзину
 * записывать новое значение в запомненную корзину. Мы гарантированно встретим пустую корзину, т.к. их количество n + 1,
 * где n - количество команд.
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * Все операции в таблице в среднем выполняются за O(1), т.к. обращение к массиву выполняется за константное время.
 * Время поиска номера корзины зависит от количества коллизий.
 * Количество операций равно n, следовательно, общая временная сложность массива составляет O(n).
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * Программа использует дополнительную память для хранения массива, размер которого равняется количеству команд и
 * составляет O(n + 1) -> O(n).
 * Дополнительная память также понадобится для хранения ответа и в худшем случае потребует O(n).
 * Таким образом общая пространственная сложность составит O(n+n) -> O(n) дополнительной памяти.
 */

// Ссылка на посылку: https://contest.yandex.ru/contest/24414/run-report/95517668/

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<String[]> commands = readCommands(reader, n);

            CustomHashMap map = new CustomHashMap(n);
            StringBuilder sb = new StringBuilder();

            for (String[] command : commands) {
                Integer key = Integer.parseInt(command[1]);
                Integer value;

                switch (command[0]) {
                    case "get":
                        value = map.get(key);
                        if (value != null) {
                            sb.append(value);
                        } else {
                            sb.append("None");
                        }
                        sb.append("\n");
                        break;

                    case "put":
                        value = Integer.parseInt(command[2]);
                        map.put(key, value);
                        break;

                    case "delete":
                        value = map.delete(key);
                        if (value != null) {
                            sb.append(value);
                        } else {
                            sb.append("None");
                        }
                        sb.append("\n");
                }
            }

            System.out.println(sb);
        }
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String[]> readCommands(BufferedReader reader, int n) throws IOException {
        List<String[]> commands = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            int count = stringTokenizer.countTokens();
            String[] command = new String[count];
            for (int j = 0; j < count; j++) {
                command[j] = stringTokenizer.nextToken();
            }
            commands.add(command);
        }
        return commands;
    }
}

class CustomHashMap {
    private final int capacity;

    private final Bucket[] buckets;

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new Bucket[capacity + 1];
    }

    public Integer get(int k) {
        for (int i = 0; i < capacity; i++) {
            int bucketNumber = getBucketNumber(k, i);

            if (buckets[bucketNumber] == null) {
                return null;
            } else if (buckets[bucketNumber].key == k && !buckets[bucketNumber].deleted) {
                return buckets[bucketNumber].value;
            }
        }
        return null;
    }

    public void put(int k, int v) {
        int firstDeletedBucket = -1;

        for (int i = 0; i < capacity; i++) {
            int bucketNumber = getBucketNumber(k, i);

            if (buckets[bucketNumber] == null && firstDeletedBucket < 0) {
                buckets[bucketNumber] = new Bucket(k, v);
                return;
            }
            if (buckets[bucketNumber] == null && firstDeletedBucket > 0) {
                buckets[firstDeletedBucket] = new Bucket(k, v);
                return;
            }
            if (buckets[bucketNumber].deleted && firstDeletedBucket < 0) {
                firstDeletedBucket = bucketNumber;
            }
            if (buckets[bucketNumber].key == k && !buckets[bucketNumber].deleted) {
                buckets[bucketNumber].value = v;
                return;
            }
        }
    }

    public Integer delete(int k) {
        for (int i = 0; i < capacity; i++) {
            int bucketNumber = getBucketNumber(k, i);

            if (buckets[bucketNumber] == null) {
                return null;
            }
            if (buckets[bucketNumber].key == k && !buckets[bucketNumber].deleted) {
                buckets[bucketNumber].deleted = true;
                return buckets[bucketNumber].value;
            }
        }
        return null;
    }

    private int getBucketNumber(int k, int i) {
        if (k < 0) k *= -1;
        return (k % capacity + i % capacity) % capacity;
    }

    class Bucket {
        int key;
        int value;
        boolean deleted;

        public Bucket(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}