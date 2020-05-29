package hometask;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

@RequiredArgsConstructor
public class RecruitsConsumer implements Runnable {
    @NonNull private BlockingQueue<Recruit> recruits;
    @NonNull private Set<Recruit> squad;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Recruit recruit = recruits.take();
                if (recruit.getAge() >= 18 && recruit.getAge() < 28 && recruit.isFit()) {
                    System.out.println(recruit + " was added to squad.");
                    squad.add(recruit);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
