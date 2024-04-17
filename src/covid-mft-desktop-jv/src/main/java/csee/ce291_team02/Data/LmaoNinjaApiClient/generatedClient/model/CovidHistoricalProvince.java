/*
 * disease.sh Docs - An open API for disease-related statistics
 * Third Party API for reliable global disease information
 *
 * OpenAPI spec version: 3.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * CovidHistoricalProvince
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-02-12T19:32:54.804Z[Europe/London]")
public class CovidHistoricalProvince {
  @SerializedName("country")
  private String country = null;

  @SerializedName("province")
  private String province = null;

  @SerializedName("timeline")
  private CovidHistoricalCountryTimeline timeline = null;

  public CovidHistoricalProvince country(String country) {
    this.country = country;
    return this;
  }

   /**
   * Get country
   * @return country
  **/
  @Schema(description = "")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public CovidHistoricalProvince province(String province) {
    this.province = province;
    return this;
  }

   /**
   * Get province
   * @return province
  **/
  @Schema(description = "")
  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public CovidHistoricalProvince timeline(CovidHistoricalCountryTimeline timeline) {
    this.timeline = timeline;
    return this;
  }

   /**
   * Get timeline
   * @return timeline
  **/
  @Schema(description = "")
  public CovidHistoricalCountryTimeline getTimeline() {
    return timeline;
  }

  public void setTimeline(CovidHistoricalCountryTimeline timeline) {
    this.timeline = timeline;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CovidHistoricalProvince covidHistoricalProvince = (CovidHistoricalProvince) o;
    return Objects.equals(this.country, covidHistoricalProvince.country) &&
        Objects.equals(this.province, covidHistoricalProvince.province) &&
        Objects.equals(this.timeline, covidHistoricalProvince.timeline);
  }

  @Override
  public int hashCode() {
    return Objects.hash(country, province, timeline);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CovidHistoricalProvince {\n");
    
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    province: ").append(toIndentedString(province)).append("\n");
    sb.append("    timeline: ").append(toIndentedString(timeline)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
