package csee.ce291_team02.MugGraphs;

import csee.ce291_team02.AppConstants;
import csee.ce291_team02.MugLogger;
import csee.ce291_team02.MugStats.Models.MugLine;
import csee.ce291_team02.MugStats.Models.MugPoint;
import csee.ce291_team02.MugStats.Regression.Regression;
import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Axis;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Line;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;
import tech.tablesaw.selection.Selection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PiecewiseLinearRegressionGraphs {

    /**
     * Displays daily cases by specimen date using piecewise linear regression model. The input data needs to be sorted by days.
     * @param data                  arraylist of the data points
     * @param dates                 arraylist of local dates
     * @param daysToPredict         the number of days to be predicted in prediction model
     * @param knotDates             the dates associated with the knots
     * @return Figure
     */
    public static Figure dailyCasesBySpecimenDate(ArrayList<MugPoint> data, ArrayList<LocalDate> dates, int daysToPredict, List<LocalDate> knotDates) {
        String xDoubleName = "numeric date";
        String yDoubleName = "daily cases";
        String xDateName = "string date";

        /* Sorting the graph data */
        ArrayList<Double> yDoubleArray = new ArrayList<>();
        ArrayList<Double> xDoubleArray = new ArrayList<>();
        ArrayList<LocalDate> xDateArray = new ArrayList<>();
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

        /* Creating the graph of the model */
        String dataLabel = "cases";
        String regressionLineLabel = "model";
        String predictionLineLabel = "prediction";
        String graphName = "Daily Cases By Specimen Date";

        DateColumn datesCol = (DateColumn)dataTable.column(xDateName);
        ArrayList<Selection> selections = new ArrayList<>();

        /* Separate the data table into chunks of data by knot date */

        /* Left inclusive, right exclusive selection */
        selections.add(datesCol.isOnOrBefore(knotDates.get(0)));
         for (int i = 1; i < knotDates.size() - 1; i++){
            selections.add(datesCol.isOnOrAfter(knotDates.get(i))
                                .and(datesCol.isOnOrBefore(knotDates.get(i + 1))
                    ));
        }
        selections.add(datesCol.isOnOrAfter(knotDates.get(knotDates.size() - 1)));
        MugLogger.log(String.format("Last batch is after: %s", knotDates.get(knotDates.size() - 1)));


        ArrayList<Table> pieceWiseTables = new ArrayList<>();
        for (Selection s: selections){
            pieceWiseTables.add(dataTable.where(s));
        }

        ArrayList<MugLine> pieceWiseLines = new ArrayList<>();
        for (Table pt: pieceWiseTables){
            pieceWiseLines.add(Regression.LinearRegression.getLine(pt, xDoubleName, yDoubleName));
        }

        /* Sorting model data*/
        ArrayList<Double> yDoubleModelArray = new ArrayList<>();
        ArrayList<Double> xDoubleModelArray = new ArrayList<>();
        ArrayList<LocalDate> xDateModelArray = new ArrayList<>();
        MugLogger.log("Creating model points ");
        for (int i = 0; i < knotDates.size(); i++) {
            Table dataChunk = pieceWiseTables.get(i);
            MugLine modelLine = pieceWiseLines.get(i);

            for (int j = dataChunk.rowCount(); j > 0 ; j--){
                Row row = dataChunk.row( dataChunk.rowCount() - j );

                yDoubleModelArray.add(modelLine.getY(row.getDouble(xDoubleName)));
                xDoubleModelArray.add(row.getDouble(xDoubleName));
                xDateModelArray.add(row.getDate(xDateName));
            }
            MugLogger.log(modelLine);
        }

        /* Creating a table of model data*/
        DoubleColumn yDoubleModelColumn = DoubleColumn.create(yDoubleName, yDoubleModelArray);
        DoubleColumn xDoubleModelColumn = DoubleColumn.create(xDoubleName, xDoubleModelArray);
        DateColumn xDateModelColumn = DateColumn.create(xDateName, xDateModelArray);
        Table modelTable = Table.create(xDateModelColumn, xDoubleModelColumn, yDoubleModelColumn);

        /* NOTE: first is last and last is zero because of the order of insertion. */
        Table lastNonZeroValueOfX = dataTable.where(dataTable.doubleColumn(yDoubleName).isGreaterThan(0)).first(1);

        // Creating the prediction graph
        ArrayList<Double> yDoubleArrayPrediction = new ArrayList<>();
        ArrayList<Double> xDoubleArrayPrediction = new ArrayList<>();
        ArrayList<LocalDate> xDateArrayPrediction = new ArrayList<>();

        MugLine lastPieceWiseLine = pieceWiseLines.get(pieceWiseLines.size() - 1);

        /* Sorting prediction data*/
        for (int i = 1; i <= daysToPredict; i++) {
            Double predictedValueOfX = lastNonZeroValueOfX.doubleColumn(xDoubleName).get(0) + i;

            yDoubleArrayPrediction.add(lastPieceWiseLine.getY(predictedValueOfX));
            xDoubleArrayPrediction.add(predictedValueOfX + i);
            xDateArrayPrediction.add(lastNonZeroValueOfX.dateColumn(xDateName).get(0).plusDays(i));
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

        Trace dataTrace = BarTrace.builder(
                dataTable.dateColumn(xDateName),
                dataTable.doubleColumn(yDoubleName))
                .name(dataLabel)
                .showLegend(true)
                .build();

        Trace modelTrace = ScatterTrace.builder(
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

        Trace predictionTrace = ScatterTrace.builder(
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

        Figure figure = new Figure(layout);
        figure.setTraces(dataTrace, modelTrace, predictionTrace);

        return figure;
    }
}
