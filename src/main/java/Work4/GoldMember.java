package Work4;

public class GoldMember extends BankAccount {

    public GoldMember(int id, double amount) {
        super(id,amount);
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    private double maxAmount = 2000;

    @Override
    public String getTypeAccount() {
        return "GoldMember";
    }
}
