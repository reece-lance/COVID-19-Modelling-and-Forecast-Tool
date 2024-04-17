package csee.ce291_team02.Data.LmaoNinjaApiClient;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryData {
    public CountryData(String country,
                       int population,
                       int cases,
                       int deaths,
                       int recovered,
                       int yesterdaysCases,
                       int yesterdaysDeaths,
                       int yesterdaysRecovered,
                       String iso3) {
        this.country = country;
        this.population = population;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
        this.yesterdaysCases = yesterdaysCases;
        this.yesterdaysDeaths = yesterdaysDeaths;
        this.yesterdaysRecovered = yesterdaysRecovered;
        this.iso3 = iso3;
    }


    @SerializedName("Country")
    @Expose
    public String country;

    @SerializedName("Population")
    @Expose
    public int population;

    @SerializedName("Cases")
    @Expose
    public int cases;

    @SerializedName("Deaths")
    @Expose
    public int deaths;

    @SerializedName("Recovered")
    @Expose
    public int recovered;

    @SerializedName("Yesterday's Cases")
    @Expose
    public int yesterdaysCases;

    @SerializedName("Yesterday's Deaths")
    @Expose
    public int yesterdaysDeaths;

    @SerializedName("Yesterday's Recovered")
    @Expose
    public int yesterdaysRecovered;

    @SerializedName("Iso3Code")
    @Expose
    private String iso3;

    public String getCountry() {
        return country;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int value) {
        this.population = value;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int value) {
        this.cases = value;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int value) {
        this.deaths = value;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int value) {
        this.recovered = value;
    }

    public int getYesterdaysCases() {
        return yesterdaysCases;
    }

    public void setYesterdaysCases(int value) {
        this.yesterdaysCases = value;
    }

    public int getYesterdaysDeaths() {
        return yesterdaysDeaths;
    }

    public void setYesterdaysDeaths(int value) {
        this.yesterdaysDeaths = value;
    }

    public int getYesterdaysRecovered() {
        return yesterdaysRecovered;
    }

    public void setYesterdaysRecovered(int value) {
        this.yesterdaysRecovered = value;
    }

    public String getIso3(){return iso3; }

    public void setIso3(String value){ this.iso3 = value; }

    @Override
    public String toString() {
        return "CountryData{" +
                "country='" + country + '\'' +
                ", population=" + population +
                ", cases=" + cases +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", yesterdaysCases=" + yesterdaysCases +
                ", yesterdaysDeaths=" + yesterdaysDeaths +
                ", yesterdaysRecovered=" + yesterdaysRecovered +
                ", iso3='" + iso3 + '\'' +
                '}';
    }
}