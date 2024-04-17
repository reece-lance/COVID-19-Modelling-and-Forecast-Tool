# Covid19GovernmentApi

All URIs are relative to *https://disease.sh/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v3Covid19GovCountryGet**](Covid19GovernmentApi.md#v3Covid19GovCountryGet) | **GET** /v3/covid-19/gov/{country} | Get COVID-19 government reported data for a specific country
[**v3Covid19GovGet**](Covid19GovernmentApi.md#v3Covid19GovGet) | **GET** /v3/covid-19/gov/ | Get a list of supported countries for government specific data

<a name="v3Covid19GovCountryGet"></a>
# **v3Covid19GovCountryGet**
> v3Covid19GovCountryGet(country, allowNull)

Get COVID-19 government reported data for a specific country

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19GovernmentApi;


Covid19GovernmentApi apiInstance = new Covid19GovernmentApi();
String country = "country_example"; // String | A valid country name from the /v3/covid-19/gov endpoint
String allowNull = "allowNull_example"; // String | By default, if a value is missing, it is returned as 0. This allows nulls to be returned
try {
    apiInstance.v3Covid19GovCountryGet(country, allowNull);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19GovernmentApi#v3Covid19GovCountryGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **country** | **String**| A valid country name from the /v3/covid-19/gov endpoint |
 **allowNull** | **String**| By default, if a value is missing, it is returned as 0. This allows nulls to be returned | [optional] [enum: true, false, 1, 0]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="v3Covid19GovGet"></a>
# **v3Covid19GovGet**
> CovidGov v3Covid19GovGet()

Get a list of supported countries for government specific data

Returns a list of supported country names

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19GovernmentApi;


Covid19GovernmentApi apiInstance = new Covid19GovernmentApi();
try {
    CovidGov result = apiInstance.v3Covid19GovGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19GovernmentApi#v3Covid19GovGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**CovidGov**](CovidGov.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

