package main.Java.CovidCaseData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class CovidCaseByDate2 {
    public CovidCaseByDate2(String areaType,
                            String areaName,
                            String areaCode,
                            LocalDate date,
                            Integer newCasesBySpecimenDate,
                            Integer cumCasesBySpecimenDate) {
        this.areaType = areaType;
        this.areaName = areaName;
        this.areaCode = areaCode;
        this.date = date;
        this.newCasesBySpecimenDate = newCasesBySpecimenDate;
        this.cumCasesBySpecimenDate = cumCasesBySpecimenDate;
    }

    @SerializedName("AreaType")
    @Expose
    private String areaType;
    @SerializedName("AreaName")
    @Expose
    private String areaName;
    @SerializedName("AreaCode")
    @Expose
    private String areaCode;
    @SerializedName("Date")
    @Expose
    private LocalDate date;
    @SerializedName("NewCasesBySpecimenDate")
    @Expose
    private Integer newCasesBySpecimenDate;
    @SerializedName("CumCasesBySpecimenDate")
    @Expose
    private Integer cumCasesBySpecimenDate;

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getNewCasesBySpecimenDate() {
        return newCasesBySpecimenDate;
    }

    public void setNewCasesBySpecimenDate(Integer newCasesBySpecimenDate) {
        this.newCasesBySpecimenDate = newCasesBySpecimenDate;
    }

    public Integer getCumCasesBySpecimenDate() {
        return cumCasesBySpecimenDate;
    }

    public void setCumCasesBySpecimenDate(Integer cumCasesBySpecimenDate) {
        this.cumCasesBySpecimenDate = cumCasesBySpecimenDate;
    }

    @Override
    public String toString() {
        return "CovidCaseByDate2{" +
                "areaType='" + areaType + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", date=" + date +
                ", newCasesBySpecimenDate=" + newCasesBySpecimenDate +
                ", cumCasesBySpecimenDate=" + cumCasesBySpecimenDate +
                '}';
    }

}
