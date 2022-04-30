package service;

import Connection.SessionFactorySingleton;
import entity.CustomerUser;
import exception.LogicException;
import org.hibernate.SessionFactory;
import repository.CustomerUserRepository;
import service.baseService.CRUDService;

import java.util.List;

public class CustomerUserService extends CRUDService<CustomerUser> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final CustomerUserRepository customerUserRepository = new CustomerUserRepository();

    @Override
    public CustomerUser save(CustomerUser customerUser) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                List<CustomerUser> customerUsers = customerUserRepository.findAll();
                for (CustomerUser c :
                        customerUsers) {
                    if (customerUser.getEmail().equals(c.getEmail())) {
                        throw new LogicException("this email address used before for a account");
                    }
                }
                customerUserRepository.save(customerUser);
                transaction.commit();
                return customerUser;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                transaction.rollback();
                return null;
            }
        }
    }

    public CustomerUser updatePassword(CustomerUser customerUser) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                CustomerUser customerUser1 = customerUserRepository.findById(customerUser.getId());
                customerUser1.setPassword(customerUser.getPassword());
                customerUserRepository.update(customerUser1);
                transaction.commit();
                return customerUser;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                transaction.rollback();
                return null;
            }
        }
    }
}
