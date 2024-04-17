package csee.ce291_team02.Data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import csee.ce291_team02.Data.LmaoNinjaApiClient.CountryApiData;
import csee.ce291_team02.Data.LmaoNinjaApiClient.CountryData;
import csee.ce291_team02.Data.LmaoNinjaApiClient.CountryDataApiMapper;
import csee.ce291_team02.Models.CovidCaseByDate;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class MockDataProvider {
    public static ArrayList<CountryData> getMockCountryData() {

        // Storing preprocessed json(Added slashes)
        String countryJson = "[" +
                "  {" +
                "    \"updated\": 1613136011768," +
                "    \"country\": \"Afghanistan\"," +
                "    \"countryInfo\": {" +
                "      \"_id\": 4," +
                "      \"iso2\": \"AF\"," +
                "      \"iso3\": \"AFG\"," +
                "      \"lat\": 33," +
                "      \"long\": 65," +
                "      \"flag\": \"https://disease.sh/assets/img/flags/af.png\"" +
                "    }," +
                "    \"cases\": 55445," +
                "    \"todayCases\": 25," +
                "    \"deaths\": 2424," +
                "    \"todayDeaths\": 5," +
                "    \"recovered\": 48279," +
                "    \"todayRecovered\": 146," +
                "    \"active\": 4742," +
                "    \"critical\": 1009," +
                "    \"casesPerOneMillion\": 1405," +
                "    \"deathsPerOneMillion\": 61," +
                "    \"tests\": 270865," +
                "    \"testsPerOneMillion\": 6864," +
                "    \"population\": 39463277," +
                "    \"continent\": \"Asia\"," +
                "    \"oneCasePerPeople\": 712," +
                "    \"oneDeathPerPeople\": 16280," +
                "    \"oneTestPerPeople\": 146," +
                "    \"activePerOneMillion\": 120.16," +
                "    \"recoveredPerOneMillion\": 1223.39," +
                "    \"criticalPerOneMillion\": 25.57" +
                "  }," +
                "  {" +
                "    \"updated\": 1613136011694," +
                "    \"country\": \"Albania\"," +
                "    \"countryInfo\": {" +
                "      \"_id\": 8," +
                "      \"iso2\": \"AL\"," +
                "      \"iso3\": \"ALB\"," +
                "      \"lat\": 41," +
                "      \"long\": 20," +
                "      \"flag\": \"https://disease.sh/assets/img/flags/al.png\"" +
                "    }," +
                "    \"cases\": 89776," +
                "    \"todayCases\": 1105," +
                "    \"deaths\": 1517," +
                "    \"todayDeaths\": 14," +
                "    \"recovered\": 54362," +
                "    \"todayRecovered\": 746," +
                "    \"active\": 33897," +
                "    \"critical\": 37," +
                "    \"casesPerOneMillion\": 31217," +
                "    \"deathsPerOneMillion\": 527," +
                "    \"tests\": 393926," +
                "    \"testsPerOneMillion\": 136977," +
                "    \"population\": 2875854," +
                "    \"continent\": \"Europe\"," +
                "    \"oneCasePerPeople\": 32," +
                "    \"oneDeathPerPeople\": 1896," +
                "    \"oneTestPerPeople\": 7," +
                "    \"activePerOneMillion\": 11786.76," +
                "    \"recoveredPerOneMillion\": 18902.91," +
                "    \"criticalPerOneMillion\": 12.87" +
                "  }," +
                "  {" +
                "    \"updated\": 1613136011691," +
                "    \"country\": \"Algeria\"," +
                "    \"countryInfo\": {" +
                "      \"_id\": 12," +
                "      \"iso2\": \"DZ\"," +
                "      \"iso3\": \"DZA\"," +
                "      \"lat\": 28," +
                "      \"long\": 3," +
                "      \"flag\": \"https://disease.sh/assets/img/flags/dz.png\"" +
                "    }," +
                "    \"cases\": 110049," +
                "    \"todayCases\": 267," +
                "    \"deaths\": 2930," +
                "    \"todayDeaths\": 4," +
                "    \"recovered\": 75436," +
                "    \"todayRecovered\": 193," +
                "    \"active\": 31683," +
                "    \"critical\": 42," +
                "    \"casesPerOneMillion\": 2482," +
                "    \"deathsPerOneMillion\": 66," +
                "    \"tests\": 0," +
                "    \"testsPerOneMillion\": 0," +
                "    \"population\": 44335512," +
                "    \"continent\": \"Africa\"," +
                "    \"oneCasePerPeople\": 403," +
                "    \"oneDeathPerPeople\": 15132," +
                "    \"oneTestPerPeople\": 0," +
                "    \"activePerOneMillion\": 714.62," +
                "    \"recoveredPerOneMillion\": 1701.48," +
                "    \"criticalPerOneMillion\": 0.95" +
                "  }," +
                "  {" +
                "    \"updated\": 1613136011861," +
                "    \"country\": \"Andorra\"," +
                "    \"countryInfo\": {" +
                "      \"_id\": 20," +
                "      \"iso2\": \"AD\"," +
                "      \"iso3\": \"AND\"," +
                "      \"lat\": 42.5," +
                "      \"long\": 1.6," +
                "      \"flag\": \"https://disease.sh/assets/img/flags/ad.png\"" +
                "    }," +
                "    \"cases\": 10391," +
                "    \"todayCases\": 39," +
                "    \"deaths\": 106," +
                "    \"todayDeaths\": 0," +
                "    \"recovered\": 9781," +
                "    \"todayRecovered\": 49," +
                "    \"active\": 504," +
                "    \"critical\": 15," +
                "    \"casesPerOneMillion\": 134353," +
                "    \"deathsPerOneMillion\": 1371," +
                "    \"tests\": 193595," +
                "    \"testsPerOneMillion\": 2503135," +
                "    \"population\": 77341," +
                "    \"continent\": \"Europe\"," +
                "    \"oneCasePerPeople\": 7," +
                "    \"oneDeathPerPeople\": 730," +
                "    \"oneTestPerPeople\": 0," +
                "    \"activePerOneMillion\": 6516.6," +
                "    \"recoveredPerOneMillion\": 126465.91," +
                "    \"criticalPerOneMillion\": 193.95" +
                "  }," +
                "  {" +
                "    \"updated\": 1613136011786," +
                "    \"country\": \"Angola\"," +
                "    \"countryInfo\": {" +
                "      \"_id\": 24," +
                "      \"iso2\": \"AO\"," +
                "      \"iso3\": \"AGO\"," +
                "      \"lat\": -12.5," +
                "      \"long\": 18.5," +
                "      \"flag\": \"https://disease.sh/assets/img/flags/ao.png\"" +
                "    }," +
                "    \"cases\": 20261," +
                "    \"todayCases\": 51," +
                "    \"deaths\": 487," +
                "    \"todayDeaths\": 0," +
                "    \"recovered\": 18710," +
                "    \"todayRecovered\": 23," +
                "    \"active\": 1064," +
                "    \"critical\": 8," +
                "    \"casesPerOneMillion\": 605," +
                "    \"deathsPerOneMillion\": 15," +
                "    \"tests\": 178539," +
                "    \"testsPerOneMillion\": 5332," +
                "    \"population\": 33486722," +
                "    \"continent\": \"Africa\"," +
                "    \"oneCasePerPeople\": 1653," +
                "    \"oneDeathPerPeople\": 68761," +
                "    \"oneTestPerPeople\": 188," +
                "    \"activePerOneMillion\": 31.77," +
                "    \"recoveredPerOneMillion\": 558.73," +
                "    \"criticalPerOneMillion\": 0.24" +
                "  }," +
                "  {" +
                "    \"updated\": 1613136011979," +
                "    \"country\": \"Anguilla\"," +
                "    \"countryInfo\": {" +
                "      \"_id\": 660," +
                "      \"iso2\": \"AI\"," +
                "      \"iso3\": \"AIA\"," +
                "      \"lat\": 18.25," +
                "      \"long\": -63.1667," +
                "      \"flag\": \"https://disease.sh/assets/img/flags/ai.png\"" +
                "    }," +
                "    \"cases\": 18," +
                "    \"todayCases\": 0," +
                "    \"deaths\": 0," +
                "    \"todayDeaths\": 0," +
                "    \"recovered\": 16," +
                "    \"todayRecovered\": 0," +
                "    \"active\": 2," +
                "    \"critical\": 0," +
                "    \"casesPerOneMillion\": 1193," +
                "    \"deathsPerOneMillion\": 0," +
                "    \"tests\": 8256," +
                "    \"testsPerOneMillion\": 547299," +
                "    \"population\": 15085," +
                "    \"continent\": \"North America\"," +
                "    \"oneCasePerPeople\": 838," +
                "    \"oneDeathPerPeople\": 0," +
                "    \"oneTestPerPeople\": 2," +
                "    \"activePerOneMillion\": 132.58," +
                "    \"recoveredPerOneMillion\": 1060.66," +
                "    \"criticalPerOneMillion\": 0" +
                "  }" +
                "]";

        // Creating a Gson Object
        Gson gson = new Gson();

        Type responseType = new TypeToken<ArrayList<CountryApiData>>() {}.getType();

        // Converting json to object
        ArrayList<CountryApiData> countriesData = gson.fromJson(countryJson, responseType);

        return CountryDataApiMapper.CountryApiDataToCountryData(countriesData);
    }

    public static List<CovidCaseByDate> getCovidCaseByDateDataMock() {
        ArrayList<CovidCaseByDate> data = new ArrayList<CovidCaseByDate>();


        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 4),
                1434,
                1123197
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 3),
                14512,
                1121763
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 2),
                29142,
                1107251
        ));
        data.add(new CovidCaseByDate(
                "Overview",
                "United Kingdom",
                "K02000001",
                LocalDate.of(2020, 11, 1),
                15419,
                1078109
        ));

        return data;
    }
}
