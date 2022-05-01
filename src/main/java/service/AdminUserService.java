package service;

import Connection.SessionFactorySingleton;
import entity.AdminUser;
import org.hibernate.SessionFactory;
import repository.AdminUserRepository;
import service.baseService.CRUDService;

import java.time.LocalDateTime;
import java.util.List;

public class AdminUserService extends CRUDService<AdminUser> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final AdminUserRepository adminUserRepository = new AdminUserRepository();

    public AdminUser findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return adminUserRepository.findById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public List<AdminUser> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return adminUserRepository.findAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public List<AdminUser> findByParameters(String firstName, String lastName,
                                           String nationalCode, String email,
                                           LocalDateTime registerTimeDate) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return adminUserRepository.findByParameters(firstName,
                        lastName,
                        nationalCode,
                        email,
                        registerTimeDate);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
