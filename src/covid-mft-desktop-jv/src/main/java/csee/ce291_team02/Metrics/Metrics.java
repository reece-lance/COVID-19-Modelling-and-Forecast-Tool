package csee.ce291_team02.Metrics;


import csee.ce291_team02.MugStats.Models.MugPoint;
import csee.ce291_team02.MugStats.Models.PclrSpline;
import csee.ce291_team02.MugStats.util.Helpers;

import java.util.ArrayList;

import static csee.ce291_team02.MugStats.util.Helpers.Calc.sum;

public class Metrics {

    /**
     * Calculates the total number of cases that displays in the metrics column.
     * @param dataArray table data
     * @return
     */

    public static int total(int[] dataArray) {
        int sum = 0;
        for (int data : dataArray) {
            sum += data;
        }
        return sum;
    }

    /**
     * Calculates the total mean number of cases that displays in the metrics column.
     * @param newCases table data
     * @return
     */
    public static double mean(int[] newCases) {
        return total(newCases) / (double) newCases.length;
    }

    public static double standardDeviation(int[] newCases) {
        double sd = 0;
        double arrayLength = newCases.length;
        for (int i = 0; i < arrayLength; i++) {
            sd += Math.pow(i - mean(newCases), 2);
        }
        return Math.sqrt(sd / arrayLength - 1);

    }

    /**
     * Calculates the total mean number of variance that displays in the metrics column.
     * @param newCases table data
     * @return
     */
    public static double variance(int[] newCases) {
        return Math.pow(standardDeviation(newCases), 2);
    }


    /**
     * Calculates the infectionRate that displays in the country comparisons tab.
     * @param cases data
     * @param population data
     * @return
     */
    public static double infectionRate(int cases, int population) {
        return (double) cases / (double) population;
    }

    /**
     * Calculates the deathRate that displays in the country comparisons tab.
     * @param deaths data
     * @param cases data
     * @return
     */
    public static double deathRate(int deaths, int cases) {
        return (double) deaths / (double) cases;
    }

    /**
     * Calculates the recoveryRate that displays in the country comparisons tab.
     * @param recovered table data
     * @param cases table data
     * @return
     */
    public static double recoveryRate(int recovered, int cases) {
        return (double) recovered / (double) cases;
    }


    /**
     * Total sum of squares.
     *
     * @param segments mugpoints
     * @return
     */
    public static double tss(ArrayList<ArrayList<MugPoint>> segments) {
        double segmentsSum = 0;
        int n = 0;
        for (ArrayList<MugPoint> segment : segments) {
            segmentsSum += Helpers.Calc.sum(segment, o -> o.b);
            n++;
        }
        double finalMean = segmentsSum / n;

        double tss = 0;
        for (ArrayList<MugPoint> segment : segments) {
            tss += Helpers.Calc.sum(segment, o -> o.b - finalMean);
            n++;
        }

        return tss;
    }

    /**
     * Total sum of rss.
     *
     * @param segments mugpoints
     * @param model   Pclrspline
     * @return
     */

    public static double rss(ArrayList<ArrayList<MugPoint>> segments, PclrSpline model) {
        double rss = 0;

        for (ArrayList<MugPoint> segment : segments) {
            rss += sum(segment, o -> Math.pow(o.b - model.getY(o.a), 2));
        }

        return rss;
    }

    /**
     * Total sum of rSquared.
     *
     * @param segments mugpoints
     * @param model Pclrspline
     * @return
     */
    public static double rSquared(ArrayList<ArrayList<MugPoint>> segments, PclrSpline model) {
        return 1 - rss(segments, model) / tss(segments);
    }
}

    /*   public static double meanSquaredError(int[] actual, int[] predicted){

        double[] sumPV = new double[actual.length];
        int n = actual.length;
        double sumUT = 0d;

        ///predicted values
        for (int i = 0; i < n; i++) {
            sumPV[i] = intercept(actual,predicted) + (slope(actual,predicted)* actual[i]);
        }

        for(int i =0; i<n ; i++){
            sumUT += Math.pow(actual[i] - sumPV[i],2);

        }

        return sumUT/n;
    }
    public static double range (int [] newCases) {
        int low = newCases[0];
        int highest = newCases[0];

        for (int i = 0; i < newCases.length; i++) {
            if (newCases[i] < low) {
                low = newCases[i];

            }
        }
        for (int i = 0; i < newCases.length; i++) {
            if (newCases[i] > highest) {
                highest = newCases[i];
            }
        }
        return highest - low;
    }*/

    /*    public static double slope(int[] newCases, int[] cumulativeCases){
        return correlation(newCases, cumulativeCases)*standardDeviation(newCases)/standardDeviation(newCases);
    }

    public static double intercept(int[] newCases, int[] cumulativeCases){
        return mean(newCases)-slope(newCases, cumulativeCases)
                *mean(cumulativeCases);
    }*/

 /*   public static double correlation(int[] xArray, int[] yArray) {
        //SumOfX
        double sumX = total(xArray);

        //SumOfY
        double sumY = total(yArray);

        //SumOfXY
        double sumXY = 0;
        for (int i = 0; i < xArray.length; i++) {
            sumXY += xArray[i] * yArray[i];
        }

        //SumOfX2
        double sumX2 = 0;
        for (int i = 0; i < xArray.length; i++) {
            sumX2 += Math.pow(xArray[i], 2);
        }

        //SumOfY2
        double sumY2 = 0;
        for (int i = 0; i < yArray.length; i++) {
            sumY2 += Math.pow(yArray[i], 2);
        }


        double upperPart = (xArray.length * sumXY) - ((sumX) * (sumY)); //n(sumXY) -(sumX)*(sumY)
        double downPart =
                Math.sqrt(((xArray.length * sumX2) - Math.pow(sumX, 2)) * ((xArray.length * sumY2) - Math.pow(sumY, 2))); //sqrt(((n*sumX2)-(sumX)^2) * ((n*sumX2)-(sumX)^2)))

        return upperPart / downPart;

    }*/





