package csee.ce291_team02.MugStats.Regression;

import csee.ce291_team02.MugLogger;
import csee.ce291_team02.MugStats.Models.MugLine;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

public class Regression {
    public static class LinearRegression {

        /* handcrafted method for getting a linear regression model*/
//        public static MugLine getLine(List<Point2D> data) {
//            var xSum = Sum(data, p -> p.getX());
//            var ySum = Sum(data, p -> p.getY());
//
//            var xySum = Sum(data, p -> p.getX() * p.getY());
//
//            var xSumSq = Math.pow(xSum, 2);
//
//            var sqXSum = Sum(data, p -> Math.pow(p.getX(), 2));
//
//            var n = data.size();
//
//            var a = getACoefficient(xSum, ySum, xySum, sqXSum, xSumSq, n);
//            var b = getBCoefficient(xSum, ySum, xySum, sqXSum, xSumSq, n);
//
//            var result = new MugLine(a, b);
//
//            return result;
//        }

        /* Method for getting a linear regression model that leverages TableSaw data frame */
        public static MugLine getLine(Table table, String xName, String yName) {

            DoubleColumn xColumn = table.doubleColumn(xName);
            DoubleColumn yColumn = table.doubleColumn(yName);

            double xSum = xColumn.sum();
            double ySum = yColumn.sum();

            double xySum = xColumn.multiply(yColumn).sum();

            double xSumSq = Math.pow(xSum, 2);

            double sqXSum = xColumn.sumOfSquares();


            int n = table.rowCount();

            double a = getACoefficient(xSum, ySum, xySum, sqXSum, xSumSq, n);
            double b = getBCoefficient(xSum, ySum, xySum, sqXSum, xSumSq, n);

            MugLine result = new MugLine(a, b);

            MugLogger.log(String.format("A: %f, B: %f", a, b));
            return result;
        }

        // A (∑yi - m*(∑xi)) / n
        private static double getACoefficient(double xSum, double ySum, double xySum, double sqXSum, double xSumSq, int n) {
            return (ySum * sqXSum - xSum * xySum) / (n * sqXSum - xSumSq);
        }

        // B(n*∑xi yi - (∑xi)*(∑yi)) / (n*∑xi2 - (∑xi)2)
        private static double getBCoefficient(double xSum, double ySum, double xySum, double sqXSum, double xSumSq, int n) {
            return (n * xySum - xSum * ySum) / (n * sqXSum - xSumSq);
        }

    }
}
