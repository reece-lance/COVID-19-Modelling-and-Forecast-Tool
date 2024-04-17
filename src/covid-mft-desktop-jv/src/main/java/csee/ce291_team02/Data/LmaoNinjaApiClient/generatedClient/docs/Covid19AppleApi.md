# Covid19AppleApi

All URIs are relative to *https://disease.sh/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v3Covid19AppleCountriesCountryGet**](Covid19AppleApi.md#v3Covid19AppleCountriesCountryGet) | **GET** /v3/covid-19/apple/countries/{country} | Get a list of supported subregions for specific country in the Apple mobility data set
[**v3Covid19AppleCountriesCountrySubregionsGet**](Covid19AppleApi.md#v3Covid19AppleCountriesCountrySubregionsGet) | **GET** /v3/covid-19/apple/countries/{country}/{subregions} | Get COVID-19 Apple mobility data for subregions of a country
[**v3Covid19AppleCountriesGet**](Covid19AppleApi.md#v3Covid19AppleCountriesGet) | **GET** /v3/covid-19/apple/countries | Get a list of supported countries for Apple mobility data

<a name="v3Covid19AppleCountriesCountryGet"></a>
# **v3Covid19AppleCountriesCountryGet**
> CovidAppleSubregions v3Covid19AppleCountriesCountryGet(country)

Get a list of supported subregions for specific country in the Apple mobility data set

Returns a list of supported subregions in a country where data is available

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19AppleApi;


Covid19AppleApi apiInstance = new Covid19AppleApi();
String country = "country_example"; // String | A valid country name from the /v3/covid-19/apple/countries endpoint
try {
    CovidAppleSubregions result = apiInstance.v3Covid19AppleCountriesCountryGet(country);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19AppleApi#v3Covid19AppleCountriesCountryGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **country** | **String**| A valid country name from the /v3/covid-19/apple/countries endpoint |

### Return type

[**CovidAppleSubregions**](CovidAppleSubregions.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19AppleCountriesCountrySubregionsGet"></a>
# **v3Covid19AppleCountriesCountrySubregionsGet**
> CovidAppleData v3Covid19AppleCountriesCountrySubregionsGet(country, subregions)

Get COVID-19 Apple mobility data for subregions of a country

Returns a list of mobility data entries for subregion(s) every day since January 13th. Each entry has driving, transit, and walking data with an associated number of percentage change since January 13th

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19AppleApi;


Covid19AppleApi apiInstance = new Covid19AppleApi();
String country = "country_example"; // String | A valid country name from the /v3/covid-19/apple/countries endpoint
String subregions = "subregions_example"; // String | Valid subregion(s) from the /v3/covid-19/apple/countries/{country} endpoint, separated by with commas
try {
    CovidAppleData result = apiInstance.v3Covid19AppleCountriesCountrySubregionsGet(country, subregions);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19AppleApi#v3Covid19AppleCountriesCountrySubregionsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **country** | **String**| A valid country name from the /v3/covid-19/apple/countries endpoint |
 **subregions** | **String**| Valid subregion(s) from the /v3/covid-19/apple/countries/{country} endpoint, separated by with commas |

### Return type

[**CovidAppleData**](CovidAppleData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19AppleCountriesGet"></a>
# **v3Covid19AppleCountriesGet**
> CovidAppleCountries v3Covid19AppleCountriesGet()

Get a list of supported countries for Apple mobility data

Returns a list of supported country names

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19AppleApi;


Covid19AppleApi apiInstance = new Covid19AppleApi();
try {
    CovidAppleCountries result = apiInstance.v3Covid19AppleCountriesGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19AppleApi#v3Covid19AppleCountriesGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**CovidAppleCountries**](CovidAppleCountries.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

