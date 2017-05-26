package org.genuinefake.wordcounter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by fnord on 17.5.26.
 */
public class MapBuilder extends Thread {
    private String fileName;
    private Map<String, Integer> wordMap;


    public MapBuilder(String fileName) {
        this.fileName = fileName;
        wordMap = new HashMap<>();
    }

    @Override
    public void run() {
        buildWordMap(fileName);
    }

    private void buildWordMap(String fileName) {

        try {
            FileInputStream fis = new FileInputStream(fileName);
            DataInputStream dis = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));

            Pattern pattern = Pattern.compile("\\W+");
            String line = null;
            while ((line = br.readLine()) != null) {
                if (!line.trim().equals("")) {
                    line = line.toLowerCase();
                    String[] words = pattern.split(line);
                    for (String word : words) {
                        if (word == null || word.trim().equals("")) {
                            continue;
                        }
                        if (wordMap.containsKey(word)) {
                            wordMap.put(word, (wordMap.get(word) + 1));
                        } else {
                            wordMap.put(word, 1);
                        }
                    }
                }
            }
            br.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    public Map<String, Integer> getWordMap() {
        return wordMap;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
