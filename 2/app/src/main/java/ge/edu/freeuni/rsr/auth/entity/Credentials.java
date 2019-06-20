package ge.edu.freeuni.rsr.auth.entity;

public class Credentials {
    private String user_name;
    private String email;
    private String password;
    private String c_password;
    private Integer chat_id;

    public Credentials(String user_name, String email, String password, String c_password) {
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.c_password = c_password;
    }

    public Credentials(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    public Credentials(Integer chat_id) {
        this.chat_id = chat_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getC_password() {
        return c_password;
    }

    public Integer getChat_id() {
        return chat_id;
    }

    public void setChat_id(Integer chat_id) {
        this.chat_id = chat_id;
    }
}
