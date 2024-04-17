package app.Util;

import java.awt.geom.Point2D;

@FunctionalInterface
public interface SumTarget {
    double getTarget(Point2D point);
}
