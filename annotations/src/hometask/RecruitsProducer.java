package hometask;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingQueue;

@RequiredArgsConstructor
public class RecruitsProducer implements Runnable {
    public static final int HIGHEST_BORDER = 46;
    public static final int LOWEST_BORDER = 18;
    private static final int SLEEPTIME_MILLIS = 1000;
    private static volatile int counter = 1;
    @NonNull private BlockingQueue<Recruit> recruits;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int id = (int) (Math.random() * (HIGHEST_BORDER - LOWEST_BORDER) + LOWEST_BORDER);
                boolean isFit = Math.random() >= 0.5;
                Recruit recruit = new Recruit("User #" + counter++, id, Sex.values()[id % 2], isFit);
                recruits.put(recruit);
                System.out.println(recruit + " has arrived.");
                Thread.sleep(SLEEPTIME_MILLIS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
