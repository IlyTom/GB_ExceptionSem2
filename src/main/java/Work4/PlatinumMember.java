package Work4;

public class PlatinumMember extends BankAccount{

    public PlatinumMember(int id, double amount) {
        super(id,amount);

    }

    public double getMaxAmount() {
        return maxAmount;
    }

    private double maxAmount = 5000;

    @Override
    public String getTypeAccount() {
        return "PlatinumMember";
    }
}
