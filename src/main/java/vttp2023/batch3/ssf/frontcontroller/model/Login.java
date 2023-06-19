package vttp2023.batch3.ssf.frontcontroller.model;

import java.io.Serializable;

public class Login implements Serializable {

    private User user;
    
    private boolean authenticated;

    private int loginAttempts;

    public Login() {
        this.authenticated = false;
        this.loginAttempts = 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String getUserUsername() {
        return user.getUsername();
    }

    public void setUserUsername(String username) {
        this.user.setUsername(username);
    }

    public String getUserPassword() {
        return user.getPassword();
    }

    public void setUserPassword(String password) {
        this.user.setPassword(password);
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }
}
