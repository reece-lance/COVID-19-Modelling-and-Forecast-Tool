package csee.ce291_team02.Data.Ukcovidapiclient;

import csee.ce291_team02.Models.CovidCaseByDate;
import csee.ce291_team02.Models.MapRegionData;
import csee.ce291_team02.MugLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiClient.covidCaseByDateIApiClientFunction;
import static csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiClient.mapRegionDataIApiClientFunction;

public class UkCovidApiClientTests {


    @Test
    public void shouldReturnMappedDataForCovidCaseByDate(){
        List<CovidCaseByDate> data = covidCaseByDateIApiClientFunction.getData();

        Assertions.assertTrue(data.size() > 0);
    }
    @Test
    public void shouldReturnMappedDataForRegionMap(){
        List<MapRegionData> data = mapRegionDataIApiClientFunction.getData();
        Assertions.assertTrue(data.size() > 0);
    }
}
