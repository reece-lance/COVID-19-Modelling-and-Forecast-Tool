package csee.ce291_team02.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import csee.ce291_team02.AppConstants;
import csee.ce291_team02.MugLogger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.GzipSource;
import okio.Okio;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static csee.ce291_team02.AppConstants.Paths.resolvePath;
import static csee.ce291_team02.AppConstants.Properties.DATE_TIME_FORMATTER;

/**
 * Retrieves online data regarding the knot dates of covid-19 cases graph.
 * In order for these values to stay up to date, they are kept online in
 * a github repository and are being periodically updated.
 */
public class CurrentKnotsRetriever {
    private static final IApiClientFunction<String> currentKnotDatesFetcher = () -> fetchCurrentKnotDates();

    private static final int cashDuration = 1;
    private static IDataSource<String> knotDatesDataSupplier =
            new DataSource<>(resolvePath(AppConstants.Paths.CachedFiles.CURRENT_KNOT_DATES),
                    currentKnotDatesFetcher,
                    new TypeToken<ArrayList<String>>(){}.getType(),
                    cashDuration);

    private static final OkHttpClient client = new OkHttpClient();

    /**
     * Cleans the cashed knot dates.
     */
    public static void cleanseCash(){
        knotDatesDataSupplier.deleteCache();
    }

    /**
     * Tries to fetch the current knot dates, if it fails, it will retrieve the default ones.
     * @return
     */
    public static List<LocalDate> getCurrentKnotDates(){

        if(!AppConstants.Properties.ONLINE_KNOT_RETRIEVAL_ENABLED){
            return AppConstants.Properties.DEFAULT_KNOT_DATES;
        }

        List<String> kd = knotDatesDataSupplier.getData();

        if(kd == null || kd.isEmpty()){
            return AppConstants.Properties.DEFAULT_KNOT_DATES;
        }
        List<LocalDate> result = new ArrayList<>();

        for (String date: kd){
            result.add(LocalDate.parse(date, DATE_TIME_FORMATTER));
        }

        result.add(LocalDate.now());

        return result;
    }

    private static List<String> fetchCurrentKnotDates(){
        Request request = new Request.Builder()
                .header("Accepts", "application/json")
                .header("Accept-Encoding", "gzip")
                .url(AppConstants.Properties.CURRENT_KNOTS_URL)
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
            MugLogger.log(String.format("Unsuccessful response! {code=%d, url=%s, message=%s}", response.code(), AppConstants.Properties.CURRENT_KNOTS_URL, response.message()));
            return null;
        }

        try {
            GzipSource responseBodySource = new GzipSource(response.body().source());
            stringResult = Okio.buffer(responseBodySource).readUtf8();

        } catch (IOException e) {
            MugLogger.log(e);
        }
        MugLogger.log("Fetched knots:");
        MugLogger.log(stringResult);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        KnotFile kd = gson.fromJson(stringResult, KnotFile.class);
        return kd.knotDates;
    }

    class KnotFile{
        @SerializedName("knotDates")
        @Expose
        List<String> knotDates;
    }
}
