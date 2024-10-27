package org.chatycli;

import picocli.CommandLine;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Args are :: " + Arrays.toString(args));
        executeArgs(args);
    }

    public static void executeArgs(String[] args) {
        new CommandLine(new CommandParser()).execute(args);
    }
}