# Covid19VaccineApi

All URIs are relative to *https://disease.sh/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v3Covid19VaccineCoverageCountriesCountryGet**](Covid19VaccineApi.md#v3Covid19VaccineCoverageCountriesCountryGet) | **GET** /v3/covid-19/vaccine/coverage/countries/{country} | Get COVID-19 vaccine doses administered for a country that has reported vaccination rollout. Sourced from https://covid.ourworldindata.org/
[**v3Covid19VaccineCoverageCountriesGet**](Covid19VaccineApi.md#v3Covid19VaccineCoverageCountriesGet) | **GET** /v3/covid-19/vaccine/coverage/countries | Get COVID-19 vaccine doses administered for all countries that have reported rolling out vaccination. Sourced  from https://covid.ourworldindata.org/
[**v3Covid19VaccineCoverageGet**](Covid19VaccineApi.md#v3Covid19VaccineCoverageGet) | **GET** /v3/covid-19/vaccine/coverage | Get total global COVID-19 vaccine doses administered. Sourced from https://covid.ourworldindata.org/
[**v3Covid19VaccineGet**](Covid19VaccineApi.md#v3Covid19VaccineGet) | **GET** /v3/covid-19/vaccine | Get vaccine trial data from RAPS (Regulatory Affairs Professional Society). Specifically published by Jeff Craven at https://www.raps.org/news-and-articles/news-articles/2020/3/covid-19-vaccine-tracker

<a name="v3Covid19VaccineCoverageCountriesCountryGet"></a>
# **v3Covid19VaccineCoverageCountriesCountryGet**
> VaccineCountryCoverage v3Covid19VaccineCoverageCountriesCountryGet(country, lastdays)

Get COVID-19 vaccine doses administered for a country that has reported vaccination rollout. Sourced from https://covid.ourworldindata.org/

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19VaccineApi;


Covid19VaccineApi apiInstance = new Covid19VaccineApi();
String country = "country_example"; // String | A valid country name, iso2, iso3
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    VaccineCountryCoverage result = apiInstance.v3Covid19VaccineCoverageCountriesCountryGet(country, lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19VaccineApi#v3Covid19VaccineCoverageCountriesCountryGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **country** | **String**| A valid country name, iso2, iso3 |
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**VaccineCountryCoverage**](VaccineCountryCoverage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19VaccineCoverageCountriesGet"></a>
# **v3Covid19VaccineCoverageCountriesGet**
> VaccineCountriesCoverage v3Covid19VaccineCoverageCountriesGet(lastdays)

Get COVID-19 vaccine doses administered for all countries that have reported rolling out vaccination. Sourced  from https://covid.ourworldindata.org/

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19VaccineApi;


Covid19VaccineApi apiInstance = new Covid19VaccineApi();
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    VaccineCountriesCoverage result = apiInstance.v3Covid19VaccineCoverageCountriesGet(lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19VaccineApi#v3Covid19VaccineCoverageCountriesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**VaccineCountriesCoverage**](VaccineCountriesCoverage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19VaccineCoverageGet"></a>
# **v3Covid19VaccineCoverageGet**
> VaccineCoverage v3Covid19VaccineCoverageGet(lastdays)

Get total global COVID-19 vaccine doses administered. Sourced from https://covid.ourworldindata.org/

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19VaccineApi;


Covid19VaccineApi apiInstance = new Covid19VaccineApi();
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    VaccineCoverage result = apiInstance.v3Covid19VaccineCoverageGet(lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19VaccineApi#v3Covid19VaccineCoverageGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**VaccineCoverage**](VaccineCoverage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19VaccineGet"></a>
# **v3Covid19VaccineGet**
> Vaccines v3Covid19VaccineGet()

Get vaccine trial data from RAPS (Regulatory Affairs Professional Society). Specifically published by Jeff Craven at https://www.raps.org/news-and-articles/news-articles/2020/3/covid-19-vaccine-tracker

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19VaccineApi;


Covid19VaccineApi apiInstance = new Covid19VaccineApi();
try {
    Vaccines result = apiInstance.v3Covid19VaccineGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19VaccineApi#v3Covid19VaccineGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Vaccines**](Vaccines.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

