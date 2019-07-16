package ipt.ipg.app;

public class UserObject {

    private String username;
    private String email;
    private String password;
    private boolean loginOption;

    public UserObject(String username, String email, String password, boolean loginOption) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.loginOption = loginOption;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoginOption() {
        return loginOption;
    }
}
