package app;

import app.Util.SumTarget;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.ScatterPlot;
import tech.tablesaw.plotly.components.Axis;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Line;
import tech.tablesaw.plotly.traces.ScatterTrace;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Point2D p1 = new Point2D.Double(2, 1);
        Point2D p2 = new Point2D.Double(4, 9);
        Point2D p3 = new Point2D.Double(5, 13);
        Point2D p4 = new Point2D.Double(4, 9);
        Point2D p5 = new Point2D.Double(6, 7);
        Point2D p6 = new Point2D.Double(7, 19);
        Point2D p7 = new Point2D.Double(8, 13);
        Point2D p8 = new Point2D.Double(9, 20);
        Point2D p9 = new Point2D.Double(10, 17);

        var data = new ArrayList<Point2D>();

        data.add(p1);
        data.add(p2);
        data.add(p3);
        data.add(p4);
        data.add(p5);
        data.add(p6);
        data.add(p7);
        data.add(p8);
        data.add(p9);

        System.out.println(Sum(data, p -> p.getX()));
        System.out.println(Sum(data, p -> p.getY()));
        Table table = null;
        try {
            table = tableCreation(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        var a = ScatterPlot.create("Random ass points", table, "X values", "Y values");
        Plot.show(
                ScatterPlot.create("Random ass points", table, "X values", "Y values"));

        var myLine = linearRegression(data);

        var xMin = data.stream().min(Comparator.comparingDouble(x -> x.getX())).get().getX();
        var xMax = data.stream().max(Comparator.comparingDouble(x -> x.getX())).get().getX();
        var rlp1 = myLine.getPoint(xMin);
        var rlp2 = myLine.getPoint(xMax);

        Layout layout1 =
                Layout.builder()
                        .title("Linear Regression")
                        .xAxis(Axis.builder().title("X values").build())
                        .yAxis(Axis.builder().title("Y values").build())
                        .build();

        ScatterTrace trace1 = ScatterTrace.builder(
                table.doubleColumn("X values"),
                table.doubleColumn("Y values"))
                .name("pointus")
                .build();

        ScatterTrace trace2 = ScatterTrace.builder(
                new double[]{rlp1.getX(), rlp2.getX()},
                new double[]{rlp1.getY(), rlp2.getY()}
                )
                .mode(ScatterTrace.Mode.LINE)
                .line(Line.builder().shape(Line
                        .Shape.SPLINE)
                        .smoothing(1.2)
                        .build())
                .name("regression line")
                .build();

        Figure figure1 = new Figure(layout1, trace1);
        figure1.setTraces(trace1, trace2);


        Plot.show(figure1);


        System.out.println(myLine.a);
        System.out.println(myLine.b);

    }

    public static MyLine linearRegression(ArrayList<Point2D> data) {
        var xSum = Sum(data, p -> p.getX());
        var ySum = Sum(data, p -> p.getY());

        var xySum = Sum(data, p -> p.getX()*p.getY());

        var xSumSq = Math.pow(xSum, 2);
        var ySumSq = Math.pow(ySum, 2);

        var sqXSum = Sum(data, p -> Math.pow(p.getX(), 2));
        var sqYSum = Sum(data, p -> Math.pow(p.getY(), 2));

        var n = data.size();

//        var a = (ySum * sqXSum - xSum * xySum)/(n * sqXSum - xSumSq);
        var a = getACoefficient(xSum, ySum, xySum, sqXSum, xSumSq, n);
//        var b = (n * xySum - xSum * ySum)/(n * sqXSum - xSumSq);
        var b = getBCoefficient(xSum, ySum, xySum, sqXSum, xSumSq, n);

        System.out.println(String.format("A: %f, B: %f", a, b));
        var result = new MyLine(a, b);

        return result;
    }

    private static double getACoefficient(double xSum, double ySum, double xySum, double sqXSum, double xSumSq, int n) {
        return (ySum * sqXSum - xSum * xySum) / (n * sqXSum - xSumSq);
    }

    private static double getBCoefficient(double xSum, double ySum, double xySum, double sqXSum, double xSumSq, int n) {
        return (n * xySum - xSum * ySum) / (n * sqXSum - xSumSq);
    }

    public static double Sum(ArrayList<Point2D> points, SumTarget target) {
        double result = 0;

        for (var point : points) {
            result += target.getTarget(point);
        }

        return result;
    }

    public static Table tableCreation(ArrayList<Point2D> data) throws IOException {
        ArrayList<Double> yArray = new ArrayList<>();
        ArrayList<Double> xArray = new ArrayList<>();

        for (int i=0; i < data.size(); i++){
            yArray.add(data.get(i).getY());
            xArray.add(data.get(i).getX());
        }

        DoubleColumn yColumn = DoubleColumn.create("Y values", yArray);
        DoubleColumn xColumn = DoubleColumn.create("X values", xArray);

        return Table.create("name", yColumn, xColumn);
    }
}
