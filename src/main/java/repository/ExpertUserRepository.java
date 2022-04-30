package repository;

import Connection.SessionFactorySingleton;
import entity.ExpertUser;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import java.util.List;

public class ExpertUserRepository extends CRUDRepository<ExpertUser> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public ExpertUser findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from ExpertUser as a where a.id = :id", ExpertUser.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<ExpertUser> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from ExpertUser as a", ExpertUser.class);
        return query.list();
    }
}
