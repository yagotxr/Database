package com.unibh.logger;

public class Logger {

    private String name;

    public Logger(String name) {
        this.name = name;
    }

    public void info(String msg) {
        System.out.println("[" + name + "] " + msg);
    }
}
