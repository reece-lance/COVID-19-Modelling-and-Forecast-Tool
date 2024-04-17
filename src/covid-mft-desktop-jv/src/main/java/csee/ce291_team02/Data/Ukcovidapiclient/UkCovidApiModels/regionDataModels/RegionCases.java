package csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiModels.regionDataModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionCases {

    @SerializedName("daily")
    @Expose
    private Integer daily;
    @SerializedName("cumulative")
    @Expose
    private Object cumulative;
    @SerializedName("male")
    @Expose
    private List<RegionMale> male = null;
    @SerializedName("female")
    @Expose
    private List<RegionFemale> female = null;

    public Integer getDaily() {
        return daily;
    }

    public void setDaily(Integer daily) {
        this.daily = daily;
    }

    public Object getCumulative() {
        return cumulative;
    }

    public void setCumulative(Object cumulative) {
        this.cumulative = cumulative;
    }

    public List<RegionMale> getMale() {
        return male;
    }

    public void setMale(List<RegionMale> male) {
        this.male = male;
    }

    public List<RegionFemale> getFemale() {
        return female;
    }

    public void setFemale(List<RegionFemale> female) {
        this.female = female;
    }

}
