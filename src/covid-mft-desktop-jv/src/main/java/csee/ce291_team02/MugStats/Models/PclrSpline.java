package csee.ce291_team02.MugStats.Models;

import java.util.ArrayList;

/**
 * Represents a piecewise continuous linear regression model. Essentially
 * combines multiple line segments and is able to then retrieve yAxis values
 * depending on into which segment does the xAxis value falls. Also used to
 * predict yAxis values that would result in extending the last segment.
 */
public final class PclrSpline extends MugSpline{
    private MugLine lastSegment;
    private MugPoint lastKnot;

    public PclrSpline(ArrayList<MugPoint> knots) {
        super(knots);

        lastSegment = segments.get(segments.size() - 1);
        lastKnot = knots.get(knots.size() - 1);
    }

/**
 * {@inheritDoc}
 *  @param x Given point X of xAxis.
 *  @return Non-negative value at a given point X.
 *  @see csee.ce291_team02.MugStats.Models.MugLine
 */
    @Override
    public double getY(double x) {
        double result = super.getY(x);
        return result > 0 ? result : 0;
    }

    /**
     * Uses the last segment to acquire yAxis values which follow the its angle.
     * @param x X value beyond the range of the last segment.
     * @return Y value prediction based on the extension of the last segment.
     */
    public MugPoint getPrediction(double x){

        return new MugPoint(lastKnot.a + x, lastSegment.getY(lastKnot.a + x));
    }
}
