package com.example.ekjava;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("user-util-mock")
@Configuration
public class ConfigureUserUtilMockTest {

    @Bean
    @Primary
    public UserUtil mockUserUtil(){
        return Mockito.mock(UserUtil.class);
    }
}
