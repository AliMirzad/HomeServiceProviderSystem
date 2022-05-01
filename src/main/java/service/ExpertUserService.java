package service;

import Connection.SessionFactorySingleton;
import entity.ExpertUser;
import entity.Service;
import entity.enums.ExpertUserStatus;
import exception.LogicException;
import org.hibernate.SessionFactory;
import repository.ExpertUserRepository;
import service.baseService.CRUDService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpertUserService extends CRUDService<ExpertUser> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final ExpertUserRepository expertUserRepository = new ExpertUserRepository();
    private final ServiceService serviceService = new ServiceService();

    @Override
    public ExpertUser save(ExpertUser expertUser) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                List<ExpertUser> expertUsers = expertUserRepository.findAll();
                for (ExpertUser c :
                        expertUsers) {
                    if (expertUser.getEmail().equals(c.getEmail())) {
                        throw new LogicException("this email address used before for a account");
                    }
                }
                expertUser.setServices(null);
                expertUser.setStatus(ExpertUserStatus.waiting);
                expertUserRepository.save(expertUser);
                transaction.commit();
                return expertUser;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                transaction.rollback();
                return null;
            }
        }
    }

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

    public ExpertUser updateService(ExpertUser expertUser, Service service) {
        try {
            Service service1 = serviceService.findById(service.getId());
            ExpertUser expertUser1 = findById(1);
            if (expertUser.getStatus().equals(ExpertUserStatus.waiting))
                throw new LogicException("first your account must be activate");
            if (service1.getParentService() == null) {
                throw new LogicException("you can only add subService to your services");
            }
            Set<Service> services = new HashSet<>();
            services.add(service1);
            expertUser1.setServices(services);
            try (var session = sessionFactory.getCurrentSession()) {
                var transaction = session.getTransaction();
                try {
                    transaction.begin();
                    expertUserRepository.update(expertUser1);
                    transaction.commit();
                    return expertUser1;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    transaction.rollback();
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ExpertUser findById(Integer id) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return expertUserRepository.findById(id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public List<ExpertUser> findAll() {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return expertUserRepository.findAll();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public List<ExpertUser> findByParameters(String firstName, String lastName,
                                             String nationalCode, String email,
                                             LocalDateTime registerTimeDate) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                return expertUserRepository.findByParameters(firstName,
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
