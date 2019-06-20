package ge.edu.freeuni.rsr.auth.entity;

public class AuthResponse<T> {
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
