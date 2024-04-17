# InfluenzaCdcApi

All URIs are relative to *https://disease.sh/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v3InfluenzaCdcILINetGet**](InfluenzaCdcApi.md#v3InfluenzaCdcILINetGet) | **GET** /v3/influenza/cdc/ILINet | Get Influenza-like-illness data for the 2019 and 2020 outbreaks from the US Center for Disease Control
[**v3InfluenzaCdcUSCLGet**](InfluenzaCdcApi.md#v3InfluenzaCdcUSCLGet) | **GET** /v3/influenza/cdc/USCL | Get Influenza report data for the 2019 and 2020 outbreaks from the US Center for Disease Control, reported by US clinical labs
[**v3InfluenzaCdcUSPHLGet**](InfluenzaCdcApi.md#v3InfluenzaCdcUSPHLGet) | **GET** /v3/influenza/cdc/USPHL | Get Influenza report data for the 2019 and 2020 outbreaks from the US Center for Disease Control, reported by US public health labs

<a name="v3InfluenzaCdcILINetGet"></a>
# **v3InfluenzaCdcILINetGet**
> InfluenzaILINet v3InfluenzaCdcILINetGet()

Get Influenza-like-illness data for the 2019 and 2020 outbreaks from the US Center for Disease Control

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.InfluenzaCdcApi;


InfluenzaCdcApi apiInstance = new InfluenzaCdcApi();
try {
    InfluenzaILINet result = apiInstance.v3InfluenzaCdcILINetGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InfluenzaCdcApi#v3InfluenzaCdcILINetGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**InfluenzaILINet**](InfluenzaILINet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3InfluenzaCdcUSCLGet"></a>
# **v3InfluenzaCdcUSCLGet**
> InfluenzaUSCL v3InfluenzaCdcUSCLGet()

Get Influenza report data for the 2019 and 2020 outbreaks from the US Center for Disease Control, reported by US clinical labs

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.InfluenzaCdcApi;


InfluenzaCdcApi apiInstance = new InfluenzaCdcApi();
try {
    InfluenzaUSCL result = apiInstance.v3InfluenzaCdcUSCLGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InfluenzaCdcApi#v3InfluenzaCdcUSCLGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**InfluenzaUSCL**](InfluenzaUSCL.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3InfluenzaCdcUSPHLGet"></a>
# **v3InfluenzaCdcUSPHLGet**
> InfluenzaUSPHL v3InfluenzaCdcUSPHLGet()

Get Influenza report data for the 2019 and 2020 outbreaks from the US Center for Disease Control, reported by US public health labs

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.InfluenzaCdcApi;


InfluenzaCdcApi apiInstance = new InfluenzaCdcApi();
try {
    InfluenzaUSPHL result = apiInstance.v3InfluenzaCdcUSPHLGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InfluenzaCdcApi#v3InfluenzaCdcUSPHLGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**InfluenzaUSPHL**](InfluenzaUSPHL.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

