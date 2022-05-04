package repository.baseRepository;

import Connection.SessionFactorySingleton;
import org.hibernate.SessionFactory;

public class CRUDRepository<T> {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public void save(T t) {
        var session = sessionFactory.getCurrentSession();
        session.getTransaction();
        session.save(t);
    }

    public void update(T t) {
        var session = sessionFactory.getCurrentSession();
        session.update(t);
    }

    public void delete(T t) {
        var session = sessionFactory.getCurrentSession();
        session.delete(t);
    }
}
