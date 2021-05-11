package Lab456;

import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;

    @Override
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -2.5;
        range.width = 4;
        range.height = 4;
    }

    @Override
    public int numIterations(double x, double y)
    {
        double r = x;
        double i = y;
        int count = 0;

        while (count < MAX_ITERATIONS)
        {
            count++;

            double k = r * r - i * i + x;
            double m = Math.abs(2 * r * i) + y;
            r = k;
            i = m;

            if (r*r + i*i > 4) break;
            if (count == MAX_ITERATIONS) return -1;
        }

        return count;
    }

    @Override
    public String toString() {
        return "BurningShip";
    }
}
