package csee.ce291_team02.MugGraphs;

import csee.ce291_team02.AppConstants;
import csee.ce291_team02.Models.GraphResult;
import csee.ce291_team02.MugLogger;
import csee.ce291_team02.MugStats.Models.MugPoint;
import csee.ce291_team02.MugStats.Models.PclrSpline;
import csee.ce291_team02.MugStats.Regression.PclRegression;
import csee.ce291_team02.MugStats.util.RegressionMappers;
import csee.ce291_team02.MugStats.util.Segmentation;
import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.components.Axis;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Gradient;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Line;
import tech.tablesaw.plotly.components.Marker;
import tech.tablesaw.plotly.traces.BarTrace;
import tech.tablesaw.plotly.traces.ScatterTrace;
import tech.tablesaw.plotly.traces.Trace;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContinuousPieceWiseLinearRegressionGraph {

    /**
     * Displays daily cases by specimen date using continuous piecewise linear regression model. The input data needs to be sorted by days.
     * @param data              arraylist of the data points
     * @param dates             arraylist of local dates
     * @param daysToPredict     the number of days to be predicted in prediction model
     * @param knotDates         the dates associated with the knots
     * @return GraphResult
     */
    public static GraphResult dailyCasesBySpecimenDate(ArrayList<MugPoint> data, ArrayList<LocalDate> dates, int daysToPredict, List<LocalDate> knotDates) {

        GraphResult result = new GraphResult();


        ArrayList<Double> numericKnotDates = RegressionMappers.toDoubleEpochDayArray(knotDates);
        ArrayList<ArrayList<MugPoint>> segments = null;
        try {
            segments = Segmentation.segmentate(data, numericKnotDates);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PclRegression pclr = new PclRegression(segments, numericKnotDates);
        pclr.fitDataSweep(10);
        MugLogger.log(pclr);
        result.tss = pclr.tss();
        result.rss = pclr.rss();
        result.rSquared = pclr.rSquared();
        PclrSpline model = pclr.getModel();

        String xDoubleName = "numeric date";
        String yDoubleName = "daily cases";
        String xDateName = "string date";

        /* Sorting the graph data*/
        ArrayList<Double> yDoubleArray = new ArrayList<>();
        ArrayList<Double> xDoubleArray = new ArrayList<>();
        ArrayList<LocalDate> xDateArray = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            yDoubleArray.add(data.get(i).b);
            xDoubleArray.add(data.get(i).a);
            xDateArray.add(dates.get(i));
        }

        /* Creating a table of the data*/
        DoubleColumn yDoubleColumn = DoubleColumn.create(yDoubleName, yDoubleArray);
        DoubleColumn xDoubleColumn = DoubleColumn.create(xDoubleName, xDoubleArray);
        DateColumn xDateColumn = DateColumn.create(xDateName, xDateArray);
        Table dataTable = Table.create(xDateColumn, xDoubleColumn, yDoubleColumn);

        /* Sorting the model data*/
        ArrayList<Double> yModDoubleArray = new ArrayList<>();
        ArrayList<Double> xModDoubleArray = new ArrayList<>();
        ArrayList<LocalDate> xModDateArray = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            yModDoubleArray.add(model.getY(data.get(i).a));
            xModDoubleArray.add(data.get(i).a);
            xModDateArray.add(dates.get(i));
        }

        /* Creating a table of model data*/
        DoubleColumn yModDoubleColumn = DoubleColumn.create(yDoubleName, yModDoubleArray);
        DoubleColumn xModDoubleColumn = DoubleColumn.create(xDoubleName, xModDoubleArray);
        DateColumn xModDateColumn = DateColumn.create(xDateName, xModDateArray);
        Table modTable = Table.create(xModDateColumn, xModDoubleColumn, yModDoubleColumn);

        /* Sorting the model knots data*/
        ArrayList<Double> yModKnotDoubleArray = new ArrayList<>();
        ArrayList<Double> xModKnotDoubleArray = new ArrayList<>();
        ArrayList<LocalDate> xModKnotDateArray = new ArrayList<>();
        ArrayList<MugPoint> knots = pclr.getKnots();
        for (MugPoint knot : knots) {
            yModKnotDoubleArray.add(knot.b);
            xModKnotDoubleArray.add(knot.a);
            xModKnotDateArray.add(LocalDate.ofEpochDay((knot.a.longValue())));
        }

        /* Creating a table of model knots*/
        DoubleColumn yModKnotDoubleColumn = DoubleColumn.create(yDoubleName, yModKnotDoubleArray);
        DoubleColumn xModKnotDoubleColumn = DoubleColumn.create(xDoubleName, xModKnotDoubleArray);
        DateColumn xModKnotDateColumn = DateColumn.create(xDateName, xModKnotDateArray);
        Table modKnotTable = Table.create(xModKnotDateColumn, xModKnotDoubleColumn, yModKnotDoubleColumn);

        /* Sorting the prediction data*/
        ArrayList<Double> yPredictDoubleArray = new ArrayList<>();
        ArrayList<Double> xPredictDoubleArray = new ArrayList<>();
        ArrayList<LocalDate> xPredictDateArray = new ArrayList<>();
        for (int i = 1; i <= daysToPredict; i++) {
            MugPoint prediction = model.getPrediction(i);
            yPredictDoubleArray.add(prediction.b);
            xPredictDoubleArray.add(prediction.a);
            xPredictDateArray.add(LocalDate.ofEpochDay((prediction.a.longValue())));
        }

        /* Creating a table of predictions*/
        DoubleColumn yPredictDoubleColumn = DoubleColumn.create(yDoubleName, yPredictDoubleArray);
        DoubleColumn xPredictDoubleColumn = DoubleColumn.create(xDoubleName, xPredictDoubleArray);
        DateColumn xPredictDateColumn = DateColumn.create(xDateName, xPredictDateArray);
        Table predictTable = Table.create(xPredictDateColumn, xPredictDoubleColumn, yPredictDoubleColumn);

        Axis xAxis = Axis.builder()
                .title(xDateName)
                .side(Axis.Side.bottom)
                .build();
        Axis yAxis = Axis.builder()
                .title(yDoubleName)
                .side(Axis.Side.left)
                .build();


        Gradient gradient1 = Gradient.builder().color(new String[]{}).build();
        Marker marker1 = Marker.builder().gradient(gradient1).build();

        Trace dataTrace = BarTrace.builder(
                dataTable.dateColumn(xDateName),
                dataTable.doubleColumn(yDoubleName))
                .name("newCasBySpecDat")
                .showLegend(true)
                .marker(marker1)
                .build();

        Trace modelTrace = ScatterTrace.builder(
                modTable.dateColumn(xDateName),
                modTable.doubleColumn(yDoubleName)
        )
                .mode(ScatterTrace.Mode.LINE)
                .line(Line.builder().shape(Line
                        .Shape.SPLINE).smoothing(1)
                        .build())
                .name("pcrl model")
                .showLegend(true)
                .fillColor("red")
                .build();

        Trace modelKnotsTrace = ScatterTrace.builder(
                modKnotTable.dateColumn(xDateName),
                modKnotTable.doubleColumn(yDoubleName))
                .name("model knots")
                .fillColor("lime")
                .whiskerWidth(1)
                .showLegend(true)
                .build();

        Trace predictionTrace = ScatterTrace.builder(
                predictTable.dateColumn(xDateName),
                predictTable.doubleColumn(yDoubleName))
                .name("predictions")
                .fillColor("pink")
                .whiskerWidth(1)
                .showLegend(true)
                .build();

        Layout layout =
                Layout.builder()
                        .title("Daily Cases By Specimen Date")
                        .xAxis(xAxis)
                        .yAxis(yAxis)
                        .width(AppConstants.Properties.GRAPH_WIDTH)
                        .height(AppConstants.Properties.GRAPH_HEIGHT)
                        .build();

        Figure figure = new Figure(layout, dataTrace, modelTrace, modelKnotsTrace, predictionTrace);

        result.figure = figure;
        return result;
    }
}
