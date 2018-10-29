package com.example.ekjava;

import java.time.LocalDate;
import java.util.function.Predicate;


public class UserUtil {
    private UserRepository userRepository;

    private static Predicate<String> nameValidator = (String name) -> name.length() > 0;
    private static Predicate<String> emailValidator = (String email) -> email.matches("^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
    private static Predicate<String> passwordValidator = (String pass) -> pass.matches("^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[#?!@$%^&*-])[a-zA-Z0-9#?!@$%^&*-]{5,10}$");

    public UserUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(String name, String email, String password, LocalDate birthday) {
        name = validate(name, nameValidator);
        email = validate(email, emailValidator);
        password = validate(password, passwordValidator);
        return this.userRepository.create(name, email, password, birthday);
    }

    public User getUser(Long id){
        return this.userRepository.findById(id);
    }

    public User getUserByLogin(String login){
        return this.userRepository.findByEmail(login);
    }

    private static <T> T validate(T value, Predicate<T> validator) {
        if (!validator.test(value)) {
            throw new Error("Wrong user parameter");
        }
        return value;
    }
}
