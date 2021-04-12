package com.chatty.data;

import org.fusesource.jansi.Ansi;

public class User {
    private String name;
    private Ansi.Color color;

    public User(String name, Ansi.Color color) {
        this.name = name;
        this.color = color;
    }

    /**
     *The getName method allows the name variable to be accessed by other classes.
     */
    public String getName() {
        return name;
    }

    /**
     *The getColor method allows the color variable to be accessed by other classes.
     */
    public Ansi.Color getColor() {
        return color;
    }
}
