package csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiModels.covidCaseByDateModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UkCovidApiResponse {

    @SerializedName("length")
    @Expose
    private int length;
    @SerializedName("maxPageLimit")
    @Expose
    private int maxPageLimit;
    @SerializedName("data")
    @Expose
    private List<UkCovidApiData> data = null;
    @SerializedName("pagination")
    @Expose
    private UkCovidApiPagination pagination;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getMaxPageLimit() {
        return maxPageLimit;
    }

    public void setMaxPageLimit(int maxPageLimit) {
        this.maxPageLimit = maxPageLimit;
    }

    public List<UkCovidApiData> getData() {
        return data;
    }

    public void setData(List<UkCovidApiData> data) {
        this.data = data;
    }

    public UkCovidApiPagination getUkCovidApiPagination() {
        return pagination;
    }

    public void setUkCovidApiPagination(UkCovidApiPagination pagination) {
        this.pagination = pagination;
    }

}
