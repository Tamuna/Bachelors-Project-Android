package ge.edu.freeuni.rsr.auth.entity;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private Integer id;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("first_name")
    private Object firstName;

    @SerializedName("last_name")
    private Object lastName;

    @SerializedName("birth_date")
    private Object birthDate;

    @SerializedName("profile_picture")
    private Object profilePicture;

    @SerializedName("chat_user_id")
    private Integer chatUserId;

    @SerializedName("email")
    private String email;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Object getFirstName() {
        return firstName;
    }

    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public Object getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Object birthDate) {
        this.birthDate = birthDate;
    }

    public Object getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Object profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Integer getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(Integer chatUserId) {
        this.chatUserId = chatUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}