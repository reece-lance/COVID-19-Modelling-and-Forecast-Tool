package app;

import java.awt.geom.Point2D;

/**
 *  y = A + Bx
 */
public class MyLine {
    public Double a;
    public Double b;

    public MyLine(Double a, Double b) {
        this.a = a;
        this.b = b;
    }

    public Point2D getPoint(double x){
        return new Point2D.Double(x, b * x + a);
    }

   public double getY(double x){
       return b * x + a;
   }
}
