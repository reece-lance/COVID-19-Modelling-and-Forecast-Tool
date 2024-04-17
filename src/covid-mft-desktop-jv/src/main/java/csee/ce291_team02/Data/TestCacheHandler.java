package csee.ce291_team02.Data;

import java.lang.reflect.Type;
import java.nio.file.Path;

/**
 * TestCacheHandler for unit tests.
 * @param <T>
 */
public class TestCacheHandler<T> extends CachedHandler<T> {

    protected TestCacheHandler(Path cachePath, Type cacheType) {
        super(cachePath, cacheType);
    }
}
