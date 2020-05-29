package hometask;

import java.util.Set;
import java.util.concurrent.*;

public class Main {
    private static final int NUMBER_OF_PRODUCERS = 5;
    private static final int NUMBER_OF_CONSUMERS = 2;
    private static final int TIMEOUT_MILLIS = 100;
    private static final int SQUAD_SIZE = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService producers = Executors.newFixedThreadPool(NUMBER_OF_PRODUCERS);
        ExecutorService consumers = Executors.newFixedThreadPool(NUMBER_OF_CONSUMERS);
        BlockingQueue<Recruit> recruits = new LinkedBlockingQueue<>();
        Set<Recruit> squad = ConcurrentHashMap.newKeySet();

        for (int i = 0; i < NUMBER_OF_PRODUCERS; i++) {
            producers.submit(new RecruitsProducer(recruits));
        }
        for (int i = 0; i < NUMBER_OF_CONSUMERS; i++) {
            consumers.submit(new RecruitsConsumer(recruits,squad));
        }

        while (squad.size() < SQUAD_SIZE) {
            Thread.sleep(TIMEOUT_MILLIS);
        }
        producers.shutdownNow();
        consumers.shutdownNow();
        System.out.println("Squad was formed: ");
        squad.forEach(System.out::println);
    }
}
