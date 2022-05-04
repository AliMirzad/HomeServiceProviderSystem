package repository;

import Connection.SessionFactorySingleton;
import entity.SysService;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import java.util.List;

public class SysServiceRepo extends CRUDRepository<SysService> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public SysService findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from SysService as a where a.id = :id", SysService.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<SysService> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from SysService as a", SysService.class);
        return query.list();
    }
}
