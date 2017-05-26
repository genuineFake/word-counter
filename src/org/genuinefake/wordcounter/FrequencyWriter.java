package org.genuinefake.wordcounter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Created by fnord on 17.5.26.
 */
public class FrequencyWriter {
    private Map<String, Integer> allWordsMap;
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;

    public FrequencyWriter(Map<String, Integer> allWordsMap) {
        this.allWordsMap = allWordsMap;
    }

    public void writeToFiles() {
        for (Map.Entry<String, Integer> e : allWordsMap.entrySet()) {
            if (e.getKey().matches("^[a-g].*$")) {
                String toWrite = e.getKey() + " - " + e.getValue();
                write(toWrite, 1);
            }
            if (e.getKey().matches("^[h-n].*$")) {
                String toWrite = e.getKey() + " - " + e.getValue();
                write(toWrite, 2);
            }
            if (e.getKey().matches("^[o-u].*$")) {
                String toWrite = e.getKey() + " - " + e.getValue();
                write(toWrite, 3);
            }
            if (e.getKey().matches("^[v-z].*$")) {
                String toWrite = e.getKey() + " - " + e.getValue();
                write(toWrite, 4);
            }

        }
    }

    private void write(String toWrite, int i) {
        String fileName = "";
        if (i == 1) {
            fileName = "A-G.txt";
        } else if (i == 2) {
            fileName = "H-N.txt";
        } else if (i == 3) {
            fileName = "O-U.txt";
        } else {
            fileName = "V-Z.txt";
        }

        try {
            String content = toWrite;
            fileWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
                if (fileWriter != null)
                    fileWriter.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
