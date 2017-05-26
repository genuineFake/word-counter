package org.genuinefake.wordcounter;

public class Main {

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        String[] fileNames = ui.start();
        WordCounter wordCounter = new WordCounter();
        wordCounter.countWordFrequency(fileNames);
        FrequencyWriter frequencyWriter = new FrequencyWriter(wordCounter.getWordMap());
        frequencyWriter.writeToFiles();
    }
}
