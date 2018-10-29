package com.example.ekjava;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

@Profile("user-util-mock")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    private String correctEmail = "Toxa23A@gmail.com";
    private String correctName = "Antony";
    private String correctPassword = "absd#123";
    private LocalDate correctBirthday = LocalDate.of(2000,5,23);

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserUtil userUtil;

    @Before
    public void beforeSetup(){
        Mockito.when(userUtil.createUser(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.any()))
                .thenReturn(1L);
        Mockito.when(userUtil.getUserByLogin(Mockito.anyString()))
                .thenReturn(new User(correctName, correctPassword, correctPassword, correctBirthday));
    }

    @Test
    public void signUpTest() {
        ResponseEntity<Long> response = restTemplate.exchange(
                "/api/sign-up",
                HttpMethod.POST,
                new HttpEntity<>(
                        new SignUpRequestBody(correctName, correctPassword, correctPassword, correctPassword, correctBirthday)),
                Long.class);

        assertEquals(response.getBody(), (Long) 1L);
    }

    @Test
    public void signUpDifferentPassTest(){
        String wrongPassword = "notpass#31";

        ResponseEntity<Long> response = restTemplate.exchange(
                "/api/sign-up",
                HttpMethod.POST,
                new HttpEntity<>(
                        new SignUpRequestBody(correctName, correctPassword, correctPassword, wrongPassword, correctBirthday)),
                Long.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void logInCorrectParamsTest(){
        ResponseEntity<User> response = restTemplate.exchange(
                "/api/login",
                HttpMethod.POST,
                new HttpEntity<>(
                        new LogInRequestBody(correctEmail, correctPassword)),
                User.class);

        assertEquals(response.getBody().getEmail(),correctEmail);
        assertEquals(response.getBody().getPassword(),correctPassword);
    }

    @Test
    public void logInWrongPasswordTest(){
        String wrongPassword = "blabla#12";

        ResponseEntity<User> response = restTemplate.exchange(
                "/api/login",
                HttpMethod.POST,
                new HttpEntity<>(
                        new LogInRequestBody(correctEmail, wrongPassword)),
                User.class);

        assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void logInWrongEmailTest(){
        String wrongEmail = "Blabla@bla.bla";

        ResponseEntity<User> response = restTemplate.exchange(
                "/api/login",
                HttpMethod.POST,
                new HttpEntity<>(
                        new LogInRequestBody(wrongEmail, correctPassword)),
                User.class);

        assertEquals(response.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
