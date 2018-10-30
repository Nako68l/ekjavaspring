package com.example.ekjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserUtil userUtil;

    @GetMapping("/")
    public @ResponseBody String helloWorld() {
        System.out.println("HIIII");
        return "Hello World!";
    }

    @PostMapping("/api/sign-up")
    public @ResponseBody Long signUp(@RequestBody SignUpRequestBody reqBody) {
        if (reqBody.getPassword().equals(reqBody.getConfirmationPassword())){
        return this.userUtil.createUser(reqBody.getName(), reqBody.getLogin(), reqBody.getPassword(), reqBody.getBirthday());
        }else {
            throw new Error("Passwords does not match");
        }
    }

    @PostMapping("/api/login")
    public @ResponseBody User logIn(@RequestBody LogInRequestBody reqBody) {
        return this.userUtil.logInUser(reqBody.getLogin(), reqBody.getPassword());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(Error.class)
    public @ResponseBody String exceptionHandler() {
        return "Wrong request params";
    }
}
