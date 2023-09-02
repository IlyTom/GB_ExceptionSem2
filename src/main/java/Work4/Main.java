package Work4;

public class Main {
// __
// Создание многопоточной программы для обработки банковских транзакций с использованием пользовательских классов исключений.
// __
// Описание задачи:
// Вы разрабатываете многопоточную программу для обработки банковских транзакций. Вам необходимо реализовать класс "BankAccount",
// который представляет счет в банке и содержит баланс пользователя. Класс "BankAccount" должен поддерживать две операции:
// пополнение счета и снятие денег. Однако, у каждого счета есть ограничение по максимальному балансу, и попытка пополнения или снятия средств,
// превышающих это ограничение, должна вызывать соответствующее исключение.
// __
// Вам также необходимо реализовать класс "Bank" для управления счетами и обработки транзакций.
// Класс "Bank" должен содержать методы для создания новых счетов, пополнения и снятия денег с счетов.
// __
// Для обработки ошибок исключений, вы должны создать два пользовательских класса исключений:
// __
//"InsufficientFundsException" - выбрасывается при попытке снятия средств, превышающих доступный баланс на счете.
//"MaxBalanceExceededException" - выбрасывается при попытке пополнения счета, превышающего максимальное допустимое значение баланса.
//            Класс "Bank" должен использовать многопоточность, чтобы обрабатывать транзакции с разных счетов параллельно.
    public static void main(String[] args) {
        Bank AviBank = new Bank("AviBank");

        AviBank.generateRandomAccounts(10); //Создание счетов
        AviBank.seeAllAccounts();
        System.out.println("=".repeat(50));

        AviBank.generateRandomTransactions(666);//Создание транзакций
        AviBank.seeAllTransactions();
        System.out.println("=".repeat(50));

        //AviBank.runTransactions();  //Запуск транзакций в однопотоке
        AviBank.runAllTransactionMultiThread(AviBank.getTransactions());//Запуск транзакций в многопотоке
        System.out.println("=".repeat(50));

        AviBank.seeAllAccounts(); //Результат транзакций

    }
}
