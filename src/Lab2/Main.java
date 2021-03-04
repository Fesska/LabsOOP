package Lab2;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        //Вводим точку А
        System.out.println("Введите координату х точки А: ");
        double x1 = input.nextDouble();
        System.out.println("Введите координату y точки А: ");
        double y1 = input.nextDouble();
        System.out.println("Введите координату z точки А: ");
        double z1 = input.nextDouble();
        Point3d A = new Point3d(x1, y1, z1);

        //Вводим точку B
        System.out.println("Введите координату х точки B: ");
        double x2 = input.nextDouble();
        System.out.println("Введите координату y точки B: ");
        double y2 = input.nextDouble();
        System.out.println("Введите координату z точки B: ");
        double z2 = input.nextDouble();
        Point3d B = new Point3d(x2, y2, z2);

        //Вводим точку C
        System.out.println("Введите координату х точки C: ");
        double x3 = input.nextDouble();
        System.out.println("Введите координату y точки C: ");
        double y3 = input.nextDouble();
        System.out.println("Введите координату z точки C: ");
        double z3 = input.nextDouble();
        Point3d C = new Point3d(x3, y3, z3);

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
