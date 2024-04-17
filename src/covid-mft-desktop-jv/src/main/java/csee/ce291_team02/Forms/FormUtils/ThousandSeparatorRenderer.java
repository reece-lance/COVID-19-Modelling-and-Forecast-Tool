package csee.ce291_team02.Forms.FormUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/*
This class is responsible for adding the comma according to the thousand seperator convention
 */
public class ThousandSeparatorRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        l.setHorizontalAlignment(SwingConstants.CENTER);

        try {
            int val = Integer.parseInt(l.getText());
            l.setText(String.format("%,d", val));
        } catch (Exception ignored) { }

        return l;
    }
}