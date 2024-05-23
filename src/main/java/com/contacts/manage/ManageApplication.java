package com.contacts.manage;

import com.contacts.manage.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class ManageApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
