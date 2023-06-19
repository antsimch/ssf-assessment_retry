package vttp2023.batch3.ssf.frontcontroller.model;

import jakarta.validation.constraints.Size;

public class User {
    
    @Size(min = 2, message = "Username cannot be less than 2 characters in length")
    private String username;

    @Size(min = 2, message = "Password cannot be less than 2 characters in length")
    private String password;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
