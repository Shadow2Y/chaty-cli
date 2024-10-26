package org.clichaty;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "chaty", description = "A CLI app for network communication.")
public class CommandParser implements Runnable {

    @Option(names = {"-s", "--server"}, description = "Connect to the server")
    private String serverArg;
    private void serverFunc(String arg) {
        if (arg.isBlank()) {
            System.out.println("Not connecting to server...");
        } else {
            System.out.println("Connecting to server... at :: " + arg);
        }
    }

    @Option(names = {"-m", "--message"}, description = "User message")
    private String messageArg;
    private void messageFunc(String arg) {
        if (arg.isEmpty()) {
            System.out.println("No message from user...");
        } else {
            System.out.println("Message from user... :: " + arg);
        }
    }

    @Override
    public void run() {
        serverFunc(serverArg);
        messageFunc(messageArg);
    }
}