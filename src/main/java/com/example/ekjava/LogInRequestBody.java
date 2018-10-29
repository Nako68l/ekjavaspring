package com.example.ekjava;

public class LogInRequestBody {
    private String login;
    private String password;

    public LogInRequestBody(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
