package service;

import Connection.SessionFactorySingleton;
import entity.Wallet;
import org.hibernate.SessionFactory;
import repository.WalletRepo;
import service.baseService.CRUDService;

import java.util.List;

public class WalletServ extends CRUDService<Wallet> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final WalletRepo walletRepo = new WalletRepo();

    public Wallet findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return walletRepo.findById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public List<Wallet> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return walletRepo.findAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

}
