package service;

import Connection.SessionFactorySingleton;
import entity.*;
import entity.enums.OrderStatus;
import entity.enums.ServiceModel;
import exception.LogicException;
import org.hibernate.SessionFactory;
import repository.CustomerRepo;
import service.baseService.CRUDService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class CustomerServ extends CRUDService<Customer> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final CustomerRepo customerRepo = new CustomerRepo();
    private final WalletServ walletServ = new WalletServ();
    private final OrderServ orderServ = new OrderServ();

    @Override
    public Customer save(Customer customer) {
        try {
            Wallet wallet = Wallet.builder().balance(100D).build();
            walletServ.save(wallet);
            customer.setWallet(wallet);
            if (customer.getEmail().isEmpty() || customer.getEmail() == null) {
                throw new LogicException("we can't add customer without email");
            }
            for (Customer c :
                    findAll()) {
                if (customer.getEmail().equals(c.getEmail())) {
                    throw new LogicException("this email used before");
                }
            }
            return super.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    public void updatePassword(Integer id, String password) {
        try {
            Customer customer = findById(id);
            customer.setPassword(password);
            super.update(customer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    public void saveOrder(Integer id, Order order) {
        try {
            Customer customer = findById(id);
            if (customer == null) {
                throw new LogicException("we can't add order without Customer set");
            }
            if (order.getAddress() == null || order.getAddress().isEmpty()) {
                throw new LogicException("you must have address");
            }
            if (order.getDescription() == null || order.getDescription().isEmpty()) {
                throw new LogicException("order description can't be null or empty");
            }
            order.setOrderRegisterTime(LocalDateTime.now());
            order.setStatus(OrderStatus.new_req);
            Set<Order> orders = customer.getOrders();
            orders.add(order);
            customer.setOrders(orders);
            orderServ.save(order);
            super.update(customer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    public List<Customer> findByParameters(String firstName, String lastName,
                                           String nationalCode, String email,
                                           LocalDateTime registerTimeDate) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return customerRepo.findByParameters(firstName,
                        lastName,
                        nationalCode,
                        email,
                        registerTimeDate);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("-----------------------------------------------------");
                System.out.println(e.getMessage());
                System.out.println("-----------------------------------------------------");
                return null;
            }
        }
    }

    public Customer findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return customerRepo.findById(id);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("-----------------------------------------------------");
                System.out.println(e.getMessage());
                System.out.println("-----------------------------------------------------");
                return null;
            }
        }
    }

    public List<Customer> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return customerRepo.findAll();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("-----------------------------------------------------");
                System.out.println(e.getMessage());
                System.out.println("-----------------------------------------------------");
                return null;
            }
        }
    }

}
