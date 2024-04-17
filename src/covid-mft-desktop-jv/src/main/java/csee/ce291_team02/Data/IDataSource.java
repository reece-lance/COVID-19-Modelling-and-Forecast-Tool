package csee.ce291_team02.Data;

import java.util.List;

public interface IDataSource<T> {
    List<T> getData();
    void deleteCache();
    boolean hasData() throws Exception;
}
