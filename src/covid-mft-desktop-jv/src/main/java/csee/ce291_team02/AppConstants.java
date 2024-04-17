package csee.ce291_team02;

import java.awt.Color;
import java.awt.Font;
//
import java.io.InputStream;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static csee.ce291_team02.Data.DataHelpers.copyFileOver;
import static csee.ce291_team02.FileSystemHelper.ifFolderDoesNotExistCreate;

public class AppConstants {

    public static class Properties {
        public final static String APP_NAME = "ce291-team02_covid-mft-desktop-ja";
        public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        public final static Font unicodeFont = new Font("Arial", Font.PLAIN, 14);
        public final static String UNKNOWN_FLAG_ISO_CODE = "unknown";
        public final static String UNKNOWN_FLAG_DESCRIPTION = "unknown flag";
        public static final List<LocalDate> DEFAULT_KNOT_DATES = Arrays.asList(
            LocalDate.parse("2020-01-03", DATE_TIME_FORMATTER),
            LocalDate.parse("2020-03-03", DATE_TIME_FORMATTER),
            LocalDate.parse("2020-03-17", DATE_TIME_FORMATTER),
            LocalDate.parse("2020-04-10", DATE_TIME_FORMATTER),
            LocalDate.parse("2020-06-10", DATE_TIME_FORMATTER),
            LocalDate.parse("2020-09-01", DATE_TIME_FORMATTER),
            LocalDate.parse("2020-10-01", DATE_TIME_FORMATTER),
            LocalDate.parse("2020-11-02", DATE_TIME_FORMATTER),
            LocalDate.parse("2020-12-02", DATE_TIME_FORMATTER),
            LocalDate.parse("2020-12-24", DATE_TIME_FORMATTER),
            LocalDate.parse("2021-01-08", DATE_TIME_FORMATTER),
            LocalDate.parse("2021-01-28", DATE_TIME_FORMATTER),
            LocalDate.parse("2021-02-08", DATE_TIME_FORMATTER),
            LocalDate.parse("2021-03-01", DATE_TIME_FORMATTER),
            LocalDate.now());
        public final static String CURRENT_KNOTS_URL = "https://raw.githubusercontent.com/PheelaV/CE291CVMFT/main/ktdts.json";
        public static final boolean ONLINE_KNOT_RETRIEVAL_ENABLED = true;
        public static final int DEFAULT_PREDICTION_DAYS = 7;
        // Grey
        public final static String DEFAULT_MAP_COLOR = "#222222";
        public final static int GRAPH_WIDTH = 1200;
        public final static int GRAPH_HEIGHT = 750;

        public final static int MAP_WIDTH = 1000;
        public final static int MAP_HEIGHT = 600;

        public static final int DEFAULT_CASH_DURATION = 4;

        public final static String PDF_EXPORT_NAME = "C-19-MFT-EXPORT.pdf";
    }

    public static class SpecialCharacters {
        public final static String upChevron = "\u25B2";
        public final static String downChevron = "\u25BC";
        public final static String equalsSign = "\u003D";

        public enum CharacterModifiers {
            GREATER_POSITIVE("__GP__"),
            GREATER_NEGATIVE("__GN__"),
            LOWER_POSITIVE("__LP__"),
            LOWER_NEGATIVE("__LN__");
            private final String name;

            CharacterModifiers(String s) {
                name = s;
            }

            public boolean equalsName(String otherName) {
                return name.equals(otherName);
            }

            public String toString() {
                return this.name;
            }
        }
    }

    public static class CountryComparison {
        public static class PerformanceLabels {
            public final static String DEATH_RATE = "Death rate";
            public final static String INFECTION_RATE = "Infection rate";
            public final static String RECOVERY_RATE = "Recovery rate";
        }

        public static class PerformanceToolTips {
            public final static String DEATH_RATE = "Deaths / Cases";
            public final static String INFECTION_RATE = "Cases / Population";
            public final static String RECOVERY_RATE = "Recovered / Cases";
        }
    }

