package org.chatycli;

import org.chatycli.helper.SessionController;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.util.Scanner;

@Command(name = ChatyCliConstant.APP_NAME, description = "A CLI app for network communication.")
public class CommandParser implements Runnable {
    @Option(names = {ChatyCliConstant.LOGIN_FLAG_SHORT, ChatyCliConstant.LOGIN_FLAG_LONG}, description = "Login to user")
    private boolean loginArg;
    private void loginFunc(boolean arg) {
        if(SessionController.isLoggedIn()) {
            System.out.println(":: Using account '"+SessionController.getUsername()+"' :: ");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username :: ");
        String username = scanner.nextLine();
        System.out.print("Password :: ");
        String password = scanner.nextLine();
        System.out.println(":: Logging into User session :: ");
        SessionController.loginUser(username,password);
    }

    public CommandLine executor;
    @Option(names = {ChatyCliConstant.SERVER_FLAG_SHORT, ChatyCliConstant.SERVER_FLAG_LONG}, description = "Connect to the server")
    private String serverArg;
    private void serverFunc(String arg) {
        if (arg.isEmpty()) {
            System.out.println("Not connecting to server...");
        } else {
            System.out.println("Connecting to server... at :: " + arg);
        }
    }

    @Option(names = {ChatyCliConstant.MESSAGE_FLAG_SHORT, ChatyCliConstant.MESSAGE_FLAG_LONG}, description = "User message")
    private String messageArg;
    private void messageFunc(String arg) {
        if (arg.isEmpty()) {
            System.out.println("No message from user...");
        } else {
            System.out.println("Message from user... :: " + arg);
        }
    }

    @Option(names = {ChatyCliConstant.INTERACTIVE_FLAG_SHORT, ChatyCliConstant.INTERACTIVE_FLAG_LONG}, description = "Interactive user session")
    private boolean interactiveArg;
    private void interactiveFunc(boolean arg) {
        if (arg) {
            System.out.println(" :-: Starting interactive runtime ::-");
            while(true) {
                String[] args = getRuntimeArgs();
                if(args[0].equals(ChatyCliConstant.EXIT_FLAG_SHORT) || args[0].equals(ChatyCliConstant.EXIT_FLAG_LONG)) {
                    System.out.println(" -:: Exiting interactive runtime :-:");
                    return;
                }
                executeArgs(args);
            }
        } else {
            System.out.println("Exiting interactive session :: ");
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
        if(executor==null) executor = new CommandLine(new CommandParser());

        loginFunc(loginArg);
        if(serverArg != null) serverFunc(serverArg);
        if(messageArg != null) messageFunc(messageArg);
        if(interactiveArg) interactiveFunc(interactiveArg);
    }
}