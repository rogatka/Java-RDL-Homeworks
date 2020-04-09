package hometask.task1.banks;

import hometask.task1.accounts.BankAccount;
import hometask.task1.exception.NotEnoughMoneyException;

public class Bank {
    private volatile int totalAmount;

    public Bank() {
    }

    public Bank(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    private boolean hasEnoughMoney(int amount) {
        if (amount <= totalAmount) {
            System.out.println("Processing...");
            return true;
        }
        return false;
    }
    public int getTotalAmount() {
        return totalAmount;
    }

    public /*synchronized*/ void withdrawMoney(int amount, BankAccount account) throws NotEnoughMoneyException {
        System.out.printf("<Attempt to withdraw [$%d] from [Account#%d]. Total amount in the bank is [$%d]>\r\n", amount, account.getId(), getTotalAmount());
        if (hasEnoughMoney(amount)) {
            if (amount <= totalAmount) {
                totalAmount -= amount;
                System.out.printf("Successfully withdraw-operation [$%d] on [Account#%d].Total amount in the Bank is [$%d]\r\n", amount, account.getId(), getTotalAmount());
            } else {
                throw new NotEnoughMoneyException("Attempt to withdraw [$" + amount + "], but actual total amount is [$" + totalAmount + "]");
            }
        }
    }
}
