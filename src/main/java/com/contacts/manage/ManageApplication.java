package com.contacts.manage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class ManageApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(ManageApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started");
        logger.error("Application started successfully");
    }

}