    public static class Paths {
        public static Path resolvePath(Path path) {
            Path resolvedPath = null;
            try {

                resolvedPath = RuntimeVariables.getAppTempFolderPath().resolve(path);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return resolvedPath;
        }

        public static class Resources {
            static String DEFAULT_IMAGE  = "defaultImage.jpg";
            static String DELETE_CACHE_IMAGE = "deleteCachePrompt.png";
            static String YES_IMAGE = "yes.png";
            static String ABOUT_FILE = "About.txt";
            static String MAP_ZIP_FILE = "Maps.zip";
            static String PDF_TEMPLATE_FILE = "PDF_Template.html";
//            static {
//                DEFAULT_IMAGE = AppConstants.class.getResource("/defaultImage.jpg").getPath();
//                DELETE_CACHE_IMAGE = AppConstants.class.getResource("/deleteCachePrompt.png").getPath();
//                YES_IMAGE = AppConstants.class.getResource("/yes.png").getPath();
//                ABOUT_FILE = AppConstants.class.getResource("/About.txt").getPath();
//            }
//            public static Path DEFAULT_IMAGE;
//            public static Path DELETE_CACHE_IMAGE;
//            public static Path YES_IMAGE;
//            public static Path ABOUT_FILE;
//
//            static {
//                try {
//                    DEFAULT_IMAGE = java.nio.file.Paths.get((new File(AppConstants.class.getResource("/defaultImage.jpg").toURI()).getAbsolutePath()));
//                    DELETE_CACHE_IMAGE = java.nio.file.Paths.get((new File(AppConstants.class.getResource("/deleteCachePrompt.png").toURI()).getAbsolutePath()));
//                    YES_IMAGE = java.nio.file.Paths.get((new File(AppConstants.class.getResource("/yes.png").toURI()).getAbsolutePath()));
//                    ABOUT_FILE = java.nio.file.Paths.get((new File(AppConstants.class.getResource("/About.txt").toURI()).getAbsolutePath()));
//                } catch (URISyntaxException e) {
//                    e.printStackTrace();
//                }
//            }

            public static class Maps {


                public final static String LTLA_MAP_NAME = "ltla_map.owo";
                public final static String REGIONS_MAP_NAME= "regions_map.owo";
                public final static String UTLA_MAP_NAME= "utla_map.owo";


//                private static URI getMapZipFile(){
//                    if (MAP_ZIP_FILE == null) {
////                        File f = new File("ColourColorMap/Maps.zip");
//                        InputStream in = classLoader.getResourceAsStream("ColourColorMap/Maps.zip");
//
//                        Path target = Paths.resolvePath(TempResources.TEMP_MAP_ZIP);
//
//                        copyFileOver(in, target);
//                        MAP_ZIP_FILE = getUri(");
//                    }
//                    return MAP_ZIP_FILE;
//                }



//                private static URI getUri(String relative){
//                    try {
//                        final URI url = classLoader.getResource(relative).toURI();
//                        System.out.println(url.toString());
//                        return url;
//                    } catch (URISyntaxException e) {
//                        MugLogger.log(e);
//                    }

//                    return null;
//                }
//                private static Path MAP_ZIP_FILE;
//                public static Path getMapZipFile(){
//                    if (MAP_ZIP_FILE == null) {
//                        try{
//                            MAP_ZIP_FILE = java.nio.file.Paths.get(
//                                    AppConstants.class.getResource("/ColourColorMap/Maps.zip").toURI());
//                        } catch (URISyntaxException e){
//                            e.printStackTrace();
//                        }
//                    }
//                    return MAP_ZIP_FILE;
//                }
            }

            public static class Templates {
//                public final static Path HTML_TEMPLATES_DIR = java.nio.file.Paths.get("/htmlTemplates");
//                public static Path SAMPLE_TEMPLATE = HTML_TEMPLATES_DIR.resolve("sampleTemplate.txt");

                //This is because of JUnit tests
//                private static Path SAMPLE_HTML_TEMPLATE;
//                public static Path getSampleHtmlTemplatePath() {
//                    if (SAMPLE_HTML_TEMPLATE == null) {
//                        try {
//                            SAMPLE_HTML_TEMPLATE = java.nio.file.Paths.get(
//                                    AppConstants.class.getResource("PDF_Template.html").toURI());
//                        } catch (URISyntaxException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    return SAMPLE_HTML_TEMPLATE;
//                }
            }
        }

        public static class CachedFiles {
            public final static Path CACHED_FILES = java.nio.file.Paths.get("Cached");
            public final static Path UK_COVID_DATA_FILE = CACHED_FILES.resolve("ukCovidData.json");
            public final static Path COUNTRY_DATA_FILE = CACHED_FILES.resolve("countryData.json");
            public final static Path REGION_DATA_FILE = CACHED_FILES.resolve("regionData.json");
            public final static Path UTLA_DATA_FILE = CACHED_FILES.resolve("utlaData.json");
            public final static Path LTLA_DATA_FILE = CACHED_FILES.resolve("ltlaonData.json");
            public static final Path CURRENT_KNOT_DATES = CACHED_FILES.resolve("currentKnotDates.json" );
        }

        public static class Outputs {
            public final static Path OUTPUT_DIR = java.nio.file.Paths.get("output");
            public final static Path UI_GRAPH_FILE = OUTPUT_DIR.resolve("uiGraph.html");
            public final static Path EXPORT_DIR = OUTPUT_DIR.resolve("export");
            public final static Path GRAPH_EXPORT_FILE = EXPORT_DIR.resolve("mapImage.jpeg");
            public final static Path MAP_EXPORT_FILE = EXPORT_DIR.resolve("mvpGraph.jpeg");
            public final static Path PDF_EXPORT_DIR = EXPORT_DIR.resolve("pdfTempOutput");
//            public final static Path EXPORT_PDF = PDF_EXPORT_DIR.resolve("mvpPdfExport.pdf");
//            public final static Path EXPORT_COVID_FILE = PDF_EXPORT_DIR.resolve("/COVID-19.pdf");

        }
//        Paths.Resources.ABOUT_FILE
        public static class TempResources{

            public static Path TEMP_RESOURCE_FILES = java.nio.file.Paths.get("TempResource");

            public static final Path TEMP_DELETE_CACHE_IMAGE = TEMP_RESOURCE_FILES.resolve(Resources.DELETE_CACHE_IMAGE);
            public static final Path TEMP_YES_IMAGE = TEMP_RESOURCE_FILES.resolve(Resources.YES_IMAGE);
            public static final Path TEMP_ABOUT_FILE = TEMP_RESOURCE_FILES.resolve(Resources.ABOUT_FILE);
            public static final Path TEMP_MAP_ZIP_FILE = TEMP_RESOURCE_FILES.resolve(Resources.MAP_ZIP_FILE);
            public static final Path TEMP_DEFAULT_IMAGE_FILE = TEMP_RESOURCE_FILES.resolve(Resources.DEFAULT_IMAGE);
            public static final Path TEMP_PDF_TEMPLATE_FILE = TEMP_RESOURCE_FILES.resolve(Resources.PDF_TEMPLATE_FILE);
        }
    }


