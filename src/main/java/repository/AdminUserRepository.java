package repository;

import Connection.SessionFactorySingleton;
import entity.AdminUser;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdminUserRepository extends CRUDRepository<AdminUser> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public AdminUser findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from AdminUser as a where a.id = :id", AdminUser.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<AdminUser> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from AdminUser as a", AdminUser.class);
        return query.list();
    }

    public List<AdminUser> findByParameters(String firstName, String lastName,
                                             String nationalCode, String email,
                                             LocalDateTime registerTimeDate) {
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var contactQuery = criteriaBuilder.createQuery(AdminUser.class);
        var root = contactQuery.from(AdminUser.class);
        contactQuery.select(root);
        List<Predicate> predicates = new ArrayList<>();

        if (firstName != null && !firstName.isEmpty()) {
            predicates.add((Predicate) criteriaBuilder.equal(root.get("firstName"), firstName));
        }

        if (lastName != null && !lastName.isEmpty()) {
            predicates.add((Predicate) criteriaBuilder.equal(root.get("lastName"), lastName));
        }

        if (nationalCode != null && !nationalCode.isEmpty()) {
            predicates.add((Predicate) criteriaBuilder.equal(root.get("nationalCode"), nationalCode));
        }

        if (email != null && !email.isEmpty()) {
            predicates.add((Predicate) criteriaBuilder.equal(root.get("email"), email));
        }
        if (registerTimeDate != null) {
            predicates.add(criteriaBuilder.equal(root.get("registerTime"), registerTimeDate));
        }
        contactQuery
                .where(predicates.toArray(new javax.persistence.criteria.Predicate[0]))
                .orderBy(criteriaBuilder.asc(root.get("firstName")));
        return session.createQuery(contactQuery).list();
    }
}
