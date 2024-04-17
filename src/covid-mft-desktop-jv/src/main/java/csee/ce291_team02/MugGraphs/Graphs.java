package csee.ce291_team02.MugGraphs;

import csee.ce291_team02.App;
import csee.ce291_team02.AppConstants;
import csee.ce291_team02.AppConstants.Paths.Outputs;
import csee.ce291_team02.Models.CovidCaseByDate;
import tech.tablesaw.plotly.components.Figure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;

public class Graphs {

    /* String that creates the upper part of the html document */
    private static String pageTop =
            "<html>"
                    + System.lineSeparator()
                    + "<head>"
                    + System.lineSeparator()
                    + "    <title>Multi-plot test</title>"
                    + System.lineSeparator()
                    + "    <script src=\"https://cdn.plot.ly/plotly-latest.min.js\"></script>"
                    + System.lineSeparator()
                    + "</head>"
                    + System.lineSeparator()
                    + "<body>"
                    + System.lineSeparator();

    /* String that creates the lower part of the html document */
    private static String pageBottom =
            "</body>"
                    + System.lineSeparator()
                    + "</html>";

    List<CovidCaseByDate> covidCaseByDateData;



    public Graphs() throws IOException {
        covidCaseByDateData = App.covidCaseByDateDataSupplier.getData();
    }


    /**
     * Gets a figure and compiles a html page with a graph of the figure.
     *
     * @param figure        Graph figure to be shown in html
     * @return Path to the created file.
     */
    public static Path createGraphPageFile(Figure figure) {
        String page = makeGraphPage(figure, "graphDiv", pageTop, pageBottom);

        Path outputPath = AppConstants.Paths.resolvePath(Outputs.UI_GRAPH_FILE);

        File outputFile = new File(outputPath.toString());

        try(Writer writer = new FileWriter(outputFile)){
            writer.write(page);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputPath;
    }

    /** The appendage of all the strings required within the html document
     *
     * @param figure            Graph figure to be shown in html
     * @param divName1          Name of the division in html
     * @param pageTop           String that represents the top of the page in html
     * @param pageBottom        String that represents the bottom of the page in html
     *
     * @return String
     * */
    private static String makeGraphPage(Figure figure, String divName1, String pageTop, String pageBottom) {
        String js = figure.asJavascript(divName1);
        return new StringBuilder()
                .append(pageTop)
                .append("<div id='" + divName1 + "'><div/>")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(js)
                .append(System.lineSeparator())
                .append(pageBottom)
                .toString();
    }
}