package csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiModels.regionDataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegionMale {

    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("value")
    @Expose
    private Integer value;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
