package ge.edu.freeuni.rsr.individual.game.entity;

import java.util.List;

public class CorrectAnswers {
    private boolean correct;
    private List<Answer> answers;

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
