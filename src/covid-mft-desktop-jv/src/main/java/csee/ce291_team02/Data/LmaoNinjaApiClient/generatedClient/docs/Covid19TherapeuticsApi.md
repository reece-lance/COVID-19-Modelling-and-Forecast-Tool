# Covid19TherapeuticsApi

All URIs are relative to *https://disease.sh/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v3Covid19TherapeuticsGet**](Covid19TherapeuticsApi.md#v3Covid19TherapeuticsGet) | **GET** /v3/covid-19/therapeutics | Get therapeutics trial data from RAPS (Regulatory Affairs Professional Society). Specifically published by Jeff Craven at https://www.raps.org/news-and-articles/news-articles/2020/3/covid-19-therapeutics-tracker

<a name="v3Covid19TherapeuticsGet"></a>
# **v3Covid19TherapeuticsGet**
> Therapeutics v3Covid19TherapeuticsGet()

Get therapeutics trial data from RAPS (Regulatory Affairs Professional Society). Specifically published by Jeff Craven at https://www.raps.org/news-and-articles/news-articles/2020/3/covid-19-therapeutics-tracker

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19TherapeuticsApi;


Covid19TherapeuticsApi apiInstance = new Covid19TherapeuticsApi();
try {
    Therapeutics result = apiInstance.v3Covid19TherapeuticsGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19TherapeuticsApi#v3Covid19TherapeuticsGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Therapeutics**](Therapeutics.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

