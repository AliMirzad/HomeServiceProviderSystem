package service;

import Connection.SessionFactorySingleton;
import entity.CustomerUser;
import entity.Order;
import entity.enums.OrderStatus;
import exception.LogicException;
import org.hibernate.SessionFactory;
import repository.OrderRepository;
import service.baseService.CRUDService;

import java.time.LocalDateTime;
import java.util.List;

public class OrderService extends CRUDService<Order> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final OrderRepository orderRepository = new OrderRepository();
    private final CustomerUserService customerUserService = new CustomerUserService();

    @Override
    public Order save(Order order) {
        try {
            if (order.getCustomerUser() == null) {
                throw new LogicException("we can't add order without Customer set");
            }
            if (order.getOrderRegisterTime().isBefore(LocalDateTime.now())) {
                throw new LogicException("your order must be after this time");
            }
            if (order.getAddress() == null || order.getAddress().isEmpty()) {
                throw new LogicException("you must have address");
            }
            if (order.getOfferPrice() <= order.getService().getBasePrice()) {
                throw new LogicException("offer price must be more or equal to service base price");
            }
            if (order.getDescription() == null || order.getDescription().isEmpty()) {
                throw new LogicException("offer description can't be null or empty");
            }
            order.setStatus(OrderStatus.new_req);
            try (var session = sessionFactory.getCurrentSession()){
                var transaction = session.getTransaction();
                try {
                    transaction.begin();
                    orderRepository.save(order);
                    transaction.commit();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public Order findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return orderRepository.findById(id);
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
                return orderRepository.findAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
