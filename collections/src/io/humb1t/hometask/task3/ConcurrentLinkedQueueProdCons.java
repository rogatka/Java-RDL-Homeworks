package io.humb1t.hometask.task3;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueProdCons {
    static Queue<Integer> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        Thread.sleep(1000);

        for (int i = 0; i < 3; i++) {
            new Thread(consumer).start();
        }
    }

    public static class Producer implements Runnable {
        private Queue<Integer> queue;

        public Producer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("Producer starts...");
            int k = 0;
            while (k < 10_000) {
                queue.offer(k);
                System.out.println("Produced " + k);
                k++;
            }
        }
    }

    public static class Consumer implements Runnable {
        private Queue<Integer> queue;

        public Consumer(Queue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("Consumer #" + Thread.currentThread().getName() + " starts...");
            Integer consumed;
            while (true) {
                if ((consumed = queue.poll()) != null) {
                    System.out.println("Consumed " + consumed + " by consumer #" + Thread.currentThread().getName());
                }
            }
        }
    }
}
