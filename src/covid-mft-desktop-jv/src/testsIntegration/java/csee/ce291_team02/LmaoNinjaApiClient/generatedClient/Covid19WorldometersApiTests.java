package csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient;

import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19WorldometersApi;
import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.model.CovidAll;
import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.model.CovidCountries;
import org.junit.jupiter.api.Test;

public class Covid19WorldometersApiTests {
    @Test
    public void worldometersCountriesFetchesData() throws ApiException {
        Covid19WorldometersApi apiInstance = new Covid19WorldometersApi();
        String yesterday = "1"; // String | Queries data reported a day ago
        String twoDaysAgo = "0"; // String | Queries data reported two days ago
        String sort = ""; // String | Provided a key (e.g. cases, todayCases, deaths, recovered, active), the result will be sorted from greatest to least
        String allowNull = "0"; // String | By default, if a value is missing, it is returned as 0. This allows nulls to be returned
        try {
            CovidCountries result = apiInstance.v3Covid19CountriesGet(yesterday, twoDaysAgo, sort, allowNull);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling Covid19WorldometersApi#v3Covid19CountriesGet");
            e.printStackTrace();
        }
    }

    @Test
    public void worldometersAllTotalsFetchesData() throws ApiException {
        Covid19WorldometersApi apiInstance = new Covid19WorldometersApi();
        String yesterday = "1"; // String | Queries data reported a day ago
        String twoDaysAgo = "1"; // String | Queries data reported two days ago
        String allowNull = "1"; // String | By default, if a value is missing, it is returned as 0. This allows nulls to be returned
        try {
            CovidAll result = apiInstance.v3Covid19AllGet(yesterday, twoDaysAgo, allowNull);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling Covid19WorldometersApi#v3Covid19AllGet");
            e.printStackTrace();
        }
    }
}
