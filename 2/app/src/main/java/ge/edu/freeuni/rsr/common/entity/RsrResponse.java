package ge.edu.freeuni.rsr.common.entity;

public class RsrResponse<T> {
    private String error;
    private T result;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
