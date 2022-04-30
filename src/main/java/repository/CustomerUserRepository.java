package repository;

import Connection.SessionFactorySingleton;
import entity.CustomerUser;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import java.util.List;

public class CustomerUserRepository extends CRUDRepository<CustomerUser> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public CustomerUser findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from CustomerUser as a where a.id = :id", CustomerUser.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<CustomerUser> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from CustomerUser as a", CustomerUser.class);
        return query.list();
    }
}
