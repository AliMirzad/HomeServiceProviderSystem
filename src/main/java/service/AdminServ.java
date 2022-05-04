package service;

import Connection.SessionFactorySingleton;
import entity.Admin;
import entity.Expert;
import exception.LogicException;
import org.hibernate.SessionFactory;
import repository.AdminRepo;
import service.baseService.CRUDService;

import java.time.LocalDateTime;
import java.util.List;

public class AdminServ extends CRUDService<Admin> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final AdminRepo adminRepo = new AdminRepo();

    @Override
    public Admin save(Admin admin) {
        try {
            if (admin.getEmail() == null || admin.getEmail().isEmpty()) {
                throw new LogicException("admin email is needed");
            }
            for (Admin a :
                    findAll()) {
                if (admin.getEmail().equals(a.getEmail())) {
                    throw new LogicException("this email used before");
                }
            }
            admin.setRegisterTime(LocalDateTime.now());
            return super.save(admin);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public void delete(Admin admin) {
        try {
            if (findById(admin.getId()) == null) {
                throw new LogicException("we don't have this admin");
            }
            super.delete(admin);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    public void updateInformation(Integer id, String nationalCode, String firstName, String password) {
        Admin admin = findById(id);
        admin.setNationalCode(nationalCode);
        admin.setFirstName(firstName);
        admin.setPassword(password);
        super.update(admin);
    }

    public void updatePassword(Integer id, String password) {
        Admin admin1 = findById(id);
        admin1.setPassword(password);
        super.update(admin1);
    }

    public List<Admin> findByParameters(String firstName, String lastName,
                                         String nationalCode, String email,
                                         LocalDateTime registerTimeDate) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return adminRepo.findByParameters(firstName,
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

    public Admin findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return adminRepo.findById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public List<Admin> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return adminRepo.findAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
