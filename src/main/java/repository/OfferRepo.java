package repository;

import Connection.SessionFactorySingleton;
import entity.Offer;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import java.util.List;

public class OfferRepo extends CRUDRepository<Offer> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public Offer findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Offer as a where a.id = :id", Offer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Offer> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Offer as a", Offer.class);
        return query.list();
    }

    public List<Offer> findByOrderId(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from Offer as a where a.order.id = :id", Offer.class);
        query.setParameter("id", id);
        return query.list();
    }
}
