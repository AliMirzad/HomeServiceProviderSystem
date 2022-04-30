package repository;

import Connection.SessionFactorySingleton;
import entity.Order;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import java.util.List;

public class OrderRepository extends CRUDRepository<Order> {

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
}
