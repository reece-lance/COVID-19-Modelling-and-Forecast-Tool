package csee.ce291_team02.Forms.FormUtils;

import csee.ce291_team02.AppConstants.CountryComparison.PerformanceLabels;
import csee.ce291_team02.AppConstants.CountryComparison.PerformanceToolTips;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

/*
This class is responsible for setting the tool tips for Infection rate, Death rate, and Recovery rate and their following chevrons.
 */
public class ComparisonDescriptionRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        l.setHorizontalAlignment(SwingConstants.CENTER);

        String val = l.getText();

        switch (val){
            case PerformanceLabels.INFECTION_RATE:
                l.setToolTipText(PerformanceToolTips.INFECTION_RATE);
                break;
            case PerformanceLabels.DEATH_RATE:
                l.setToolTipText(PerformanceToolTips.DEATH_RATE);
                break;
            case PerformanceLabels.RECOVERY_RATE:
                l.setToolTipText(PerformanceToolTips.RECOVERY_RATE);
                break;
            default:

        }
        return l;
        }
    }