package Lab2;
/**
 * класс трехмерной точки.
 **/
public class Point3d extends Point2d{
    /** Координата Z **/
    private double pointZ;
    /** Конструктор инициализации **/
    public Point3d(double x, double y, double z)
    {
        super(x, y);
        pointZ = z;
    }
    /** Конструктор по умолчанию. **/
    public Point3d()
    {
        this(0, 0, 0);
    }
    /** Возвращение координаты Z **/
    public double getZ() {
        return pointZ;
    }
    /** Установка значения координаты Z **/
    public void setZ(double z) {
        this.pointZ = z;
    }
    /** Сравнение точек **/
    public boolean equals(Point3d newPoint)
    {
        return (this.getX() == newPoint.getX() &&
                this.getY() == newPoint.getY() &&
                this.getZ() == newPoint.getZ());
    }
    /** Вычисление расстояния **/
    public double distanceTo(Point3d nPoint)
    {
        return Math.sqrt(Math.pow((nPoint.getX() - this.getX()),2) +
                         Math.pow((nPoint.getY() - this.getY()),2) +
                         Math.pow((nPoint.getZ() - this.getZ()),2));
    }

}
