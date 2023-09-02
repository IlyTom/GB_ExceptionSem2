package Work4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Bank {
    Random random = new Random();
    private String name;
    public Bank(String name) {      //Конструктор
        this.name = name;
    }

    private ArrayList<BankAccount> accounts = new ArrayList<BankAccount>(); //Список счетов

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    private ArrayList<Transaction> transactions= new ArrayList<Transaction>(); //Список транзакций

    public void createRandomAccount() {     //Создание случайного счета
        int index = random.nextInt(4);
        switch (index) {
            case 0:
                accounts.add(createBronzeMemberAccount());
                break;
            case 1:
                accounts.add(createSilverMemberAccount());
                break;
            case 2:
                accounts.add(createGoldMemberAccount());
                break;
            case 3:
                accounts.add(createPlatinumMemberAccount());
                break;
        }
    }
    public void generateRandomAccounts(int numAccount){            //Создание нескольких случайных счетов
        for(int i=0; i<numAccount;i++){
            createRandomAccount();
        }
    }
    public BankAccount createBronzeMemberAccount(){ //Создание "бронзового" счета
        return new BronzeMember(accounts.size(), random.nextInt(1000));
    }
    public BankAccount createSilverMemberAccount(){ //Создание "серебрянного" счета
        return new SilverMember(accounts.size(), random.nextInt(1500));
    }
    public BankAccount createGoldMemberAccount(){ //Создание "золотого" счета
        return new GoldMember(accounts.size(), random.nextInt(2000));
    }
    public BankAccount createPlatinumMemberAccount(){ //Создание "платинового" счета
        return new PlatinumMember(accounts.size(), random.nextInt(5000));
    }

    public void seeAllAccounts(){   //Отображение всех счетов
        for(BankAccount account : accounts){
            System.out.println("id account:"+ account.getId()+"\t"+"Account amount:"+account.getAmount()+"\t"+ account.getTypeAccount());
        }
    }
    public void addTransaction(Transaction transaction){ //Добавление транзакции
        transactions.add(transaction);
    }
    public void addRandomTransaction(){ // Добавление случайной транзакции
        transactions.add(createRandomTransaction());
    }
    public Transaction createRandomTransaction(){ //Создание случайной транзакции
        int index = random.nextInt(2);
        BankAccount targetAccount = accounts.get(random.nextInt(accounts.size()));
        double amount = random.nextInt(2500);
        switch (index) {
            case 0:
                return new Transaction(TypeTransaction.Replenishment, amount, targetAccount);
            case 1:
                return new Transaction(TypeTransaction.Discarding,amount, targetAccount);
        }
        return null;
    }
    public void generateRandomTransactions(int transactionNumbers){ //Создание случайных транзакций
        for (int i = 0; i < transactionNumbers; i++) {
            transactions.add(createRandomTransaction());
        }
    }
    public void seeAllTransactions(){ //Отображение всех транзакций
        for(Transaction transaction : transactions){
            System.out.println(transaction.getType()+"\t\t Amount: "+transaction.getAmount()+"\t\t TargetAccountId:"+transaction.getAccount().getId());
        }
    }
    public void runOneTransaction(Transaction transaction){  //Запуск одной транзакции
        if (transaction.getType() == TypeTransaction.Discarding){
            try{
                if (transaction.getAccount().getAmount()>=transaction.getAmount()){
                    transaction.getAccount().setAmount(transaction.getAccount().getAmount()-transaction.getAmount());
                }
                else throw new InsufficientFundsException("Сумма снятия("+transaction.getAmount()+") превышает " +
                        "доступные на счете (id="+transaction.getAccount().getId()+") средства");
            } catch (InsufficientFundsException e) {
                System.out.println(e.getMessage());
            }
        }
        else if (transaction.getType() == TypeTransaction.Replenishment){
            try{
                if (transaction.getAccount().getAmount()+transaction.getAmount()<=transaction.getAccount().getMaxAmount()){
                    transaction.getAccount().setAmount(transaction.getAmount()+transaction.getAccount().getAmount());
                }
                else throw new MaxBalanceExceededException("Превышено максимальное значение "+
                        transaction.getAccount().getMaxAmount()+" баланса счета (id="+transaction.getAccount().getId()+").");
            }
            catch (MaxBalanceExceededException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
    public void runTransactions(){    //Запуск всех транзакций без многопоточности
        for (Transaction transaction: transactions){
                runOneTransaction(transaction);
        }
    }

    public void runAllTransactionMultiThread(ArrayList<Transaction> list) //Запуск всех транзакций в мультипотоке
    {
        ExecutorService execut = Executors.newFixedThreadPool(4);

        List<List<Transaction>> transactionParts = new ArrayList<List<Transaction>>();
        int parts = list.size()/4;

        for (int i = 0; i < 4; i++){
            int start = i*parts;
            int end;
            if (i==parts-1){
                end = list.size();
            }
            else{
                end = (i+1)*parts;
            }
            transactionParts.add(list.subList(start, end));
        }

        for (final List<Transaction> part: transactionParts){
            execut.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());

                    for (Transaction transaction:part){
                        runOneTransaction(transaction);
                    };
                }
            });
        }
        execut.shutdown();
        try {
            execut.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException e) {
            System.err.println(e);
        }
    }
}



