package me.supcheg.carcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class CarControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarControlApplication.class, args);
    }

}
