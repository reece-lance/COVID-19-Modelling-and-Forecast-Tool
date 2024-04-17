package csee.ce291_team02.ColourCodedMap;

import csee.ce291_team02.AppConstants;
import csee.ce291_team02.AppConstants.Selections.MapColorSchemes;
import csee.ce291_team02.Models.MapRegionData;
import csee.ce291_team02.MugLogger;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import static csee.ce291_team02.AppConstants.Selections.MapColorSchemes.hex2Rgb;

import static csee.ce291_team02.Models.MapRegionData.MapRegionDataAccessor;
import static csee.ce291_team02.Models.MapRegionData.mapRegionDataAccessor;

public class MugOwoRenderer extends JComponent {

    private OwoMap map;

    private final int xProjectionSize = AppConstants.Properties.MAP_WIDTH;
    private final int yProjectionSize = AppConstants.Properties.MAP_HEIGHT;

    private int xDataSize;
    private int yDataSize;

    private int themeBorder = 5;

    //beginfun
    private float zoom;
    private int xZoomPanCompensation;
    private int yZoomPanCompensation;

    private float xVirtualProjectionSize;
    private float yVirtualProjectionSize;
    private float xVirtualPan;
    private float yVirtualPan;

    private double realXUnit;
    private double realYUnit;

    private boolean mouseDragInProgress = false;
    private int xLastMouseDragStart = 0;
    private int yLastMouseDragStart = 0;
    private int xPan = 0;
    private int yPan = 0;

    // indicator border offset
    private final short ibo = 1;
    // indicator line angle factor
    private final short ilaf = 3;
    // indicator line length
    private final short ill = 10;
    //endfun

    private boolean clickIndicatorActive = false;
    private int xLastClicked = 0;
    private int yLastClicked = 0;
    private short lastCategoryClicked;

    // Correspond to categories.
    private Color[] colours;

    private List<String> theme = MapColorSchemes.ScaledRed;

    private int legendMin;
    private int legendMax;
    private int legendMean;

    Consumer<MouseEvent> ac;
    private HashMap<String, Short> codeCatMap;

    public Integer getLegendMin() {
        return legendMin;
    }

    public Integer getLegendMax() {return legendMax;
    }

    public Integer getLegendMean() {
        return legendMean;
    }

    public MugOwoRenderer(OwoMap map, List<MapRegionData> mapData, Consumer<MouseEvent> clickAction, MapRegionDataAccessor acs){
        this.map = map;
        refreshMetadata(mapData, acs);
        mapRegionDataAccessor = acs;
        this.ac = clickAction;
        MapInputAdapter adaptor = new MapInputAdapter();
        this.addMouseListener(adaptor);
        this.addMouseWheelListener(adaptor);
        this.addMouseMotionListener(adaptor);
        this.addKeyListener(adaptor);
        this.init();
    }

    public List<String> getTheme() {
        return theme;
    }

    public void loadMap(String filePath) {
        OwoMap map = new OwoMap();
        try {
            map.readOwoMap(filePath);
        } catch (IOException e) {
            MugLogger.log(e);
        }
        this.map = map;

        this.init();
    }

    @Override
    public int getHeight() {
        return this.yProjectionSize;
    }

    @Override
    public int getWidth(){
        return this.xProjectionSize;
    }

