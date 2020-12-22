package com.antecedentium.worker;

import java.util.ArrayList;

public class Worker {
    public static ArrayList<Worker> workers = new ArrayList<>();
    private Thread thread;
    private int delay;

    public Worker(int delay) {
        this.delay = delay;
        reInit();
    }

    public void run() {}
    public void stop() { this.thread.stop(); Worker.workers.remove(this); }
    public void reInit() {
        thread = new Thread(() -> {
            run();
            while (thread.isAlive()) {
                try {
                    Thread.sleep(delay);
                    run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Worker.workers.add(this);
    }
}