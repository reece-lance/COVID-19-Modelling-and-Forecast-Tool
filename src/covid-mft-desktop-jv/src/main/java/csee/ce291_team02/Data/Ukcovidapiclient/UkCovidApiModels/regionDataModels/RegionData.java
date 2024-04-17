package csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiModels.regionDataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionData {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("cases")
    @Expose
    private RegionCases cases;
    @SerializedName("deaths")
    @Expose
    private RegionDeaths deaths;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RegionCases getCases() {
        return cases;
    }

    public void setCases(RegionCases cases) {
        this.cases = cases;
    }

    public RegionDeaths getDeaths() {
        return deaths;
    }

    public void setDeaths(RegionDeaths deaths) {
        this.deaths = deaths;
    }

    @Override
    public String toString() {
        return "RegionData{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", cases=" + cases +
                ", deaths=" + deaths +
                '}';
    }
}
