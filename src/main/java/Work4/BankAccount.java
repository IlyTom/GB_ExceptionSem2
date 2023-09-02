package Work4;

import java.util.Random;

public class BankAccount {
    public double getMaxAmount() {
        return maxAmount;
    }

    private double maxAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private int id;
    private double amount;
    public BankAccount(int id, double amount){
        this.id = id;
        this.amount = amount;
    }

    public String getTypeAccount() {
        return "BankAccount";
    }



}
