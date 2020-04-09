package hometask.task2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private final int id;
    private Lock lock = new ReentrantLock();

    public Fork(int id) {
        this.id = id;
    }

    public boolean pickUp(Philosopher philosopher, String fork) {
        if (lock.tryLock()) {
            System.out.println("Philosopher #" + philosopher.getId() + " picked up " + fork + " Fork#" + id);
            return true;
        }
        return false;
    }

    public void putDown(Philosopher philosopher, String fork) {
        lock.unlock();
        System.out.println("Philosopher #" + philosopher.getId() + " put down " + fork + " Fork#" + id);
    }
}
