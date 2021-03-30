package com.chatty.util;

public class Debug {
    private static boolean enable = true;

    public static void error(Object message){
        System.out.println(message);
    }

    public static void log(Object message){
        if(enable) {
            System.out.println(message);
        }
    }
}
