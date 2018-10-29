package com.example.ekjava;

import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;

public class SignUpRequestBody {
    String name;
    String login;
    String password;
    private String confirmationPassword;
    LocalDate birthday;

    public SignUpRequestBody(String name, String login, String password, String confirmationPassword, LocalDate birthday) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.confirmationPassword = confirmationPassword;
        this.birthday = birthday;
    }

    private boolean passwordsAreEqual(String password, String confirmationPassword) {
        return password.equals(confirmationPassword);
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        if (passwordsAreEqual(password, confirmationPassword)) {
            return password;
        } else {
            throw new Error("passwords are not equal");
        }
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
