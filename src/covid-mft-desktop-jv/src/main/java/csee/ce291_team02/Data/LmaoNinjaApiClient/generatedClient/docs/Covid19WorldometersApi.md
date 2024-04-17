# Covid19WorldometersApi

All URIs are relative to *https://disease.sh/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**v3Covid19AllGet**](Covid19WorldometersApi.md#v3Covid19AllGet) | **GET** /v3/covid-19/all | Get global COVID-19 totals for today, yesterday and two days ago
[**v3Covid19ContinentsContinentGet**](Covid19WorldometersApi.md#v3Covid19ContinentsContinentGet) | **GET** /v3/covid-19/continents/{continent} | Get COVID-19 totals for a specific continent
[**v3Covid19ContinentsGet**](Covid19WorldometersApi.md#v3Covid19ContinentsGet) | **GET** /v3/covid-19/continents | Get COVID-19 totals for all continents
[**v3Covid19CountriesCountriesGet**](Covid19WorldometersApi.md#v3Covid19CountriesCountriesGet) | **GET** /v3/covid-19/countries/{countries} | Get COVID-19 totals for a specific set of countries
[**v3Covid19CountriesCountryGet**](Covid19WorldometersApi.md#v3Covid19CountriesCountryGet) | **GET** /v3/covid-19/countries/{country} | Get COVID-19 totals for a specific country
[**v3Covid19CountriesGet**](Covid19WorldometersApi.md#v3Covid19CountriesGet) | **GET** /v3/covid-19/countries | Get COVID-19 totals for all countries
[**v3Covid19StatesGet**](Covid19WorldometersApi.md#v3Covid19StatesGet) | **GET** /v3/covid-19/states | Get COVID-19 totals for all US States
[**v3Covid19StatesStatesGet**](Covid19WorldometersApi.md#v3Covid19StatesStatesGet) | **GET** /v3/covid-19/states/{states} | Get COVID-19 totals for specific US State(s)

<a name="v3Covid19AllGet"></a>
# **v3Covid19AllGet**
> CovidAll v3Covid19AllGet(yesterday, twoDaysAgo, allowNull)

Get global COVID-19 totals for today, yesterday and two days ago

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19WorldometersApi;


Covid19WorldometersApi apiInstance = new Covid19WorldometersApi();
String yesterday = "yesterday_example"; // String | Queries data reported a day ago
String twoDaysAgo = "twoDaysAgo_example"; // String | Queries data reported two days ago
String allowNull = "allowNull_example"; // String | By default, if a value is missing, it is returned as 0. This allows nulls to be returned
try {
    CovidAll result = apiInstance.v3Covid19AllGet(yesterday, twoDaysAgo, allowNull);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19WorldometersApi#v3Covid19AllGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **yesterday** | **String**| Queries data reported a day ago | [optional] [enum: true, false, 1, 0]
 **twoDaysAgo** | **String**| Queries data reported two days ago | [optional] [enum: true, false, 1, 0]
 **allowNull** | **String**| By default, if a value is missing, it is returned as 0. This allows nulls to be returned | [optional] [enum: true, false, 1, 0]

### Return type

[**CovidAll**](CovidAll.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19ContinentsContinentGet"></a>
# **v3Covid19ContinentsContinentGet**
> CovidContinent v3Covid19ContinentsContinentGet(continent, yesterday, twoDaysAgo, strict, allowNull)

Get COVID-19 totals for a specific continent

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19WorldometersApi;


Covid19WorldometersApi apiInstance = new Covid19WorldometersApi();
String continent = "continent_example"; // String | Continent name
String yesterday = "yesterday_example"; // String | Queries data reported a day ago
String twoDaysAgo = "twoDaysAgo_example"; // String | Queries data reported two days ago
String strict = "true"; // String | Setting to false gives you the ability to fuzzy search continents (i.e. Oman vs. rOMANia)
String allowNull = "allowNull_example"; // String | By default, if a value is missing, it is returned as 0. This allows nulls to be returned
try {
    CovidContinent result = apiInstance.v3Covid19ContinentsContinentGet(continent, yesterday, twoDaysAgo, strict, allowNull);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19WorldometersApi#v3Covid19ContinentsContinentGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **continent** | **String**| Continent name |
 **yesterday** | **String**| Queries data reported a day ago | [optional] [enum: true, false, 1, 0]
 **twoDaysAgo** | **String**| Queries data reported two days ago | [optional] [enum: true, false, 1, 0]
 **strict** | **String**| Setting to false gives you the ability to fuzzy search continents (i.e. Oman vs. rOMANia) | [optional] [default to true] [enum: true, false]
 **allowNull** | **String**| By default, if a value is missing, it is returned as 0. This allows nulls to be returned | [optional] [enum: true, false, 1, 0]

### Return type

[**CovidContinent**](CovidContinent.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19ContinentsGet"></a>
# **v3Covid19ContinentsGet**
> CovidContinents v3Covid19ContinentsGet(yesterday, twoDaysAgo, sort, allowNull)

Get COVID-19 totals for all continents

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19WorldometersApi;


Covid19WorldometersApi apiInstance = new Covid19WorldometersApi();
String yesterday = "yesterday_example"; // String | Queries data reported a day ago
String twoDaysAgo = "twoDaysAgo_example"; // String | Queries data reported two days ago
String sort = "sort_example"; // String | Provided a key (e.g. cases, todayCases, deaths, recovered, active), results will be sorted from greatest to least
String allowNull = "allowNull_example"; // String | By default, if a value is missing, it is returned as 0. This allows nulls to be returned
try {
    CovidContinents result = apiInstance.v3Covid19ContinentsGet(yesterday, twoDaysAgo, sort, allowNull);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19WorldometersApi#v3Covid19ContinentsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **yesterday** | **String**| Queries data reported a day ago | [optional] [enum: true, false, 1, 0]
 **twoDaysAgo** | **String**| Queries data reported two days ago | [optional] [enum: true, false, 1, 0]
 **sort** | **String**| Provided a key (e.g. cases, todayCases, deaths, recovered, active), results will be sorted from greatest to least | [optional] [enum: cases, todayCases, deaths, todayDeaths, recovered, active, critical, casesPerOneMillion, deathsPerOneMillion]
 **allowNull** | **String**| By default, if a value is missing, it is returned as 0. This allows nulls to be returned | [optional] [enum: true, false, 1, 0]

### Return type

[**CovidContinents**](CovidContinents.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19CountriesCountriesGet"></a>
# **v3Covid19CountriesCountriesGet**
> CovidCountries v3Covid19CountriesCountriesGet(countries, yesterday, twoDaysAgo, allowNull)

Get COVID-19 totals for a specific set of countries

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19WorldometersApi;


Covid19WorldometersApi apiInstance = new Covid19WorldometersApi();
String countries = "countries_example"; // String | Multiple country names, iso2, iso3, or country IDs separated by commas
String yesterday = "yesterday_example"; // String | Queries data reported a day ago
String twoDaysAgo = "twoDaysAgo_example"; // String | Queries data reported two days ago
String allowNull = "allowNull_example"; // String | By default, if a value is missing, it is returned as 0. This allows nulls to be returned
try {
    CovidCountries result = apiInstance.v3Covid19CountriesCountriesGet(countries, yesterday, twoDaysAgo, allowNull);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19WorldometersApi#v3Covid19CountriesCountriesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **countries** | **String**| Multiple country names, iso2, iso3, or country IDs separated by commas |
 **yesterday** | **String**| Queries data reported a day ago | [optional] [enum: true, false, 1, 0]
 **twoDaysAgo** | **String**| Queries data reported two days ago | [optional] [enum: true, false, 1, 0]
 **allowNull** | **String**| By default, if a value is missing, it is returned as 0. This allows nulls to be returned | [optional] [enum: true, false, 1, 0]

### Return type

[**CovidCountries**](CovidCountries.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19CountriesCountryGet"></a>
# **v3Covid19CountriesCountryGet**
> CovidCountry v3Covid19CountriesCountryGet(country, yesterday, twoDaysAgo, strict, allowNull)

Get COVID-19 totals for a specific country

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19WorldometersApi;


Covid19WorldometersApi apiInstance = new Covid19WorldometersApi();
String country = "country_example"; // String | A country name, iso2, iso3, or country ID code
String yesterday = "yesterday_example"; // String | Queries data reported a day ago
String twoDaysAgo = "twoDaysAgo_example"; // String | Queries data reported two days ago
String strict = "true"; // String | Setting to false gives you the ability to fuzzy search countries (i.e. Oman vs. rOMANia)
String allowNull = "allowNull_example"; // String | By default, if a value is missing, it is returned as 0. This allows nulls to be returned
try {
    CovidCountry result = apiInstance.v3Covid19CountriesCountryGet(country, yesterday, twoDaysAgo, strict, allowNull);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19WorldometersApi#v3Covid19CountriesCountryGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **country** | **String**| A country name, iso2, iso3, or country ID code |
 **yesterday** | **String**| Queries data reported a day ago | [optional] [enum: true, false, 1, 0]
 **twoDaysAgo** | **String**| Queries data reported two days ago | [optional] [enum: true, false, 1, 0]
 **strict** | **String**| Setting to false gives you the ability to fuzzy search countries (i.e. Oman vs. rOMANia) | [optional] [default to true] [enum: true, false]
 **allowNull** | **String**| By default, if a value is missing, it is returned as 0. This allows nulls to be returned | [optional] [enum: true, false, 1, 0]

### Return type

[**CovidCountry**](CovidCountry.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19CountriesGet"></a>
# **v3Covid19CountriesGet**
> CovidCountries v3Covid19CountriesGet(yesterday, twoDaysAgo, sort, allowNull)

Get COVID-19 totals for all countries

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19WorldometersApi;


Covid19WorldometersApi apiInstance = new Covid19WorldometersApi();
String yesterday = "yesterday_example"; // String | Queries data reported a day ago
String twoDaysAgo = "twoDaysAgo_example"; // String | Queries data reported two days ago
String sort = "sort_example"; // String | Provided a key (e.g. cases, todayCases, deaths, recovered, active), the result will be sorted from greatest to least
String allowNull = "allowNull_example"; // String | By default, if a value is missing, it is returned as 0. This allows nulls to be returned
try {
    CovidCountries result = apiInstance.v3Covid19CountriesGet(yesterday, twoDaysAgo, sort, allowNull);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19WorldometersApi#v3Covid19CountriesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **yesterday** | **String**| Queries data reported a day ago | [optional] [enum: true, false, 1, 0]
 **twoDaysAgo** | **String**| Queries data reported two days ago | [optional] [enum: true, false, 1, 0]
 **sort** | **String**| Provided a key (e.g. cases, todayCases, deaths, recovered, active), the result will be sorted from greatest to least | [optional] [enum: cases, todayCases, deaths, todayDeaths, recovered, active, critical, casesPerOneMillion, deathsPerOneMillion]
 **allowNull** | **String**| By default, if a value is missing, it is returned as 0. This allows nulls to be returned | [optional] [enum: true, false, 1, 0]

### Return type

[**CovidCountries**](CovidCountries.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19StatesGet"></a>
# **v3Covid19StatesGet**
> CovidStates v3Covid19StatesGet(sort, yesterday, allowNull)

Get COVID-19 totals for all US States

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19WorldometersApi;


Covid19WorldometersApi apiInstance = new Covid19WorldometersApi();
String sort = "sort_example"; // String | Provided a key (e.g. cases, todayCases, deaths, active), result will be sorted from greatest to least
String yesterday = "yesterday_example"; // String | Queries data reported a day ago
String allowNull = "allowNull_example"; // String | By default, if a value is missing, it is returned as 0. This allows nulls to be returned
try {
    CovidStates result = apiInstance.v3Covid19StatesGet(sort, yesterday, allowNull);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19WorldometersApi#v3Covid19StatesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sort** | **String**| Provided a key (e.g. cases, todayCases, deaths, active), result will be sorted from greatest to least | [optional] [enum: cases, todayCases, deaths, todayDeaths, active]
 **yesterday** | **String**| Queries data reported a day ago | [optional] [enum: true, false, 1, 0]
 **allowNull** | **String**| By default, if a value is missing, it is returned as 0. This allows nulls to be returned | [optional] [enum: true, false, 1, 0]

### Return type

[**CovidStates**](CovidStates.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="v3Covid19StatesStatesGet"></a>
# **v3Covid19StatesStatesGet**
> CovidState v3Covid19StatesStatesGet(states, yesterday, allowNull)

Get COVID-19 totals for specific US State(s)

### Example
```java
// Import classes:
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.invokerPackage.ApiException;
//import csee.ce291_team02.Data.LmaoNinjaApiClient.generatedClient.apiPackage.Covid19WorldometersApi;


Covid19WorldometersApi apiInstance = new Covid19WorldometersApi();
String states = "states_example"; // String | State name or comma separated names spelled correctly
String yesterday = "yesterday_example"; // String | Queries data reported a day ago
String allowNull = "allowNull_example"; // String | By default, if a value is missing, it is returned as 0. This allows nulls to be returned
try {
    CovidState result = apiInstance.v3Covid19StatesStatesGet(states, yesterday, allowNull);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Covid19WorldometersApi#v3Covid19StatesStatesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **states** | **String**| State name or comma separated names spelled correctly |
 **yesterday** | **String**| Queries data reported a day ago | [optional] [enum: true, false, 1, 0]
 **allowNull** | **String**| By default, if a value is missing, it is returned as 0. This allows nulls to be returned | [optional] [enum: true, false, 1, 0]

### Return type

[**CovidState**](CovidState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

