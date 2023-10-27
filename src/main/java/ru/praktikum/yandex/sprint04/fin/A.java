package ru.praktikum.yandex.sprint04.fin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = readInt(reader);
            List<String> docs = readDocs(reader, n);
            int m = readInt(reader);
            List<String> requests = readDocs(reader, m);

            List<List<Integer>> relevances = getRelevance(docs, requests);

            StringBuilder sb = new StringBuilder();
            for (List<Integer> list : relevances) {
                for (Integer integer : list) {
                    sb.append(integer).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb);
        }
    }

    private static List<List<Integer>> getRelevance(List<String> docs, List<String> requests) {
        List<List<Integer>> result = new ArrayList<>();


        List<Map<String, Integer>> appearances = mapDocs(docs);
        List<Set<String>> requestsSet = new ArrayList<>();

        for (String s : requests) {
            requestsSet.add(getUniqKeyWords(s));
        }

        for (int i = 0; i < requests.size(); i++) {
            Set<String> request = requestsSet.get(i);
            Set<Relevance> relevances = new HashSet<>();

            for (int j = 0; j < docs.size(); j++) {
                Map<String, Integer> doc = appearances.get(j);

                int relevance = 0;
                for (String requestWord : request) {
                    if (doc.containsKey(requestWord)) {
                        relevance += doc.get(requestWord);
                    }
                }

                if (relevance > 0) {
                    relevances.add(new Relevance(j, relevance));
                }
            }

            result.add(getTopRelevant(relevances));
        }

        return result;
    }

    private static List<List<Integer>> getRelevance2(List<String> docs, List<String> requests) {
        List<List<Integer>> result = new ArrayList<>();

        Map<String, Map<Integer, Integer>> dictionary = makeDictionary(docs);

        List<Set<String>> requestsSet = new ArrayList<>();
        for (String s : requests) {
            requestsSet.add(getUniqKeyWords(s));
        }

        for (int i = 0; i < requests.size(); i++) {
            Set<String> request = requestsSet.get(i);

            for (String searchWord : request) {
                if (dictionary.containsKey(searchWord)) {
                    Map<Integer, Integer> searchWordIndex = dictionary.get(searchWord);
                    for (Map.Entry<Integer, Integer> index : searchWordIndex.entrySet()) {

                    }
                }
            }
        }

        return result;
    }


    private static Set<String> getUniqKeyWords(String s) {
        String[] array = s.split(" ");
        return new HashSet<>(Arrays.asList(array));
    }

    private static List<Map<String, Integer>> mapDocs(List<String> docs) {
        List<Map<String, Integer>> result = new ArrayList<>();
        for (String doc : docs) {
            Map<String, Integer> appearance = new HashMap<>();
            String[] string = doc.split(" ");
            for (String docWord : string) {
                appearance.put(docWord, appearance.getOrDefault(docWord, 0) + 1);
            }
            result.add(appearance);
        }
        return result;
    }

    private static Map<String, Map<Integer, Integer>> makeDictionary(List<String> docs) {
        Map<String, Map<Integer, Integer>> result = new HashMap<>();

        for (int i = 0; i < docs.size(); i++) {
            String[] words = docs.get(i).split(" ");
            for (String word : words) {
                Map<Integer, Integer> map = result.getOrDefault(word, new HashMap<>());
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }

        return result;
    }

    private static List<Integer> getTopRelevant(Set<Relevance> relevance) {
        List<Integer> result = new ArrayList<>();

        List<Relevance> relevances = new ArrayList<>(relevance);
        relevances.sort((o1, o2) -> {
            if (o1.getCount() > o2.getCount()) {
                return -1;
            }
            if (o1.getCount() < o2.getCount()) {
                return 1;
            } else {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });

        for (int i = 0; i < 5 && i < relevances.size(); i++) {
            result.add(relevances.get(i).getId() + 1);
        }

        return result;
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
}

class Relevance {
    private final int id;
    private int count;

    public Relevance(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relevance relevance1 = (Relevance) o;

        if (id != relevance1.id) return false;
        return count == relevance1.count;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + count;
        return result;
    }
}
