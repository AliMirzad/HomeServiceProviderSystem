package com.Maktab.Final;

import com.Maktab.Final.entity.*;
import com.Maktab.Final.service.AdminServ;
import com.Maktab.Final.service.CustomerServ;
import com.Maktab.Final.service.ExpertServ;
import com.Maktab.Final.service.OfferServ;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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
        private final OfferServ offerServ;


        public OnStartup(CustomerServ customerServ, ExpertServ expertServ, AdminServ adminServ, OfferServ offerServ) {
            this.customerServ = customerServ;
            this.expertServ = expertServ;
            this.adminServ = adminServ;
            this.offerServ = offerServ;
        }

        @Override
        public void run(ApplicationArguments args) {
            //needed items
            Services services = Services.builder().id(null).name("tasisat").build();
            SubService subService = SubService.builder().id(null).basePrice(1100D).name("taviz lmap").description("lampo avaz mikonan").services(services).build();
            Customer customer = Customer.builder().id(null).email("ali@").firstName("ali").lastName("mirzad").nationalCode("449444").password("449444").build();
            Expert expert = Expert.builder().id(null).email("mamad@").firstName("mamad").lastName("mirzad").nationalCode("449443").password("449443").build();


//            expertServ.createExpert(expert);
            subService.setId(1);
            expert.setId(1);
//            adminServ.createService(services);
//            adminServ.createSubService(subService, services.getId());
//            expertServ.saveSubService(expert.getId(), subService.getId());
//            customerServ.createCustomer(customer);

            //1-save customer order
            Order order = Order.builder().id(null).subService(subService).address("ilam").description("lamp avazi").suggestPrice(1200D).build();
            customer.setId(2);
//            customerServ.saveOrder(customer.getId(), order);

            //2-save offer for order
            order.setId(1);
            String dateTime = "2023-06-03 20:19";
            String dateTime2 = "2024-06-03 20:19";
            String dateTime3 = "2025-06-03 20:19";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Offer offer = Offer.builder().id(null).offerPrice(1300D).order(order).startTime(LocalDateTime.parse(dateTime2, formatter)).endTime(LocalDateTime.parse(dateTime3, formatter)).build();
//            expertServ.saveOffer(1, offer);
            offer.setId(1);


            //3-select offer for customer order
//            customerServ.chooseOfferForOrder(3, 1);


            //4-select offers of order with sort param
//            offerServ.findOfferByOrderWithSort(order, "offerPrice").forEach(System.out::println);
//            System.out.println("------------------");
//            offerServ.findOfferByOrderWithSort(order, "expert_point").forEach(System.out::println);


            //paging sorting filtering customer
//            CustomerPage customerPage = new CustomerPage();
//            CustomerSearchCriteria customerSearchCriteria = new CustomerSearchCriteria();
//            customerSearchCriteria.setLastName("mirzad");
//            customerPage.setPageSize(5);
//            customerServ.getCustomers(customerPage, customerSearchCriteria).forEach(System.out::println);

            //paging sorting filtering expert
//            ExpertPage expertPage = new ExpertPage();
//            ExpertSearchCriteria expertSearchCriteria = new ExpertSearchCriteria();
//            expertSearchCriteria.setFirstName("mamad");
//            expertPage.setPageSize(5);
//            expertServ.getExpert(expertPage, expertSearchCriteria).forEach(System.out::println);
        }
    }
}
