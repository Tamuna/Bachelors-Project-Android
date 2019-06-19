package ge.edu.freeuni.rsr.individual.game.entity;


public class IndGameResponse {
    private String error;
    private Result result;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
