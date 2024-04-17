package csee.ce291_team02.ColourCodedMap;

import csee.ce291_team02.AppConstants;

import javax.swing.JPanel;
import java.awt.*;
import java.util.List;

import static csee.ce291_team02.ColourCodedMap.MugOwoRenderer.scaleColor;

public class LegendPanel extends JPanel {

    private List<String> theme;
    private int min;
    private int max;
    private int mean;
    private int partCount;

    public LegendPanel(List<String> theme, int min, int max, int mean) {
        this.theme = theme;
        this.min = min;
        this.max = max;
        this.mean = mean;
    }

    public LegendPanel() {

    }

    public LegendPanel(MugOwoRenderer mugOwoRenderer) {
        this.theme = mugOwoRenderer.getTheme();
        this.min = mugOwoRenderer.getLegendMin();
        this.max = mugOwoRenderer.getLegendMax();
        this.mean = mean;
    }

    public void setLegendTheme(List<String> theme, int min, int max, int mean){
        this.theme = theme;
        this.min = min;
        this.max = max;
        this.mean = mean;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.setBackground(new Color(0,0,0,0));

        final int width = getWidth();
        final int height = getHeight();
        
        int part = height / (theme.size() - 1);

        for (int i = 0; i < theme.size() - 1; i++) {
            final Color c1 = AppConstants.Selections.MapColorSchemes.hex2Rgb(theme.get(i));
            final Color c2 = AppConstants.Selections.MapColorSchemes.hex2Rgb(theme.get(i + 1));
            for (int j = 1; j < part + 1; j++) {

                final Color col = scaleColor(j, 0, part, c1, c2);
                final Color displayCol = new Color(col.getRed(), col.getGreen(), col.getBlue(), 240);
                g.setColor(displayCol);
                g.drawRect(-1, i * part, width, j + 1);
            }
        }
    }
}
