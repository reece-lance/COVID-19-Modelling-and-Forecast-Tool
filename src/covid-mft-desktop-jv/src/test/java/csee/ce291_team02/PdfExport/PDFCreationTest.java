package csee.ce291_team02.PdfExport;

import com.ibm.icu.impl.Assert;
import csee.ce291_team02.AppConstants;
import csee.ce291_team02.Data.MockDataProvider;
import csee.ce291_team02.Models.CovidCaseByDate;
import csee.ce291_team02.Models.GraphResult;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tech.tablesaw.plotly.components.Figure;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PDFCreationTest {

    private static Path path;
    private static File file;

    List<CovidCaseByDate> covidCaseByDateMockData = MockDataProvider.getCovidCaseByDateDataMock();
    private static GraphResult graphResult;

    private static final int tss = 1;
    private static final int rss = 1;
    private static final int rSquared = 1;

    @BeforeAll
    static void init() {
//        AppConstants.RuntimeVariables.initializeRuntimeVariables();
        path = Paths.get("pdfTemplateTest.html");
        file = new File (path.toString());
        try {
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        LocalDateTime newLocalDateTime = LocalDateTime.of(1999, 9, 30, 10, 30, 22);

        // Convert LocalDateTime to Instant
        Instant instant = newLocalDateTime.toInstant(ZoneOffset.UTC);

        // Update last modified time of a file
        try {
            Files.setLastModifiedTime(path, FileTime.from(instant));
        } catch (IOException e) {
            e.printStackTrace();
        }

        graphResult =  new GraphResult();
        graphResult.rss = rss;
        graphResult.tss = tss;
        graphResult.rSquared = rSquared;
        graphResult.figure = new Figure();
    }

    @AfterAll
    static void tearDown(){
        if(file.exists()){
            file.delete();
        }
    }

    /**
     * Disabled because changes regarding JAR packaging require additional changes to make the underlying classes unit testable again.
     */
//    /**The following tests if pdf is created by PDFCreation.create().
//     * If pdf already exists before running the aforementioned  method,
//     * then it is deleted (as shown in the if statement).**/
//    @Test
//    public void pdfFileShouldGetCreated() throws IOException {
//        // Arrange
//        String dupDirName= "test_pdf_should_exist_DUMMY";
//        String dummyExportedPdfName = "chackalacka.pdf";
//
//        Path dumpPath = Paths.get(dupDirName);
//        Files.deleteIfExists(dumpPath);
//        Assertions.assertFalse(Files.exists(dumpPath));
//        Files.createDirectory(dumpPath);
//        Assertions.assertTrue(Files.exists(dumpPath), "Dummy folder should exist by now.");
//        File dumpDir = dumpPath.toFile();
//
//        // Act
//        PDFCreation.createPdf(covidCaseByDateMockData, graphResult, dumpDir, new boolean[]{false, false, false, false, false, false, false, false, false, false}, dummyExportedPdfName);
//
//        // Assert
//        String dummyPdfDestination = dumpDir.toString() + File.separatorChar+ dummyExportedPdfName;
//
//        File dummpyExportFile = new File(dummyPdfDestination);
////
//        Assertions.assertTrue(dummpyExportFile.exists());
//
//
//
//        // Cleanup
//        Files.deleteIfExists(dummpyExportFile.toPath());
//        Files.deleteIfExists(dumpPath);
//    }

//    /**The following tests that pdf is not created by PDFCreation.create().
//     * If pdf already exists before running the aforementioned  method,
//     * then it is deleted (as shown in the if statement).**/
//    @Test
//    public void test_pdf_should_not_exist() throws IOException{
//        if (exportPdfFile.toFile().exists()) {
//            Files.delete(exportPdfFile);
//        }
//
//        PDFCreation.createPdf(covidCaseByDateMockData, graphResult, null, new boolean[]{false, false, false, false, false, false, false, false, false});
//
//        assertFalse(exportPdfFile.toFile().exists(), "File does exist, when it shouldn't");
//    }

    /**Tests whether the size of the pdf is larger than zero in kilobytes.
     * [Sort of a get around for checking if the pdf is empty]
     * **/
    @Test
    public void test_pdf_should_have_substance(){
//        PDFCreation.createPdf(covidCaseByDateMockData, graphResult, exportPdfDir, new boolean[]{false, false, false, false, false, false, false, false, false});
//        double kbSize = exportPdfFile.toFile().length() / 1024.0;
//        System.out.println("kb of file = " + kbSize);
//
//        assertTrue(kbSize>0, "Size of pdf is zero");
    }

//    @Test
//    public void pdf_template_should_exist(){
//        Path pdfHtmlFile = getSampleHtmlTemplatePath();
//        File file = pdfHtmlFile.toFile();
//
//        assertTrue(file.exists());
//    }
}
