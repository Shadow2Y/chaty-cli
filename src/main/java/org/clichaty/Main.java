package org.clichaty;

import picocli.CommandLine;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if(args[0].equals("-i")) {
            System.out.println(" :-: Starting interactive runtime ::-");
            while(true) {
                args = getRuntimeArgs();
                if(args[0].equals("-e")) {
                    System.out.println(" :-: Exiting interactive runtime ::-");
                    return;
                }
                executeArgs(args);
            }
        } else {
            System.out.println("Args are :: " + Arrays.toString(args));
            executeArgs(args);
        }
    }
    public static String[] getRuntimeArgs() {
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();
        return inp.split(" ");
    }
    public static void executeArgs(String[] args) {
        new CommandLine(new CommandParser()).execute(args);
    }
}