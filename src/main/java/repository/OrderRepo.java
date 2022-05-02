package repository;

import Connection.SessionFactorySingleton;
import entity.Order;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import java.util.List;

public class OrderRepo extends CRUDRepository<Order> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public Order findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Order as a where a.id = :id", Order.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<Order> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Order as a", Order.class);
        return query.list();
    }

    public List<Order> findByCustomerId(Integer customerId) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Order as a where a.customerUser.id = :id", Order.class);
        query.setParameter("id", customerId);
        return query.list();
    }
}
