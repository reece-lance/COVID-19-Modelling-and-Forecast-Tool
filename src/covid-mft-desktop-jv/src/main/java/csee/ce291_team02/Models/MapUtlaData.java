package csee.ce291_team02.Models;

import java.time.LocalDate;

public class MapUtlaData extends MapRegionData{
    public MapUtlaData(String code,
                       String name,
                       LocalDate date,
                       Integer regionCasesDaily,
                       Object regionCasesCumulative,
                       Integer deathsDaily,
                       Integer deathsCumulative,
                       Integer regCasMal,
                       Integer regCasFe) {
        super(code,
                name,
                date,
                regionCasesDaily,
                regionCasesCumulative,
                deathsDaily,
                deathsCumulative,
                regCasMal,
                regCasFe);
    }
}
