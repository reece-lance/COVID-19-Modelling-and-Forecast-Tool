package csee.ce291_team02.PdfExport;

import com.github.mustachejava.DeferringMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import csee.ce291_team02.AppConstants;
import csee.ce291_team02.Models.CovidCaseByDate;
import csee.ce291_team02.Models.GraphResult;
import csee.ce291_team02.MugLogger;
import csee.ce291_team02.PdfExport.templateModels.TemplateModel;
import csee.ce291_team02.PdfExport.templateModels.TemplateModelBuilder;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class PDFCreation {
    /**
     * Creates the pdf containing the selected components using Moustace to include a created model.
     * @param covidCaseByDateData covid data
     * @param graphResult graph data
     * @param dumpDir directory the user has chosen to save the PDF to
     * @param pdfCheckBoxes stores whether to add each component string to the file
     */
    public static void createPdf(List<CovidCaseByDate> covidCaseByDateData, GraphResult graphResult, File dumpDir, boolean[] pdfCheckBoxes, String ExportedFileName) {
        TemplateModel templateModel = TemplateModelBuilder.build(covidCaseByDateData, graphResult, pdfCheckBoxes);

        Path output = AppConstants.Paths.resolvePath(AppConstants.Paths.Outputs.UI_GRAPH_FILE);

        createPdfTemplate(pdfCheckBoxes);

        Path transformedHtmlTemplatePath = transformTemplate(output, templateModel);

        if (dumpDir!=null) {
            PDFRenderer.renderToPDF(transformedHtmlTemplatePath.toUri().toString(), Paths.get(dumpDir.getPath(), ExportedFileName));
        }
    }

    /**
     * createPdfTemplate prints the xhtml strings into a file
     * @param pdfCheckBoxes stores whether to add each component string to the file
     */
    private static void createPdfTemplate(boolean[] pdfCheckBoxes) {
        PrintWriter pw;
        try {
            Path pdfTemplatePath = AppConstants.Paths.resolvePath(AppConstants.Paths.TempResources.TEMP_PDF_TEMPLATE_FILE);
            pw = new PrintWriter(pdfTemplatePath.toString()); // gets html output file

            // xhtml styling code
            pw.print("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\n" +
                    "<head>\n" +
                    "    <title>{{title}}</title>\n" +
                    "    <script src=\"https://cdn.plot.ly/plotly-latest.min.js\"></script>\n" +
                    "    <style>\n" +
                    "        p.Title-Paragraph {\n" +
                    "            color: #000000;\n" +
                    "            font-family: \"Minion Pro\", serif;\n" +
                    "            font-size: 30px;\n" +
                    "            font-style: normal;\n" +
                    "            font-variant: normal;\n" +
                    "            font-weight: bold;\n" +
                    "            line-height: 1.2;\n" +
                    "            margin-bottom: 0;\n" +
                    "            margin-left: 0;\n" +
                    "            margin-right: 0;\n" +
                    "            margin-top: 0;\n" +
                    "            orphans: 1;\n" +
                    "            page-break-after: auto;\n" +
                    "            page-break-before: auto;\n" +
                    "            text-align: center;\n" +
                    "            text-decoration: none;\n" +
                    "            text-indent: 0;\n" +
                    "            text-transform: none;\n" +
                    "            widows: 1;\n" +
                    "            page-break-inside: avoid;\n" +
                    "        }\n" +
                    "\n" +
                    "        p.Basic-Paragraph {\n" +
                    "            color: #000000;\n" +
                    "            font-family: \"Minion Pro\", serif;\n" +
                    "            font-size: 12px;\n" +
                    "            font-style: normal;\n" +
                    "            font-variant: normal;\n" +
                    "            font-weight: normal;\n" +
                    "            line-height: 1.2;\n" +
                    "            margin-bottom: 0;\n" +
                    "            margin-left: 0;\n" +
                    "            margin-right: 0;\n" +
                    "            margin-top: 0;\n" +
                    "            orphans: 1;\n" +
                    "            page-break-after: auto;\n" +
                    "            page-break-before: auto;\n" +
                    "            text-align: center;\n" +
                    "            text-decoration: none;\n" +
                    "            text-indent: 0;\n" +
                    "            text-transform: none;\n" +
                    "            widows: 1;\n" +
                    "            page-break-inside: avoid;\n" +
                    "        }\n" +
                    "\n" +
                    "        table.Basic-Table {\n" +
                    "            border-collapse: collapse;\n" +
                    "            border-color: #000000;\n" +
                    "            border-style: solid;\n" +
                    "            border-width: 1px;\n" +
                    "            margin-bottom: -4px;\n" +
                    "            margin-top: 4px;\n" +
                    "            margin-left: auto;\n" +
                    "            margin-right: auto;\n" +
                    "            page-break-inside: avoid;\n" +
                    "        }\n" +
                    "\n" +
                    "        td.Basic-Table {\n" +
                    "            border-left-width: 1px;\n" +
                    "            border-left-style: solid;\n" +
                    "            border-left-color: #000000;\n" +
                    "            border-top-width: 1px;\n" +
                    "            border-top-style: solid;\n" +
                    "            border-top-color: #000000;\n" +
                    "            border-right-width: 1px;\n" +
                    "            border-right-style: solid;\n" +
                    "            border-right-color: #000000;\n" +
                    "            border-bottom-width: 1px;\n" +
                    "            border-bottom-style: solid;\n" +
                    "            border-bottom-color: #000000;\n" +
                    "            padding-top: 4px;\n" +
                    "            padding-bottom: 4px;\n" +
                    "            padding-left: 4px;\n" +
                    "            padding-right: 4px;\n" +
                    "            vertical-align: top;\n" +
                    "            page-break-inside: avoid;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n");

            if (pdfCheckBoxes[0]) {
                pw.print(addTitleParagraph());
            }
            if (pdfCheckBoxes[1]) {
                pw.print(addGraphImage());
            }
            if (pdfCheckBoxes[9]) {
                pw.print(addMapImage());
            }
            for (int i = 2; i < 9; i++) {
                if (pdfCheckBoxes[i]) {
                    pw.print(addTable(pdfCheckBoxes));
                    break;
                }
            }
            pw.print("\n</body>\n" +
                    "</html>");
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return string containing xhtml code to insert the title
     */
    public static String addTitleParagraph() {
        return "<div id=\"_idContainer000\" class=\"Basic-Text-Frame\">\n" +
                "    <p class=\"Title-Paragraph\">{{{title}}}</p>\n" +
                "</div>\n\n";
    }

    /**
     * @return string containing xhtml code to insert the graph image
     */
    public static String addGraphImage() {
        return "<img src=\"{{{graphImage.path}}}\" />";
    }

    /**
     * @return string containing xhtml code to insert the map image
     */
    public static String addMapImage() {
        return "<img src=\"{{{mapImage.path}}}\" width=\"600\" height=\"360\" />";
    }

    /**
     * This combines all of the table creation xhtml code to a string
     * @param pdfCheckBoxes stores whether to add each component string to the file
     * @return string containing xhtml code to insert the table
     */
    public static String addTable(boolean[] pdfCheckBoxes) {
        String[] head = {"column1", "column2"}; // list of column head strings
        String[] columns = {"metricName", "metricValue"}; // list of column type
        ArrayList<String> rows = setTableRows(pdfCheckBoxes); // gets list of rows to include in the table

        StringBuilder table = new StringBuilder();

        // The following code adds table creation xhtml code to a string
        table.append("<table id=\"table002\" class=\"Basic-Table\">\n" +
                "    <colgroup>\n");

        for (int i = 0; i < head.length; i++) {
            table.append("<col class=\"_idGenTableRowColumn-1\"/>\n");
        }

        table.append("</colgroup>\n" +
                "    <thead>\n" +
                "    <tr class=\"Basic-Table _idGenTableRowColumn-2\">\n");

        // The following code adds table head addition xhtml code to a string
        for (String value : head) {
            table.append("<td class=\"Basic-Table\">\n" + "            <p class=\"Basic-Paragraph\">{{table.tableHead.").append(value).append("}}</p>\n").append("        </td>\n");
        }

        table.append("    </tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n");

        // The following code adds table row creation xhtml code to a string
        for (String row : rows) {
            table.append("<tr class=\"Basic-Table _idGenTableRowColumn-2\">\n");
            for (String column : columns) {
                table.append("<td class=\"Basic-Table\">\n" + "                <p class=\"Basic-Paragraph\">{{table.tableBody.").append(row).append(".").append(column).append("}}</p>\n").append("            </td>\n");
            }
            table.append("        </tr>\n");
        }
        table.append("    </tbody>\n" +
                "</table>\n");

        return table.toString();
    }

    /**
     * Checks which rows the user wants in the table in the PDF
     * @param pdfCheckBoxes stores whether to add each component string to the file
     * @return list of row names to add to the table
     */
    private static ArrayList<String> setTableRows(boolean[] pdfCheckBoxes) {
        ArrayList<String> rows = new ArrayList<>();

        if (pdfCheckBoxes[2]) { // if mean check box is checked
            rows.add("meanRow");
        }

        if (pdfCheckBoxes[3]) { // if sd check box is checked
            rows.add("sdRow");
        }

        if (pdfCheckBoxes[4]) { // if var check box is checked
            rows.add("varRow");
        }

        if (pdfCheckBoxes[5]) { // if total check box is checked
            rows.add("totalRow");
        }

        if (pdfCheckBoxes[6]) { // if rss check box is checked
            rows.add("rssRow");
        }

        if (pdfCheckBoxes[7]) { // if tss check box is checked
            rows.add("tssRow");
        }

        if (pdfCheckBoxes[8]) { // if rSquared check box is checked
            rows.add("rSquaredRow");
        }

        return rows;
    }

    /**
     * Combines the file containing xhtml code and the model by replacing key words using Moustache
     * @param outputPath File containing xhtml code
     * @param dataObject PDF model
     * @return File containing xhtml code with data from model inserted
     */
    private static Path transformTemplate(Path outputPath, TemplateModel dataObject) {
        Path pdfTemplatePath = AppConstants.Paths.resolvePath(AppConstants.Paths.TempResources.TEMP_PDF_TEMPLATE_FILE);
        MustacheFactory mustacheFactory = getMustacheFactory();

        MugLogger.log("Writing pdf to " + pdfTemplatePath);
        Mustache mustache = mustacheFactory.compile("PDF_Template.html");

        try {
            Files.deleteIfExists(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Writer writer = null;
        try {
            writer = new FileWriter(outputPath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mustache.execute(writer, dataObject).close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputPath;
    }

    /**
     * @return Moustache Factory
     */
    private static DeferringMustacheFactory getMustacheFactory() {
        DeferringMustacheFactory mf = new DeferringMustacheFactory();
        mf.setExecutorService(Executors.newCachedThreadPool());

        return mf;
    }
}
