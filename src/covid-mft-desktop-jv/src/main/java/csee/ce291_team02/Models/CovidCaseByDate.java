package csee.ce291_team02.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class CovidCaseByDate implements Comparable<CovidCaseByDate> {
    public CovidCaseByDate(String areaType,
                           String areaName,
                           String areaCode,
                           LocalDate date,
                           Integer newCasesBySpecimenDate,
                           Integer cumCasesBySpecimenDate) {
        this.AreaType = areaType;
        this.AreaName = areaName;
        this.AreaCode = areaCode;
        this.Date = date;
        this.NewCasesBySpecimenDate = newCasesBySpecimenDate;
        this.CumCasesBySpecimenDate = cumCasesBySpecimenDate;
    }

    /*TODO: make this public and eventually encapsulate them as private with accessors */

    @SerializedName("AreaType")
    @Expose
//    private String areaType;
    public String AreaType;

    @SerializedName("AreaName")
    @Expose
//    private String areaName;
    public String AreaName;

    @SerializedName("AreaCode")
    @Expose
//    private String areaCode;
    public String AreaCode;

    @SerializedName("Date")
    @Expose
//    private LocalDate date;
    public LocalDate Date;

    @SerializedName("NewCasesBySpecimenDate")
    @Expose
//    private Integer newCasesBySpecimenDate;
    public int NewCasesBySpecimenDate;

    @SerializedName("CumCasesBySpecimenDate")
    @Expose
//    private Integer cumCasesBySpecimenDate;
    public int CumCasesBySpecimenDate;



    public String getAreaType() {
        return AreaType;
    }

    public void setAreaType(String areaType) {
        this.AreaType = areaType;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        this.AreaName = areaName;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        this.AreaCode = areaCode;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        this.Date = date;
    }

    public Integer getNewCasesBySpecimenDate() {
        return NewCasesBySpecimenDate;
    }

    public void setNewCasesBySpecimenDate(Integer newCasesBySpecimenDate) {
        this.NewCasesBySpecimenDate = newCasesBySpecimenDate;
    }

    public Integer getCumCasesBySpecimenDate() {
        return CumCasesBySpecimenDate;
    }

    public void setCumCasesBySpecimenDate(Integer cumCasesBySpecimenDate) {
        this.CumCasesBySpecimenDate = cumCasesBySpecimenDate;
    }

    @Override
    public String toString() {
        return "CovidCaseByDate2{" +
                "areaType='" + AreaType + '\'' +
                ", areaName='" + AreaName + '\'' +
                ", areaCode='" + AreaCode + '\'' +
                ", date=" + Date +
                ", newCasesBySpecimenDate=" + NewCasesBySpecimenDate +
                ", cumCasesBySpecimenDate=" + CumCasesBySpecimenDate +
                '}';
    }

    @Override
    public int compareTo(@NotNull CovidCaseByDate o) {
        return this.getDate().compareTo(o.getDate());
    }
}