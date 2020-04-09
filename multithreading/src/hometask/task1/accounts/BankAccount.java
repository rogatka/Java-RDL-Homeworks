package hometask.task1.accounts;

import hometask.task1.banks.Bank;
import hometask.task1.exception.NotEnoughMoneyException;

public class BankAccount implements Runnable {
    private final Bank bank;
    private final int id;

    public BankAccount(int id, Bank bank) {
        this.id = id;
        this.bank = bank;
    }

    @Override
    public void run() {
        int totalAmount;
        while (!Thread.currentThread().isInterrupted() && (totalAmount = bank.getTotalAmount()) > 0) {
            try {
                int amount = (int) (Math.random() * totalAmount + 1);
                bank.withdrawMoney(amount, this);
            } catch (NotEnoughMoneyException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getId() {
        return id;
    }
}
