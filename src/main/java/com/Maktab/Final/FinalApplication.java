package com.Maktab.Final;

import com.Maktab.Final.entity.Admin;

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
    class OnStartup implements ApplicationRunner {



        @Override
        public void run(ApplicationArguments args) {
            System.out.println("successful");
        }
    }

}
