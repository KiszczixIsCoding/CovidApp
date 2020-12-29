package pl.emb.covidsupport.global;

/***
 * Class for connection between ViewModel and Repository.
 * Result.Success - response body
 * Result.Error - response error
 * @param <T>
 */
public abstract class Result<T> {
    private Result() {}

    public static final class Success<T> extends Result<T> {
        public T data;

        public Success(T data) {
            this.data = data;
        }
    }

    public static final class Error<T> extends Result<T> {
        public Exception exception;

        public Error(Exception exception) {
            this.exception = exception;
        }
    }
}