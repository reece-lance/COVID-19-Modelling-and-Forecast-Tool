package csee.ce291_team02.Data;

import csee.ce291_team02.Data.LmaoNinjaApiClient.CountryData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountryDataSourceTests {
    @Test
    public void CountryDataSerializes() {
        ArrayList<CountryData> data = MockDataProvider.getMockCountryData();

        assertNotNull(data);
    }
}
