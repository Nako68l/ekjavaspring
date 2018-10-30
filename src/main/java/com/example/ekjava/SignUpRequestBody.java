package com.example.ekjava;


import java.time.LocalDate;

public class SignUpRequestBody {
    private String name;
    private String login;
    private String password;
    private String confirmationPassword;
    private LocalDate birthday;

    public SignUpRequestBody(String name, String login, String password, String confirmationPassword, LocalDate birthday) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
        this.birthday = birthday;
    }

    public SignUpRequestBody(){}

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
