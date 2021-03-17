package Lab2;

import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        //Вводим точку А
        System.out.println("Введите координаты точки A (через пробел): ");
        String lineA = input.nextLine();
        String [] elementsA = lineA.split(" ");
        double [] arrayA = Arrays.stream(elementsA).mapToDouble(Double::parseDouble).toArray();
        Point3d A = new Point3d(arrayA[0], arrayA[1], arrayA[2]);

        //Вводим точку B
        System.out.println("Введите координаты точки B (через пробел): ");
        String lineB = input.nextLine();
        String [] elementsB = lineB.split(" ");
        double [] arrayB = Arrays.stream(elementsB).mapToDouble(Double::parseDouble).toArray();
        Point3d B = new Point3d(arrayB[0], arrayB[1], arrayB[2]);

        //Вводим точку C
        System.out.println("Введите координаты C (через пробел): ");
        String lineC = input.nextLine();
        String [] elementsC = lineC.split(" ");
        double [] arrayC = Arrays.stream(elementsC).mapToDouble(Double::parseDouble).toArray();
        Point3d C = new Point3d(arrayC[0], arrayC[1], arrayC[2]);

        //Проверяем, введены ли одинаковые точки
        if (A.equals(B) || A.equals(C) || B.equals(C)) {
            System.out.println("Ошибка! Невозможно построить треугольник по этим точкам.");
            return;
        }

        //Считаем площадь и выводим ответ
        System.out.println("Площадь заданного треугольника: " + computeArea(A, B, C));
    }

    /** Площадь треугольника по формуле Герона **/
    public static double computeArea(Point3d A, Point3d B, Point3d C)
    {
        //Считаем стороны треугольника
        double AB = A.distanceTo(B);
        double AC = A.distanceTo(C);
        double BC = B.distanceTo(C);

        double p = (AB + AC + BC)/2;    //Вычисляем полупериметр

        return Math.sqrt(p * (p - AB) * (p - AC) * (p - BC));
    }
}
