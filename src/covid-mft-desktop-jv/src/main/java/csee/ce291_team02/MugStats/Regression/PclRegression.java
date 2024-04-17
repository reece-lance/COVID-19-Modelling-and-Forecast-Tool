package csee.ce291_team02.MugStats.Regression;

import csee.ce291_team02.Metrics.Metrics;
import csee.ce291_team02.MugLogger;
import csee.ce291_team02.MugStats.Models.PclrSpline;
import csee.ce291_team02.MugStats.util.AccessTarget;
import csee.ce291_team02.MugStats.Models.MugPoint;
import csee.ce291_team02.MugStats.util.Helpers;

import java.util.ArrayList;

import static csee.ce291_team02.MugStats.util.Helpers.Calc.sum;

public class PclRegression {

    private ArrayList<ArrayList<MugPoint>> segments;

    private ArrayList<MugPoint> knots = new ArrayList<>();

    private int iterations = 1;

    private PclrSpline spline;

    private boolean fitted = false;

    /**
     * Initializes piecewise continuous linear regression model.
     * @param segments Segments, collection of points in between knotValues, left-exclusive right-inclusive
     * @param knotValues Knot values, all boundaries of segments.
     */
    public PclRegression(ArrayList<ArrayList<MugPoint>> segments, ArrayList<Double> knotValues) {
        this.segments = segments;

        // Here we set the initial knot values for the pclr regression

        // We assume that the first knot fits and leave it's y (b) value as it is
        knots.add(getFirstKnot());

        // We set the y (b) values of all the inner knots to zero
        for (int i = 1; i < knotValues.size() - 1; i++) {
            knots.add(new MugPoint(knotValues.get(i), 0d));
        }

        // We assume that the last knot fits and leave it's y (b) value as it is
        knots.add(getLastKnot());
    }


/*
* Seems like the spline converges  after 9 iterations in case of the new cases by specimen date data of ukCovidApiClient.
*
MP{a=18264, b=0} MP{a=18324, b=0}   MP{a=18338, b=0} MP{a=18362, b=0} MP{a=18423, b=0} MP{a=18506, b=0} MP{a=18555, b=0} MP{a=18576, b=0} MP{a=18599, b=11992}
MP{a=18264, b=0} MP{a=18324, b=17}  MP{a=18338, b=1628} MP{a=18362,b=3489} MP{a=18423, b=307} MP{a=18506, b=3133}MP{a=18555, b=17545} MP{a=18576, b=21952} MP{a=18599, b=11992}
MP{a=18264, b=0} MP{a=18324, b=-137}MP{a=18338, b=485} MP{a=18362, b=3540} MP{a=18423, b=-624}MP{a=18506, b=113} MP{a=18555, b=15268} MP{a=18576, b=22522} MP{a=18599, b=11992}
MP{a=18264, b=0} MP{a=18324, b=-29} MP{a=18338, b=447} MP{a=18362, b=3891} MP{a=18423, b=187} MP{a=18506, b=283} MP{a=18555, b=15119} MP{a=18576, b=22559} MP{a=18599, b=11992}
MP{a=18264, b=0} MP{a=18324, b=-26} MP{a=18338, b=329} MP{a=18362, b=3607} MP{a=18423, b=199} MP{a=18506, b=308} MP{a=18555, b=15104} MP{a=18576, b=22563} MP{a=18599, b=11992}
MP{a=18264, b=0} MP{a=18324, b=-14} MP{a=18338, b=422} MP{a=18362, b=3590} MP{a=18423, b=195} MP{a=18506, b=312} MP{a=18555, b=15102} MP{a=18576, b=22564} MP{a=18599, b=11992}
MP{a=18264, b=0} MP{a=18324, b=-23} MP{a=18338, b=430} MP{a=18362, b=3590} MP{a=18423, b=194} MP{a=18506, b=312} MP{a=18555, b=15102} MP{a=18576, b=22564} MP{a=18599, b=11992}
MP{a=18264, b=0} MP{a=18324, b=-24} MP{a=18338, b=430} MP{a=18362, b=3590} MP{a=18423, b=194} MP{a=18506, b=313} MP{a=18555, b=15102} MP{a=18576, b=22564} MP{a=18599, b=11992}
MP{a=18264, b=0} MP{a=18324, b=-24} MP{a=18338, b=429} MP{a=18362, b=3590} MP{a=18423, b=194} MP{a=18506, b=313} MP{a=18555, b=15102} MP{a=18576, b=22564} MP{a=18599, b=11992}
* */

    /**
     * Tries to fit the knots to the data via sweeping left to right a set number of times.
     * @param iterations Number of iterations. For example for CovidCaseByDate data the knots seem to converge after 10 iterations.
     */

    public void fitDataSweep(int iterations){
        PclRegression.writePoints(knots);

        for (int iter = 0; iter < iterations; iter++) {
            fitDataSweep();
            PclRegression.writePoints(knots);
        }

        this.spline = new PclrSpline(knots);
        fitted = true;
    }

