package org.example;


import org.example.helpers.Scheduler;

public class App {
    public static void main(String[] args) {
        Scheduler scheduler = Scheduler.getInstance();
        scheduler.run();
    }
}
