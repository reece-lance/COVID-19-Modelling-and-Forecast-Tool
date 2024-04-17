package csee.ce291_team02.Forms;

import com.codebrig.journey.JourneyBrowserView;
import com.codebrig.journey.JourneyLoader;
import com.codebrig.journey.proxy.CefBrowserProxy;
import com.codebrig.journey.proxy.CefClientProxy;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import csee.ce291_team02.App;
import csee.ce291_team02.AppConstants;
import csee.ce291_team02.AppConstants.CountryComparison.PerformanceLabels;
import csee.ce291_team02.ColourCodedMap.LegendPanel;
import csee.ce291_team02.ColourCodedMap.MugOwoRenderer;
import csee.ce291_team02.ColourCodedMap.OwoMap;
import csee.ce291_team02.Data.CurrentKnotsRetriever;
import csee.ce291_team02.Data.DataHelpers;
import csee.ce291_team02.Data.LmaoNinjaApiClient.CountryData;
import csee.ce291_team02.Forms.FormUtils.ComparisonChevronRenderer;
import csee.ce291_team02.Forms.FormUtils.ComparisonDescriptionRenderer;
import csee.ce291_team02.Forms.FormUtils.ComparisonPerformanceRenderer;
import csee.ce291_team02.Forms.FormUtils.FlagImageResolver;
import csee.ce291_team02.Forms.FormUtils.ThousandSeparatorRenderer;
import csee.ce291_team02.Metrics.Metrics;
import csee.ce291_team02.Models.CovidCaseByDate;
import csee.ce291_team02.Models.GraphResult;
import csee.ce291_team02.Models.MapRegionData;
import csee.ce291_team02.MugBrowser.BrowserHelper;
import csee.ce291_team02.MugBrowser.MugLoadListener;
import csee.ce291_team02.MugGraphs.ContinuousPieceWiseLinearRegressionGraph;
import csee.ce291_team02.MugGraphs.Graphs;
import csee.ce291_team02.MugGraphs.LinearRegressionGraphs;
import csee.ce291_team02.MugGraphs.PiecewiseLinearRegressionGraphs;
import csee.ce291_team02.MugLogger;
import csee.ce291_team02.MugStats.Models.MugPoint;
import csee.ce291_team02.MugStats.util.RegressionMappers;
import csee.ce291_team02.PdfExport.PDFCreation;
import tech.tablesaw.plotly.components.Figure;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import static csee.ce291_team02.AppConstants.SpecialCharacters.*;
import static csee.ce291_team02.Models.MapRegionData.zeroIfNull;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JTable dataTable;
    private JButton refreshButton;
    private JComboBox<AppConstants.Selections.Model> graphComboBox;
    private JPanel browserPanel;
    private JTabbedPane MainTabbedPane;
    private JPanel AboutTab;
    private JScrollPane dataTableScrollPane;
    private JButton refreshGraphButton;
    private JButton exportButton;
    private JTextPane aboutTextPane;
    private JButton stuffOutButtonButton;
    private JPanel GraphsPanel;
    private JLabel rssLabel;
    private JLabel tssLabel;
    private JLabel rSquaredLabel;
    private JPanel Comparison;
    private JTable countryTable1;
    private JTable countryTable2;
    private JButton refreshCountryDataButton;
    private JComboBox<String> countryComboBox1;
    private JComboBox<String> countryComboBox2;
    private JTabbedPane tabbedPane1;
    private JCheckBox titleCheckBox;
    private JCheckBox graphCheckBox;
    private JCheckBox meanCheckBox;
    private JCheckBox sdCheckBox;
    private JCheckBox varCheckBox;
    private JCheckBox totalCheckBox;
    private JPanel MapTab;
    private JTable mapTable;
    private JPanel mapPanel;
    private JTable countryPerformanceTable1;
    private JTable countryPerformanceTable2;
    private JLabel FlagLabel1;
    private JLabel FlagLabel2;
    private JButton cacheDeleteButton;
    private JLabel deleteCacheLabel;
    private JScrollPane ColourcodedInfoTable;
    private JComboBox mapComboBox;
    private JComboBox mapVariableComboBox;
    private JComboBox mapThemeComboBox;
    private JLabel MapHeatLabelHigh;
    private JLabel MapHeatLabelMiddle;
    private JLabel MapHeatLabelLow;
    private JPanel mapColorLegend;
    private JCheckBox rssCheckBox;
    private JCheckBox tssCheckBox;
    private JCheckBox rSquaredCheckBox;
    private JPanel metricsPanel;
    private JLabel meanLabel;
    private JLabel sdLabel;
    private JLabel varLabel;
    private JLabel totalLabel;
    private JComboBox graphedVariableComboBox;
    private JSpinner predictionDaysSpinner;
    private JCheckBox mapCheckBox;
    private JLabel ukData;
    private JFileChooser fileChooser;

    private GraphResult graphResult;

    /**
     * List of UK Covid Cases
     */
    private List<CovidCaseByDate> covidCaseByDateData;
    /**
     * Models for UK cases table model
     */
    private DefaultTableModel modelCovidCases;

    /**
     * List of Country Data
     */
    private List<CountryData> countryData;
    /**
     * Models for countries table
     */
    private DefaultTableModel modelTrendingCountries1;
    private DefaultTableModel modelTrendingCountries2;
    private DefaultTableModel modelTrendingCountriesPerformance1;
    private DefaultTableModel modelTrendingCountriesPerformance2;

    /**
     * Default number of prediction days when program starts
     */
    private int numberOfPredictDays = AppConstants.Properties.DEFAULT_PREDICTION_DAYS;

    /**
     * Browser related items
     */
    private static JourneyBrowserView browserView;
    private CefBrowserProxy browserProxy;

    public static boolean startMaximized = false;

    /**
     * Map Items, App Constants, Panels and Table Model
     */
    private OwoMap map;
    private MugOwoRenderer mugOwoRenderer;
    private AppConstants.Selections.Model selectedModel = AppConstants.Selections.Model.PCLR;
    private AppConstants.Selections.Map selectedMap = AppConstants.Selections.Map.REGIONS;
    private List<MapRegionData> mapData;
    private DefaultTableModel mapTableModel;
    private AppConstants.Selections.MapTheme selectedMapTheme;
    private AppConstants.Selections.MapVariable selectedMapVariable;
    private LegendPanel pane;
    private AppConstants.Selections.GraphedVariable selectedVariableToGraph = AppConstants.Selections.GraphedVariable.NEW_CASES;

    /**
     * Constructor responsible for setting title, visibility, root panel, and initializing functions.
     */
    public MainForm() {
        add(rootPanel);
        setTitle(rootPanel.getToolTipText());
        setVisible(true);

        if (startMaximized) {
            this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        } else {
            setSize(1300, 1000);
        }

        initialize();
        predictionDaysSpinner.addComponentListener(new ComponentAdapter() {
        });
    }

    /**
     * Initialises methods that are responsible for initialising certain functionalities within the program
     */
    private void initialize() {
        try {
            Path t = AppConstants.RuntimeVariables.getAppTempFolderPath();
            System.out.println(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initializeTable();
        initializeTableRefreshButton();
        initializeGraphRefreshButton();
        initializeExportButton();
        initializeGraphComboBox();
        initializeGraphedVariableComboBox();
        initializeBrowser();
        initializePredictionDaysSpinner();
        MainTabbedPane.addChangeListener(e -> {
            JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
            int selectedIndex = tabbedPane.getSelectedIndex();

            if (selectedIndex == 0) { // Graph page
                refreshGraphPage();
            } else if (selectedIndex == 1) {// CountryComparisonTab
                try {
                    if (App.countryDataDataSupplier.hasData()) {
                        loadCountriesData();
                        refreshCountryDataButton.doClick();
                    }
                } catch (Exception ex) {
                    MugLogger.log(ex);
                }
            }
        });

        initializeComparisonTab();
        initializeMapTab();
        initializeDangerZoneTab();
        initializeAboutTab();
    }

    /**
     * Initialises a spinner that allows one to change the number of prediction days
     */
    private void initializePredictionDaysSpinner() {
        SpinnerNumberModel snm = new SpinnerNumberModel(AppConstants.Properties.DEFAULT_PREDICTION_DAYS, 1, 60, 1);
        snm.addChangeListener(e -> MugLogger.log(e));
        ((JSpinner.DefaultEditor) predictionDaysSpinner.getEditor()).getTextField().setEditable(false);

        predictionDaysSpinner.setModel(snm);
        predictionDaysSpinner.addChangeListener(e -> {
            numberOfPredictDays = (int) predictionDaysSpinner.getValue();
        });
    }

    /**
     * Initialises the combo box responsible for changing the graph type
     */
    private void initializeGraphComboBox() {
        graphComboBox.addItem(AppConstants.Selections.Model.PCLR);
        graphComboBox.addItem(AppConstants.Selections.Model.PLR);
        graphComboBox.addItem(AppConstants.Selections.Model.LR);

        graphComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selectedModel = (AppConstants.Selections.Model) e.getItem();
                refreshGraphPage();
            }
        });
    }

    /**
     * Initialises the combo box responsible for changing the graph variable
     */
    private void initializeGraphedVariableComboBox() {
        graphedVariableComboBox.addItem(AppConstants.Selections.GraphedVariable.NEW_CASES);
        graphedVariableComboBox.addItem(AppConstants.Selections.GraphedVariable.CUM_CASES);

        graphedVariableComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selectedVariableToGraph = (AppConstants.Selections.GraphedVariable) e.getItem();
                refreshGraphPage();
            }
        });
    }

    /**
     * Loads table data, refreshes graph and shows metrics
     */
    private void refreshGraphPage() {
        loadTableData();
        refreshGraph();
        showMetrics();
    }

    private void initializeMetricsTable() {

    }

    private void initializeExportTab() {

    }

    private void initializeDangerZoneTab() {
        deleteCacheLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

//        File deleteCacheImage = new File(AppConstants.Paths.Resources.DELETE_CACHE_IMAGE);
        File deleteCacheImage = AppConstants.Paths.resolvePath(AppConstants.Paths.TempResources.TEMP_DELETE_CACHE_IMAGE).toFile();
//        File yesImageFile = new File(AppConstants.Paths.Resources.YES_IMAGE);
        File yesImageFile = AppConstants.Paths.resolvePath(AppConstants.Paths.TempResources.TEMP_YES_IMAGE).toFile();

        deleteCacheLabel.setIcon(new ImageIcon(deleteCacheImage.getAbsolutePath()));
        cacheDeleteButton.setIcon(new ImageIcon(yesImageFile.getAbsolutePath()));
        cacheDeleteButton.addActionListener(e ->
                {
                    App.covidCaseByDateDataSupplier.deleteCache();
                    App.countryDataDataSupplier.deleteCache();
                    App.mapLtlaDataSupplier.deleteCache();
                    App.mapUtlaDataSupplier.deleteCache();
                    App.mapRegionDataSupplier.deleteCache();
                    CurrentKnotsRetriever.cleanseCash();
                }
        );
    }

    private void initializeComparisonTab() {
        initializeComparisonTables();
        initializeRefreshCountryDataButton();
        initializeCountryComparisionFlagImages();

        Border b = BorderFactory.createLineBorder(Color.black, 1);
        FlagLabel1.setBorder(b);
        FlagLabel2.setBorder(b);
    }

    /**
     * The following sets the model of the country comparison table
     */
    private void initializeComparisonTables() {
        String[] leftColumnsCountries = {"Variable", "Data", "Chevron"};
        String[] rightColumnsCountries = {"Chevron", "Variable", "Data"};
        modelTrendingCountries1 = new DefaultTableModel(leftColumnsCountries, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelTrendingCountries2 = new DefaultTableModel(rightColumnsCountries, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelTrendingCountriesPerformance1 = new DefaultTableModel(leftColumnsCountries, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelTrendingCountriesPerformance2 = new DefaultTableModel(rightColumnsCountries, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        countryTable1.setCellSelectionEnabled(false);
        countryTable2.setCellSelectionEnabled(false);
        countryPerformanceTable1.setCellSelectionEnabled(false);
        countryPerformanceTable2.setCellSelectionEnabled(false);
    }

    private void initializeRefreshCountryDataButton() {
        refreshCountryDataButton.addActionListener(e -> {
            loadCountriesData();

            if (countryComboBox1.getItemCount() == 0) {
                initializeCountryComboBox(countryComboBox1);
            }
            if (countryComboBox2.getItemCount() == 0) {
                initializeCountryComboBox(countryComboBox2);
            }

            countryComboBox1.setSelectedIndex(207);
            countryComboBox2.setSelectedIndex(93);
            updateCountryTables();
        });
    }

    private void loadCountriesData() {
        if (countryData != null) {
            countryData.clear();
        }
        countryData = App.countryDataDataSupplier.getData();
    }

    private void initializeCountryComboBox(JComboBox<String> comboBox) {
        for (int i = 0; i < countryData.size(); i++) {
            comboBox.addItem(countryData.get(i).getCountry());
        }
        comboBox.addActionListener(e -> {
            updateCountryTables();
        });
    }

    private void initializeCountryComparisionFlagImages() {
        updateCountryComparisonFlags(
                AppConstants.Properties.UNKNOWN_FLAG_ISO_CODE,
                AppConstants.Properties.UNKNOWN_FLAG_DESCRIPTION,
                AppConstants.Properties.UNKNOWN_FLAG_ISO_CODE,
                AppConstants.Properties.UNKNOWN_FLAG_DESCRIPTION);
    }

    private void updateCountryComparisonFlags(String iso3Code1, String countryName1, String iso3Code2, String countryName2) {
        FlagLabel1.setIcon(FlagImageResolver.getFlag(iso3Code1, countryName1));
        FlagLabel2.setIcon(FlagImageResolver.getFlag(iso3Code2, countryName2));
    }

    private void updateCountryComparisonFlags() {
        CountryData firstCountry = countryData.get(countryComboBox1.getSelectedIndex());
        CountryData secondCountry = countryData.get(countryComboBox2.getSelectedIndex());
        updateCountryComparisonFlags(firstCountry.getIso3(), firstCountry.getCountry(), secondCountry.getIso3(), secondCountry.getCountry());
    }

    /**
     * Sets the country comparison table models, sets fonts and adds columns to the tables
     */
    private void updateCountryTables() {
        createCountriesTableModel();
        countryTable1.setModel(modelTrendingCountries1);
        countryTable2.setModel(modelTrendingCountries2);
        countryPerformanceTable1.setModel(modelTrendingCountriesPerformance1);
        countryPerformanceTable2.setModel(modelTrendingCountriesPerformance2);

        countryTable1.setFont(AppConstants.Properties.unicodeFont);
        countryTable2.setFont(AppConstants.Properties.unicodeFont);
        countryPerformanceTable1.setFont(AppConstants.Properties.unicodeFont);
        countryPerformanceTable2.setFont(AppConstants.Properties.unicodeFont);

        countryTable1.getColumn("Data").setCellRenderer(new ThousandSeparatorRenderer());
        countryTable2.getColumn("Data").setCellRenderer(new ThousandSeparatorRenderer());

        countryPerformanceTable1.getColumn("Chevron").setCellRenderer(new ComparisonChevronRenderer());
        countryPerformanceTable2.getColumn("Chevron").setCellRenderer(new ComparisonChevronRenderer());
        countryPerformanceTable1.getColumn("Data").setCellRenderer(new ComparisonPerformanceRenderer());
        countryPerformanceTable2.getColumn("Data").setCellRenderer(new ComparisonPerformanceRenderer());
        countryPerformanceTable1.getColumn("Variable").setCellRenderer(new ComparisonDescriptionRenderer());
        countryPerformanceTable2.getColumn("Variable").setCellRenderer(new ComparisonDescriptionRenderer());

        updateCountryComparisonFlags();
    }

    /**
     * This has to do with the creation of the model into which the country comparison data goes into
     */
    private void createCountriesTableModel() {
        String[] variableValues = {"Country", "Population", "Cases", "Deaths", "Recovered", "Yesterday Cases",
                "Yesterday Deaths", "Yesterday Recovered"};
        modelTrendingCountries1.setRowCount(0);
        modelTrendingCountries2.setRowCount(0);
        Object[] leftDataValues = getDataValues(countryComboBox1.getSelectedIndex());
        Object[] rightDataValues = getDataValues(countryComboBox2.getSelectedIndex());
        String[][] dataChevrons = calculateChevrons(leftDataValues, rightDataValues, null);

        for (int i = 0; i < variableValues.length; i++) {
            modelTrendingCountries1.addRow(new Object[]{variableValues[i], leftDataValues[i], dataChevrons[0][i]});
            modelTrendingCountries2.addRow(new Object[]{dataChevrons[1][i], variableValues[i], rightDataValues[i]});
        }

        String[] performanceVariableValues = {PerformanceLabels.INFECTION_RATE, PerformanceLabels.DEATH_RATE, PerformanceLabels.RECOVERY_RATE};
        modelTrendingCountriesPerformance1.setRowCount(0);
        modelTrendingCountriesPerformance2.setRowCount(0);
        Object[] leftPerformanceDataValues = getPerformanceDataValues((int) leftDataValues[1], (int) leftDataValues[2], (int) leftDataValues[3], (int) leftDataValues[4]);
        Object[] rightPerformanceDataValues = getPerformanceDataValues((int) rightDataValues[1], (int) rightDataValues[2], (int) rightDataValues[3], (int) rightDataValues[4]);
        String[][] performanceDataChevrons = calculateChevrons(leftPerformanceDataValues, rightPerformanceDataValues, new boolean[]{true, true, false});

        for (int i = 0; i < performanceVariableValues.length; i++) {
            modelTrendingCountriesPerformance1.addRow(new Object[]{performanceVariableValues[i], leftPerformanceDataValues[i], performanceDataChevrons[0][i]});
            modelTrendingCountriesPerformance2.addRow(new Object[]{performanceDataChevrons[1][i], performanceVariableValues[i], rightPerformanceDataValues[i]});
        }
    }

    /**
     * This is the retrieval of country data and assigning them into int and string variables.
     * Each index of Object[] represents a country and it's information.
     *
     * @param index The index of Object[] in which the data is in.
     * @return Object[]
     */
    private Object[] getDataValues(int index) {
        String country = countryData.get(index).getCountry();
        int population = countryData.get(index).getPopulation();
        int cases = countryData.get(index).getCases();
        int deaths = countryData.get(index).getDeaths();
        int recovered = countryData.get(index).getRecovered();
        int yesterdayCases = countryData.get(index).getYesterdaysCases();
        int yesterdayDeaths = countryData.get(index).getYesterdaysDeaths();
        int yesterdayRecovered = countryData.get(index).getYesterdaysRecovered();

        return new Object[]{country, population, cases, deaths, recovered, yesterdayCases, yesterdayDeaths, yesterdayRecovered};
    }

    /**
     * Makes use of data values to retrieve performance data values.
     *
     * @param population Population of the country
     * @param cases      Number of cases of the country
     * @param deaths     Number deaths due to COVID-19 in the country
     * @param recovered  Number of people who have recovered from COVID-19 in the country
     * @return Object[]
     */
    private Object[] getPerformanceDataValues(int population, int cases, int deaths, int recovered) {
        double infectionRate = Metrics.infectionRate(cases, population);
        double deathRate = Metrics.deathRate(deaths, cases);
        double recoveryRate = Metrics.recoveryRate(recovered, cases);

        return new Object[]{infectionRate, deathRate, recoveryRate};
    }

    /**
     * Compares country data values to determine the correct chevron to show
     *
     * @param leftDataValues  Data values of the country on the left side of country comparison UI
     * @param rightDataValues Data values of the country on the right side of country comparison UI
     * @param performanceRows The row of performance metrics
     * @return String[][]
     */
    private String[][] calculateChevrons(Object[] leftDataValues, Object[] rightDataValues, boolean[] performanceRows) {
        String[] leftChevrons = new String[leftDataValues.length];
        String[] rightChevrons = new String[rightDataValues.length];

        for (int i = 0; i < leftDataValues.length; i++) {
            if (!(leftDataValues[i] instanceof String)) {
                String high = performanceRows == null
                        ? upChevron
                        : performanceRows[i]
                        ? CharacterModifiers.GREATER_NEGATIVE.toString()
                        : CharacterModifiers.GREATER_POSITIVE.toString();

                String low = performanceRows == null
                        ? downChevron
                        : !performanceRows[i]
                        ? CharacterModifiers.LOWER_NEGATIVE.toString()
                        : CharacterModifiers.LOWER_POSITIVE.toString();
                Double left;
                Double right;
                if (leftDataValues[i] instanceof Integer) {
                    left = (double) (int) leftDataValues[i];
                    right = (double) (int) rightDataValues[i];
                } else {
                    left = (Double) leftDataValues[i];
                    right = (Double) rightDataValues[i];
                }

                if (left.compareTo(right) > 0) {
                    leftChevrons[i] = high;
                    rightChevrons[i] = low;
                } else if (left.compareTo(right) < 0) {
                    leftChevrons[i] = low;
                    rightChevrons[i] = high;
                } else {
                    leftChevrons[i] = equalsSign;
                    rightChevrons[i] = equalsSign;
                }
            } else {
                leftChevrons[i] = "";
                rightChevrons[i] = "";
            }
        }
        return new String[][]{leftChevrons, rightChevrons};
    }

    public void initializeMapTab() {
        initializeMapPanel();
    }


    public void initializeMapPanel() {
        initializeMapCombobox();
        initializeMapTable();
        refreshMapData();
        loadMapTableData(mapData);
        map = new OwoMap();
        try {
            map.readOwoMap(AppConstants.Paths.Resources.Maps.REGIONS_MAP_NAME);
        } catch (IOException e) {
            MugLogger.log(e);
        }
        MugOwoRenderer mugOwoRenderer = new MugOwoRenderer(map, mapData, this::highlightMapRow, x -> x.regionCasesDaily);
        this.mugOwoRenderer = mugOwoRenderer;
        //region renderer listener section
        MainForm that = this;
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                mugOwoRenderer.dispatchEvent(e);
                mapPanel.repaint();
                repaint();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                mugOwoRenderer.dispatchEvent(e);
                mapPanel.repaint();
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                mugOwoRenderer.dispatchEvent(e);
                mapPanel.repaint();
                repaint();
            }
        });

        MouseAdapter renderedAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mugOwoRenderer.dispatchEvent(SwingUtilities.convertMouseEvent(mapPanel, e, mugOwoRenderer));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mugOwoRenderer.dispatchEvent(SwingUtilities.convertMouseEvent(mapPanel, e, mugOwoRenderer));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mugOwoRenderer.dispatchEvent(SwingUtilities.convertMouseEvent(mapPanel, e, mugOwoRenderer));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mugOwoRenderer.dispatchEvent(SwingUtilities.convertMouseEvent(mapPanel, e, mugOwoRenderer));

            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                mugOwoRenderer.dispatchEvent(SwingUtilities.convertMouseEvent(mapPanel, e, mugOwoRenderer));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mugOwoRenderer.dispatchEvent(SwingUtilities.convertMouseEvent(mapPanel, e, mugOwoRenderer));
                mapPanel.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mugOwoRenderer.dispatchEvent(SwingUtilities.convertMouseEvent(mapPanel, e, mugOwoRenderer));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
//                mapTableHighlight(mugOwoRenderer.getLastSelectedCoordinate());
                mugOwoRenderer.dispatchEvent(SwingUtilities.convertMouseEvent(mapPanel, e, mugOwoRenderer));
            }
        };
        mapPanel.addMouseListener(renderedAdapter);
        mapPanel.addMouseMotionListener(renderedAdapter);
        //endregion section
        mapPanel.removeAll();
        mapPanel.add(mugOwoRenderer, BorderLayout.CENTER);
        mapPanel.revalidate();
        mapPanel.repaint();
        mapColorLegend.removeAll();
        mapColorLegend.revalidate();
        mapColorLegend.setLayout(new BorderLayout());
        pane = new LegendPanel(mugOwoRenderer);
        mapColorLegend.add(pane);
        refreshMapLegend();

        mapTable.getSelectionModel().addListSelectionListener(event -> {
            // do some actions here, for example
            // print first column value from selected row
            final int selectedRow = mapTable.getSelectedRow();
            if (selectedRow != -1) {
                mugOwoRenderer.setLastClickedCode(mapTable.getValueAt(selectedRow, 0).toString());
            }
        });
    }

    /**
     * Highlights the region on the map that the user clicks on
     *
     * @param e Population of the country
     */
    private void highlightMapRow(MouseEvent e) {
        final String codeSelected = mugOwoRenderer.getLastSelectedCode();
        int mapTableIx = -1;

        if (codeSelected == "none") {
            return;
        }

        for (int i = 0; i < mapTableModel.getRowCount(); i++) {
            final String code = (String) ((Vector) mapTableModel.getDataVector().get(i)).elementAt(0);
            if (codeSelected.equalsIgnoreCase(code)) {
                mapTableIx = i;
            }
        }

        // Not found
        if (mapTableIx == -1) {
            return;
        }
        mapTable.getSelectionModel().setSelectionInterval(mapTableIx, mapTableIx);
        mapTable.scrollRectToVisible(new Rectangle(mapTable.getCellRect(mapTableIx, 0, true)));

    }

    /**
     * Refreshes colour scale associated with the map according to what colour them has been chosen
     */
    private void refreshMapLegend() {
        pane.setLegendTheme(mugOwoRenderer.getTheme(), mugOwoRenderer.getLegendMin(), mugOwoRenderer.getLegendMax(), mugOwoRenderer.getLegendMean());

        MapHeatLabelLow.setText(mugOwoRenderer.getLegendMax().toString());
        MapHeatLabelMiddle.setText(mugOwoRenderer.getLegendMean().toString());
        MapHeatLabelHigh.setText(mugOwoRenderer.getLegendMin().toString());
    }

    private void initializeMapCombobox() {
        mapComboBox.addItem(AppConstants.Selections.Map.REGIONS);
        mapComboBox.addItem(AppConstants.Selections.Map.UTLA);
        mapComboBox.addItem(AppConstants.Selections.Map.LTLA);

        mapComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selectedMap = (AppConstants.Selections.Map) e.getItem();
                refreshMapData();
                MugLogger.log(e);
                switchMap();
                mugOwoRenderer.refreshMetadata(mapData, MapRegionData.mapRegionDataAccessor);
                mugOwoRenderer.repaint();
                loadMapTableData(mapData);
            }
        });

        mapThemeComboBox.addItem(AppConstants.Selections.MapTheme.ScaledRed);
        mapThemeComboBox.addItem(AppConstants.Selections.MapTheme.Flp);
        mapThemeComboBox.addItem(AppConstants.Selections.MapTheme.Rc);
        mapThemeComboBox.addItem(AppConstants.Selections.MapTheme.Dn);
        mapThemeComboBox.addItem(AppConstants.Selections.MapTheme.Jvr);
        mapThemeComboBox.addItem(AppConstants.Selections.MapTheme.Snskr);
        mapThemeComboBox.addItem(AppConstants.Selections.MapTheme.Nsr);
        mapThemeComboBox.addItem(AppConstants.Selections.MapTheme.DARK_SALMON);
        mapThemeComboBox.addItem(AppConstants.Selections.MapTheme.CUBEHELIX);
        mapThemeComboBox.addItem(AppConstants.Selections.MapTheme.Blues);
        mapThemeComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selectedMapTheme = (AppConstants.Selections.MapTheme) e.getItem();
                List<String> theme = null;
                switch (selectedMapTheme) {
                    case Blues:
                        theme = AppConstants.Selections.MapColorSchemes.Blues;
                        break;
                    case Flp:
                        theme = AppConstants.Selections.MapColorSchemes.Filip;
                        break;
                    case CUBEHELIX:
                        theme = AppConstants.Selections.MapColorSchemes.Cubehelix;
                        break;
                    case DARK_SALMON:
                        theme = AppConstants.Selections.MapColorSchemes.DarkSalmon;
                        break;
                    case Jvr:
                        theme = AppConstants.Selections.MapColorSchemes.Javier;
                        break;
                    case Dn:
                        theme = AppConstants.Selections.MapColorSchemes.Dean;
                        break;
                    case Snskr:
                        theme = AppConstants.Selections.MapColorSchemes.Sanskar;
                        break;
                    case Rc:
                        theme = AppConstants.Selections.MapColorSchemes.Reece;
                        break;
                    case Nsr:
                        theme = AppConstants.Selections.MapColorSchemes.Nasar;
                        break;
                    default:
                        theme = AppConstants.Selections.MapColorSchemes.ScaledRed;
                }
                mugOwoRenderer.selectTheme(theme);
                mapTabRefresh();
            }
        });

        mapVariableComboBox.addItem(AppConstants.Selections.MapVariable.CASES);
        mapVariableComboBox.addItem(AppConstants.Selections.MapVariable.CASES_FEMALE);
        mapVariableComboBox.addItem(AppConstants.Selections.MapVariable.CASES_MALE);
        mapVariableComboBox.addItem(AppConstants.Selections.MapVariable.DEATHS_TOTAL);
        mapVariableComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                selectedMapVariable = (AppConstants.Selections.MapVariable) e.getItem();

                switch (selectedMapVariable) {
                    case CASES:
                        MapRegionData.mapRegionDataAccessor = x -> x.regionCasesDaily;
                        break;
                    case DEATHS_TOTAL:
                        MapRegionData.mapRegionDataAccessor = x -> zeroIfNull(x.deathsCumulative);
                        break;
                    case CASES_FEMALE:
                        MapRegionData.mapRegionDataAccessor = x -> zeroIfNull(x.regionCasesFemale);
                        break;
                    case CASES_MALE:
                        MapRegionData.mapRegionDataAccessor = x -> zeroIfNull(x.regionCasesMale);
                        break;
                    case DEATHS_DAILY:
                        MapRegionData.mapRegionDataAccessor = x -> zeroIfNull(x.deathsDaily);
                        break;
                }

                mapTabRefresh();
                loadMapTableData(mapData);
                mugOwoRenderer.resetIndicators();
            }
        });
    }

    /**
     * Changes the map that the user selects
     */
    private void switchMap() {
        try {
            switch (selectedMap) {

                case REGIONS:
                    map.readOwoMap(AppConstants.Paths.Resources.Maps.REGIONS_MAP_NAME);
                    break;
                case UTLA:
                    map.readOwoMap(AppConstants.Paths.Resources.Maps.UTLA_MAP_NAME);
                    break;
                case LTLA:
                    map.readOwoMap(AppConstants.Paths.Resources.Maps.LTLA_MAP_NAME);
                    break;
            }
        } catch (IOException e) {
            MugLogger.log(e);
        }
        mugOwoRenderer.refreshMetadata(mapData, MapRegionData.mapRegionDataAccessor);
        mugOwoRenderer.resetView();
        refreshMapLegend();
    }

    /**
     * Refreshes everything to do with map tab besides the map data itself
     */
    private void mapTabRefresh() {
        mugOwoRenderer.refreshMetadata(mapData, MapRegionData.mapRegionDataAccessor);
        mugOwoRenderer.repaint();
        refreshMapLegend();
        mapColorLegend.repaint();
    }

    private void refreshMapData() {
        switch (selectedMap) {
            case REGIONS:
                mapData = App.mapRegionDataSupplier.getData();
                break;
            case UTLA:
                mapData = App.mapUtlaDataSupplier.getData();
                break;
            case LTLA:
                mapData = App.mapLtlaDataSupplier.getData();
                break;
        }
    }

    /**
     * Sets the the map table column names and model
     */
    private void initializeMapTable() {
        String[] mapTableColumns = {"Code", "Date", "Name", "Cases/D", "Deaths/D", "Deaths total", "Cases Fem", "Cases Male"};

        mapTableModel = new DefaultTableModel(mapTableColumns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        mapTable.setModel(mapTableModel);
        mapTable.getTableHeader().setReorderingAllowed(false);
        mapTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * Loads the map data into the map table model
     *
     * @param mapData List of region map data
     */
    private void loadMapTableData(List<MapRegionData> mapData) {
        Collections.sort(mapData);
        mapTableModel.setRowCount(0);
        for (MapRegionData rd : mapData) {
            Object[] tableValues = {rd.Code, rd.Date, rd.Name, rd.regionCasesDaily, rd.deathsDaily, rd.deathsCumulative, rd.regionCasesFemale, rd.regionCasesMale};
            mapTableModel.addRow(tableValues);
        }
    }

    private void initializeExportButton() {
        exportButton.addActionListener(e -> {
            mugOwoRenderer.saveMapToImage();
            createImage(browserPanel);
            File pdfDir = initializeFileChooser();
            if (pdfDir != null) {
                PDFCreation.createPdf(covidCaseByDateData, graphResult, pdfDir, new boolean[]{titleCheckBox.isSelected(),
                                graphCheckBox.isSelected(),
                                meanCheckBox.isSelected(),
                                sdCheckBox.isSelected(),
                                varCheckBox.isSelected(),
                                totalCheckBox.isSelected(),
                                rssCheckBox.isSelected(),
                                tssCheckBox.isSelected(),
                                rSquaredCheckBox.isSelected(),
                                mapCheckBox.isSelected()},
                        AppConstants.Properties.PDF_EXPORT_NAME);

            }
        });
    }

    /**
     * Initialises the File Chooser responsible for choosing where to save the pdf
     *
     * @return File
     */
    private File initializeFileChooser() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose a directory to save your export file: ");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {
                return jfc.getSelectedFile();
            }
        }
        return null;
    }

    private void initializeBrowser() {
        JourneyLoader.setJourneyLoaderListener(new MugLoadListener(() -> {
            MugLogger.log("Browser loaded");
//            loadTableData();
//            showMetrics();
        }));
        File defaultImage = AppConstants.Paths.resolvePath(AppConstants.Paths.TempResources.TEMP_DEFAULT_IMAGE_FILE).toFile();
//        URI defaultImagePath = AppConstants.Paths.Resources.DEFAULT_IMAGE;
        MugLogger.log(defaultImage);
        browserView = new JourneyBrowserView("data:text/html,<h1>Press refresh graph</h1>");
        String imageUrl = String.valueOf(defaultImage.toURI());
        browserView = new JourneyBrowserView(imageUrl);
        browserProxy = browserView.getCefBrowser();
//        CefClientProxy browserClient = browserView.getCefClient();


        CefBrowserProxy cefProxy = browserView.getCefBrowser();
        BrowserHelper.Loader.browserProxy = browserProxy;

        browserPanel.add(cefProxy.getUIComponent(), BorderLayout.CENTER);
        setTitle("COVID-19 | MFT | TEAM-02");

        MainForm that = this;
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                browserView.getCefApp().dispose();
                that.dispose();
            }
        });

    }

    /**
     * Creates and sets table model of the UK Covid data in Graphs Tab
     */
    private void initializeTable() {
        String[] columnsCovidCases = {"Type", "Name", "Code", "New Cases/Date", "Cum Cases/Date"};

        modelCovidCases = new DefaultTableModel(columnsCovidCases, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        dataTable.setModel(modelCovidCases);
    }

    private void initializeTableRefreshButton() {
        refreshButton.addActionListener(e -> {
            loadTableData();
        });
    }

    private void initializeGraphRefreshButton() {
        refreshGraphButton.addActionListener(e -> {
            if (covidCaseByDateData == null) {
                JOptionPane.showMessageDialog(null, "Fetch the data first.");
                return;
            }

            refreshGraph();
        });
    }

    /**
     * Sets the UK Covid data into the Covid Cases table model
     */
    private void loadTableData() {

        covidCaseByDateData = App.covidCaseByDateDataSupplier.getData();

        modelCovidCases.setRowCount(0);

        for (int i = 0; i < covidCaseByDateData.size(); i++) {
            String areaType = covidCaseByDateData.get(i).AreaType;
            String areaName = covidCaseByDateData.get(i).AreaName;
            String areaCode = covidCaseByDateData.get(i).AreaCode;
            int newCasesBySpecimenDate = covidCaseByDateData.get(i).NewCasesBySpecimenDate;
            int cumCasesBySpecimenDate = covidCaseByDateData.get(i).CumCasesBySpecimenDate;
            Object[] tableValues = {areaType, areaName, areaCode, newCasesBySpecimenDate, cumCasesBySpecimenDate
            };
//            BrowserHelper.Loader.LoadUrl("data:text/html;base64,PCFET0NUWVBFIGh0bWw+DQo8aHRtbCB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94aHRtbCI+DQoNCjxoZWFkPg0KDQogICAgPHRpdGxlPkNvbmZvcm1pbmcgWEhUTUwgMS4xIFRlbXBsYXRlPC90aXRsZT4NCg0KPC9oZWFkPg0KDQo8Ym9keT4NCiAgICA8aDEgc3R5bGU9InRleHQtYWxpZ246IGNlbnRlcjsiPkNPVklELU1GVCBSRVBPUlQ8L2gxPg0KICAgIDxoMiBzdHlsZT0idGV4dC1hbGlnbjogY2VudGVyOyI+dGVhbV8wMjwvaDI+DQogICAgPHA+PGltZyBzdHlsZT0iZGlzcGxheTogYmxvY2s7IG1hcmdpbi1sZWZ0OiBhdXRvOyBtYXJnaW4tcmlnaHQ6IGF1dG87IiBzcmM9InNmZCIgYWx0PSIiIHdpZHRoPSI0MDAiIGhlaWdodD0iMzAwIiAvPg0KICAgIDwvcD4NCiAgICA8aDEgc3R5bGU9InRleHQtYWxpZ246IGNlbnRlcjsiPkNPVklELU1GVCBSRVBPUlQ8L2gxPg0KICAgIDxoMiBzdHlsZT0idGV4dC1hbGlnbjogY2VudGVyOyI+Y2UyOTFfdGVhbTAyPC9oMj4NCiAgICA8cD48aW1nIHNyYz0iYWRmIiB3aWR0aD0iNjAwIiBoZWlnaHQ9IjQwMCIgYWx0PSIiIHN0eWxlPSJkaXNwbGF5OiBibG9jazsgbWFyZ2luLWxlZnQ6IGF1dG87IG1hcmdpbi1yaWdodDogYXV0bzsiIC8+DQogICAgPC9wPg0KICAgIDxociAvPg0KICAgIDx0YWJsZSBib3JkZXI9IjEiIHN0eWxlPSJib3JkZXItY29sbGFwc2U6IGNvbGxhcHNlOyB3aWR0aDogMTAwJTsiPg0KICAgICAgICA8dGJvZHk+DQogICAgICAgICAgICA8dHI+DQogICAgICAgICAgICAgICAgPHRkIHN0eWxlPSJ3aWR0aDogNTAlOyI+PC90ZD4NCiAgICAgICAgICAgICAgICA8dGQgc3R5bGU9IndpZHRoOiA1MCU7Ij48L3RkPg0KICAgICAgICAgICAgPC90cj4NCiAgICAgICAgPC90Ym9keT4NCiAgICA8L3RhYmxlPg0KICAgIDxwPjwvcD4NCjwvYm9keT4NCg0KPC9odG1sPg==");
//            BrowserHelper.Loader.LoadUrl("file:///C:/Users/Filip%20Vlcek/source/repos/ce291_team02-env2/ce291_team02/src/RegressionPrototypesJava/testoutput/output9fab7bae-8af5-40c9-8de8-283089149ad8.html");
//            BrowserHelper.Loader.LoadUrl("https://coronavirus.data.gov.uk/details/testing");
            modelCovidCases.addRow(tableValues);
        }
    }


    private void refreshGraph() {

        List<LocalDate> knotDates = CurrentKnotsRetriever.getCurrentKnotDates();

        Figure figure = null;
        List<CovidCaseByDate> data = App.covidCaseByDateDataSupplier.getData();
        Collections.sort(data);
        ArrayList<MugPoint> inputData = null;
        switch (selectedVariableToGraph) {
            case NEW_CASES:
                inputData = RegressionMappers.covidCasesByDatesToMyPointsByNewCases(data);
                break;
            case CUM_CASES:
                inputData = RegressionMappers.covidCasesByDatesToMyPointsByCumCases(data);
                break;
        }
        ArrayList<LocalDate> inputDates = new ArrayList<>();
        for (CovidCaseByDate c : data) {
            inputDates.add(c.Date);
        }

        switch (selectedModel) {

            case PCLR:
                graphResult = ContinuousPieceWiseLinearRegressionGraph.dailyCasesBySpecimenDate(inputData, inputDates, numberOfPredictDays, knotDates);
                figure = graphResult.figure;
                break;
            case PLR:
                figure = PiecewiseLinearRegressionGraphs.dailyCasesBySpecimenDate(inputData, inputDates, numberOfPredictDays, knotDates);
                graphResult = null;
                break;
            case LR:
                figure = LinearRegressionGraphs.dailyCasesBySpecimenDate(inputData, inputDates, numberOfPredictDays);
                graphResult = null;
                break;
        }

        Path uiGraphFilePath = Graphs.createGraphPageFile(figure);

        MugLogger.log(uiGraphFilePath.toUri());

        String graphUrl = uiGraphFilePath.toUri().toString();
        MugLogger.log(graphUrl);
        browserProxy.loadURL(graphUrl);
        showMetrics();
    }

    public void showMetrics() {
        meanLabel.setText(String.valueOf(Metrics.mean(DataHelpers.getNewCasesBySpeciminDate(covidCaseByDateData))));
        sdLabel.setText(String.valueOf(Metrics.standardDeviation(DataHelpers.getNewCasesBySpeciminDate(covidCaseByDateData))));
        varLabel.setText(String.valueOf(Metrics.variance(DataHelpers.getNewCasesBySpeciminDate(covidCaseByDateData))));
        totalLabel.setText(String.valueOf(Metrics.total(DataHelpers.getNewCasesBySpeciminDate(covidCaseByDateData))));
        if (graphResult != null) {
            tssLabel.setText(String.valueOf(graphResult.tss));
            rssLabel.setText(String.valueOf(graphResult.rss));
            rSquaredLabel.setText(String.valueOf(graphResult.rSquared));
        } else {
            tssLabel.setText("");
            rssLabel.setText("");
            rSquaredLabel.setText("");
        }

    }

    private void initializeAboutTab() {
        String aboutcontent = null;
        try {
            aboutcontent = new String(Files.readAllBytes(AppConstants.Paths.resolvePath(AppConstants.Paths.TempResources.TEMP_ABOUT_FILE)), StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            MugLogger.log(e);
        } catch (IOException e) {
            MugLogger.log(e);
        }
        aboutTextPane.setText(aboutcontent);

    }

    /**
     * Takes a screenshot of the panel which is then included in pdf export.
     *
     * @param panel Panel in which the graph is in
     */
    private static void createImage(JPanel panel) {

        try {
            Thread.sleep(60);
            Robot r = new Robot();

            String path = AppConstants.Paths.resolvePath(AppConstants.Paths.Outputs.GRAPH_EXPORT_FILE).toString();

            Point pt = new Point(panel.getLocationOnScreen());
            int threshold = 2;
            Rectangle capture = new Rectangle(pt.x - threshold, pt.y - threshold, panel.getWidth() - threshold, panel.getHeight() - threshold);
            BufferedImage Image = r.createScreenCapture(capture);

            ImageIO.write(Image, "jpg", new File(path));
            MugLogger.log("Graph saved");
            MugLogger.log(path);
        } catch (AWTException | IOException | InterruptedException ex) {
            MugLogger.log(ex);
        }
    }

    /**
     * Creates colour scale for the map
     */
    private void createUIComponents() {
        mapColorLegend = new LegendPanel();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainTabbedPane = new JTabbedPane();
        MainTabbedPane.setEnabled(true);
        MainTabbedPane.setTabLayoutPolicy(1);
        rootPanel.add(MainTabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        GraphsPanel = new JPanel();
        GraphsPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        MainTabbedPane.addTab("Graphs", GraphsPanel);
        GraphsPanel.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        browserPanel = new JPanel();
        browserPanel.setLayout(new BorderLayout(0, 0));
        browserPanel.setEnabled(false);
        browserPanel.setForeground(new Color(-4485507));
        browserPanel.putClientProperty("html.disable", Boolean.FALSE);
        GraphsPanel.add(browserPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(2, 2), null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        GraphsPanel.add(panel1, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(350, -1), 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-12236470)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tabbedPane1 = new JTabbedPane();
        tabbedPane1.setTabLayoutPolicy(1);
        panel1.add(tabbedPane1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 350), null, new Dimension(350, -1), 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Graph", panel2);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel4.setBorder(BorderFactory.createTitledBorder(null, "Graph type", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        graphComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        graphComboBox.setModel(defaultComboBoxModel1);
        graphComboBox.setToolTipText("Select graph type");
        panel4.add(graphComboBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder(null, "Graphed variable", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        graphedVariableComboBox = new JComboBox();
        panel5.add(graphedVariableComboBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        refreshGraphButton = new JButton();
        refreshGraphButton.setText("Refresh Graph");
        refreshGraphButton.setVerticalAlignment(0);
        panel3.add(refreshGraphButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(8, 2, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Export", panel6);
        exportButton = new JButton();
        exportButton.setText("Export");
        panel6.add(exportButton, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        titleCheckBox = new JCheckBox();
        titleCheckBox.setText("Title");
        panel6.add(titleCheckBox, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        varCheckBox = new JCheckBox();
        varCheckBox.setText("Variance");
        panel6.add(varCheckBox, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalCheckBox = new JCheckBox();
        totalCheckBox.setText("Total");
        panel6.add(totalCheckBox, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(0);
        label1.setText("PDF Components");
        panel6.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setHorizontalAlignment(0);
        label2.setText("Table Metrics");
        panel6.add(label2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sdCheckBox = new JCheckBox();
        sdCheckBox.setText("Standard Deviation");
        panel6.add(sdCheckBox, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        meanCheckBox = new JCheckBox();
        meanCheckBox.setText("Mean");
        panel6.add(meanCheckBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel6.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel6.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel7.setToolTipText("");
        panel6.add(panel7, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel7.setBorder(BorderFactory.createTitledBorder(null, "Refresh graph first", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        rssCheckBox = new JCheckBox();
        rssCheckBox.setText("RSS");
        panel7.add(rssCheckBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tssCheckBox = new JCheckBox();
        tssCheckBox.setText("TSS");
        panel7.add(tssCheckBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rSquaredCheckBox = new JCheckBox();
        rSquaredCheckBox.setText("rSquared");
        panel7.add(rSquaredCheckBox, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        graphCheckBox = new JCheckBox();
        graphCheckBox.setText("Graph");
        panel7.add(graphCheckBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("Model", panel8);
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel8.add(panel9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Prediction Days");
        panel9.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        predictionDaysSpinner = new JSpinner();
        panel9.add(predictionDaysSpinner, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        metricsPanel = new JPanel();
        metricsPanel.setLayout(new GridLayoutManager(10, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(metricsPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Mean");
        label4.setToolTipText("Average daily cases");
        metricsPanel.add(label4, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        meanLabel = new JLabel();
        meanLabel.setText("");
        metricsPanel.add(meanLabel, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("SD");
        label5.setToolTipText("Standard deviation of new cases");
        metricsPanel.add(label5, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sdLabel = new JLabel();
        sdLabel.setText("");
        metricsPanel.add(sdLabel, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Variance");
        label6.setToolTipText("Variance of new cases");
        metricsPanel.add(label6, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        varLabel = new JLabel();
        varLabel.setText("");
        metricsPanel.add(varLabel, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Total");
        label7.setToolTipText("Total cases");
        metricsPanel.add(label7, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalLabel = new JLabel();
        totalLabel.setText("");
        metricsPanel.add(totalLabel, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("RSS");
        label8.setToolTipText("Residual sum of squares");
        metricsPanel.add(label8, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rssLabel = new JLabel();
        rssLabel.setText("");
        metricsPanel.add(rssLabel, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("TSS");
        label9.setToolTipText("Total sum of squares");
        metricsPanel.add(label9, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tssLabel = new JLabel();
        tssLabel.setText("");
        metricsPanel.add(tssLabel, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("rSquared");
        label10.setToolTipText("Coefficient of determination");
        metricsPanel.add(label10, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rSquaredLabel = new JLabel();
        rSquaredLabel.setText("");
        metricsPanel.add(rSquaredLabel, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        metricsPanel.add(spacer3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        metricsPanel.add(spacer4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        metricsPanel.add(spacer5, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        metricsPanel.add(spacer6, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        metricsPanel.add(spacer7, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        metricsPanel.add(spacer8, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        metricsPanel.add(spacer9, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        metricsPanel.add(spacer10, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        metricsPanel.add(spacer11, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        Font label11Font = this.$$$getFont$$$(null, Font.BOLD, -1, label11.getFont());
        if (label11Font != null) label11.setFont(label11Font);
        label11.setText("Metrics");
        metricsPanel.add(label11, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        metricsPanel.add(spacer12, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 10), new Dimension(-1, 10), new Dimension(-1, 10), 0, false));
        final Spacer spacer13 = new Spacer();
        metricsPanel.add(spacer13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(20, -1), new Dimension(20, -1), new Dimension(20, -1), 0, false));
        final JSeparator separator1 = new JSeparator();
        panel1.add(separator1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 1), new Dimension(-1, 1), 0, false));
        refreshButton = new JButton();
        refreshButton.setText("Fetch Data");
        panel1.add(refreshButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        GraphsPanel.add(panel10, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(-1, 300), 0, false));
        dataTableScrollPane = new JScrollPane();
        panel10.add(dataTableScrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(1, 1), null, null, 0, false));
        dataTable = new JTable();
        dataTable.setPreferredScrollableViewportSize(new Dimension(-1, 270));
        dataTableScrollPane.setViewportView(dataTable);
        Comparison = new JPanel();
        Comparison.setLayout(new GridLayoutManager(10, 13, new Insets(0, 0, 0, 0), -1, -1));
        MainTabbedPane.addTab("Comparison", Comparison);
        final Spacer spacer14 = new Spacer();
        Comparison.add(spacer14, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer15 = new Spacer();
        Comparison.add(spacer15, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer16 = new Spacer();
        Comparison.add(spacer16, new GridConstraints(0, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer17 = new Spacer();
        Comparison.add(spacer17, new GridConstraints(0, 7, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer18 = new Spacer();
        Comparison.add(spacer18, new GridConstraints(0, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer19 = new Spacer();
        Comparison.add(spacer19, new GridConstraints(0, 10, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer20 = new Spacer();
        Comparison.add(spacer20, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer21 = new Spacer();
        Comparison.add(spacer21, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer22 = new Spacer();
        Comparison.add(spacer22, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer23 = new Spacer();
        Comparison.add(spacer23, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        countryComboBox2 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        countryComboBox2.setModel(defaultComboBoxModel2);
        Comparison.add(countryComboBox2, new GridConstraints(1, 8, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer24 = new Spacer();
        Comparison.add(spacer24, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer25 = new Spacer();
        Comparison.add(spacer25, new GridConstraints(0, 12, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        Comparison.add(scrollPane1, new GridConstraints(2, 1, 5, 10, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new GridLayoutManager(4, 7, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(panel11);
        countryTable1 = new JTable();
        panel11.add(countryTable1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        countryTable2 = new JTable();
        panel11.add(countryTable2, new GridConstraints(1, 4, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer26 = new Spacer();
        panel11.add(spacer26, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer27 = new Spacer();
        panel11.add(spacer27, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer28 = new Spacer();
        panel11.add(spacer28, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer29 = new Spacer();
        panel11.add(spacer29, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer30 = new Spacer();
        panel11.add(spacer30, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer31 = new Spacer();
        panel11.add(spacer31, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer32 = new Spacer();
        panel11.add(spacer32, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(50, -1), new Dimension(50, -1), new Dimension(50, -1), 0, false));
        countryPerformanceTable1 = new JTable();
        panel11.add(countryPerformanceTable1, new GridConstraints(3, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        countryPerformanceTable2 = new JTable();
        panel11.add(countryPerformanceTable2, new GridConstraints(3, 4, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JSeparator separator2 = new JSeparator();
        panel11.add(separator2, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator3 = new JSeparator();
        panel11.add(separator3, new GridConstraints(2, 4, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer33 = new Spacer();
        Comparison.add(spacer33, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer34 = new Spacer();
        Comparison.add(spacer34, new GridConstraints(9, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 50), new Dimension(-1, 50), new Dimension(-1, 50), 0, false));
        final Spacer spacer35 = new Spacer();
        Comparison.add(spacer35, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer36 = new Spacer();
        Comparison.add(spacer36, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 50), new Dimension(-1, 50), new Dimension(-1, 50), 0, false));
        refreshCountryDataButton = new JButton();
        refreshCountryDataButton.setText("Refresh Data");
        Comparison.add(refreshCountryDataButton, new GridConstraints(8, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        countryComboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        countryComboBox1.setModel(defaultComboBoxModel3);
        Comparison.add(countryComboBox1, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer37 = new Spacer();
        Comparison.add(spacer37, new GridConstraints(7, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        FlagLabel1 = new JLabel();
        FlagLabel1.setText("");
        Comparison.add(FlagLabel1, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer38 = new Spacer();
        Comparison.add(spacer38, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer39 = new Spacer();
        Comparison.add(spacer39, new GridConstraints(1, 11, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        FlagLabel2 = new JLabel();
        FlagLabel2.setText("");
        Comparison.add(FlagLabel2, new GridConstraints(1, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer40 = new Spacer();
        Comparison.add(spacer40, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 200), new Dimension(-1, 200), new Dimension(-1, 200), 0, false));
        MapTab = new JPanel();
        MapTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainTabbedPane.addTab("Map", MapTab);
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        MapTab.add(panel12, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        mapPanel = new JPanel();
        mapPanel.setLayout(new BorderLayout(0, 0));
        panel12.add(mapPanel, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(1000, 600), new Dimension(1000, 600), new Dimension(1000, 600), 1, false));
        final Spacer spacer41 = new Spacer();
        panel12.add(spacer41, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer42 = new Spacer();
        panel12.add(spacer42, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(100, -1), new Dimension(100, -1), new Dimension(100, -1), 0, false));
        final Spacer spacer43 = new Spacer();
        panel12.add(spacer43, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(100, -1), new Dimension(100, -1), new Dimension(100, -1), 0, false));
        ColourcodedInfoTable = new JScrollPane();
        panel12.add(ColourcodedInfoTable, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 200), new Dimension(-1, 200), new Dimension(-1, 200), 0, false));
        mapTable = new JTable();
        ColourcodedInfoTable.setViewportView(mapTable);
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel12.add(panel13, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel13.add(panel14, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 600), new Dimension(-1, 600), new Dimension(-1, 600), 0, false));
        MapHeatLabelHigh = new JLabel();
        MapHeatLabelHigh.setText("");
        panel14.add(MapHeatLabelHigh, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        MapHeatLabelMiddle = new JLabel();
        MapHeatLabelMiddle.setText("");
        panel14.add(MapHeatLabelMiddle, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        MapHeatLabelLow = new JLabel();
        MapHeatLabelLow.setText("");
        panel14.add(MapHeatLabelLow, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_SOUTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mapColorLegend = new JPanel();
        mapColorLegend.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mapColorLegend.setBackground(new Color(-12828863));
        mapColorLegend.setForeground(new Color(-4513512));
        panel14.add(mapColorLegend, new GridConstraints(0, 2, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(20, 600), new Dimension(20, 600), new Dimension(20, 600), 0, false));
        mapColorLegend.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-16777216)));
        final Spacer spacer44 = new Spacer();
        panel14.add(spacer44, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer45 = new Spacer();
        panel14.add(spacer45, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer46 = new Spacer();
        panel14.add(spacer46, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel12.add(panel15, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 200), new Dimension(-1, 200), new Dimension(-1, 200), 0, false));
        mapVariableComboBox = new JComboBox();
        panel15.add(mapVariableComboBox, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, -1), new Dimension(100, -1), new Dimension(100, -1), 0, false));
        mapComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        mapComboBox.setModel(defaultComboBoxModel4);
        panel15.add(mapComboBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, -1), new Dimension(100, -1), new Dimension(100, -1), 0, false));
        mapThemeComboBox = new JComboBox();
        panel15.add(mapThemeComboBox, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(100, -1), new Dimension(100, -1), new Dimension(100, -1), 0, false));
        mapCheckBox = new JCheckBox();
        mapCheckBox.setText("Include in PDF");
        panel15.add(mapCheckBox, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer47 = new Spacer();
        panel12.add(spacer47, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
        final JPanel panel16 = new JPanel();
        panel16.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainTabbedPane.addTab("Danger Zone", panel16);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel16.add(scrollPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel17 = new JPanel();
        panel17.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane2.setViewportView(panel17);
        cacheDeleteButton = new JButton();
        cacheDeleteButton.setBackground(new Color(-12828863));
        cacheDeleteButton.setForeground(new Color(-4509925));
        cacheDeleteButton.setText("");
        panel17.add(cacheDeleteButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer48 = new Spacer();
        panel17.add(spacer48, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        deleteCacheLabel = new JLabel();
        deleteCacheLabel.setText("");
        panel17.add(deleteCacheLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer49 = new Spacer();
        panel17.add(spacer49, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        AboutTab = new JPanel();
        AboutTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        MainTabbedPane.addTab("About", AboutTab);
        final JScrollPane scrollPane3 = new JScrollPane();
        AboutTab.add(scrollPane3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        aboutTextPane = new JTextPane();
        aboutTextPane.setEditable(false);
        aboutTextPane.setEnabled(true);
        aboutTextPane.setText("");
        scrollPane3.setViewportView(aboutTextPane);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }

}
