package csee.ce291_team02.PdfExport.templateModels;

import java.util.ArrayList;
import java.util.List;

public class TableBody {
    public TableRow meanRow = new TableRow();
    public TableRow sdRow = new TableRow();
    public TableRow varRow = new TableRow();
    public TableRow totalRow = new TableRow();
    public TableRow rssRow = new TableRow();
    public TableRow tssRow = new TableRow();
    public TableRow rSquaredRow = new TableRow();
    public List<TableRow> rows = new ArrayList<>();

    public void addRow(TableRow row) {
        rows.add(row);
    }
}
