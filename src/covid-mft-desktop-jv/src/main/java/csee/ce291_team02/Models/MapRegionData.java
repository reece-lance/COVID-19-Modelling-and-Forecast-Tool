package csee.ce291_team02.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class MapRegionData implements Comparable<MapRegionData> {

    //TODO: Sanksar, delete this once you are done with making changes here.
    // You will need to add whatever properties from RegionData you want.
    // The data structure does not have to be copied 1:1.
    // Maybe you want to change it somehow so that you can better view it in the UI.

    @SerializedName("Code")
    @Expose
    public String Code;
    @SerializedName("Name")
    @Expose
    public String Name;
    @SerializedName("Date")
    @Expose
    public LocalDate Date;
    @SerializedName("regionCasesDaily")
    @Expose
    public Integer regionCasesDaily;
    @SerializedName("regionCasesCumulative")
    @Expose
    public Object regionCasesCumulative;
    @SerializedName("deathsDaily")
    @Expose
    public Integer deathsDaily;
    @SerializedName("deathsCumulative")
    @Expose
    public Integer deathsCumulative;
    @SerializedName("regionDeathsMale")
    @Expose
    public Integer regionCasesMale;
    @SerializedName("regionDeathsFemale")
    @Expose
    public Integer regionCasesFemale;

    public static MapRegionDataAccessor mapRegionDataAccessor = x -> x.regionCasesDaily;

    public MapRegionData(String code,
                         String name,
                         LocalDate date,
                         Integer regionCasesDaily,
                         Object regionCasesCumulative,
                         Integer deathsDaily,
                         Integer deathsCumulative,
                         Integer regCasMal,
                         Integer regCasFem) {
        this.Code = code;
        this.Name = name;
        this.Date = date;
        this.regionCasesDaily = regionCasesDaily;
        this.regionCasesCumulative = regionCasesCumulative;
        this.deathsDaily = deathsDaily;
        this.deathsCumulative = deathsCumulative;
        this.regionCasesMale = regCasMal;
        this.regionCasesFemale = regCasFem;
    }

    public static int compare(MapRegionData o1, MapRegionData o2) {
        final int o1v = mapRegionDataAccessor.getVal(o1);
        final int o2v = mapRegionDataAccessor.getVal(o2);
        if(o1v > o2v){
            return 1;
        } else if(o1v < o2v){
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int compareTo(@NotNull MapRegionData o) {
        return compare(this, o);
    }

    public interface MapRegionDataAccessor{
        int getVal(MapRegionData dto);
    }

    public static int zeroIfNull(Integer i){
        if(i == null){
            return 0;
        } else {
            return i;
        }
    }
}
