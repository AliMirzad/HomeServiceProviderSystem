package repository;

import Connection.SessionFactorySingleton;
import entity.Wallet;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import java.util.List;

public class WalletRepository extends CRUDRepository<Wallet> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public Wallet findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Wallet as a where a.id = :id", Wallet.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<Wallet> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Wallet as a", Wallet.class);
        return query.list();
    }


}
