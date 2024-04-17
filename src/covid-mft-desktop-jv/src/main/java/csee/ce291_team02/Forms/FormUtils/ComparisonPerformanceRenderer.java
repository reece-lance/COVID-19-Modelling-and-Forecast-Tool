package csee.ce291_team02.Forms.FormUtils;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

public class ComparisonPerformanceRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        l.setHorizontalAlignment(SwingConstants.CENTER);

        String val = l.getText();

        double percentage = Double.valueOf(val) * 100d;

        l.setText(String.format("%.2f", percentage ) + "%");

        return l;
        }
    }