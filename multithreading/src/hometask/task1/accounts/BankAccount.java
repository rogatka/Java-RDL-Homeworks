package hometask.task1.accounts;

import hometask.task1.banks.Bank;
import hometask.task1.exception.NotEnoughMoneyException;

public class BankAccount implements Runnable {
    private final Bank bank;
    private int id;

    public BankAccount(int id, Bank bank) {
        this.id = id;
        this.bank = bank;
    }

    @Override
    public void run() {
        while (bank.getTotalAmount() > 0) {
            int amount = (int) (Math.random() * 1000 + 1);
            withdrawMoney(amount);
        }
    }

    public void withdrawMoney(int amount) {
        try {
            bank.withdrawMoney(amount, this);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }
}
