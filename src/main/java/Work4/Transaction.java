package Work4;

public class Transaction {
    private TypeTransaction type;
    private double amount;

    public BankAccount getAccount() {
        return account;
    }

    BankAccount account;
    public Transaction(TypeTransaction type, double amount, BankAccount targetAccountTransaction) {
        this.type = type;
        this.amount = amount;
        this.account = targetAccountTransaction;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
