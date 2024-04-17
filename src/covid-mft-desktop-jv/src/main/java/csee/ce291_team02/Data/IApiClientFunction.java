package csee.ce291_team02.Data;

import java.util.List;

/**
 * Functional interface, enables to declare lambda properties on apiClients for use in DataSources of different types
 * @param <T> Type of retrieved class.
 */
public interface IApiClientFunction<T> {
    List<T> getData();
}
