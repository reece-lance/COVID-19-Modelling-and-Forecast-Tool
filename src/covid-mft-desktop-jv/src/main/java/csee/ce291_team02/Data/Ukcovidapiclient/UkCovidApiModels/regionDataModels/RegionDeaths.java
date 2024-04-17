package csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiModels.regionDataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionDeaths {

    @SerializedName("daily")
    @Expose
    private Integer daily;
    @SerializedName("cumulative")
    @Expose
    private Integer cumulative;

    public Integer getDaily() {
        return daily;
    }

    public void setDaily(Integer daily) {
        this.daily = daily;
    }

    public Integer getCumulative() {
        return cumulative;
    }

    public void setCumulative(Integer cumulative) {
        this.cumulative = cumulative;
    }

}
