import Connection.SessionFactorySingleton;
import entity.*;
import entity.enums.ServiceModel;
import org.hibernate.SessionFactory;
import service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
        AdminServ adminServ = new AdminServ();
        ExpertServ expertServ = new ExpertServ();
        SysServiceServ sysServiceServ = new SysServiceServ();
        CustomerServ customerServ = new CustomerServ();
        CommentServ commentServ = new CommentServ();
        SysTransactionServ sysTransactionServ = new SysTransactionServ();

        //admin crud and grid search
//        Admin admin = Admin.builder().firstName("admin").email("admin").build();
//        adminServ.save(admin);
//        admin.setId(5); //input id from table
//        admin.setLastName("admin");
//        adminServ.update(admin);
//        adminServ.delete(admin);
//        adminServ.findByParameters("admin","","","",null).forEach(System.out::println);

        //service crud
//        SysService service = SysService.builder().name("sakhteman").model(ServiceModel.main).build();
//        SysService service1 = SysService.builder().id(2).build();
//        SysService service2 = SysService.builder().basePrice(150D).name("bil").model(ServiceModel.sub).parentService(service1).build();
//        sysServiceServ.save(service);
//        sysServiceServ.save(service2);
//        Integer id = 1;
//        sysServiceServ.deleteById(id);
//        Set<SysService> services = new HashSet<>();
//        services.add(service1);

        //expert crud - save and delete logic
//        Expert expert = Expert.builder().firstName("expert").lastName("expert").email("expert2").build();
//        Expert expert2 = Expert.builder().id(1).password("lfk").build();
//        expertServ.save(expert);
//        expertServ.saveService(1, 2);
//        expertServ.updateInformation(1, "444333", "ali", "asqar");
//        expertServ.delete(expert2);
//        expertServ.updatePassword(1, "4dskfjglh");

        //Customer crud - save and some more methods
//        Customer customer = Customer.builder().firstName("customer").lastName("customer").email("customer").build();
//        customerServ.save(customer);
//        customerServ.updatePassword(1, "mamadi");
//        customerServ.findByParameters("customer", null, null, null, null);

        //order
//        String dateTime = "2023-06-03 20:19";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        Order order = Order.builder().address("fkadlhs").description("aflhsf").orderRegisterTime(LocalDateTime.parse(dateTime, formatter)).offerPrice(200D).build();
//        customerServ.saveOrder(1, order);

        //offer
//        String dateTime = "2023-06-03 20:19";
//        String dateTime2 = "2023-08-03 20:19";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        Offer offer = Offer.builder().offerPrice(300D).startTime(LocalDateTime.parse(dateTime, formatter)).endTime(LocalDateTime.parse(dateTime2, formatter)).build();
//        expertServ.saveOffer(1, 1, offer);

        //comment
//        Comment comment = Comment.builder().comment("ali").point(1).build();
//        commentServ.saveComment(1,1,comment);

        //transaction
//        sysTransactionServ.payForOrder(1,1,1,1);

    }

}
