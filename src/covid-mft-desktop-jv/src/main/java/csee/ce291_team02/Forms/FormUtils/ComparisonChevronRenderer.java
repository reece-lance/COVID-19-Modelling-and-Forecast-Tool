package csee.ce291_team02.Forms.FormUtils;

import csee.ce291_team02.MugLogger;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;

import static csee.ce291_team02.AppConstants.SpecialCharacters.CharacterModifiers.*;
import static csee.ce291_team02.AppConstants.SpecialCharacters.downChevron;
import static csee.ce291_team02.AppConstants.SpecialCharacters.upChevron;

/*
This class is responsible for setting the chevrons once the data is compared.
 */
public class ComparisonChevronRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        l.setHorizontalAlignment(SwingConstants.CENTER);

        String val = l.getText();
        if (GREATER_POSITIVE.equalsName(val)) {
            l.setForeground(Color.GREEN);
            l.setText(upChevron);
            l.setToolTipText(GREATER_POSITIVE.name());
        } else if (LOWER_POSITIVE.equalsName(val)) {
            l.setForeground(Color.GREEN);
            l.setText(downChevron);
            l.setToolTipText(LOWER_POSITIVE.name());
        } else if (GREATER_NEGATIVE.equalsName(val)) {
            l.setForeground(Color.RED);
            l.setText(upChevron);
            l.setToolTipText(GREATER_NEGATIVE.name());
        } else if (LOWER_NEGATIVE.equalsName(val)) {
            l.setForeground(Color.RED);
            l.setText(downChevron);
            l.setToolTipText(LOWER_NEGATIVE.name());
        } else {
            MugLogger.log("Unexpected value in CharacterModifiers enum selection: " + val);
        }
        return l;
        }
    }