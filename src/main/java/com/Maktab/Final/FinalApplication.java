package com.Maktab.Final;

import com.Maktab.Final.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class FinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalApplication.class, args);
    }

    @Component
    static class OnStartup implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) {
        }
    }
}
