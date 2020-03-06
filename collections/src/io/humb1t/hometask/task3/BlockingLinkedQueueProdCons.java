package io.humb1t.hometask.task3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingLinkedQueueProdCons {
    static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();

        for (int i = 0; i < 3; i++) {
            new Thread(consumer).start();
        }
    }

    public static class Producer implements Runnable {
        private BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("Producer starts...");
            int k = 0;
            while (true) {
                try {
                    queue.put(k);
                    System.out.println("Produced " + k);
                    k++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //DO NOTHING
                }
            }
        }
    }

    public static class Consumer implements Runnable {
        private BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println("Consumer #" + Thread.currentThread().getName() + " starts...");
            Integer consumed;
            while (true) {
                try {
                    consumed = queue.take();
                    System.out.println("Consumed " + consumed + " by consumer #" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    //DO NOTHING
                }
            }
        }
    }
}
