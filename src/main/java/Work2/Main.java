package Work2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws  DivisionByZeroException {
    //Напишите программу, которая запрашивает у пользователя два числа и выполняет их деление.
    //Если второе число равно нулю, программа должна выбрасывать исключение DivisionByZeroException
    //с сообщением "Деление на ноль недопустимо". В противном случае, программа должна выводить результат деления.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите первое число: ");
        int num1 = scanner.nextInt();
        System.out.print("Введите второе число: ");
        int num2 = scanner.nextInt();
        double result = divide(num1,num2);
        System.out.println(result);
    }
    public static double divide(int num1, int num2) throws DivisionByZeroException{
        if (num2!=0){
            double result = num1/num2;
            return result;
        }
        else throw new DivisionByZeroException("Деление на ноль недопустимо");
    }
}
