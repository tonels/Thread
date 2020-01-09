package tonels.bank;

import lombok.Data;

@Data
public class Account {
    private static long idCounter;
    private long id;
    private double value;

    public Account(double d) {
        value = d;
        id = idCounter++;
    }

    public boolean deposit(double amount) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            //never gonna happen
        }
        value += amount;
        return Math.random() < 0.00001;
    }

    public boolean withdraw(double amount) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            //never gonna happen
        }
        if (value > amount) {
            value -= amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean transfer(double amount, Account target) {
        if (withdraw(amount)) {
            if (target.deposit(amount)) {
                return true;
            } else {
                deposit(amount);
                return false;
            }
        } else {
            return false;
        }
    }

    public static Account generateFakeAccount() {
        return new Account(500 + Math.random() * 1500);
    }
}
