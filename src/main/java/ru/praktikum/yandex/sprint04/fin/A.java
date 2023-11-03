package ru.praktikum.yandex.sprint04.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* --ПРИНЦИП РАБОТЫ--
 * Т.к. релевантность документа определяется количеством уникальных слов из запроса, содержащихся в документе, то удобно
 * построить словарь, в котором за O(1) мы сможем по слову из запроса найти id всех документов и количество вхождений
 * в документе, просуммировать их и выбрать 5 наиболее подходящих.
 * Для этого построим словарь вида Map<Слова из документов, Map <Id документа, Количество вхождений в этом документе>>
 *
 * После построения словаря поиск сводится к перебору всех слов из запроса и составления списка ID документов для
 * каждого отдельного запроса.
 *
 * --ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ--
 * Ключевым в работе алгоритма является создание словаря, по которому мы затем будем осуществлять поиск и определять
 * релевантность документа. Для его создания пройдемся по каждому слову каждого документа и занесем его в таблицу,
 * ключом которой будет само слово, а значением - другая таблица, в которой ключ - id документа, а значение -
 * количество вхождений этого слова в документе.
 * После составления словаря без проблем определяем самые релевантные документы путем суммирования количества вхождений
 * каждого уникально слова из запроса в каждом документе.
 *
 * --ВРЕМЕННАЯ СЛОЖНОСТЬ--
 * При составлении индекса мы проходимся по каждому слову каждого документу, что составляет O(n * k), где n - количество
 * документов, а k - количество слов в документе.
 *
 * При обработке запроса в худшем случае, когда каждое слово встречается в хотя бы в одном документе мы получим
 * сложность O(m * t).
 *
 * Т.о. общая сложность составит O(n * k + m * t).
 *
 * Если принять количеством слов в документе за константу, от сложность будет O(n + m * t) = O(m * t).
 *
 * --ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ--
 * В алгоритме используется дополнительная память на создание словаря, что в худшем случае (когда в документах все слова
 * уникальны) потребует O(n*k) памяти.
 * В процессе поиска для каждого запроса создается промежуточная таблица, которая хранит id документ и его
 * релевантность. Т.о. в худшем случае (если каждый из документов содержит хотя бы одно слово из запроса) это
 * потребует O(n) дополнительной памяти.
 * Результат программы хранится в списке вида List<List<ID документа, но не более 5 значений>>, что потребует O(m*5).
 * Отбросив константы получим O(m).
 * Таким образом сложность алгоритма составит O(n*k + n + m) -> O(n*(k+1) + m) -> O(n*k + m) памяти.
 */

//Ссылка на посылку: https://contest.yandex.ru/contest/24414/run-report/95685380/

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<String> docs = readDocs(reader, n);
            int m = readInt(reader);
            List<Set<String>> requests = readRequests(reader, m);

            List<List<Integer>> searchResult = search(docs, requests);

            StringBuilder sb = new StringBuilder();
            for (List<Integer> list : searchResult) {
                for (Integer integer : list) {
                    sb.append(integer).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb);
        }
    }

    private static List<List<Integer>> search(List<String> docs, List<Set<String>> requests) {
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Map<Integer, Integer>> dictionary = makeDictionary(docs);

        for (Set<String> request : requests) {
            Map<Integer, Integer> relevances = new HashMap<>();
            //id документа -> совпадение для запроса

            for (String searchWord : request) {
                Map<Integer, Integer> indexes = dictionary.getOrDefault(searchWord, new HashMap<>());
                //id документа -> сколько раз встречается слово

                for (Map.Entry<Integer, Integer> index : indexes.entrySet()) {
                    relevances.put(index.getKey(),
                            relevances.getOrDefault(index.getKey(), 0) + index.getValue());
                }
            }

            List<Integer> subResult = new ArrayList<>();
            int size = relevances.size();
            for (int i = 0; i < 5 && i < size; i++) {
                subResult.add(findMax(relevances) + 1);
            }
            result.add(subResult);
        }

        return result;
    }

    private static Integer findMax(Map<Integer, Integer> map) {
        Map.Entry<Integer, Integer> max = null;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (max == null) {
                max = entry;
                continue;
            }
            if (max.getValue() < entry.getValue()) {
                max = entry;
            }
            if (max.getValue().equals(entry.getValue())) {
                if (max.getKey() > entry.getKey()) {
                    max = entry;
                }
            }

        }
        map.remove(max.getKey());
        return max.getKey();
    }

    private static Map<String, Map<Integer, Integer>> makeDictionary(List<String> docs) {
        Map<String, Map<Integer, Integer>> dictionary = new HashMap<>();

        for (int i = 0; i < docs.size(); i++) {
            String[] words = docs.get(i).split(" ");
            for (String word : words) {
                Map<Integer, Integer> index = dictionary.getOrDefault(word, new HashMap<>());
                index.put(i, index.getOrDefault(i, 0) + 1);
                dictionary.put(word, index);
            }
        }
        return dictionary;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static List<String> readDocs(BufferedReader reader, int n) throws IOException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(reader.readLine());
        }
        return result;
    }

    private static List<Set<String>> readRequests(BufferedReader reader, int n) throws IOException {
        List<Set<String>> requests = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
            Set<String> request = new HashSet<>();
            while (stringTokenizer.hasMoreTokens()) {
                request.add(stringTokenizer.nextToken());
            }
            requests.add(request);
        }
        return requests;
    }
}
