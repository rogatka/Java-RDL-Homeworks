package hometask.task1;

import hometask.task1.accounts.BankAccount;
import hometask.task1.banks.Bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final int TOTAL_AMOUNT = 1000_000;
    public static final int NUMBER_OF_ACCOUNTS = 100;
    public static final int TIMEOUT = 2000;

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank(TOTAL_AMOUNT);
//        Bank bank = new LockBank(TOTAL_AMOUNT);

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(NUMBER_OF_ACCOUNTS);
        for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
            fixedThreadPool.execute(new BankAccount(i, bank));
        }

//        waiting 2000ms for threads to stop
        fixedThreadPool.awaitTermination(TIMEOUT, TimeUnit.MILLISECONDS);
        fixedThreadPool.shutdownNow();
        System.out.println("\r\nTotal amount in the Bank is $" + bank.getTotalAmount());
    }
}
