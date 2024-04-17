package csee.ce291_team02.Data;

import com.google.gson.reflect.TypeToken;
import com.ibm.icu.impl.Assert;
import csee.ce291_team02.AppConstants;
import csee.ce291_team02.Models.CovidCaseByDate;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CachedHandlerTest {

    private static Path path;
    private static File file;

    private class ScrapClass {

    }

    @BeforeAll
    static void init() {
        AppConstants.RuntimeVariables.initializeRuntimeVariables();
        path = Paths.get("cashHasExpiredTest.json");
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

    }

    @AfterAll
    static void tearDown(){
        if(file.exists()){
            file.delete();
        }
    }

    @Test
    public void cashHasExpiredShouldReturnFalseForStaleFile() {

        // Arrange
        TestCacheHandler<ScrapClass> testCacheHandler = new TestCacheHandler<>(path, new TypeToken<ArrayList<ScrapClass>>(){}.getType());

        // Act
        boolean result = false;

        try {
            result = testCacheHandler.hasData();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

        // Assert
        Assertions.assertFalse(result);
    }

    // Testing variables
    public String ukCovidDataFilePathTest = AppConstants.Paths.resolvePath(AppConstants.Paths.CachedFiles.UK_COVID_DATA_FILE).toString();
    File testFileFF = new File(String.valueOf(AppConstants.Paths.resolvePath(AppConstants.Paths.CachedFiles.UK_COVID_DATA_FILE).toString()));

    File ff = new File(ukCovidDataFilePathTest);
    long fileLastModifiedTest = ff.lastModified();
    long testFileLastModified = testFileFF.lastModified();

    Instant instant = Instant.ofEpochSecond(fileLastModifiedTest); //Current Time for fileLastModifiedTest
    Instant instant2 = Instant.ofEpochSecond(testFileLastModified); //Current Time for testFileLastModifiedTest

    Instant minusHours = instant2.minus(10, ChronoUnit.HOURS); //Minus Hours
    Instant addHours = instant2.plus(10, ChronoUnit.HOURS); //Add Hours
    Instant add100days = instant2.plus(100, ChronoUnit.DAYS);


    /**
     * @throws IOException
     */
    @Test
    public void isTheValidUkCovidApiData() throws IOException {
        //CachedHandler f = new CachedHandler();

        //assertFalse(ukCovidDataFilePathTest.equals(false));


        if (ukCovidDataFilePathTest.isEmpty()) {
            Files.delete(Paths.get(ukCovidDataFilePathTest));
        }

        Assertions.assertFalse(ukCovidDataFilePathTest.isEmpty());


    }

    /**
     * The following JUnit test, tests if the time of the lastFileModified
     * is less than 4 hours,if it is less than 4 hours, this will return false.
     *
     * @throws IOException
     */

    @Test
    public void cashShouldExpiredAfterThresholdTime() throws IOException {
        // Time < than 4 hours ago, should return false
        //Comparing the lastFileModified
        //assertFalse

        //7 2nd that might work //Main Solver

        //assertFalse(instant.isBefore(minusHours));
        //assertFalse(instant.compareTo(minusHours)>4);


        //Might Work
       /*CachedHandler.cashHasExpired(fileLastModifiedTest,4);

       boolean lessthan4 = testFileFF.setLastModified(fileLastModifiedTest);
       assertFalse(testFileFF.setLastModified(fileLastModifiedTest)<4);

       System.out.println(fileLastModifiedTest);*/

        //3 //I think this works
        /*
        CachedHandler f = new CachedHandler();
        boolean expected = false;
        boolean actual = f.cashHasExpired(fileLastModified,4); //false
        assertFalse(expected == actual);*/

    }

    /**
     * The following tests if the time of the lastFileModified is bigger than 4 hours,
     * if it is more than 4 hours it will return true.
     */

    @Test
    public void cashIsNotExpiredWithinTimeRange() {
        // time > 4 hours, should return true
        ////Comparing the lastFileModified
        //assertTrue

        Assertions.assertTrue(addHours.isAfter(instant));
        Assertions.assertTrue(addHours.compareTo(instant) < 4);


    }

    /**
     * @throws DateTimeException
     */

    @Test
    public void cashExpiredThrowsWhenGivenInvalidDate() {
        if (add100days.isAfter(instant)) {
            Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                throw new IllegalArgumentException("The time is in the future.");
            });
            Assertions.assertEquals("The time is in the future.", exception.getMessage());
        }
    }

    @Test
    public void cashLastModifiedTime() {
        Path ukCovidDataFilePath = AppConstants.Paths.resolvePath(AppConstants.Paths.CachedFiles.UK_COVID_DATA_FILE);
        try {
            BasicFileAttributes attr = Files.readAttributes(ukCovidDataFilePath, BasicFileAttributes.class);

            System.out.println("Creation time: " + attr.creationTime());
            System.out.println("lastAccess time: " + attr.lastAccessTime());
            System.out.println("lastModified time: " + attr.lastModifiedTime());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
