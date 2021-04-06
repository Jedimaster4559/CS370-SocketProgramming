package com.chatty.data;

import org.fusesource.jansi.Ansi;

public class User {
    private String name;
    private Ansi.Color color;

    public User(String name, Ansi.Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Ansi.Color getColor() {
        return color;
    }
}
