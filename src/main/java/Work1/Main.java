package Work1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidNumberException {
//Напишите программу, которая запрашивает у пользователя число и проверяет, является ли оно положительным.
//Если число отрицательное или равно нулю, программа должна выбрасывать исключение InvalidNumberException
//с сообщением "Некорректное число". В противном случае, программа должна выводить сообщение "Число корректно".
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите положительное число: ");
        int num = sc.nextInt();
        if (num >= 0) System.out.println("Число корректно");
        else throw new InvalidNumberException("Некорректное число");
    }
}
