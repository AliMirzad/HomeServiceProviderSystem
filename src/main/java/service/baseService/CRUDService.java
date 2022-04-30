package service.baseService;

import Connection.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

public class CRUDService<T> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    private final CRUDRepository crudRepository = new CRUDRepository();

    public T save(T t) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                crudRepository.save(t);
                transaction.commit();
                return t;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public void delete(T t) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                crudRepository.delete(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }

    public void update(T t) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                crudRepository.update(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }
}
