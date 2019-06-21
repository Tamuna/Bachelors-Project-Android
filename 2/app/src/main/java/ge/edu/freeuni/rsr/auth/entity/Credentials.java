package ge.edu.freeuni.rsr.auth.entity;

public class Credentials {
    private String user_name;
    private String email;
    private String password;
    private String c_password;
    private Integer chat_id;
    private String device_token;

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

    public Credentials(Integer chat_id, String deviceToken) {
        this.chat_id = chat_id;
        this.device_token = deviceToken;
    }
}
