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
 * CovidAppleData
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2021-02-12T19:32:54.804Z[Europe/London]")
public class CovidAppleData {
  @SerializedName("country")
  private String country = null;

  @SerializedName("subregion")
  private String subregion = null;

  @SerializedName("data")
  private java.util.List<CovidAppleDataData> data = null;

  public CovidAppleData country(String country) {
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

  public CovidAppleData subregion(String subregion) {
    this.subregion = subregion;
    return this;
  }

   /**
   * Get subregion
   * @return subregion
  **/
  @Schema(description = "")
  public String getSubregion() {
    return subregion;
  }

  public void setSubregion(String subregion) {
    this.subregion = subregion;
  }

  public CovidAppleData data(java.util.List<CovidAppleDataData> data) {
    this.data = data;
    return this;
  }

  public CovidAppleData addDataItem(CovidAppleDataData dataItem) {
    if (this.data == null) {
      this.data = new java.util.ArrayList<>();
    }
    this.data.add(dataItem);
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @Schema(description = "")
  public java.util.List<CovidAppleDataData> getData() {
    return data;
  }

  public void setData(java.util.List<CovidAppleDataData> data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CovidAppleData covidAppleData = (CovidAppleData) o;
    return Objects.equals(this.country, covidAppleData.country) &&
        Objects.equals(this.subregion, covidAppleData.subregion) &&
        Objects.equals(this.data, covidAppleData.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(country, subregion, data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CovidAppleData {\n");
    
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    subregion: ").append(toIndentedString(subregion)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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
