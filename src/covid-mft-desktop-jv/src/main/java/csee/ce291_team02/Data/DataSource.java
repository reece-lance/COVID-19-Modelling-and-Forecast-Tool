package csee.ce291_team02.Data;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;

public class DataSource<T> extends CachedHandler<T> implements IDataSource<T> {


    private IApiClientFunction<T> apiClientFuction;

    public DataSource(Path cachePath, IApiClientFunction<T> apiClientFuction, Type cacheType){
        super(cachePath, cacheType);
        this.apiClientFuction = apiClientFuction;
    }

    public DataSource(Path cachePath, IApiClientFunction<T> apiClientFuction,  Type cacheType, int cashDuration) {
        super(cachePath, cacheType, cashDuration);
        this.apiClientFuction = apiClientFuction;
    }

    @Override
    public List<T> getData() {
        boolean hasData = false;
        try{
            hasData = hasData();


        } catch (Exception e) {
            e.printStackTrace();
        }

        if (hasData) {
            return getCacheData();
        } else {
            List<T> data = apiClientFuction.getData();
            setCache(data);
            return data;
        }

    }
}
