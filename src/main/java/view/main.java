package view;

import Connection.SessionFactorySingleton;
import entity.*;
import entity.enums.ExpertUserStatus;
import entity.enums.ServiceModel;
import org.hibernate.SessionFactory;
import repository.CustomerUserRepository;
import service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class main {
    //faqat method hayi ke logic khasi darand add shode dar in qesmat
    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
        CustomerUserService customerUserService = new CustomerUserService();
        AdminUserService adminUserService = new AdminUserService();
        ServiceService serviceService = new ServiceService();
        ExpertUserService expertUserService = new ExpertUserService();
        OrderService orderService = new OrderService();
        OfferService offerService = new OfferService();

        //save customer
        /*CustomerUser customerUser = CustomerUser.builder().id(null).password("123").nationalCode("4490434").email("alvinmirzad").build();
        customerUserService.save(customerUser);*/

        //update customer
        /*CustomerUser customerUser1 = CustomerUser.builder().id(1).password("123").build();
        customerUserService.updatePassword(customerUser1);*/

        //grid search customer
        /*customerUserService.findByParameters("ali","","","",null).forEach(System.out::println);*/

        //save admin
        /* AdminUser adminUser = AdminUser.builder().id(null).password("1233").nationalCode("44904434").email("alv5inmirzad").build();
        adminUserService.save(adminUser);*/

        //findById
        /*System.out.println(adminUserService.findById(1));*/

        //register sub service w/o service and repeated row
        /*Service service = Service.builder().id(null).name("sakhteman").basePrice(200D).model(ServiceModel.main).build();
        Service service1 = Service.builder().id(1).build();
        Service service2 = Service.builder().id(null).name("periz").basePrice(20230D).model(ServiceModel.sub).parentService(service1).build();
        serviceService.save(service);
        serviceService.save(service2);*/

        //expert service check
        /*ExpertUser expertUser = ExpertUser.builder().id(1).email("asqar").firstName("afsasf").password("asfas").nationalCode("asdsad").status(ExpertUserStatus.waiting).build();
        expertUserService.save(expertUser);
        expertUserService.update(expertUser);
        Service service3 = Service.builder().id(2).build();
        expertUserService.updateService(expertUser ,service3);*/

        //save order by logic
        /*Service service = serviceService.findById(2);
        CustomerUser customerUser = CustomerUser.builder().id(1).build();
        String dateTime = "2023-06-03 20:19";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Order order = Order.builder().orderRegisterTime(LocalDateTime.parse(dateTime, formatter)).description("aslfjaslkf").customerUser(customerUser).address("asfkljaskf").offerPrice(540D).service(service).build();
        orderService.saveByLogic(order);*/

        //offer to an order
        /*ExpertUser expertUser = new ExpertUser();
        expertUser.setId(1);
        Order order = Order.builder().id(1).build();
        String dateTime = "2023-06-03 20:19";
        String dateTime2 = "2023-07-03 20:19";
        String dateTime3 = "2022-11-03 20:19";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Offer offer = Offer.builder().id(null).offerPrice(200D).startTime(LocalDateTime.parse(dateTime, formatter)).endTime(LocalDateTime.parse(dateTime2, formatter)).registrationDateTime(LocalDateTime.parse(dateTime3, formatter)).expertUser(expertUser).order(order).build();
        offerService.save(offer);*/



    }
}
