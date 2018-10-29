package com.example.ekjava;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {
    private HashMap<Long,User> users = new HashMap<>();


    public User findById(Long id) {
        return users.get(id);
    }

    public User findByEmail(String email) {
        Optional<Map.Entry<Long, User>> correctUserOptional = users.entrySet().stream()
                .filter(userEntry -> userEntry.getValue().getEmail().equals(email))
                .findFirst();
        return correctUserOptional.get().getValue();
    }

    public Long create(String name, String email, String password, LocalDate birthday) {
        User user = new User(name, email, password, birthday);
        Long id = new Date().getTime();
        users.put(id, user);
        return id;
    }

}
