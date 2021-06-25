package com.example.api.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class userConfig {
    @Bean
    CommandLineRunner commandLineRunner(userRepository repository){
        return args -> {
           user hamma= new user(

            "hamma",
            "fataka",
            "teacher",
            "mfataka@utb.cz",
                    12L,
                   LocalDate.of(2000,01,01)

            );
            user milad=new user(
                    "milad",
                    "barzani",
                    "student",
                    "m_barzani@utb,cz",
                    LocalDate.of(2000,01,01)

            );
            repository.saveAll(List.of(hamma,milad));
        };

    }

}
