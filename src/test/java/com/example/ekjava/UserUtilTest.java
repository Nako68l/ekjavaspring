package com.example.ekjava;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserUtilTest {
    private UserRepository userRepositoryMock;
    private UserUtil userUtil;
    private String correctEmail = "Toxa23A@gmail.com";
    private String correctName = "Antony";
    private String correctPass = "absd#123";
    private LocalDate correctBirthday = LocalDate.of(2000,5,23);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup(){
        this.userRepositoryMock = Mockito.mock(UserRepository.class);
        Mockito.when(userRepositoryMock.create(correctName, correctEmail, correctPass, correctBirthday))
                .thenReturn(1L);
        Mockito.when(userRepositoryMock.findById(1L))
                .thenReturn(new User(correctName, correctEmail, correctPass, correctBirthday));

        this.userUtil = new UserUtil(userRepositoryMock);

    }

    @Test
    public void createUser() {
        long firstUserId = userUtil.createUser(correctName, correctEmail, correctPass, correctBirthday);
        assertEquals(firstUserId, 1L);
        User firstUser = userUtil.getUser(firstUserId);
        assertEquals(firstUser.getEmail(), correctEmail);
    }

    @Test
    public void emailValidation(){
        thrown.expect(Error.class);
        thrown.expectMessage("Wrong user parameter");
        String wrongEmail = "Toxa23A@gmail.com@";
        userUtil.createUser(correctName, wrongEmail, correctPass, correctBirthday);
    }

    @Test
    public void nameValidation(){
        thrown.expect(Error.class);
        thrown.expectMessage("Wrong user parameter");
        String wrongName = "";
        userUtil.createUser(wrongName, correctEmail, correctPass, correctBirthday);
    }

    //TODO: Why if there is no error, test still pass. Why expectMessage doesn't check message content
    @Test
    public void passValidation(){
        thrown.expect(Error.class);
        thrown.expectMessage("Wrong user parameter");
        String wrongNPass= "absd123";
        userUtil.createUser(correctName, correctEmail, wrongNPass, correctBirthday);
    }
}