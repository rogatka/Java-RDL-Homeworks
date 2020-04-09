package hometask.task2;

import java.util.Random;

public class Philosopher implements Runnable {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private final Random random = new Random();

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (leftFork.pickUp(this, "left")) {
                    if (rightFork.pickUp(this, "right")) {
                        eat();
                        rightFork.putDown(this, "right");
                    }
                    leftFork.putDown(this, "left");
                }
                think();
            }
        } catch (InterruptedException e) {
            System.out.println("Philosopher " + this.getId() + " has been interrupted.");
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher #" + id + " is thinking");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher #" + id + " is eating");
        Thread.sleep(random.nextInt(1000));
    }

    public int getId() {
        return id;
    }
}