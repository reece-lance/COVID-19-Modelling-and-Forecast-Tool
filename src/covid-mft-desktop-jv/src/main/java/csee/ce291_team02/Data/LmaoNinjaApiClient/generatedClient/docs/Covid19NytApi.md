# Covid19NytApi

All URIs are relative to *https://disease.sh/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v3Covid19NytCountiesCountyGet**](Covid19NytApi.md#v3Covid19NytCountiesCountyGet) | **GET** /v3/covid-19/nyt/counties/{county} | Get COVID-19 time series data for a county or set of counties, with an entry for each day since the pandemic began
[**v3Covid19NytCountiesGet**](Covid19NytApi.md#v3Covid19NytCountiesGet) | **GET** /v3/covid-19/nyt/counties | Get COVID-19 time series data for all available US counties, with an entry for each day since the pandemic began
[**v3Covid19NytStatesGet**](Covid19NytApi.md#v3Covid19NytStatesGet) | **GET** /v3/covid-19/nyt/states | Get COVID-19 time series data for all states, with an entry for each day since the pandemic began
[**v3Covid19NytStatesStateGet**](Covid19NytApi.md#v3Covid19NytStatesStateGet) | **GET** /v3/covid-19/nyt/states/{state} | Get COVID-19 time series data for a state or set of states, with an entry for each day since the pandemic began
[**v3Covid19NytUsaGet**](Covid19NytApi.md#v3Covid19NytUsaGet) | **GET** /v3/covid-19/nyt/usa | Get COVID-19 time series data for the entire USA, with an entry for each day since the pandemic began

<a name="v3Covid19NytCountiesCountyGet"></a>
# **v3Covid19NytCountiesCountyGet**
> CovidNYTCounty v3Covid19NytCountiesCountyGet(county, lastdays)

Get COVID-19 time series data for a county or set of counties, with an entry for each day since the pandemic began

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19NytApi;


Covid19NytApi apiInstance = new Covid19NytApi();
String county = "county_example"; // String | County name(s), separated by commas (e.g. 'Alameda, Humboldt')
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    CovidNYTCounty result = apiInstance.v3Covid19NytCountiesCountyGet(county, lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19NytApi#v3Covid19NytCountiesCountyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **county** | **String**| County name(s), separated by commas (e.g. &#x27;Alameda, Humboldt&#x27;) |
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**CovidNYTCounty**](CovidNYTCounty.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19NytCountiesGet"></a>
# **v3Covid19NytCountiesGet**
> CovidNYTCounty v3Covid19NytCountiesGet(lastdays)

Get COVID-19 time series data for all available US counties, with an entry for each day since the pandemic began

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19NytApi;


Covid19NytApi apiInstance = new Covid19NytApi();
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    CovidNYTCounty result = apiInstance.v3Covid19NytCountiesGet(lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19NytApi#v3Covid19NytCountiesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**CovidNYTCounty**](CovidNYTCounty.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19NytStatesGet"></a>
# **v3Covid19NytStatesGet**
> CovidNYTState v3Covid19NytStatesGet(lastdays)

Get COVID-19 time series data for all states, with an entry for each day since the pandemic began

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19NytApi;


Covid19NytApi apiInstance = new Covid19NytApi();
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    CovidNYTState result = apiInstance.v3Covid19NytStatesGet(lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19NytApi#v3Covid19NytStatesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**CovidNYTState**](CovidNYTState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19NytStatesStateGet"></a>
# **v3Covid19NytStatesStateGet**
> CovidNYTState v3Covid19NytStatesStateGet(state, lastdays)

Get COVID-19 time series data for a state or set of states, with an entry for each day since the pandemic began

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19NytApi;


Covid19NytApi apiInstance = new Covid19NytApi();
String state = "state_example"; // String | State name(s), separated by commas (e.g. 'Illinois, California')
String lastdays = "30"; // String | Number of days to return. Use 'all' for the full data set (e.g. 15, all, 24)
try {
    CovidNYTState result = apiInstance.v3Covid19NytStatesStateGet(state, lastdays);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19NytApi#v3Covid19NytStatesStateGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **state** | **String**| State name(s), separated by commas (e.g. &#x27;Illinois, California&#x27;) |
 **lastdays** | **String**| Number of days to return. Use &#x27;all&#x27; for the full data set (e.g. 15, all, 24) | [optional] [default to 30]

### Return type

[**CovidNYTState**](CovidNYTState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19NytUsaGet"></a>
# **v3Covid19NytUsaGet**
> CovidNYTUSA v3Covid19NytUsaGet()

Get COVID-19 time series data for the entire USA, with an entry for each day since the pandemic began

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19NytApi;


Covid19NytApi apiInstance = new Covid19NytApi();
try {
    CovidNYTUSA result = apiInstance.v3Covid19NytUsaGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19NytApi#v3Covid19NytUsaGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**CovidNYTUSA**](CovidNYTUSA.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

