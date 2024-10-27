package org.chatycli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.Scanner;

@Command(name = ChatyCliConstant.APP_NAME, description = "A CLI app for network communication.")
public class CommandParser implements Runnable {
    public CommandLine executor;
    @Option(names = {ChatyCliConstant.SERVER_FLAG_SHORT, "--server"}, description = "Connect to the server")
    private String serverArg;
    private void serverFunc(String arg) {
        if (arg.isEmpty()) {
            System.out.println("Not connecting to server...");
        } else {
            System.out.println("Connecting to server... at :: " + arg);
        }
    }

    @Option(names = {ChatyCliConstant.MESSAGE_FLAG_SHORT, "--message"}, description = "User message")
    private String messageArg;
    private void messageFunc(String arg) {
        if (arg.isEmpty()) {
            System.out.println("No message from user...");
        } else {
            System.out.println("Message from user... :: " + arg);
        }
    }

    @Option(names = {ChatyCliConstant.INTERACTIVE_FLAG_SHORT, "--interactive"}, description = "Interactive user session")
    private boolean interactiveArg;
    private void interactiveFunc(boolean arg) {
        if (arg) {
            System.out.println(" :-: Starting interactive runtime ::-");
            while(true) {
                String[] args = getRuntimeArgs();
                if(args[0].equals(ChatyCliConstant.EXIT_FLAG_SHORT)) {
                    System.out.println(" -:: Exiting interactive runtime :-:");
                    return;
                }
                executeArgs(args);
            }
        } else {
            System.out.println("Message from user... :: " + arg);
        }
    }

    public void executeArgs(String[] args) {
        executor.execute(args);
    }
    public static String[] getRuntimeArgs() {
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();
        return inp.split(" ");
    }

    @Override
    public void run() {
        executor = new CommandLine(new CommandParser());
        if(serverArg != null) serverFunc(serverArg);
        if(messageArg != null) messageFunc(messageArg);
        interactiveFunc(interactiveArg);
    }
}