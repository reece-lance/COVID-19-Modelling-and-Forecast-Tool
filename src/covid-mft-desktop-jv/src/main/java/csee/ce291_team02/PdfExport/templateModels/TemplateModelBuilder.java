package csee.ce291_team02.PdfExport.templateModels;

import csee.ce291_team02.App;
import csee.ce291_team02.AppConstants;
import csee.ce291_team02.Metrics.Metrics;
import csee.ce291_team02.Models.CovidCaseByDate;
import csee.ce291_team02.Models.GraphResult;
import csee.ce291_team02.Data.DataHelpers;

import java.util.List;

public class TemplateModelBuilder {
    public static TemplateModel build(List<CovidCaseByDate> covidCaseByDateData, GraphResult graphResult, boolean[] pdfCheckBoxes) {

        TemplateModel model = new TemplateModel();

        setTitleParagraph(model);
        setGraphImage(model, AppConstants.Paths.resolvePath(AppConstants.Paths.Outputs.GRAPH_EXPORT_FILE).toUri().toString());
        setMapImage(model, AppConstants.Paths.resolvePath(AppConstants.Paths.Outputs.MAP_EXPORT_FILE).toUri().toString());

        if (pdfCheckBoxes[2] || pdfCheckBoxes[3] ||pdfCheckBoxes[4] ||pdfCheckBoxes[5]) {
            if (covidCaseByDateData == null) {
                covidCaseByDateData = App.covidCaseByDateDataSupplier.getData();
            }
            int[] newCasesBySpecimenDate = DataHelpers.getNewCasesBySpeciminDate(covidCaseByDateData);
            setSdRow(model.table, newCasesBySpecimenDate);
            setMeanRow(model.table, newCasesBySpecimenDate);
            setVarRow(model.table, newCasesBySpecimenDate);
            setTotal(model.table, newCasesBySpecimenDate);
        }

        if (pdfCheckBoxes[6] || pdfCheckBoxes[7] ||pdfCheckBoxes[8]) {
            setRss(model.table, graphResult);
            setTss(model.table, graphResult);
            setRSquared(model.table, graphResult);
        }

        /*
        Hard-coded table

        ArrayList<LocalDate> dateArray = new ArrayList<>();
        ArrayList<Integer> casesArray = new ArrayList<>();

        for (CovidCaseByDate covidCase : covidCaseByDateData) {
            dateArray.add(covidCase.Date);
            casesArray.add(covidCase.getNewCasesBySpecimenDate());
        }
            DateColumn dateColumn = DateColumn.create("Date", dateArray);
            DoubleColumn casesColumn = DoubleColumn.create("Cases", casesArray);

            Table propertyValueTable = Table.create(dateColumn, casesColumn);

            Metrics m = new Metrics(propertyValueTable);
            Double sd = m.sd(propertyValueTable, "Cases");
            Double var = Math.pow(sd, 2);
         */

//        MVPTableModel table = new MVPTableModel();
//        TableBody tableBody = new TableBody();
//        table.body = tableBody;
//
//        ArrayList<TableRow> tableRows = new ArrayList<TableRow>();
//        tableBody.rows = tableRows;
//
//        TableRow row1 = new TableRow();
//        TableRow row2 = new TableRow();
//
//        TableData row1Data1 = new TableData();
//        row1Data1.value = "<p class=\"Basic-Paragraph\">1</p>";
//        TableData row1Data2 = new TableData();
//        row1Data1.value = "<p class=\"Basic-Paragraph\">2</p>";
//
//        row1.data.add(row1Data1);
//        row1.data.add(row1Data2);
//        row2.data.add(row1Data1);
//        row2.data.add(row1Data2);
//
//        tableRows.add(row1);
//        tableRows.add(row2);
//
//        table.body = tableBody;
//
//        model.table = table;


            return model;
    }

    public static void setTitleParagraph(TemplateModel model) {
        model.title = "COVID-19 Report";
    }

    public static void setGraphImage(TemplateModel model, String graphPath) {
        GraphImage graphImage = new GraphImage();
        graphImage.path = graphPath;
        model.graphImage = graphImage;
    }

    public static void setMapImage(TemplateModel model, String mapPath) {
        MapImage mapImage = new MapImage();
        mapImage.path = mapPath;
        model.mapImage = mapImage;
    }

    public static void setMeanRow(TableModel table, int[] data) {
        table.tableBody.meanRow.metricName = "Mean";
        table.tableBody.meanRow.metricValue = Metrics.mean(data);
        table.tableBody.addRow(table.tableBody.meanRow);
    }

    public static void setSdRow(TableModel table, int[] data) {
        table.tableBody.sdRow.metricName = "Standard Deviation";
        table.tableBody.sdRow.metricValue = Metrics.standardDeviation(data);
        table.tableBody.addRow(table.tableBody.sdRow);
    }

    public static void setVarRow(TableModel table, int[] data) {
        table.tableBody.varRow.metricName = "Variance";
        table.tableBody.varRow.metricValue = Metrics.variance(data);
        table.tableBody.addRow(table.tableBody.varRow);
    }

    public static void setTotal(TableModel table, int[] data) {
        table.tableBody.totalRow.metricName = "Total";
        table.tableBody.totalRow.metricValue = Metrics.total(data);
        table.tableBody.addRow(table.tableBody.totalRow);
    }

    public static void setRss(TableModel table, GraphResult graphResult) {
        table.tableBody.rssRow.metricName = "Residual Sum of Squares (RSS)";
        if (graphResult != null) {
            table.tableBody.rssRow.metricValue = graphResult.rss;
        } else {
            table.tableBody.rssRow.metricValue = 0;
        }
        table.tableBody.addRow(table.tableBody.rssRow);
    }

    public static void setTss(TableModel table, GraphResult graphResult) {
        table.tableBody.tssRow.metricName = "Total Sum of Squares (TSS)";
        if (graphResult != null) {
            table.tableBody.tssRow.metricValue = graphResult.tss;
        } else {
            table.tableBody.tssRow.metricValue = 0;
        }
        table.tableBody.addRow(table.tableBody.tssRow);
    }

    public static void setRSquared(TableModel table, GraphResult graphResult) {
        table.tableBody.rSquaredRow.metricName = "Coefficient of Determination (rSquared)";
        if (graphResult != null) {
            table.tableBody.rSquaredRow.metricValue = graphResult.rSquared;
        } else {
            table.tableBody.rSquaredRow.metricValue = 0;
        }
        table.tableBody.addRow(table.tableBody.rSquaredRow);
    }
}
