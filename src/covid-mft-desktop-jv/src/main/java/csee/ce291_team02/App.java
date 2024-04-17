package csee.ce291_team02;

import com.google.gson.reflect.TypeToken;
import csee.ce291_team02.AppConstants.Paths.CachedFiles;
import csee.ce291_team02.Data.DataSource;
import csee.ce291_team02.Data.IDataSource;
import csee.ce291_team02.Data.LmaoNinjaApiClient.CountryData;
import csee.ce291_team02.Data.LmaoNinjaApiClient.MugCovid19WorldoMetersApiClient;
import csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiClient;
import csee.ce291_team02.Models.CovidCaseByDate;
import csee.ce291_team02.Models.MapRegionData;

import java.util.ArrayList;

import static csee.ce291_team02.AppConstants.Paths.resolvePath;

public class App {
    public static IDataSource<CovidCaseByDate> covidCaseByDateDataSupplier =
            new DataSource<>(resolvePath(CachedFiles.UK_COVID_DATA_FILE),
                    UkCovidApiClient.covidCaseByDateIApiClientFunction,
                    new TypeToken<ArrayList<CovidCaseByDate>>(){}.getType());

    public static IDataSource<CountryData> countryDataDataSupplier =
            new DataSource<>(resolvePath(CachedFiles.COUNTRY_DATA_FILE),
                    MugCovid19WorldoMetersApiClient.covidCaseByDateIApiClientFunction,
                    new TypeToken<ArrayList<CountryData>>(){}.getType());

    public static IDataSource<MapRegionData> mapRegionDataSupplier =
            new DataSource<>(resolvePath(CachedFiles.REGION_DATA_FILE),
                    UkCovidApiClient.mapRegionDataIApiClientFunction,
                    new TypeToken<ArrayList<MapRegionData>>(){}.getType());

    public static IDataSource<MapRegionData> mapUtlaDataSupplier =
            new DataSource<>(resolvePath(CachedFiles.UTLA_DATA_FILE),
                    UkCovidApiClient.mapUtlaDataIApiClientFunction,
                    new TypeToken<ArrayList<MapRegionData>>(){}.getType());

    public static IDataSource<MapRegionData> mapLtlaDataSupplier =
            new DataSource<>(resolvePath(CachedFiles.LTLA_DATA_FILE),
                    UkCovidApiClient.mapLtlaDataIApiClientFunction,
                    new TypeToken<ArrayList<MapRegionData>>(){}.getType());
}
