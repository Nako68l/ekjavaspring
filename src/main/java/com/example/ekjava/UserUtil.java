package com.example.ekjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.function.Predicate;

@Service
public class UserUtil {
    private UserRepository userRepository;

    private static Predicate<String> nameValidator = (String name) -> name.length() > 0;
    private static Predicate<String> emailValidator = (String email) -> email.matches("^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
    private static Predicate<String> passwordValidator = (String pass) -> pass.matches("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[#?!@$%^&*-])[a-zA-Z0-9#?!@$%^&*-]{5,10}$");

    public UserUtil(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(String name, String email, String password, LocalDate birthday) {
        name = validate(name, nameValidator);
        email = validate(email, emailValidator);
        password = validate(password, passwordValidator);
        return this.userRepository.create(name, email, password, birthday);
    }

    public User getUser(Long id) {
        return this.userRepository.findById(id);
    }

    public User getUserByLogin(String login) throws Error{
        return this.userRepository.findByEmail(login);
    }

    public User logInUser(String login, String password) throws Error{
        User user = this.getUserByLogin(login);
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new Error("Wrong password");
        }
    }


    private static <T> T validate(T value, Predicate<T> validator) {
        if (!validator.test(value)) {
            throw new Error("Wrong user parameter");
        }
        return value;
    }
}
