# Covid19JhucsseApi

All URIs are relative to *https://disease.sh/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v3Covid19HistoricalAllGet**](Covid19JhucsseApi.md#v3Covid19HistoricalAllGet) | **GET** /v3/covid-19/historical/all | Get global accumulated COVID-19 time series data
[**v3Covid19HistoricalCountriesGet**](Covid19JhucsseApi.md#v3Covid19HistoricalCountriesGet) | **GET** /v3/covid-19/historical/{countries} | Get COVID-19 time series data for a specific set of countries
[**v3Covid19HistoricalCountryGet**](Covid19JhucsseApi.md#v3Covid19HistoricalCountryGet) | **GET** /v3/covid-19/historical/{country} | Get COVID-19 time series data for a specific country
[**v3Covid19HistoricalCountryProvinceGet**](Covid19JhucsseApi.md#v3Covid19HistoricalCountryProvinceGet) | **GET** /v3/covid-19/historical/{country}/{province} | Get COVID-19 time series data for a specific province in a country
[**v3Covid19HistoricalCountryProvincesGet**](Covid19JhucsseApi.md#v3Covid19HistoricalCountryProvincesGet) | **GET** /v3/covid-19/historical/{country}/{provinces} | Get COVID-19 time series data for a set of provinces in a country
[**v3Covid19HistoricalGet**](Covid19JhucsseApi.md#v3Covid19HistoricalGet) | **GET** /v3/covid-19/historical | Get COVID-19 time series data for all countries and their provinces
[**v3Covid19HistoricalUsacountiesGet**](Covid19JhucsseApi.md#v3Covid19HistoricalUsacountiesGet) | **GET** /v3/covid-19/historical/usacounties | Get all possible US States to query the /historical/usacounties/{state} endpoint with
[**v3Covid19HistoricalUsacountiesStateGet**](Covid19JhucsseApi.md#v3Covid19HistoricalUsacountiesStateGet) | **GET** /v3/covid-19/historical/usacounties/{state} | Get COVID-19 time series data for all counties in a specified US state
[**v3Covid19JhucsseCountiesCountyGet**](Covid19JhucsseApi.md#v3Covid19JhucsseCountiesCountyGet) | **GET** /v3/covid-19/jhucsse/counties/{county} | Get COVID-19 totals for a specific county
[**v3Covid19JhucsseCountiesGet**](Covid19JhucsseApi.md#v3Covid19JhucsseCountiesGet) | **GET** /v3/covid-19/jhucsse/counties | Get COVID-19 totals for all US counties
[**v3Covid19JhucsseGet**](Covid19JhucsseApi.md#v3Covid19JhucsseGet) | **GET** /v3/covid-19/jhucsse | Get COVID-19 totals for all countries and their provinces

<a name="v3Covid19HistoricalAllGet"></a>
# **v3Covid19HistoricalAllGet**
> CovidHistoricalAll v3Covid19HistoricalAllGet(lastdays)

Get global accumulated COVID-19 time series data

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19JhucsseApi;


Covid19JhucsseApi apiInstance = new Covid19JhucsseApi();
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    CovidHistoricalAll result = apiInstance.v3Covid19HistoricalAllGet(lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19JhucsseApi#v3Covid19HistoricalAllGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**CovidHistoricalAll**](CovidHistoricalAll.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19HistoricalCountriesGet"></a>
# **v3Covid19HistoricalCountriesGet**
> CovidHistoricalCountries v3Covid19HistoricalCountriesGet(countries, lastdays)

Get COVID-19 time series data for a specific set of countries

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19JhucsseApi;


Covid19JhucsseApi apiInstance = new Covid19JhucsseApi();
String countries = "countries_example"; // String | Multiple country names, iso2, iso3, or country IDs separated by commas
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    CovidHistoricalCountries result = apiInstance.v3Covid19HistoricalCountriesGet(countries, lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19JhucsseApi#v3Covid19HistoricalCountriesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **countries** | **String**| Multiple country names, iso2, iso3, or country IDs separated by commas |
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**CovidHistoricalCountries**](CovidHistoricalCountries.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19HistoricalCountryGet"></a>
# **v3Covid19HistoricalCountryGet**
> CovidHistoricalCountry v3Covid19HistoricalCountryGet(country, lastdays)

Get COVID-19 time series data for a specific country

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19JhucsseApi;


Covid19JhucsseApi apiInstance = new Covid19JhucsseApi();
String country = "country_example"; // String | A country name, iso2, iso3, or country ID code
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    CovidHistoricalCountry result = apiInstance.v3Covid19HistoricalCountryGet(country, lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19JhucsseApi#v3Covid19HistoricalCountryGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **country** | **String**| A country name, iso2, iso3, or country ID code |
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**CovidHistoricalCountry**](CovidHistoricalCountry.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19HistoricalCountryProvinceGet"></a>
# **v3Covid19HistoricalCountryProvinceGet**
> CovidHistoricalProvince v3Covid19HistoricalCountryProvinceGet(country, province, lastdays)

Get COVID-19 time series data for a specific province in a country

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19JhucsseApi;


Covid19JhucsseApi apiInstance = new Covid19JhucsseApi();
String country = "country_example"; // String | A country name, iso2, iso3, or country ID code
String province = "province_example"; // String | Province name. All available names can be found in the /v3/covid-19/historical/{query} endpoint
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    CovidHistoricalProvince result = apiInstance.v3Covid19HistoricalCountryProvinceGet(country, province, lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19JhucsseApi#v3Covid19HistoricalCountryProvinceGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **country** | **String**| A country name, iso2, iso3, or country ID code |
 **province** | **String**| Province name. All available names can be found in the /v3/covid-19/historical/{query} endpoint |
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**CovidHistoricalProvince**](CovidHistoricalProvince.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19HistoricalCountryProvincesGet"></a>
# **v3Covid19HistoricalCountryProvincesGet**
> CovidHistoricalProvinces v3Covid19HistoricalCountryProvincesGet(country, provinces, lastdays)

Get COVID-19 time series data for a set of provinces in a country

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19JhucsseApi;


Covid19JhucsseApi apiInstance = new Covid19JhucsseApi();
String country = "country_example"; // String | A country name, iso2, iso3, or country ID code
String provinces = "provinces_example"; // String | Provinces spelled correctly separated by ',' or '|' delimiters, never both in the same query
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    CovidHistoricalProvinces result = apiInstance.v3Covid19HistoricalCountryProvincesGet(country, provinces, lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19JhucsseApi#v3Covid19HistoricalCountryProvincesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **country** | **String**| A country name, iso2, iso3, or country ID code |
 **provinces** | **String**| Provinces spelled correctly separated by &#x27;,&#x27; or &#x27;|&#x27; delimiters, never both in the same query |
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**CovidHistoricalProvinces**](CovidHistoricalProvinces.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19HistoricalGet"></a>
# **v3Covid19HistoricalGet**
> CovidHistorical v3Covid19HistoricalGet(lastdays)

Get COVID-19 time series data for all countries and their provinces

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19JhucsseApi;


Covid19JhucsseApi apiInstance = new Covid19JhucsseApi();
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    CovidHistorical result = apiInstance.v3Covid19HistoricalGet(lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19JhucsseApi#v3Covid19HistoricalGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**CovidHistorical**](CovidHistorical.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19HistoricalUsacountiesGet"></a>
# **v3Covid19HistoricalUsacountiesGet**
> CovidHistoricalUSCounties v3Covid19HistoricalUsacountiesGet()

Get all possible US States to query the /historical/usacounties/{state} endpoint with

Returns a list of US States and provinces

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19JhucsseApi;


Covid19JhucsseApi apiInstance = new Covid19JhucsseApi();
try {
    CovidHistoricalUSCounties result = apiInstance.v3Covid19HistoricalUsacountiesGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19JhucsseApi#v3Covid19HistoricalUsacountiesGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**CovidHistoricalUSCounties**](CovidHistoricalUSCounties.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19HistoricalUsacountiesStateGet"></a>
# **v3Covid19HistoricalUsacountiesStateGet**
> CovidHistoricalUSCounty v3Covid19HistoricalUsacountiesStateGet(state, lastdays)

Get COVID-19 time series data for all counties in a specified US state

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19JhucsseApi;


Covid19JhucsseApi apiInstance = new Covid19JhucsseApi();
String state = "state_example"; // String | US state name, validated in the array returned from the /v3/covid-19/historical/usacounties endpoint
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    CovidHistoricalUSCounty result = apiInstance.v3Covid19HistoricalUsacountiesStateGet(state, lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19JhucsseApi#v3Covid19HistoricalUsacountiesStateGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **state** | **String**| US state name, validated in the array returned from the /v3/covid-19/historical/usacounties endpoint |
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**CovidHistoricalUSCounty**](CovidHistoricalUSCounty.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19JhucsseCountiesCountyGet"></a>
# **v3Covid19JhucsseCountiesCountyGet**
> CovidJHUCounty v3Covid19JhucsseCountiesCountyGet(county)

Get COVID-19 totals for a specific county

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19JhucsseApi;


Covid19JhucsseApi apiInstance = new Covid19JhucsseApi();
String county = "county_example"; // String | Name of any county in the USA. All counties are listed in the /v3/covid-19/jhucsse/counties/ endpoint
try {
    CovidJHUCounty result = apiInstance.v3Covid19JhucsseCountiesCountyGet(county);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19JhucsseApi#v3Covid19JhucsseCountiesCountyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **county** | **String**| Name of any county in the USA. All counties are listed in the /v3/covid-19/jhucsse/counties/ endpoint |

### Return type

[**CovidJHUCounty**](CovidJHUCounty.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19JhucsseCountiesGet"></a>
# **v3Covid19JhucsseCountiesGet**
> CovidJHUCounties v3Covid19JhucsseCountiesGet()

Get COVID-19 totals for all US counties

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19JhucsseApi;


Covid19JhucsseApi apiInstance = new Covid19JhucsseApi();
try {
    CovidJHUCounties result = apiInstance.v3Covid19JhucsseCountiesGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19JhucsseApi#v3Covid19JhucsseCountiesGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**CovidJHUCounties**](CovidJHUCounties.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19JhucsseGet"></a>
# **v3Covid19JhucsseGet**
> CovidJHUCountries v3Covid19JhucsseGet()

Get COVID-19 totals for all countries and their provinces

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19JhucsseApi;


Covid19JhucsseApi apiInstance = new Covid19JhucsseApi();
try {
    CovidJHUCountries result = apiInstance.v3Covid19JhucsseGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19JhucsseApi#v3Covid19JhucsseGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**CovidJHUCountries**](CovidJHUCountries.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

