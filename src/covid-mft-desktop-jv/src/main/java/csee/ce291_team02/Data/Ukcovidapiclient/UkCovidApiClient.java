package csee.ce291_team02.Data.Ukcovidapiclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import csee.ce291_team02.AppConstants;
import csee.ce291_team02.Data.IApiClientFunction;
import csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiModels.regionDataModels.RegionResponse;
import csee.ce291_team02.Models.CovidCaseByDate;
import csee.ce291_team02.Data.Ukcovidapiclient.UkCovidApiModels.covidCaseByDateModels.UkCovidApiResponse;
import csee.ce291_team02.Models.MapRegionData;
import csee.ce291_team02.MugLogger;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.GzipSource;
import okio.Okio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/**
 * PRIMITIVE client for https://api.coronavirus.data.gov.uk
 *
 * Used REST client
 * SOURCE: https://square.github.io/okhttp
 *
 * Used documentation for UK COVID API
 * SOURCE: https://coronavirus.data.gov.uk/details/developers-guide
 * */
public class UkCovidApiClient {
    public static final IApiClientFunction<CovidCaseByDate> covidCaseByDateIApiClientFunction = () ->
            UkCovidApiMapper.UkCovidApiDataToCovidCaseByDate(getCasesByDateReported().getData());

    public static final IApiClientFunction<MapRegionData> mapRegionDataIApiClientFunction = () ->
            UkCovidApiMapper.RegionDataToMapRegionData(getAreaMapData(AreaTypes.Regioin).getData());

    public static final IApiClientFunction<MapRegionData> mapUtlaDataIApiClientFunction = () ->
            UkCovidApiMapper.RegionDataToMapRegionData(getAreaMapData(AreaTypes.Uplta).getData());

    public static final IApiClientFunction<MapRegionData> mapLtlaDataIApiClientFunction = () ->
            UkCovidApiMapper.RegionDataToMapRegionData(getAreaMapData(AreaTypes.Ltla).getData());


    private static class AreaTypes{
        public static final String Regioin = "region";
        public static final String Uplta = "utla";
        public static final String Ltla = "ltla";
    }

    private static final OkHttpClient client = new OkHttpClient();

    /**
     * The main endpoint to download data related to Coronavirus in the UK
     */
    private static final String UK_COVID_API_EP = "https://api.coronavirus.data.gov.uk/v1/data";

    //UkCovidApiCalls.py helps us to build these urls interactivelly:
    private static final String UK_COVID_API_REGION_DATA_URL = "https://api.coronavirus.data.gov.uk/v1/data?filters=areaType%3D{{INSERT_REGION}}%3Bdate%3D{{INSERT_DATE_HERE}}&structure=%7B%22date%22%3A%22date%22%2C%22name%22%3A%22areaName%22%2C%22code%22%3A%22areaCode%22%2C%22cases%22%3A%7B%22daily%22%3A%22newCasesByPublishDate%22%2C%22cumulative%22%3A%22cumCasesByPublishDate%22%2C%22male%22%3A%22maleCases%22%2C%22female%22%3A%22femaleCases%22%7D%2C%22deaths%22%3A%7B%22daily%22%3A%22newDeathsByDeathDate%22%2C%22cumulative%22%3A%22cumDeathsByDeathDate%22%7D%7D";
    private static final String DATE_TOKEN = "{{INSERT_DATE_HERE}}";
    private static final String REGION_TOKEN = "{{INSERT_REGION}}";

    /**
     * Retrieves UK region data.
     * @return Two days old data for UK regions.
     */
    public static RegionResponse getAreaMapData(String areaType){
        String url = UK_COVID_API_REGION_DATA_URL
                .replace(DATE_TOKEN,
                        LocalDate.now()
                                .minusDays(2)
                                .format(AppConstants.Properties.DATE_TIME_FORMATTER)
                ).replace(REGION_TOKEN, areaType);
        String stringResult = getResponse(url);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        RegionResponse serializedResponse = gson.fromJson(stringResult, RegionResponse.class);
        return serializedResponse;
    }

    /**
     * Retrieves UK cases by date reported.
     * @return
     */
    public static UkCovidApiResponse getCasesByDateReported(){

        HttpUrl.Builder urlBuilder = HttpUrl.parse(UK_COVID_API_EP).newBuilder();

        urlBuilder.addEncodedQueryParameter("filters", encodeValue("areaType=nation;areaName=england"));
        urlBuilder.addEncodedQueryParameter("structure", encodeValue("{\"date\":\"date\",\"name\":\"areaName\",\"areaType\":\"areaType\",\"code\":\"areaCode\",\"cases\":{\"daily\":\"newCasesByPublishDate\",\"cumulative\":\"cumCasesByPublishDate\"},\"deaths\":{\"daily\":\"newDeathsByDeathDate\",\"cumulative\":\"cumDeathsByDeathDate\"}}"));


        String url = urlBuilder.build().toString();
        String stringResult = getResponse(url);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        UkCovidApiResponse serializedResponse = gson.fromJson(stringResult, UkCovidApiResponse.class);
        return serializedResponse;
    }

    private static String getResponse(String url){
        Request request = new Request.Builder()
                .header("Accepts", "application/json")
                .header("Accept-Encoding", "gzip")
                .url(url)
                .build();

        Response response = null;
        String stringResult = null;

        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            MugLogger.log(e);
        }

        if(response == null || !response.isSuccessful()){
                MugLogger.log(String.format("Unsuccessful response! {code=%d, message=%s, url=%s}", response.code(), response.message(), url));
            return null;
        }

        try {

            GzipSource responseBodySource = new GzipSource(response.body().source());
            stringResult = Okio.buffer(responseBodySource).readUtf8();

        } catch (IOException e) {
            e.printStackTrace();
            MugLogger.log(e);
        }

        return stringResult;
    }

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
}
