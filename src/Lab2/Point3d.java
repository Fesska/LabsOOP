package Lab2;
/**
 * класс трехмерной точки.
 **/
public class Point3d {
    /** Координата X **/
    private double pointX;
    /** Координата Y **/
    private double pointY;
    /** Координата Z **/
    private double pointZ;
    /** Конструктор инициализации **/
    public Point3d(double x, double y, double z)
    {
        pointX = x;
        pointY = y;
        pointZ = z;
    }
    /** Конструктор по умолчанию. **/
    public Point3d()
    {
        this(0, 0, 0);
    }
    /** Возвращение координаты X **/
    public double getPointX() {
        return pointX;
    }
    /** Возвращение координаты X **/
    public double getPointY() {
        return pointY;
    }
    /** Возвращение координаты X **/
    public double getPointZ() {
        return pointZ;
    }
    /** Установка значения координаты X. **/
    public void setPointX(double x) {
        this.pointX = x;
    }
    /** Установка значения координаты X. **/
    public void setPointY(double y) {
        this.pointY = y;
    }
    /** Установка значения координаты X. **/
    public void setPointZ(double z) {
        this.pointZ = z;
    }
    public double distanceTo(Point3d point1, Point3d point2)
    {
        return 0;
        //TODO
    }
}
