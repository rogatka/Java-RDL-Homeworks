package hometask.task2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int NUMBER_OF_PHILOSOPHERS = 5;
    private static final int TIMEOUT = 1000 * 10;

    public static void main(String[] args) throws InterruptedException {
        Philosopher[] philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];
        Fork[] forks = new Fork[NUMBER_OF_PHILOSOPHERS];
        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            forks[i] = new Fork(i);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_PHILOSOPHERS);
        for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % NUMBER_OF_PHILOSOPHERS]);
            executorService.submit(philosophers[i]);
        }
        Thread.sleep(TIMEOUT);
        executorService.shutdownNow();
    }
}
