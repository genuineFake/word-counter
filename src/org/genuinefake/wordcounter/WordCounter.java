package org.genuinefake.wordcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fnord on 17.5.26.
 */
public class WordCounter {
    private Map<String, Integer> wordMap;

    public WordCounter() {
        wordMap = new HashMap<>();
    }


    public void countWordFrequency(String[] fileNames) {
        List<MapBuilder> threadlist = new ArrayList<>();
        List<Map<String, Integer>> wordCounts = new ArrayList<>();

        for (int i = 0; i < fileNames.length; i++) {
            MapBuilder mb = new MapBuilder(fileNames[i]);
            threadlist.add(mb);
            mb.start();
        }

        for (MapBuilder mb : threadlist) {
            try {
                mb.join();
                wordCounts.add(mb.getWordMap());
            } catch (Exception ex) {

            }
        }

        for (Map<String, Integer> wcount : wordCounts) {
            for (Map.Entry<String, Integer> entry : wcount.entrySet()) {
                wordMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        System.out.println(wordMap.size());
        System.out.println("List of repeated word from file and their count");
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + " => " + entry.getValue());
            }
        }
    }

    public Map<String, Integer> getWordMap() {
        return wordMap;
    }
}