    public static class RuntimeVariables {
        private static Path appTempFolderPath;
        /* Runs at start up, ensures that every directory the app relies on exists. */

        public static void initializeRuntimeVariables(){
            /* temp directory */
            String defaultBaseDir = System.getProperty("java.io.tmpdir");

            try {
                RuntimeVariables.setAppTempFolderPath(java.nio.file.Paths.get(defaultBaseDir, AppConstants.Properties.APP_NAME));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Path appTempFolderPath = null;
            try {
                appTempFolderPath = RuntimeVariables.getAppTempFolderPath();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ifFolderDoesNotExistCreate(appTempFolderPath);
            assert appTempFolderPath != null;

            /* output directory */
            Path outputFolderPath = appTempFolderPath.resolve(AppConstants.Paths.Outputs.OUTPUT_DIR);
            ifFolderDoesNotExistCreate(outputFolderPath);


            /* export directory */
            Path exportFolderPath = appTempFolderPath.resolve(AppConstants.Paths.Outputs.EXPORT_DIR);
            ifFolderDoesNotExistCreate(exportFolderPath);


            /* pdfExport directory */
            Path pdfExportFolderPath = appTempFolderPath.resolve(AppConstants.Paths.Outputs.PDF_EXPORT_DIR);
            ifFolderDoesNotExistCreate(pdfExportFolderPath);


            /*Cache directory*/
            Path cacheFolderPath = appTempFolderPath.resolve(AppConstants.Paths.CachedFiles.CACHED_FILES);
            ifFolderDoesNotExistCreate(cacheFolderPath);

            // Copying over resources because of JAR compatibility, just makes things easier and costs only twice as much memory...
            /*Temp resource directory*/
            Path tempResourceFolderPath = appTempFolderPath.resolve(Paths.TempResources.TEMP_RESOURCE_FILES);
            ifFolderDoesNotExistCreate(tempResourceFolderPath);

            ClassLoader classLoader = AppConstants.class.getClassLoader();

            InputStream in = classLoader.getResourceAsStream(Paths.Resources.MAP_ZIP_FILE);
            Path target = Paths.resolvePath(Paths.TempResources.TEMP_MAP_ZIP_FILE);
            copyFileOver(in, target);

            in = classLoader.getResourceAsStream(Paths.Resources.DELETE_CACHE_IMAGE);
            target = Paths.resolvePath(Paths.TempResources.TEMP_DELETE_CACHE_IMAGE);
            copyFileOver(in, target);

            in = classLoader.getResourceAsStream(Paths.Resources.YES_IMAGE);
            target = Paths.resolvePath(Paths.TempResources.TEMP_YES_IMAGE);
            copyFileOver(in, target);

            in = classLoader.getResourceAsStream(Paths.Resources.ABOUT_FILE);
            target = Paths.resolvePath(Paths.TempResources.TEMP_ABOUT_FILE);
            copyFileOver(in, target);

            in = classLoader.getResourceAsStream(Paths.Resources.DEFAULT_IMAGE);
            target = Paths.resolvePath(Paths.TempResources.TEMP_DEFAULT_IMAGE_FILE);
            copyFileOver(in, target);

            in = classLoader.getResourceAsStream(Paths.Resources.PDF_TEMPLATE_FILE);
            target = Paths.resolvePath(Paths.TempResources.TEMP_PDF_TEMPLATE_FILE);
            copyFileOver(in, target);
        }

        /**
         * Retrieves a copy of the app's temporary folder path.
         *
         * @return
         */
        public static Path getAppTempFolderPath() throws Exception {
            if (appTempFolderPath == null) {
                throw new Exception("The temp folder has not been set yet.");
            }

            return appTempFolderPath;
        }

        /**
         * Set's the app's temporary folder path as a singleton.
         *
         * @param path
         * @throws Exception If the path has been set more than once.
         */
        public static void setAppTempFolderPath(Path path) throws Exception {
            if (appTempFolderPath != null) {
                throw new Exception("The temp directory can only be set once!");
            }

            appTempFolderPath = path;
        }
    }

    public static class Selections {
        public enum GraphedVariable {
            NEW_CASES("New Cases"),
            CUM_CASES("Cum Cases");

            private final String name;

            GraphedVariable(String s) {
                name = s;
            }

            public boolean equalsName(String otherName) {
                return name.equals(otherName);
            }

            public String toString() {
                return this.name;
            }
        }
        public enum Model {
            PCLR("PiecewiseContinuousLR"),
            PLR("PiecewiseLR"),
            LR("LinearRegression");

            private final String name;

            Model(String s) {
                name = s;
            }

            public boolean equalsName(String otherName) {
                return name.equals(otherName);
            }

            public String toString() {
                return this.name;
            }
        }

        public enum Map {
            REGIONS("Regions"),
            UTLA("UTLA"),
            LTLA("LTLA");

            private final String name;

            Map(String s) {
                name = s;
            }

            public boolean equalsName(String otherName) {
                return name.equals(otherName);
            }

            public String toString() {
                return this.name;
            }
        }

        public enum MapVariable {
            CASES("Cases"),
            DEATHS_TOTAL("DeathsTotal"),
            CASES_FEMALE("FemaleCases"),
            CASES_MALE("MaleCases"),
            DEATHS_DAILY("DeathsDaily");

            private final String name;

            MapVariable(String s) {
                name = s;
            }

            public boolean equalsName(String otherName) {
                return name.equals(otherName);
            }

            public String toString() {
                return this.name;
            }
        }

        public enum MapTheme {
            ScaledRed("Red Scaled"),
            Blues("Blues"),
            Flp("Pheela"),
            CUBEHELIX("Cubehelix"),
            DARK_SALMON("DarkSalmon"),
            Jvr("Jdiazgar"),
            Dn("deanmak"),
            Snskr("Gupta"),
            Nsr("Nasar"),
            Rc("reece");

            private final String name;

            MapTheme(String s) {
                name = s;
            }

            public boolean equalsName(String otherName) {
                return name.equals(otherName);
            }

            public String toString() {
                return this.name;
            }
        }

        /**
         * Themes have to have less colors then there will be areas on the maps, for regions that is min of 9.
         * Scaled schemes have length of 2, others are treated as associative colors - evenly distributed across
         * all categories with division leftover assigned to the last color)
         */
        public static class MapColorSchemes {
            public static final List<String> ScaledRed = Arrays.asList("#c9c9c9", "#fa2a2a");

            public static final List<String> Blues = Arrays.asList("#dbe9f6", "#bad6eb", "#89bedc", "#539ecd", "#2b7bba", "#0b559f");
            public static final List<String> Cubehelix = Arrays.asList("#1a2441", "#1b6145", "#697b30", "#c87b7c", "#cda2e0", "#c6e1f1");
            public static final List<String> DarkSalmon = Arrays.asList("#2e2322", "#573632", "#804842", "#a95b52", "#d16d62", "#fa8072");

            public static final List<String> Filip = Arrays.asList("#e98d6b", "#e3685c", "#d14a61", "#b13c6c", "#8f3371", "#6c2b6d");
            public static final List<String> Javier = Arrays.asList("#e2514a", "#fca55d", "#fee999", "#edf8a3", "#a2d9a4", "#47a0b3");
            public static final List<String> Sanskar = Arrays.asList("#55a3cd", "#4954b0", "#282739", "#3b2127", "#9c2f45", "#e96f36");
            public static final List<String> Dean = Arrays.asList("#f6b48f", "#f37651", "#e13342", "#ad1759", "#701f57", "#35193e");
            public static final List<String> Reece = Arrays.asList("#46327e", "#365c8d", "#277f8e", "#1fa187", "#4ac16d", "#a0da39");
            public static final List<String> Nasar = Arrays.asList("#7dba91", "#59a590", "#40908e", "#287a8c", "#1c6488", "#254b7f");

            public static Color hex2Rgb(String hex) {
                return new Color(
                        Integer.valueOf(hex.substring(1, 3), 16),
                        Integer.valueOf(hex.substring(3, 5), 16),
                        Integer.valueOf(hex.substring(5, 7), 16));
            }
        }
    }
}
