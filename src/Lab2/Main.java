package Lab2;

public class Main
{
    public static void main(String[] args)
    {
        //TODO
        /*
        * Здесь создаем три объекта класса Point3d, т.е. - три точки.
        * С помощью метода computeArea по формуле Герона считаем
        * площадь треугаольника по трем точкам.
        * Перед вызовом метода computeArea проверяем на равенство
        * значения всех трех объектов Point3d. Если одна из точек
        * равна другой, то выводим соответствующее сообщение
        * пользователю и не вычисляем площадь.
        */
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
