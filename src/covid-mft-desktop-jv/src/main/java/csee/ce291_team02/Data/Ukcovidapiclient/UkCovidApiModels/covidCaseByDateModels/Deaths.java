package csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiModels.covidCaseByDateModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deaths {

    @SerializedName("daily")
    @Expose
    private int daily;
    @SerializedName("cumulative")
    @Expose
    private int cumulative;

    public int getDaily() {
        return daily;
    }

    public void setDaily(int daily) {
        this.daily = daily;
    }

    public int getCumulative() {
        return cumulative;
    }

    public void setCumulative(int cumulative) {
        this.cumulative = cumulative;
    }

}
