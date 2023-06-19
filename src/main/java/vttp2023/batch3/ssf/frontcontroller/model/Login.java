package vttp2023.batch3.ssf.frontcontroller.model;

public class Login {
    
    private User user;

    private boolean authenticated;

    public Login() {
    }

    public Login(User user, boolean authenticated) {
        this.user = user;
        this.authenticated = authenticated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
