package repository;

import Connection.SessionFactorySingleton;
import entity.Order;
import entity.Service;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import java.util.List;

public class ServiceRepository extends CRUDRepository<Service> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public Service findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Service as a where a.id = :id", Service.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<Service> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Service as a", Service.class);
        return query.list();
    }
}
