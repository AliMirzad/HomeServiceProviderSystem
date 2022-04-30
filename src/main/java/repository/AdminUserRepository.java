package repository;

import Connection.SessionFactorySingleton;
import entity.AdminUser;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import java.util.List;

public class AdminUserRepository extends CRUDRepository<AdminUser> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public AdminUser findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from AdminUser as a where a.id = :id", AdminUser.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<AdminUser> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from AdminUser as a", AdminUser.class);
        return query.list();
    }
}
