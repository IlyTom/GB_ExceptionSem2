package Work4;

public class BronzeMember extends BankAccount{

    public BronzeMember(int id, double amount) {
        super(id,amount);
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    private double maxAmount = 1000;

    @Override
    public String getTypeAccount() {
        return "BronzeMember";
    }
}
