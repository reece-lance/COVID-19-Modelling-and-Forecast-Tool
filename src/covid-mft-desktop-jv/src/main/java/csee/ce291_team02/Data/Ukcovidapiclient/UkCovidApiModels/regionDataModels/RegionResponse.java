package csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiModels.regionDataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegionResponse {

    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("maxPageLimit")
    @Expose
    private Integer maxPageLimit;
    @SerializedName("data")
    @Expose
    private List<RegionData> data = null;
    @SerializedName("pagination")
    @Expose
    private RegionPagination pagination;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getMaxPageLimit() {
        return maxPageLimit;
    }

    public void setMaxPageLimit(Integer maxPageLimit) {
        this.maxPageLimit = maxPageLimit;
    }

    public List<RegionData> getData() {
        return data;
    }

    public void setData(List<RegionData> data) {
        this.data = data;
    }

    public RegionPagination getPagination() {
        return pagination;
    }

    public void setPagination(RegionPagination pagination) {
        this.pagination = pagination;
    }

}