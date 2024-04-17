package csee.ce291_team02.Data.LmaoNinjaApiClient;

import csee.ce291_team02.Data.IApiClientFunction;
import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19WorldometersApi;
import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.model.CovidCountries;
import csee.ce291_team02.MugLogger;

public class MugCovid19WorldoMetersApiClient extends Covid19WorldometersApi {
    public static final IApiClientFunction<CountryData> covidCaseByDateIApiClientFunction = () ->
    {
        String yesterday = "1"; // String | Queries data reported a day ago
        String twoDaysAgo = "0"; // String | Queries data reported two days ago
        String sort = ""; // String | Provided a key (e.g. cases, todayCases, deaths, recovered, active), the result will be sorted from greatest to least
        String allowNull = "0"; // String | By default, if a value is missing, it is returned as 0. This allows nulls to be returned
        Covid19WorldometersApi apiInstance = new Covid19WorldometersApi();
        CovidCountries data = null;
        try {
            data = apiInstance.v3Covid19CountriesGet(yesterday, twoDaysAgo, sort, allowNull);
            } catch (ApiException e) {
            MugLogger.log("Exception when calling Covid19WorldometersApi#v3Covid19CountriesGet");
            e.printStackTrace();
        }

        return CountryDataApiMapper.covidCountriesToCountryData(data);
    };
}
