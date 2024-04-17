package csee.ce291_team02.MugStats.Models;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a series of ordered points joining int oa spline.
 */
public class MugSpline {
    protected ArrayList<MugLine> segments = new ArrayList<>();
    protected ArrayList<MugPoint> knots;

    public MugSpline(ArrayList<MugPoint> knots) {

        this.knots = knots;

        Collections.sort(this.knots);

        for (int i = 0; i < knots.size() - 1; i++) {
            segments.add(new MugLine(knots.get(i), knots.get(i + 1)));
        }
    }

    /**
     * Get yAxis value for points within its segments that corresponds to left to right ordered inner collection of {@link csee.ce291_team02.MugStats.Models.MugLine}s
     * @param x Given point X of xAxis.
     * @return Value at a given point X.
     * @see csee.ce291_team02.MugStats.Models.MugLine
     */
    public double getY(double x){

        return  segments.get(getSegmentIndex(x)).getY(x);
    }
    
    private int getSegmentIndex(double x){
        for (int i = 1; i <= knots.size() - 2; i++) {
            if (x < knots.get(i).a){
                return i - 1;
            }
        }
        return segments.size() - 1;
    }
}
