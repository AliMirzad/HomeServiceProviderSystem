package com.Maktab.Final;

import com.Maktab.Final.entity.*;
import com.Maktab.Final.service.AdminServ;
import com.Maktab.Final.service.CustomerServ;
import com.Maktab.Final.service.ExpertServ;
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
        private final CustomerServ customerServ;
        private final ExpertServ expertServ;
        private final AdminServ adminServ;


        public OnStartup(CustomerServ customerServ, ExpertServ expertServ, AdminServ adminServ) {
            this.customerServ = customerServ;
            this.expertServ = expertServ;
            this.adminServ = adminServ;
        }

        @Override
        public void run(ApplicationArguments args) {
            System.out.println("successful");
            //needed items
            Services services = Services.builder().id(null).name("tasisat").build();
            SubService subService = SubService.builder().id(null).basePrice(1100D).name("taviz lmap").description("lampo avaz mikonan").services(services).build();
            Customer customer = Customer.builder().id(null).email("ali@").firstName("ali").lastName("mirzad").nationalCode("449444").password("449444").build();
            Expert expert = Expert.builder().id(null).email("mamad@").firstName("mamad").lastName("mirzad").nationalCode("449443").password("449443").build();
//
//
//            expertServ.createExpert(expert);
            subService.setId(1);
            expert.setId(1);
//            expertServ.saveSubService(expert.getId(), subService.getId());
//            customerServ.createCustomer(customer);
//            adminServ.createService(services);
//            adminServ.createSubService(subService, services.getId());

            //save Order
            Order order = Order.builder().id(null).subService(subService).address("ilam").description("lamp avazi").suggestPrice(1200D).build();
//            customer.setId(2);
//            customerServ.saveOrder(customer.getId(), order);

            //save offer
            order.setId(1);
            Offer offer = Offer.builder().id(null).offerPrice(1300D).order(order).build();
//            expertServ.saveOffer(1, offer);

            //

            //select offer for customer
//            customerServ.chooseOfferForOrder(1, 1);



        }
    }

}
