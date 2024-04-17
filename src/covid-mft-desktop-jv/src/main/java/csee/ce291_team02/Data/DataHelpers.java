package csee.ce291_team02.Data;

import csee.ce291_team02.Models.CovidCaseByDate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *This class is created to retrieve two types of Data.
 */

public class DataHelpers {


    /**
     * This method will retrieve the Cumumulative Cases
     * @param covidCaseByDateData List type.
     * @return cumCasesBySpecimenDate
     */
    public static int[] getCumCasesBySpeciminDate(List<CovidCaseByDate> covidCaseByDateData) {
        int[] cumCasesBySpecimenDate = new int[covidCaseByDateData.size()];
        for (int i = 0; i < covidCaseByDateData.size(); i++) {
            cumCasesBySpecimenDate[i] = covidCaseByDateData.get(i).CumCasesBySpecimenDate;
        }
        return cumCasesBySpecimenDate;
    }

    /**
     * This method will retrive the New Cases
     * @param covidCaseByDateData List type.
     * @return newCasesBySpecimenDate
     */
    public static int[] getNewCasesBySpeciminDate(List<CovidCaseByDate> covidCaseByDateData) {
        int[] newCasesBySpecimenDate = new int[covidCaseByDateData.size()];
        for (int i = 0; i < covidCaseByDateData.size(); i++) {
            newCasesBySpecimenDate[i] = covidCaseByDateData.get(i).NewCasesBySpecimenDate;
        }
        return newCasesBySpecimenDate;
    }

    /**
     * Copies contents of an input into a target file.
     * @param in
     * @param target
     */
    public static void copyFileOver(InputStream in, Path target){
        try {
            System.out.println(target);
            if(!Files.exists(target)){
                Files.createFile(target);
            }
            Files.copy(in, target, REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
