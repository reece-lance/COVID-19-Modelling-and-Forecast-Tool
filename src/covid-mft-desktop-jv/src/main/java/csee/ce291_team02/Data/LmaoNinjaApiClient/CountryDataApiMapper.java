package csee.ce291_team02.Data.LmaoNinjaApiClient;

import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.model.CovidCountries;
import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.model.CovidCountry;

import java.util.ArrayList;
import java.util.List;

public class CountryDataApiMapper {

    /**
     * Takes Data from the API and insert into an arraylist
     * @param inData List type.
     * @return
     */
    public static ArrayList<CountryData> CountryApiDataToCountryData(List<CountryApiData> inData) {
        ArrayList<CountryData> outData = new ArrayList<>();

        for (CountryApiData inItem : inData){
            outData.add(new CountryData(
                    inItem.getCountry(),
                    inItem.getPopulation(),
                    inItem.getCases(),
                    inItem.getDeaths(),
                    inItem.getRecovered(),
                    inItem.getTodayCases(),
                    inItem.getTodayDeaths(),
                    inItem.getTodayRecovered(),
                    inItem.getCountryInfo().getIso3())

            );
        }
        return outData;
    }


    /**
     * Takes the Countries data that will input to the map region and returns an List.
     * @param data
     * @return
     */
    public static List<CountryData> covidCountriesToCountryData(CovidCountries data){
        ArrayList<CountryData> result = new ArrayList<>();

        for (CovidCountry country : data) {
            result.add(new CountryData(
                    country.getCountry(),
                    country.getPopulation().intValue(),
                    country.getCases().intValue(),
                    country.getDeaths().intValue(),
                    country.getRecovered().intValue(),
                    country.getTodayCases().intValue(),
                    country.getTodayDeaths().intValue(),
                    country.getTodayRecovered().intValue(),
                    country.getCountryInfo().getIso3()
            ));
        }

        return result;
    }
}
