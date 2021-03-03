package Lab1;

import java.util.Scanner;

public class Palindrome
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();

        if (isPalindrome(s))
            System.out.println("Введенная строка является палиндромом");
        else
            System.out.println("Введенная строка не является палиндромом");
    }

    public static String reverseString(String s)
    {
        String reverse = "";

        for (int i = s.length() - 1; i >= 0; i--)
        {
            reverse += s.charAt(i);     //Записываем строку с конца в переменную
        }

        return reverse;
    }

    public static boolean isPalindrome(String s)
    {
        return s.equals(reverseString(s));  //Сравниваем с исходной строкой
    }
}
