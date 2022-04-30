package service;

import Connection.SessionFactorySingleton;
import entity.ExpertUser;
import org.hibernate.SessionFactory;
import repository.ExpertUserRepository;
import service.baseService.CRUDService;

public class ExpertUserService extends CRUDService<ExpertUser> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final ExpertUserRepository expertUserRepository = new ExpertUserRepository();

    public ExpertUser updatePassword(ExpertUser expertUser) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                ExpertUser expertUser1 = expertUserRepository.findById(expertUser.getId());
                expertUser1.setPassword(expertUser.getPassword());
                expertUserRepository.update(expertUser1);
                transaction.commit();
                return expertUser1;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                transaction.rollback();
                return null;
            }
        }
    }
}
