package com.example.ekjava;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private HashMap<Long, User> users = new HashMap<>();


    public User findById(Long id) {
        return users.get(id);
    }

    public User findByEmail(String email) throws Error{
        Optional<Map.Entry<Long, User>> correctUserOptional = users.entrySet().stream()
                .filter(userEntry -> userEntry.getValue().getEmail().equals(email))
                .findFirst();
        if (correctUserOptional.isPresent()) {
            return correctUserOptional.get().getValue();
        } else {
            throw new Error("User with this login not found");
        }
    }

    public Long create(String name, String email, String password, LocalDate birthday) {
        User user = new User(name, email, password, birthday);
        Long id = new Date().getTime();
        users.put(id, user);
        return id;
    }

}
