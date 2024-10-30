package org.chatycli;

public final class ChatyCliConstant {
    final public static String APP_NAME = "chaty";

    final public static String INTERACTIVE_FLAG_SHORT = "-i";
    final public static String EXIT_FLAG_SHORT = "-e";
    final public static String HELP_FLAG_SHORT = "-h";
    final public static String SERVER_FLAG_SHORT = "-s";
    final public static String MESSAGE_FLAG_SHORT = "-m";
    final public static String LOGIN_FLAG_SHORT = "-l";
    final public static String CONFIGURE_FLAG_SHORT = "-c";

    final public static String INTERACTIVE_FLAG_LONG = "--interactive";
    final public static String EXIT_FLAG_LONG = "--exit";
    final public static String HELP_FLAG_LONG = "--help";
    final public static String SERVER_FLAG_LONG = "--server";
    final public static String MESSAGE_FLAG_LONG = "--message";
    final public static String LOGIN_FLAG_LONG = "login";
    final public static String CONFIGURE_FLAG_LONG = "configure";

    public enum MESSAGE_STATUS {UNSENT,SENT,RECEIVED,READ}
}
