package csee.ce291_team02.Data.Ukcovidapiclient;

import csee.ce291_team02.AppConstants;
import csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiModels.regionDataModels.*;
import csee.ce291_team02.Models.CovidCaseByDate;
import csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiModels.covidCaseByDateModels.UkCovidApiData;
import csee.ce291_team02.Models.MapRegionData;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UkCovidApiMapper {

    /**
     * Takes Data from the API and insert into an arraylist
     * @param inData List type
     * @return
     */
    public static ArrayList<CovidCaseByDate> UkCovidApiDataToCovidCaseByDate(List<UkCovidApiData> inData){
        ArrayList<CovidCaseByDate> outData = new ArrayList<>();

        for (UkCovidApiData inItem : inData){
            outData.add(new CovidCaseByDate(
                    inItem.getAreaType(),
                    inItem.getName(),
                    inItem.getCode(),
                    LocalDate.parse(inItem.getDate(), AppConstants.Properties.DATE_TIME_FORMATTER),
                    inItem.getCases().getDaily(),
                    inItem.getCases().getCumulative())
            );
        }

        return outData;
    }

    /**
     * Takes the region data that will input to the map region and returns an arrayList.
     * @param inData List type
     * @return
     */

    public static ArrayList<MapRegionData> RegionDataToMapRegionData(List<RegionData> inData){
        ArrayList<MapRegionData> outData = new ArrayList<>();
        for (RegionData inItem: inData){
            RegionCases regionCases = inItem.getCases();
            RegionDeaths regionDeaths = inItem.getDeaths();

            //TODO: Do something with these
            final List<RegionMale> regionCasesMale = regionCases.getMale();
            Integer regCasMal = 0;
            if(regionCasesMale != null){
                for (RegionMale rm: regionCasesMale){
                    regCasMal += rm.getValue();
                }
            } else {
                regCasMal = null;
            }

            final List<RegionFemale> regionCasesFemale = regionCases.getFemale();
            Integer regCasFem = 0;
            if(regionCasesMale != null){
                for (RegionFemale rm: regionCasesFemale){
                    regCasFem += rm.getValue();
                }
            } else {
                regCasFem = null;
            }

            outData.add(new MapRegionData(
                inItem.getCode(),
                inItem.getName(),
                LocalDate.parse(inItem.getDate(), AppConstants.Properties.DATE_TIME_FORMATTER),
                regionCases.getDaily(),
                regionCases.getCumulative(),
                regionDeaths.getDaily(),
                regionDeaths.getCumulative(),
                regCasMal,
                regCasFem
            ));
        }
        return outData;
    }
}