    @Override
    public void paintComponent(Graphics g) {
        this.setProjectionVariables();

        final int rightEdge = xProjectionSize - themeBorder - 10;
        final int bottomEdge = yProjectionSize - themeBorder;
        // Draw the map
        for (int x = 0; x < xProjectionSize; x++){
            for (int y = 0; y < yProjectionSize; y++){
                // get the subSample
                final short cat = getProjectedCategory(x, y);

                // Chceck whether or not we're on the edge
                if(!(x < themeBorder || x > rightEdge || y < themeBorder || y > bottomEdge)){
                    //TODO: Instead of checking the underlying array of data, we could be checking the virtual projection
                    // as the calls bellow are fairly expensive.
                    final short right = getProjectedCategory(x + 1 % xProjectionSize, y);
                    final short left = getProjectedCategory(x - 1 % xProjectionSize, y);
                    final short top = getProjectedCategory(x, y + 1);
                    final short down = getProjectedCategory(x, y - 1);

                    if(top == down && down == left && left == right || zoom < 1){
                        g.setColor(colours[cat]);
                    } else
                    {
                        if ( cat != 0 && this.lastCategoryClicked == cat){
                            g.setColor(Color.white);
                        } else {
                            g.setColor(Color.black);
                        }
                    }
                } else {
                    // Just fancy stuff
                    g.setColor(colours[1]);
                }

                // Invert Y - projecting top left to bottom right data structure into a bottom left, top right plane
                final int yInverted = yProjectionSize - 1 - y;
                g.drawLine(x,yInverted,x,yInverted);
            }
        }

        // Draw the pointer
        if(clickIndicatorActive){
            g.setColor(Color.green);
            g.drawLine(xLastClicked, yLastClicked+ibo, xLastClicked + ill, yLastClicked+ibo+ilaf);
            g.drawLine(xLastClicked, yLastClicked-ibo, xLastClicked + ill, yLastClicked-ibo+ilaf);
            g.drawLine(xLastClicked+ibo, yLastClicked, xLastClicked+ibo+ilaf, yLastClicked + ill);
            g.drawLine(xLastClicked-ibo, yLastClicked, xLastClicked-ibo+ilaf, yLastClicked + ill);
            g.drawLine(xLastClicked, yLastClicked, xLastClicked + ill+1, yLastClicked+ilaf);
            g.drawLine(xLastClicked, yLastClicked, xLastClicked+ilaf, yLastClicked + ill+1);
            g.setColor(Color.BLACK);
            g.drawLine(xLastClicked, yLastClicked, xLastClicked + ill, yLastClicked+ilaf);
            g.drawLine(xLastClicked, yLastClicked, xLastClicked+ilaf, yLastClicked + ill);
        }
    }

    private void setProjectionVariables(){
        this.xVirtualProjectionSize = this.zoom * this.xProjectionSize;
        this.yVirtualProjectionSize = this.zoom * this.yProjectionSize;
        this.xVirtualPan = this.xPan * zoom;
        this.yVirtualPan = this.yPan * zoom;
    }

    private int getProjectedX(int x){
        float xSubModulo =  ((x + this.xVirtualPan) % this.xVirtualProjectionSize);
        double xSubExact = this.realXUnit * (xSubModulo < 0 ? this.xVirtualProjectionSize + xSubModulo : xSubModulo);
        return (int)Math.floor(xSubExact);
    }

    private int getProjectedY(int y){
        float ySubModulo =  ((y - this.yVirtualPan) % this.yVirtualProjectionSize);
        double ySubExact = this.realYUnit * (ySubModulo < 0 ? this.yVirtualProjectionSize + ySubModulo : ySubModulo);
        return (int)Math.floor(ySubExact);
    }

    private short getProjectedCategory(int x, int y){
        return this.map.data[getProjectedX(x)][getProjectedY(y)];
    }

    /**
     * Refreshes the projected metadata regarding categories and their colours as well as accessed value statistics.
     * @param mapMetadata
     */
    public void refreshMetadata(List<MapRegionData> mapMetadata,  MapRegionDataAccessor acs){
        mapRegionDataAccessor = acs;
        Collections.sort(mapMetadata);

        final int[] minMaxMean = getMinMaxMean(mapMetadata);
        legendMin = minMaxMean[0];
        legendMax = minMaxMean[1];
        legendMean = minMaxMean[2];

        codeCatMap =  this.map.getCodeCatMap();
        colours = getColorsForTheme(mapMetadata, theme);
    }

    /**
     * Returns the min, max, mean of the accessed value in this order.
     * @param mapData Data transfer object.
//     * @param acs Lambda, defines what value is accessed on mapData.
     * @return Returns the min, max, mean of the accessed value in this order.
     */
    public int[] getMinMaxMean(List<MapRegionData> mapData){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int mean = 0;

        for (MapRegionData regionData: mapData){
            final int value = mapRegionDataAccessor.getVal(regionData);
            if(min > value){
                min = value;
            } else if (max < value){
                max = value;
            }
            mean += value;
        }
        mean /= mapData.size();

        return new int[]{min, max, mean};
    }

