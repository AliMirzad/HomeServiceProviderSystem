package repository;

import Connection.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import java.util.List;

public class SysTransaction extends CRUDRepository<SysTransaction> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public SysTransaction findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from SysTransaction as a where a.id = :id", SysTransaction.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<SysTransaction> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from SysTransaction as a", SysTransaction.class);
        return query.list();
    }
}
