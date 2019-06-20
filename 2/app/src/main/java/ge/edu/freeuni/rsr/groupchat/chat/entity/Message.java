package ge.edu.freeuni.rsr.groupchat.chat.entity;

/*
 * created by tgeldiashvili on 6/6/2019
 */

import ge.edu.freeuni.rsr.common.entity.User;

public class Message {
    private User sender;
    private String message;
    private boolean isQuestion;
    private boolean isAnswer;

    public Message(User sender, String message, boolean isQuestion, boolean isAnswer) {
        this.sender = sender;
        this.message = message;
        this.isQuestion = isQuestion;
        this.isAnswer = isAnswer;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isQuestion() {
        return isQuestion;
    }

    public void setQuestion(boolean question) {
        isQuestion = question;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }
}
