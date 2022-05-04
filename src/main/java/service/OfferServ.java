package service;

import Connection.SessionFactorySingleton;
import entity.Offer;
import org.hibernate.SessionFactory;
import repository.OfferRepo;
import service.baseService.CRUDService;

import java.util.List;

public class OfferServ extends CRUDService<Offer> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final OfferRepo offerRepo = new OfferRepo();

    public Offer findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return offerRepo.findById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public List<Offer> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return offerRepo.findAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
