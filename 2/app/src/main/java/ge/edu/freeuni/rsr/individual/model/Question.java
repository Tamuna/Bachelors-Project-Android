package ge.edu.freeuni.rsr.individual.model;


import com.google.gson.annotations.SerializedName;

public class Question {
    @SerializedName("id")
    int id;
    @SerializedName("question_content")
    String questionContent;
}
