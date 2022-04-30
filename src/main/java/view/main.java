package view;

import Connection.SessionFactorySingleton;
import entity.CustomerUser;
import org.hibernate.SessionFactory;
import repository.CustomerUserRepository;
import service.CustomerUserService;

public class main {
    public static void main(String[] args) {
        CustomerUserService customerUserService = new CustomerUserService();

        //save customer
        /*SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
        CustomerUser customerUser = CustomerUser.builder().id(null).password("123").nationalCode("4490434").email("alvinmirzad").build();
        customerUserService.save(customerUser);*/

        //update
        /*CustomerUser customerUser1 = CustomerUser.builder().id(1).password("123").build();
        customerUserService.updatePassword(customerUser1);*/
    }
}
