package csee.ce291_team02.MugGraphs;

import csee.ce291_team02.AppConstants;
import csee.ce291_team02.MugStats.Models.MugLine;
import csee.ce291_team02.MugStats.Models.MugPoint;
import csee.ce291_team02.MugStats.Regression.Regression;
import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Axis;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Line;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;

import java.time.LocalDate;
import java.util.ArrayList;

public class LinearRegressionGraphs {

    /**
     * Displays daily cases by specimen date using linear regression model. The input data needs to be sorted by days.
     * @param data                  arraylist of the data points
     * @param dates                 arraylist of local dates
     * @param daysToPredict         the number of days to be predicted in prediction model
     *
     * @return Figure
     */
    public static Figure dailyCasesBySpecimenDate(ArrayList<MugPoint> data, ArrayList<LocalDate> dates, int daysToPredict) {

        String xDoubleName = "numeric date";
        String yDoubleName = "daily cases";
        String xDateName = "string date";

        /* Sorting the graph data*/
        ArrayList<Double> yDoubleArray = new ArrayList<Double>();
        ArrayList<Double> xDoubleArray = new ArrayList<Double>();
        ArrayList<LocalDate> xDateArray = new ArrayList<LocalDate>();
        for (int i = 0; i < data.size(); i++) {
            yDoubleArray.add(data.get(i).b);
            xDoubleArray.add(data.get(i).a);
            xDateArray.add(dates.get(i));
        }

        /* Creating a table of data*/
        DoubleColumn yDoubleColumn = DoubleColumn.create(yDoubleName, yDoubleArray);
        DoubleColumn xDoubleColumn = DoubleColumn.create(xDoubleName, xDoubleArray);
        DateColumn xDateColumn = DateColumn.create(xDateName, xDateArray);
        Table dataTable = Table.create(xDateColumn, xDoubleColumn, yDoubleColumn);

        String dataLabel = "cases";
        String regressionLineLabel = "model";
        String predictionLineLabel = "prediction";
        String graphName = "Daily Cases By Specimen Date";

        MugLine myLine = Regression.LinearRegression.getLine(dataTable, xDoubleName, yDoubleName);

        /* NOTE: first is last and last is zero because of the order of insertion. */
        Table firstNonZeroValueOfX = dataTable.where(dataTable.doubleColumn(yDoubleName).isGreaterThan(0)).last(1);
        Table lastNonZeroValueOfX = dataTable.where(dataTable.doubleColumn(yDoubleName).isGreaterThan(0)).first(1);
        Table modelTable = firstNonZeroValueOfX.append(lastNonZeroValueOfX);

        Double firstNonZeroYValueOfX = modelTable.doubleColumn(xDoubleName).get(0);
        Double lastNonZeroYValueOfX = modelTable.doubleColumn(xDoubleName).get(1);
        LocalDate lastNonZeroYDateValueOfX = modelTable.dateColumn(xDateName).get(1);
        modelTable.doubleColumn(yDoubleName).set(0, myLine.getY(firstNonZeroYValueOfX));
        modelTable.doubleColumn(yDoubleName).set(1, myLine.getY(lastNonZeroYValueOfX));

        /* Sorting predictions data*/
        ArrayList<Double> yDoubleArrayPrediction = new ArrayList<Double>();
        ArrayList<Double> xDoubleArrayPrediction = new ArrayList<Double>();
        ArrayList<LocalDate> xDateArrayPrediction = new ArrayList<LocalDate>();
        for (int i = 1; i <= daysToPredict; i++) {
            yDoubleArrayPrediction.add(myLine.getY(lastNonZeroYValueOfX + i));
            xDoubleArrayPrediction.add(lastNonZeroYValueOfX + i);
            xDateArrayPrediction.add(lastNonZeroYDateValueOfX.plusDays(i));
        }

        /* Creating a table of predictions*/
        DoubleColumn yDoubleColumnPrediction = DoubleColumn.create(yDoubleName, yDoubleArrayPrediction);
        DoubleColumn xDoubleColumnPrediction = DoubleColumn.create(xDoubleName, xDoubleArrayPrediction);
        DateColumn xDateColumnPrediction = DateColumn.create(xDateName, xDateArrayPrediction);
        Table predictionTable = Table.create(yDoubleColumnPrediction, xDoubleColumnPrediction, xDateColumnPrediction);


        Axis xAxis = Axis.builder()
                .title(xDateName)
                .side(Axis.Side.bottom)
                .build();
        Axis yAxis = Axis.builder()
                .title(yDoubleName)
                .side(Axis.Side.left)

                .build();


        Layout layout =
                Layout.builder()
                        .title(graphName)
                        .xAxis(xAxis)
                        .yAxis(yAxis)
                        .width(AppConstants.Properties.GRAPH_WIDTH)
                        .height(AppConstants.Properties.GRAPH_HEIGHT)
                        .build();


        Trace trace1 = ScatterTrace.builder(
                dataTable.dateColumn(xDateName),
                dataTable.doubleColumn(yDoubleName))
                .name(dataLabel)
                .showLegend(true)
                .build();

        Trace trace2 = ScatterTrace.builder(
                modelTable.dateColumn(xDateName),
                modelTable.doubleColumn(yDoubleName)
        )
                .mode(ScatterTrace.Mode.LINE)
                .line(Line.builder().shape(Line
                        .Shape.SPLINE)
                        .build())
                .name(regressionLineLabel)
                .showLegend(true)
                .build();
        Trace trace3 = ScatterTrace.builder(
                predictionTable.dateColumn(xDateName),
                predictionTable.doubleColumn(yDoubleName)
        )
                .mode(ScatterTrace.Mode.LINE)
                .line(Line.builder().shape(Line
                        .Shape.SPLINE)
                        .build())
                .name(predictionLineLabel)
                .showLegend(true)
                .fillColor("lime")
                .build();

        Figure figure = new Figure(layout, trace1, trace2, trace3);

        return figure;
    }
}