    /**
     * Sweeps the segments from left to right and fits their knots.
     */
    private void fitDataSweep(){
        for (int i = 0; i < knots.size() - 2; i++) {
            MugPoint k0 = knots.get(i);
            MugPoint k1 = knots.get(i + 1);
            MugPoint k2 = knots.get(i + 2);

            ArrayList<MugPoint> seg0 = segments.get(i);
            ArrayList<MugPoint> seg1 = segments.get(i + 1);

            k1.b = fitKnot(k0, k1, k2, seg0, seg1);
        }
    }


    public ArrayList<MugPoint> getKnots(){
        return knots;
    }


    public PclrSpline getModel(){
        return spline;
    }

    /**
     * Total sum of squares.
     * @return
     */

    public double tss(){
        if(!fitted){
            return 0;
        }

        return Metrics.tss(segments);
    }

    /**
     * Sum of squared residuals
     * @return
     */

    public double rss(){
        if(!fitted){
            return 0;
        }

        return Metrics.rss(segments, spline);
    }
//mean and the current point that is being iterated.
    /**
     * Coefficient of determination
     * @return
     */


    public double rSquared(){
        if(!fitted){
            return 0;
        }
        return Metrics.rSquared(segments, spline);
    }

    @Override
    public String toString() {
        return "PclRegression{RSS=" +  rss() + ",TSS=" + tss() + "rSq="+ rSquared() + "}";
    }

/**
 * Author: Filip Vlcek
 * Peer reviewers: Bin Syed Nasimudin, Dean Tanyaradzwa Makwarimba
 *  SOURCE: https://golovchenko.org/docs/ContinuousPiecewiseLinearFit.pdf
 *  Consultant: Dr Dan Brawn
 */

    /**
     *
     * @param k0 The left hand side knot.
     * @param k1 The center knot who's Y coordinate we are calculating.
     * @param k2 The right hand side knot.
     * @param segment0 Data points belonging to the segment between the left k-1 knot and k0.
     * @param segment1 Data points that belong to the segment between the right k2 knot and k3
     * @return Calculated Y coordinate value of k1.
     */
    private Double fitKnot(MugPoint k0, MugPoint k1, MugPoint k2, ArrayList<MugPoint> segment0, ArrayList<MugPoint> segment1){

        /**
         *         coef1 = (-k0.b * sum(segment0, o -> (o.a - k0.a) * (o.a - k1.a)) + k1.b * sum(segment0, o -> Math.pow(o.a - k0.a, 2))
         *                   / Math.pow(k1.a - k0.a, 2);
         *         coef1 = -k0.b * G * k1.b * H
         *                  / I^2
         *
         *         coef2 = (k1.b * sum(segment1, o -> Math.pow(o.a - k2.a, 2)) - k2.b * sum(segment1, o -> (o.a - k1.a) * (o.a - k2.a)))
         *                  /Math.pow(k2.a - k1.a, 2);
         *         coef2 = k1.b * J - k2.b * K
         *                  / L ^ 2
         *
         *         coef3 = (sum(segment0, o -> o.a * o.b) - k0.a * sum(segment0, o -> o.b))
         *                   / k1.a - k0.a;
         *         coef3 = M/I
         *
         *         coef4 = (-sum(segment1, o -> o.a * o.b) + k2.a * sum(segment1, o -> o.b))
         *                   / k2.a - k1.a;
         *         coef4 = N/L
         *
         *
         *         coef1 + coef2 = coef3 + coef4
         *
         *         If we factor k1.b out, we get:
         *
         *         k1.b = (k0.b + GL^2 + k2.b*KI^2 + MIL^2 + NLI^2)
         *                  / HL^2 + JI^2
         */

        double resultY = (k0.b * sum(segment0, o -> (o.a - k0.a) * (o.a - k1.a)) * Math.pow(k2.a - k1.a, 2)
                + k2.b * sum(segment1, o -> (o.a - k1.a) * (o.a - k2.a)) * Math.pow((k1.a - k0.a), 2)
                + (sum(segment0, o -> (o.a * o.b)) - k0.a * sum(segment0, o -> o.b)) * (k1.a - k0.a) * Math.pow((k2.a - k1.a), 2)
                + (- sum(segment1, o -> o.a * o.b) + k2.a * sum(segment1, o -> o.b)) * (k2.a - k1.a) * Math.pow((k1.a - k0.a), 2))
                / (sum(segment0, o -> Math.pow(o.a - k0.a, 2)) * Math.pow(k2.a - k1.a, 2) + sum(segment1, o -> Math.pow(o.a - k2.a, 2)) * Math.pow(k1.a - k0.a, 2));
        return resultY;
    }

    private static void writePoints(ArrayList<MugPoint> points){
        StringBuilder sb = new StringBuilder();

        for (MugPoint p: points){
            sb.append(p.toString());
        }
        MugLogger.log(sb);
    }

    private MugPoint getFirstKnot(){

        return segments.get(0).get(0);
    }

    private MugPoint getLastKnot(){
        return segments.get(segments.size() - 1).get(segments.get(segments.size() - 1).size() - 1);
    }
}
