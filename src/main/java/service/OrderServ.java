package service;

import Connection.SessionFactorySingleton;
import entity.Order;
import org.hibernate.SessionFactory;
import repository.OrderRepo;
import service.baseService.CRUDService;

import java.util.List;

public class OrderServ extends CRUDService<Order> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final OrderRepo orderRepo = new OrderRepo();

    public Order findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return orderRepo.findById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public List<Order> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return orderRepo.findAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
