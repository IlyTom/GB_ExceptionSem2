package Work4;

public class SilverMember extends BankAccount{
    public SilverMember(int id, double amount) {
        super(id,amount);
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    private double maxAmount = 1500;

    @Override
    public String getTypeAccount() {
        return "SilverMember";
    }
}
