package hometask.task3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Buffer {
    private static final int BUFFER_SIZE = 4;
    final static BlockingQueue<Element> queue = new LinkedBlockingQueue<>(BUFFER_SIZE);
    public static final int NUMBER_OF_PRODUCERS = 10;
    public static final int NUMBER_OF_CONSUMERS = 2;
    public static final int TIMEOUT = 10000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService producerService = Executors.newFixedThreadPool(NUMBER_OF_PRODUCERS);
        ExecutorService consumerService = Executors.newFixedThreadPool(NUMBER_OF_CONSUMERS);

        for (int i = 0; i < NUMBER_OF_PRODUCERS; i++) {
            producerService.submit(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Element e = new Element();
                        queue.put(e);
                        System.out.println(Thread.currentThread().getName() + " offered " + e);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        for (int i = 0; i < NUMBER_OF_CONSUMERS; i++) {
            consumerService.submit(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Element e = queue.take();
                        System.out.println(e + " was taken by " + Thread.currentThread().getName());

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        Thread.sleep(TIMEOUT);
        producerService.shutdownNow();
        consumerService.shutdownNow();
    }

    public static class Element {
        private static volatile AtomicLong id = new AtomicLong(0);
        private final long elementId;

        public Element() {
            this.elementId = id.incrementAndGet();
        }

        @Override
        public String toString() {
            return "<ELEM" + elementId + ">";
        }
    }
}
