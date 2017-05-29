package org.genuinefake.wordcounter;

import java.util.Scanner;

/**
 * Created by fnord on 17.5.26.
 */
public class UserInterface {
    private Scanner scanner = new Scanner(System.in);

    public String[] start() {
        System.out.println("Enter file names separated by space: ");
        String input = scanner.nextLine();
        return input.split(" ");
    }
}
