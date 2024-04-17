package csee.ce291_team02.MugExceptions;

public class MugInvalidCacheException extends Exception {
    public MugInvalidCacheException() {
    }

    public MugInvalidCacheException(String message) {
        super(message);
    }

    public MugInvalidCacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public MugInvalidCacheException(Throwable cause) {
        super(cause);
    }

    public MugInvalidCacheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
