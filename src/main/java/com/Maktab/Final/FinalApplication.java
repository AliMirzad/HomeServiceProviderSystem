package com.Maktab.Final;

import com.Maktab.Final.entity.Admin;

import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Order;
import com.Maktab.Final.service.CustomerServ;
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
    static
    class OnStartup implements ApplicationRunner {
        CustomerServ customerServ;

        public OnStartup(CustomerServ customerServ) {
            this.customerServ = customerServ;
        }

        @Override
        public void run(ApplicationArguments args) {
            System.out.println("successful");
            System.out.println(customerServ.findCustomerById(1));
        }
    }

}
