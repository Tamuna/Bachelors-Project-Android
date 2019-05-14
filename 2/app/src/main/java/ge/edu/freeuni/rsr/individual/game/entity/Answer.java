package ge.edu.freeuni.rsr.individual.game.entity;

import com.google.gson.annotations.SerializedName;

public class Answer {
    int id;
    @SerializedName("question_id")
    int questionId;
    String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
