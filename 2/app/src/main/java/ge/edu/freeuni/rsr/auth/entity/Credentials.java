package ge.edu.freeuni.rsr.auth.entity;

public class Credentials {
    private String user_name;
    private String email;
    private String password;
    private String c_password;
    private Integer chat_user_id;

    public Credentials(String user_name, String email, String password, String c_password, Integer chat_user_id) {
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.c_password = c_password;
        this.chat_user_id = chat_user_id;
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

    public Integer getChat_user_id() {
        return chat_user_id;
    }
}
