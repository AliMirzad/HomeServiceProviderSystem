package repository;

import Connection.SessionFactorySingleton;
import entity.CustomerUser;
import org.hibernate.SessionFactory;
import repository.baseRepository.CRUDRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

public class CustomerUserRepository extends CRUDRepository<CustomerUser> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public CustomerUser findById(Integer id) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from CustomerUser as a where a.id = :id", CustomerUser.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<CustomerUser> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("from CustomerUser as a", CustomerUser.class);
        return query.list();
    }

    public List<CustomerUser> findByParameters(String firstName, String lastName,
                                               String nationalCode, String email,
                                               LocalDateTime registerTimeDate) {
        var session = sessionFactory.getCurrentSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var contactQuery = criteriaBuilder.createQuery(CustomerUser.class);
        var root = contactQuery.from(CustomerUser.class);
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
            predicates.add(criteriaBuilder.equal(root.get("registertime"), registerTimeDate));
        }
        contactQuery
                .where(predicates.toArray(new javax.persistence.criteria.Predicate[0]))
                .orderBy(criteriaBuilder.asc(root.get("firstName")));
        return session.createQuery(contactQuery).list();
    }
}