    public Color[] getColorsForTheme(List<MapRegionData> mapData, List<String> theme){
        Color[] colours = new Color[mapData.size() + 1];
        colours[0] = hex2Rgb(AppConstants.Properties.DEFAULT_MAP_COLOR);

        // Scaled colors
        if(theme.size() == 2){
            int[] measures = getMinMaxMean(mapData);
            final int min = measures[0];
            final int max = measures[1];
            for (int i = 0; i < mapData.size(); i++) {
                final MapRegionData curData = mapData.get(i);
                colours[codeCatMap.get(curData.Code)] = scaleColor(mapRegionDataAccessor.getVal(curData), min, max, hex2Rgb(theme.get(0)), hex2Rgb(theme.get(1)));
            }

        }

        // Associated colors

        final int catsPerColour = mapData.size() / theme.size();
        final int catsPerLastColour = catsPerColour + mapData.size() % theme.size();

        // for all 'full' colours
        for (int colIx = 0; colIx < theme.size() - 1; colIx++) {
            final int ixFirst = colIx * catsPerColour;
            final int ixLast = colIx * catsPerColour + catsPerColour - 1;

            for (int catIx = 0; catIx < catsPerColour; catIx++) {
                final MapRegionData curData = mapData.get(ixFirst + catIx);
                colours[codeCatMap.get(curData.Code)] = scaleColor(
                        mapRegionDataAccessor.getVal(curData),
                        mapRegionDataAccessor.getVal(mapData.get(ixFirst)),
                        mapRegionDataAccessor.getVal(mapData.get(ixLast)),
                        hex2Rgb(theme.get(colIx)),
                        hex2Rgb(theme.get(colIx + 1))
                        );
            }
        }

        final int ixFirst = (theme.size() - 1) * catsPerColour;
        final int ixLast = mapData.size() - 1;

        for (int i = 0; i < catsPerLastColour; i++) {
            final MapRegionData curData = mapData.get(ixFirst + i);
            colours[codeCatMap.get(curData.Code)] = scaleColor(
                    mapRegionDataAccessor.getVal(mapData.get(ixFirst + i)),
                    mapRegionDataAccessor.getVal(mapData.get(ixFirst)),
                    mapRegionDataAccessor.getVal(mapData.get(ixLast)),
                    hex2Rgb(theme.get(theme.size() - 2)),
                    hex2Rgb(theme.get(theme.size() - 1))
            );
        }

        return colours;
    }

    public static Color scaleColor(double val,  double valMin, double valMax, Color colMin, Color colMax){
        if(valMin == valMax){
            return colMin;
        }

        return new Color(
                (int)Math.floor(scaleValue(val, valMin, valMax, colMin.getRed(), colMax.getRed())),
                (int)Math.floor(scaleValue(val, valMin, valMax, colMin.getGreen(), colMax.getGreen())),
                (int)Math.floor(scaleValue(val, valMin, valMax, colMin.getBlue(), colMax.getBlue()))
        );
    }

    public static double scaleValue(double val, double valMin, double valMax, double limitMin, double limitMax) {
        if(valMin > valMax){
            return scaleValue(val, valMax, valMin, limitMin, limitMax);
        }
        return ((limitMax - limitMin) * (val - valMin) / (valMax - valMin)) + limitMin;
    }

    private void init(){
        resetControlUnits();
        setRenderingUnits();
    }

    private void resetControlUnits(){
        zoom = 1;
        xZoomPanCompensation = 0;
        yZoomPanCompensation = 0;

        mouseDragInProgress = false;
        xLastMouseDragStart = 0;
        yLastMouseDragStart = 0;
        xPan = 0;
        yPan = 0;

        clickIndicatorActive = false;
        xLastClicked = 0;
        yLastClicked = 0;
        lastCategoryClicked = 0;
    }

    private void setRenderingUnits() {
        this.xDataSize = map.getxSize();
        this.yDataSize = map.getySize();
        this.realXUnit = xDataSize/(double)(xProjectionSize * zoom);
        this.realYUnit = yDataSize/(double)(yProjectionSize * zoom);
    }

    public int getLastClickedX() {
        return this.xLastClicked;
    }

    public int getLastClickedY() {
        return this.yLastClicked;
    }

    public void resetView(){
        resetControlUnits();
        setRenderingUnits();
        repaint();
    }

    public short getLastSelectedCat(){
        return this.lastCategoryClicked;
    }

    public String getLastSelectedCode(){
        return this.map.getCatLabels()[this.lastCategoryClicked];
    }

    public void mouseClicked(MouseEvent e) {

    }

    private void onCoordinateSelected(){
        clickIndicatorActive = true;
    }

    private void onCoordinateDeselected(){
        clickIndicatorActive = false;
    }


    public void selectTheme(List<String> theme) {
        this.theme = theme;
    }

    public void setLastClickedCode(String code) {
        short cat = map.getCodeCatMap().get(code);
        this.lastCategoryClicked = cat;
        this.clickIndicatorActive = false;
        repaint();
    }

    public void resetIndicators() {
        this.clickIndicatorActive = false;
        this.lastCategoryClicked = 0;
    }

    public void saveMapToImage(){
        BufferedImage bImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        paint(cg);
        try {
            ImageIO.write(bImg, "jpeg", AppConstants.Paths.resolvePath(AppConstants.Paths.Outputs.MAP_EXPORT_FILE).toFile());
        } catch (IOException e) {
            System.out.println("Error saving map");
        }
    }

    class MapInputAdapter extends MouseAdapter implements KeyListener {
        public MapInputAdapter() {
            super();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);

            if (e.getButton() != MouseEvent.BUTTON1) return;
            xLastClicked = e.getX();
            yLastClicked = e.getY();
            setProjectionVariables();
            int yInverted = yProjectionSize - 1 - yLastClicked;
            lastCategoryClicked = getProjectedCategory(xLastClicked,  yInverted);
            ac.accept(e);
            onCatSelected();
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            xLastMouseDragStart = 0;
            yLastMouseDragStart = 0;
            mouseDragInProgress = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            requestFocusInWindow();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            super.mouseWheelMoved(e);

//            if(e.getPreciseWheelRotation() > 0){
            if(e.getPreciseWheelRotation() > 0 && zoom > 0.40){
                zoomOut();
//            } else if( e.getPreciseWheelRotation() < 0) {
            } else if( e.getPreciseWheelRotation() < 0 && zoom < 16) {
                zoomIn();
            }
            onCoordinateDeselected();
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);

            int xMouseDragEnd = e.getX();
            int yMouseDragEnd = e.getY();

            if(mouseDragInProgress){
                xPan += (xLastMouseDragStart - xMouseDragEnd) / zoom;
                yPan += (yLastMouseDragStart - yMouseDragEnd) / zoom;
                repaint();
            }

            xLastMouseDragStart = xMouseDragEnd;
            yLastMouseDragStart = yMouseDragEnd;
            mouseDragInProgress = true;
            onCoordinateDeselected();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
        }

        @Override
        public void keyTyped(KeyEvent e) {
            char charPressed = e.getKeyChar();

            if( charPressed == 'i'){
                zoomIn();
            } else if (charPressed == 'o'){
                zoomOut();
            } else if (charPressed == KeyEvent.VK_ESCAPE) {
                resetView();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        private void onCatSelected(){
            clickIndicatorActive = true;
        }

        private void onCoordinateDeselected(){
            clickIndicatorActive = false;
        }

        private void zoomIn(){
            zoom *= 2;

            int xCurrentCompensation = (int)((xProjectionSize - (xProjectionSize/zoom)) / 2) - xZoomPanCompensation;
            int yCurrentCompensation = -(int)((yProjectionSize - (yProjectionSize/zoom)) / 2) - yZoomPanCompensation;
            xZoomPanCompensation += xCurrentCompensation;
            yZoomPanCompensation += yCurrentCompensation;
            xPan += xCurrentCompensation;
            yPan += yCurrentCompensation;

            setRenderingUnits();
            clickIndicatorActive = false;
            repaint();
        }

        private void zoomOut(){
            zoom /= 2;

            int xCurrentCompensation = -xZoomPanCompensation +(int)((xProjectionSize - (xProjectionSize/zoom)) / 2);
            int yCurrentCompensation = - (yZoomPanCompensation  + (int)((yProjectionSize - (yProjectionSize/zoom)) / 2));
            xZoomPanCompensation += xCurrentCompensation;
            yZoomPanCompensation += yCurrentCompensation;
            xPan += xCurrentCompensation;
            yPan += yCurrentCompensation;

            setRenderingUnits();
            clickIndicatorActive = false;
            repaint();
        }
    }
}
